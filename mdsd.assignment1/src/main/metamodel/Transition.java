package main.metamodel;

public class Transition {
	
	private String event;
    private State target;
    private String operationVariableName;
    private boolean hasSetOperation;
    private boolean hasIncrementOperation;
    private boolean hasDecrementOperation;
    private boolean isConditional;
    private String conditionVariableName;
    private Integer conditionComparedValue;
    private boolean isConditionEqual;
    private boolean isConditionGreaterThan;
    private boolean isConditionLessThan;
    private Integer setValue;
	
	// Fluent API for event configuration
	public Transition setEvent(String event) {
		this.event = event;
		return this;
	}

	public Transition setTarget(State target) {
		this.target = target;
		return this;
	}

	// Fluent API for operations
	public Transition setOperationVariableName(String name) {
		this.operationVariableName = name;
		return this;
	}

	public Transition setHasSetOperation(boolean value) {
		this.hasSetOperation = value;
		return this;
	}

	public Transition setHasIncrementOperation(boolean value) {
		this.hasIncrementOperation = value;
		return this;
	}

	public Transition setHasDecrementOperation(boolean value) {
		this.hasDecrementOperation = value;
		return this;
	}

	public Transition setSetValue(Integer value) {
		this.setValue = value;
		return this;
	}

	// Fluent API for conditions
	public Transition setConditional(boolean value) {
		this.isConditional = value;
		return this;
	}

	public Transition setConditionVariableName(String name) {
		this.conditionVariableName = name;
		return this;
	}

	public Transition setConditionComparedValue(Integer value) {
		this.conditionComparedValue = value;
		return this;
	}

	public Transition setConditionEqual(boolean value) {
		this.isConditionEqual = value;
		return this;
	}

	public Transition setConditionGreaterThan(boolean value) {
		this.isConditionGreaterThan = value;
		return this;
	}

	public Transition setConditionLessThan(boolean value) {
		this.isConditionLessThan = value;
		return this;
	}

	// Getters
	public String getEvent() {
		return event;
	}

	public State getTarget() {
		return target;
	}

	public String getOperationVariableName() {
		return operationVariableName;
	}

	public boolean hasSetOperation() {
		return hasSetOperation;
	}

	public boolean hasIncrementOperation() {
		return hasIncrementOperation;
	}

	public boolean hasDecrementOperation() {
		return hasDecrementOperation;
	}

	public boolean isConditional() {
		return isConditional;
	}

	public String getConditionVariableName() {
		return conditionVariableName;
	}

	public Integer getConditionComparedValue() {
		return conditionComparedValue;
	}

	public boolean isConditionEqual() {
		return isConditionEqual;
	}

	public boolean isConditionGreaterThan() {
		return isConditionGreaterThan;
	}

	public boolean isConditionLessThan() {
		return isConditionLessThan;
	}

	public Integer getSetValue() {
		return setValue;
	}

	// Helper methods
	public boolean hasOperation() {
		return hasSetOperation || hasIncrementOperation || hasDecrementOperation;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Transition[event=").append(event)
		  .append(", target=").append(target.getName());
		
		if (hasOperation()) {
			sb.append(", operation=");
			if (hasSetOperation) sb.append("set ").append(operationVariableName).append("=").append(setValue);
			if (hasIncrementOperation) sb.append("increment ").append(operationVariableName);
			if (hasDecrementOperation) sb.append("decrement ").append(operationVariableName);
		}
		
		if (isConditional) {
			sb.append(", condition=").append(conditionVariableName);
			if (isConditionEqual) sb.append("==");
			if (isConditionGreaterThan) sb.append(">");
			if (isConditionLessThan) sb.append("<");
			sb.append(conditionComparedValue);
		}
		
		sb.append("]");
		return sb.toString();
	}

}
