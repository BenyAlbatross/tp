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
We would like to thank our TA Nigel Yeo, Prof Akshay and the CS2113 Team.

## Design

### Architecture
The Internity application adopts layered architecture where responsibilities are divided among UI, Logic, Model and Storage related
components. It also follows the Command Pattern for handling user actions. This design separates concerns
clearly, allowing for modular, maintainable and extensible code.

The **Architecture Diagram** below explains the high-level design of the Internity application.\
![Architecture Diagram](diagrams/ArchitectureOverview.png)\

The diagram below shows a simplified **Class Diagram** of all of Internity's classes and their relationships.
![Internity Class Diagram](diagrams/InternityCD.png)

#### Layers
1. Main
   - Classes: `InternityManager`, `Internity` (main)
   - Responsibilities:
     - Launches and shuts down the application.
     - Receives input from the user and delegates parsing to the `Logic` layer.
     - Commands executed by `Logic` layer may modify the `Model` or trigger UI updates.
     - It also handles reading from and writing to the `Storage` layer.
     - Simplifies interactions between all layers and maintains a clear separation of concerns.
2. UI (User Interface)
    - Classes: `Ui`, `DashboardUi`
    - Responsibilities:
        - Handles all user-facing output (printing, dashboards, etc.).
        - Does not perform any logic or state changes.
        - Displays information passed from the `Logic` or `Model` layers in a user-friendly format.
        - Invoked by Commands to show feedback or results.
3. Logic
   - Classes: `CommandParser`, `CommandFactory`, `ArgumentParser`, `Command` subclasses
   - Responsibilities:
       - Acts as the intermediary between user input and `Model` operations.
       - Parses and validates user commands.
       - Constructs the appropriate `Command` object through the `CommandFactory`.
       - Executes commands, which modify the `Model` or trigger the `UI` to display information.
4. Model
   - Classes: `InternshipList`, `Internship`, `Date`, `Status`
   - Responsibilities:
     - Stores internship data
     - Provides operations like adding, deleting, updating, finding or listing internships.
     - Completely independent of UI and input logic.
5. Storage
   - Classes: `Storage`
   - Responsibilities:
     - Reads and writes internship data to persistent storage (e.g., file system). 
     - Keeps data consistent between sessions. 
     - Used by InternityManager to save or load application state.

#### User Interaction
The Sequence Diagram below shows how the components interact with each other when the user issues the command
`delete 1`.
![User Interaction: Sequence Diagram](diagrams/UserInteractionSD.png)

### UI Component
#### Overview
The UI component is responsible for all interactions between the user and the application.
It displays messages, prompts, and formatted lists in the command-line interface (CLI), and ensures that feedback 
from executed commands is presented clearly.

The API of this component is specified in the [`Ui.java`](https://github.com/AY2526S1-CS2113-W14-4/tp/blob/master/src/main/java/internity/ui/Ui.java) class
and the [`DashboardUi.java`](https://github.com/AY2526S1-CS2113-W14-4/tp/blob/master/src/main/java/internity/ui/DashboardUi.java).

![UI Component Diagram](diagrams/UiComponentOverview.png)

#### How it Works
1. The `InternityManager` handles all user input through a `Scanner`.
When a command is executed, it delegates output responsibilities to the `Ui` class.
2. The `Ui` component formats and prints the messages or internship data to the console.
For example:
   - `Ui.printAddInternship()` displays confirmation for a newly added internship.
   - `Ui.printFindInternship()` displays results in a neat, column-aligned format.
3. For specialized displays such as the dashboard, the `DashboardUi` class is used.

#### Design Considerations
- Static methods
  - The `Ui` class methods are static to ensure simplicity and easy access across commands without requiring
  instantiation.
- Loose coupling
  - The UI does not directly modify model or logic components. 
  - It only displays results based on data passed to it.
 
### Logic Component

#### Overview
The Logic component is responsible for:
- Parsing user input commands.
- Creating the appropriate `Command` object.
- Executing that command to modify the Model or interact with the UI.

#### Chosen Approach
This component follows the **Command Pattern**, which decouples the user input parsing from the execution of commands.
Each command is represented as a subclass of the abstract `Command` class, encapsulating its execution logic. This
allows new commands to be easily added without modifying the core parsing or execution workflow.

####  Class Diagram
![Logic Component: Class Diagram](diagrams/LogicComponentCD.png)

The class diagram above shows the main classes involved in parsing, creating, and executing commands.
- CommandParser is responsible for validating and splitting the input.
- CommandFactory creates the appropriate Command object.
- ArgumentParser is a static utility class used to parse arguments for commands that require them.
- All Command subclasses implement the execute() method, following the Command Pattern.

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
5. Finally, the result of the execution is printed ot the console via the `Ui`.

#### Sequence Diagram
The following sequence diagram illustrates how the Logic Component processes an input command:

![Logic Component: Sequence Diagram](diagrams/LogicComponentSD.png)

#### Explaining Commands with and without arguments
1. Commands that **require** arguments 
   - `add`, `update`, `delete`, `find`, `list`, `username`
   - These commands need extra information to execute correctly:
     - `add` needs company, role, deadline and pay.
     - `update` needs an index and fields to update.
     - `find` needs a search keyword.
2. Commands that **do not require** arguments
   - `exit`, `dashboard`
   - These commands operate independently of data supplied by the user.
   - `CommandFactory` directly constructs the corresponding `Command` object (e.g. `ExitCommand` or `DashboardCommand`) without invoking `ArgumentParser`.

This distinction is represented in the above sequence diagram's `alt` block, showing the two conditional flows:
- Top path (commands requiring arguments) -> parsed via `ArgumentParser`.
- Bottom path (commands not requiring arguments) -> instantiated directly.

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

### Find feature

The find mechanism is implemented by the `FindCommand` class, which allows users to search for internships based on a
keyword that matches either the company name or the role of the internship.

The FindCommand class extends Command and consists of the following key components and operations:
* FindCommand.execute() — Executes the command by searching for internships that match the provided keyword.
The search looks for matching company or role names.

### Class and Method Breakdown

#### 1. `FindCommand` Constructor

- **Purpose**: Initialises a `FindCommand` object with a given keyword.
- **Signature**:
    ```java
    public FindCommand(String keyword)
    ```
- **Parameters**:
    - `keyword`: The keyword to search for in the company or role fields of the internships.

#### 2. `FindCommand.execute()`

- **Purpose**: Executes the find command, which triggers a search for internships matching the keyword.
- **Signature**:
    ```java
    @Override
    public void execute() throws InternityException
    ```
- **Steps**:
    1. Logs the command execution start.
    2. Calls `InternshipList.findInternship(keyword)` to filter and search through the list of internships.
    3. Logs the success of the operation.

#### 3. `InternshipList.findInternship()`

- **Purpose**: Filters and returns internships whose company or role contains the given keyword, case-insensitively.
- **Signature**:
    ```java
    public static void findInternship(String keyword)
    ```
- **Parameters**:
    - `keyword`: The string to search for in the company or role names of internships.
- **Implementation**:
    - Internships are filtered using a stream to check if the keyword exists in either the company name or the role.
    - The filter is case-insensitive (`keyword.toLowerCase()`).
    - If no internships match the keyword, a message is printed: "No internships with this Company or Role found."
    - If matches are found, the results are passed to the `Ui.printFindInternship()` method for display.

### Example Usage Scenario

1. **Step 1**: The user launches the application and executes a find command by typing `find Google`.

2. **Step 2**: The `CommandParser` parses the input, extracting the command word `find` and the argument `Google`.

3. **Step 3**: The `CommandFactory` creates a `FindCommand` with the keyword `Google`.

4. **Step 4**: The `FindCommand.execute()` method is called, triggering the `InternshipList.findInternship()` method.

5. **Step 5**: `findInternship()` filters the internships, looking for the keyword in both the company and role fields.
If any matches are found, they are displayed through the UI.

6. **Step 6**: If no matches are found, the user sees the message printed in the Ui: "No internships with this Company or Role found."

### Internals and Key Functions

- **Keyword Matching**: The keyword is matched against both the company and role fields of each internship in a
case-insensitive manner using the `toLowerCase()` method.
  
- **Logging**: The command execution is logged at the start and end, using the `Logger` class to track the command’s
lifecycle.

- **UI Handling**: When matching internships are found, they are passed to the `Ui.printFindInternship()` method
for display. The UI is responsible for presenting the search results to the user.

### Example Interaction

- **User Input**:
    ```sh
    find Google
    ```

- **Expected Output**:
    ```
    These are the matching internships in your list:
      1.  Google - Software Engineer | Deadline: 10-12-2025 | Pay: 100000 | Status: Pending
      2.  Alphabet - Googler | Deadline: 10-12-2025 | Pay: 150000 | Status: Pending
    ```

  If no internships match:
    ```
    No internships with this Company or Role found.
    ```

### Edge Cases and Considerations

- **Case Insensitivity**: The search is case-insensitive, so `find google`, `find GOOGLE`, or `find GoOgLe`
would all match the same internships.

- **Empty or Invalid Keyword**: If an empty string is provided as the keyword, the Ui will print
"Invalid find command. Usage: find KEYWORD"

- **Performance**: The search mechanism uses a stream-based filter on the internship list, which is efficient
for moderate-sized datasets but may require optimisation for larger datasets.

### Persistence

Since this is a search command and does not modify the underlying data, no changes are persisted to disk
during the `find` operation. However, any modifications (such as deletion or addition of internships) will require a subsequent call to `Storage.save()` to persist the changes.


## Appendix: Requirements

## Product scope

### Target user profile
- Computer Science students who are actively applying for multiple internships, often over 200 applications per recruitment cycle.
- Detail-oriented users who want a structured way to track internship applications.
- Comfortable using command-line interfaces (CLI) and prefer a lightweight and fast tool over complex graphical applications.

### Value proposition
Internity provides a centralized and efficient way to manage internship applications through a command-line interface.
It allows users to:
- Store and organize internships along with key attributes such as company name, role, deadline, pay and
application status
- Find internships by keyword
- Sort internships by date in ascending or descending order.
- View an automatically generated dashboard showing key statistics such as total applications, nearest deadlines and
status breakdowns.

## User Stories


| Version | As a ... | I want to ...                                                          | So that I can ...                                                                |
|---------|----------|------------------------------------------------------------------------|----------------------------------------------------------------------------------|
| v1.0    | new user | add a new internship with company, role, and deadline details          | keep all opportunities organized in one place                                    |
| v1.0    | user     | set the status of my application (applied, interview, offer, rejected) | easily see my progress with each internship                                      |
| v1.0    | user     | see a list of all my internships                                       | easily view the opportunities I’m tracking                                       |
| v1.0    | user     | remove an internship entry                                             | keep the list relevant and up to date                                            |
| v2.0    | user     | update the company, role, deadline and pay for an internship           | keep my application information accurate and up to date                          |
| v2.0    | user     | see my internships sorted by deadlines                                 | prioritize applications that are due soon                                        |
| v2.0    | user     | save and load internships automatically                                | avoid losing my progress and notes between sessions                              |
| v2.0    | user     | find internships based on the company or role                          | easily view my applications to specific companies or positions I'm interested in |
| v2.0    | user     | set or change my username                                              | personalize my internship tracker experience                                     |
| v2.0    | user     | view a condensed dashboard                                             | to see the current status of my applications                                     |


## Use Cases


## Non-Functional Requirements

1. Should work on any mainstream OS (Windows, macOS, Linux) as long as it has Java `17` or above installed.
2. Should be able to hold up to 1000 internship applications without a noticeable sluggishness in performance
for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) 
should be able to accomplish most of the tasks faster using commands than using the mouse.
4. User data should not be shared or transmitted externally. All storage is local to the user's system.
5. The architecture should allow easy addition of new commands without breaking existing functionality.


## Glossary
**Internship**\
A temporary work experience offered by a company or organization that allows a student or early-career individual
to gain practical skills, industry knowledge and professional exposure in a specific field. Internships may be paid
or unpaid, part-time or full-time, and can occur during or after academic study.


## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

### Adding an internship

### Updating an internship

### Deleting an internship

### Listing and sorting all internships

### Finding an internship by keyword

### Changing username
Prerequisites: The application has been launched and the user is at the command prompt.

Test case 1: Changing username
- Action: `username Dexter`
- Expected:
  - Username is updated to "Dexter".
  - Confirmation message reflects the new username: `Username set to Dexter`.

Test case 2: Invalid username input
- Action: `username` (without specifying a name)
- Expected:
  - Error message is displayed indicating an invalid username command.
  - Username remains unchanged.

### Displaying the Internity Dashboard
Prerequisites: At least one internship has been added to the system.

Test case 1: Display dashboard with multiple internships
- Action: `dashboard`
- Expected:
  - Dashboard shows the current username.
  - Total internships are displayed.
  - Nearest upcoming internship deadline is displayed.
  - Status overview counts for each status category are shown.

Test case 2: Display dashboard with no internships
- Action: `dashboard`
- Expected:
  - Dashboard stills shows the current username.
  - Dashboard indicates no internships are found.

Test case 3: Display dashboard after changing username
- Action: `username Doakes`, then `dashboard`
- Expected:
  - Dashboard displays the new username `Doakes`.

Test case 4: Dashboard reflects recent changes
- Action: Add, update, or delete internships, then `dashboard`
- Expected:
  - Dashboard reflects the updated internship count, deadlines and statuses.

### Saving Data
