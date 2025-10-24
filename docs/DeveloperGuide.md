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

**API**: [`Storage.java`](https://github.com/AY2526S1-CS2113-W14-4/tp/blob/master/src/main/java/internity/core/Storage.java)

The `Storage` component:
* can save internship data in a pipe-delimited text format, and read it back into corresponding objects.
* depends on classes in the `internity.core` package (specifically `Internship` and `InternshipList`) to load and save internship data.
* automatically creates the data directory and file if they don't exist.
* handles corrupted data gracefully by skipping invalid entries and logging warnings instead of crashing the application.

### Common Classes

## Implementation

### Delete feature

The delete mechanism is facilitated by `DeleteCommand`. It extends `Command` with an index field, internally stored as a 0-based integer. Additionally, it implements the following operation:

* `DeleteCommand.execute()` — Removes the internship at the specified index from the `InternshipList`.

Given below is an example usage scenario and how the delete mechanism behaves at each step.

Step 1. The user launches the application and the `InternshipList` contains 3 internships. The user executes `delete 2` to delete the 2nd internship in the list.

Step 2. The `CommandParser` parses the input and extracts the command word "delete" and arguments "2".

Step 3. The `CommandFactory` uses `ArgumentParser` to convert the user's 1-based index (2) to a 0-based index (1) and validates that it is within bounds.

Step 4. A `DeleteCommand` object is created with index 1 and its `execute()` method is called.

Step 5. `DeleteCommand.execute()` retrieves the internship at index 1, removes it from `InternshipList`, and displays a success message showing the deleted internship details and the updated list size.

Step 6. After execution, `Storage.save()` is automatically called to persist the changes to disk.

#### Design considerations

**Aspect: Index base convention**

* **Alternative 1 (current choice):** Users provide 1-based indices, converted to 0-based internally in `ArgumentParser`.
  * Pros: More intuitive for users who see numbered lists starting from 1.
  * Cons: Requires conversion logic and careful management of the conversion point.

* **Alternative 2:** Use 0-based indices throughout, including in user-facing output.
  * Pros: Simpler implementation with no conversion needed.
  * Cons: Less intuitive for users unfamiliar with programming conventions.

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
