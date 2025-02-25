package main.metamodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Machine {
	
	private List<State> states;
	private State initialState;
	private Map<String, Integer> integers;

	public Machine() {
		this.states = new ArrayList<>();
		this.integers = new HashMap<>();
	}

	// Fluent API for machine configuration
	public Machine addState(State state) {
		states.add(state);
		return this;
	}

	public Machine setInitialState(State state) {
		this.initialState = state;
		return this;
	}

	public Machine addInteger(String name) {
		integers.put(name, 0);
		return this;
	}

	// Getters with enhanced null checking
	public List<State> getStates() {
		return new ArrayList<>(states);
	}

	public State getInitialState() {
		return initialState;
	}

	public State getState(String name) {
		return states.stream()
				.filter(state -> state.getName().equals(name))
				.findFirst()
				.orElse(null);
	}

	public Map<String, Integer> getIntegers() {
		return new HashMap<>(integers);
	}

	// Query methods
	public int numberOfIntegers() {
		return integers.size();
	}

	public boolean hasInteger(String name) {
		return integers.containsKey(name);
	}

	// Fluent variable operations
	public Machine setInteger(String name, int value) {
		if (hasInteger(name)) {
			integers.put(name, value);
		}
		return this;
	}

	public int getIntegerValue(String name) {
		return integers.getOrDefault(name, 0);
	}
}

