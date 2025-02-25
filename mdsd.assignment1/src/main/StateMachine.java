package main;

import main.metamodel.Machine;
import main.metamodel.State;
import main.metamodel.Transition;

public class StateMachine {
    private Machine machine;
    private State currentState;
    private Transition currentTransition;
    private String currentEvent;

    public StateMachine() {
        this.machine = new Machine();
    }

    // Fluent API for state machine configuration
    public StateMachine integer(String name) {
        machine.addInteger(name);
        return this;
    }

    public StateMachine state(String name) {
        // First check if state already exists
        State existingState = machine.getState(name);
        if (existingState != null) {
            currentState = existingState;
        } else {
            currentState = new State(name);
            machine.addState(currentState);
        }
        return this;
    }

    public StateMachine initial() {
        machine.setInitialState(currentState);
        return this;
    }

    public StateMachine when(String event) {
        currentEvent = event;
        currentTransition = new Transition();
        currentTransition.setEvent(event);
        return this;
    }

    public StateMachine to(String targetState) {
        // First ensure the target state exists
        State target = machine.getState(targetState);
        if (target == null) {
            target = new State(targetState);
            machine.addState(target);
        }
        currentTransition.setTarget(target);
        currentState.addTransition(currentTransition);
        return this;
    }

    // Enhanced fluent API for operations
    public StateMachine set(String variable, int value) {
        currentTransition.setOperationVariableName(variable);
        currentTransition.setHasSetOperation(true);
        currentTransition.setSetValue(value);
        return this;
    }

    public StateMachine increment(String variable) {
        currentTransition.setOperationVariableName(variable);
        currentTransition.setHasIncrementOperation(true);
        return this;
    }

    public StateMachine decrement(String variable) {
        currentTransition.setOperationVariableName(variable);
        currentTransition.setHasDecrementOperation(true);
        return this;
    }

    // Enhanced fluent API for conditions
    public StateMachine ifEquals(String variable, int value) {
        currentTransition.setConditional(true);
        currentTransition.setConditionVariableName(variable);
        currentTransition.setConditionComparedValue(value);
        currentTransition.setConditionEqual(true);
        return this;
    }

    public StateMachine ifGreaterThan(String variable, int value) {
        currentTransition.setConditional(true);
        currentTransition.setConditionVariableName(variable);
        currentTransition.setConditionComparedValue(value);
        currentTransition.setConditionGreaterThan(true);
        return this;
    }

    public StateMachine ifLessThan(String variable, int value) {
        currentTransition.setConditional(true);
        currentTransition.setConditionVariableName(variable);
        currentTransition.setConditionComparedValue(value);
        currentTransition.setConditionLessThan(true);
        return this;
    }

    public Machine build() {
        return machine;
    }
}
