package main.metamodel;

import java.util.List;
import java.util.ArrayList;

public class State {
	
	private final String name;
	private final List<Transition> transitions;
	
	public State(String name) {
		this.name = name;
		this.transitions = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}

	public List<Transition> getTransitions() {
		return new ArrayList<>(transitions);
	}
	
	public State addTransition(Transition transition) {
		transitions.add(transition);
		return this;
	}

	public Transition getTransitionByEvent(String event) {
		return transitions.stream()
				.filter(t -> t.getEvent().equals(event))
				.findFirst()
				.orElse(null);
	}

	public boolean hasTransition(String event) {
		return getTransitionByEvent(event) != null;
	}

	public int numberOfTransitions() {
		return transitions.size();
	}

	@Override
	public String toString() {
		return "State[name=" + name + ", transitions=" + transitions.size() + "]";
	}
}
