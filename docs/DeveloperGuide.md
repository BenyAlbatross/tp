# Developer Guide

1. [Acknowledgements](#acknowledgements)
2. [Design](#design)
    - [Architecture](#Architecture)
    - [UI Component](#ui-component)
    - [Logic Component](#logic-component)
    - [Model Component](#model-component)
    - [Storage Component](#storage-component)
    - [Common Classes](#common-classes)
3. [Implementation](#implementation)
4. [Documentation, logging, testing, configuration, dev-ops](#documentation-logging-testing-configuration-dev-ops)
5. [Appendix: Requirements](#appendix-requirements)
   - [Product Scope](#product-scope)
   - [User Stories](#user-stories)
   - [Use Cases](#use-cases)
   - [Non-Functional Requirements](#non-functional-requirements)
   - [Glossary](#glossary)
6. [Instructions for Manual Testing](#instructions-for-manual-testing)



## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Architecture

### UI Component

### Logic Component

#### Overview
The Logic component is responsible for:
- Parsing user input commands.
- Creating the appropriate `Command` object.
- Executing that command to modify the Model or interact with the UI.

#### How it Works
1. User input (e.g. `add company/Google role/SWE deadline/10-10-2025 pay/1000`) is received by CommandParser.
2. The `CommandParser`:
   - Validates that the input is not empty or malformed.
   - Splits the input into a command keyword and arguments.
   - Passes them into the `CommandFactory`.
3. The `CommandFactory`:
   - Matches the keyword to a corresponding `Command` subclass (e.g. `AddCommand`).
   - Uses the `ArgumentParser` to interpret argument strings.
   - Returns a fully constructed `Command` object.
4. The `Command` object executes its logic (e.g. adds a new internship to `InternshipList`).
5. The result of the execution is printed ot the console via the `Ui`.

#### Sequence Diagram
Below is a simplified interaction for the `delete 1` command:
<insert uml diagram>

### Model Component

### Storage Component

### Common Classes

## Implementation

## Documentation, logging, testing, configuration, dev-ops

## Appendix: Requirements

## Product scope

### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

| Version | As a ... | I want to ...                                                          | So that I can ...                                   |
|---------|----------|------------------------------------------------------------------------|-----------------------------------------------------|
| v1.0    | new user | add a new internship with company, role, and deadline details          | keep all opportunities organized in one place       |
| v1.0    | user     | set the status of my application (applied, interview, offer, rejected) | easily see my progress with each internship         |
| v1.0    | user     | see a list of all my internships                                       | easily view the opportunities I’m tracking          |
| v1.0    | user     | remove an internship entry                                             | keep the list relevant and up to date               |
| v2.0    | user     | see my internships sorted by upcoming deadlines                        | prioritize applications that are due soon           |
| v2.0    | user     | save and load internships automatically                                | avoid losing my progress and notes between sessions |
| v2.0    | user     | see an overall countdown for upcoming interviews                       | manage my time effectively and be prepared          |

## Use Cases

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
