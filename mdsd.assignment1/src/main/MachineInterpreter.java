package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

import java.util.HashMap;
import java.util.Map;

public class MachineInterpreter {
	
	private Machine machine;
    private State currentState;
    private Map<String, Integer> variables;

    public MachineInterpreter() {
        this.variables = new HashMap<>();
    }

    // Fluent API for machine execution
    public MachineInterpreter run(Machine machine) {
        this.machine = machine;
        this.currentState = machine.getInitialState();
        if (machine.getIntegers() != null) {
            variables.clear();
            machine.getIntegers().forEach((var, value) -> variables.put(var, 0));
        }
        return this;
    }

    public MachineInterpreter processEvent(String event) {
        if (currentState == null) {
            return this;
        }

        // Get all transitions for this event
        Transition validTransition = currentState.getTransitions().stream()
            .filter(t -> t.getEvent().equals(event))
            .filter(this::isTransitionValid)
            .findFirst()
            .orElse(null);

        if (validTransition != null) {
            executeTransition(validTransition);
        }
        return this;
    }

    // Helper methods for transition execution
    private boolean isTransitionValid(Transition transition) {
        if (!transition.isConditional()) {
            return true;
        }

        String varName = transition.getConditionVariableName();
        int variableValue = variables.getOrDefault(varName, 0);
        int comparedValue = transition.getConditionComparedValue();

        if (transition.isConditionEqual()) {
            return variableValue == comparedValue;
        }
        if (transition.isConditionGreaterThan()) {
            return variableValue > comparedValue;
        }
        if (transition.isConditionLessThan()) {
            return variableValue < comparedValue;
        }
        return false;
    }

    private void executeTransition(Transition transition) {
        // Execute operations first
        String varName = transition.getOperationVariableName();
        if (varName != null) {
            if (transition.hasSetOperation()) {
                variables.put(varName, transition.getSetValue());
            } else if (transition.hasIncrementOperation()) {
                int currentValue = variables.getOrDefault(varName, 0);
                variables.put(varName, currentValue + 1);
            } else if (transition.hasDecrementOperation()) {
                int currentValue = variables.getOrDefault(varName, 0);
                variables.put(varName, currentValue - 1);
            }
        }
        
        // Then change state
        currentState = transition.getTarget();
    }

    // Getters
    public State getCurrentState() {
        return currentState;
    }

    public int getInteger(String name) {
        return variables.getOrDefault(name, 0);
    }

    // Fluent variable operations
    public MachineInterpreter setInteger(String name, int value) {
        variables.put(name, value);
        return this;
    }

    public MachineInterpreter incrementInteger(String name) {
        variables.merge(name, 1, Integer::sum);
        return this;
    }

    public MachineInterpreter decrementInteger(String name) {
        variables.merge(name, -1, Integer::sum);
        return this;
    }
}
