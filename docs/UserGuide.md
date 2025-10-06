# User Guide

## Introduction

Internity is a CLI based app for managing internship applications. Internity can help you manage and keep track 
of your internship applications more efficiently, perfect for Computer Science students who need to 
manage hundreds of applications.

# Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  - 

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 17 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

{Give detailed description of each feature}


### Adding an internship entry: `add`
Add a new internship with company, role, deadline and pay details

Format
```
add company/COMPANY_NAME role/ROLE_NAME deadline/DEADLINE pay/PAY_DETAILS
```

Example:
```
add company/Google role/Software Engineer deadline/2025-09-17 pay/SGD 100,000
```
This command adds an internship at Google for the role of Software Engineer with a deadline of 17 September 2025, and a monthly salary of 100,000 SGD.


### Removing an internship entry: `remove`
Remove an existing internship entry from the internship list

Format:
```
remove INDEX
```

Example:
```
remove 2
```

This command removes the internship entry at index 2 from the list.


### Updating an internship entry: update
Makes an update to the internship application
For now, the update feature only has the most crucial function of updating internship priority. Updating the internship contents will be added in future iterations.

To update the status of an internship application, use the update command followed by the internship index and the new status.

Format:
```
update INDEX status/NEW_STATUS
```

Example:
```
update 1 status/Accepted
```

This command updates the status of the internship at index 1 to "Accepted".


### Viewing internship list: list
To view the list of all internship entries, use the list command

Format:
```
list
```

Example:
```
list
```

This command displays all internships currently logged into the system.


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
