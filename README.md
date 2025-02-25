# Assignment 1: Internal DSL

Base repository of the model-driven software development assignment 1: Internal DSL.
This repository provides three test classes:

* `MachineStructureTest`: Tests on the structure of the state machine metamodel (use them if you feel lost).
* `MachineInterpreterTest`: Tests on the behavior of the state machine.
* `CDPlayerTest`: Complex test of a CD Player defined using a state machine.

# Summary of how the exercise was solved
I implemented the internal DSL for a state machine by populating the metamodel classes (`Machine`, `State` and `Transition`) to represent the state machine structure. The `Machine` class holds states and variables, The `State` class manage transitions, and the `Transition` class handles events, conditions and operations. Then I populated the fluent builder class `StateMachine` that provides an API with method chaining (state(), when(), to(), etc.) which allows for constructing the state machine in a readable way. At last I implemented the `MachineInterpreter` which executes the state machine and handles state transitions, variable operations and conditions.
