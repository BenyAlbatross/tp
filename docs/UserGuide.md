# Internity User Guide

## Introduction

Internity is a CLI based app for managing internship applications. Internity can help you manage and keep track
of your internship applications more efficiently, perfect for Computer Science students who need to
manage hundreds of applications.

# Table of Contents

- [Quick Start](#quick-start)
- [Features](#features)
    - [Adding an application](#adding-an-application-add)
    - [Deleting an application](#deleting-an-application-delete)
    - [Updating an application](#updating-an-application-update)
    - [Viewing all applications](#listing-all-applications-list)
- [FAQ](#faq)
- [Command Summary](#command-summary)

---

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `Internity` from [here](http://link.to/Internity).
3. Copy the file to the folder you want to use as the home folder for Internity.
4. Open a terminal, cd into the folder with the jar file.
5. Run: `java -jar Paddington.jar`
6. Type a command and press Enter to execute it.  
   You may refer to the Features below for details of each command.

---

## Features

<div style="background-color: #002b5c; color: #cce6ff; padding: 15px; border-radius: 8px; border-left: 5px solid #3399ff;">
<h4>Notes about the command format</h4>
<ul>
<li>Words in <strong>UPPER_CASE</strong> are placeholders that must be supplied by you.<br>
Example: <code>delete INDEX</code> → Please input <code>delete 1</code></li>
<li>If using a PDF version, be careful when copying commands that span multiple lines as spaces surrounding line-breaks may be omitted.</li>
</ul>
</div>

### Adding an application: `add`

Add a new internship application with company, role, deadline and pay amount

Format

```
add company/COMPANY_NAME role/ROLE_NAME deadline/DEADLINE pay/PAY_AMOUNT
```

Example:

```
add company/Google role/Software Engineer Intern deadline/17-09-2025 pay/100000
```

This command adds an internship application at Google for the role of Software Engineer Intern with a deadline of 17
September 2025, and an annual salary of $100000.

<div style="background-color: #331c16; color: #c3b091; padding: 15px; border-radius: 8px; border-left: 5px solid #966919;">
<h4>Notes</h4>
<ul>
<li>The parameters should be entered in the specified order i.e. company, role, deadline, followed by pay.</li>
<li>No duplicate parameter type, so only exactly one of each parameter type.</li>
<li><code>DEADLINE</code> must be in <code>DD-MM-YYYY</code> format.</li>
<li><code>PAY_AMOUNT</code> must be a non-negative integer.</li>
</ul>
</div>

---

### Deleting an application: `delete`

Delete an existing internship application from the internship list

Format:

```
delete INDEX
```

Example:

```
delete 2
```

This command deletes the internship application at index 2 from the list.

---

### Updating an application: `update`

Use this command to update the **status** of an internship application.  
For v1.0, only the `status` field can be updated. Updating other fields (company, role, deadline, pay) will be added in
future iterations.

**Valid `status` values:**

- `Pending` -> You’re considering the internship but haven’t applied yet.
- `Interested` → You’ve found the internship and might apply.
- `Applied` → You’ve submitted your application.
- `Interviewing` → You’re currently in the interview process.
- `Offer` → You’ve received an internship offer.
- `Accepted` → You’ve accepted the offer.
- `Rejected` → The application was unsuccessful or withdrawn.

Format:

```
update INDEX status/NEW_STATUS
```

Example:

```
update 1 status/Accepted
```

This command updates the status of the internship application at index 1 to "Accepted".

---

### Listing all applications: `list`

Use this command to view all internship applications.  
By default, the list shows applications in the **order they were added**.
You can optionally sort them by deadline in ascending or descending order.

Format:

```
list
list sort/ORDER
```

Example:

```
list
list sort/asc
list sort/desc
```

- `list` → shows all applications in the order they were added
- `list sort/asc` → sorts applications by deadline ascending
- `list sort/desc` → sorts applications by deadline descending

---

### Finding by keyword: `find`

Search for internship applications by keyword. The search is case-insensitive.

Format:

```
find KEYWORD
```

Example:

```
find Software Engineer
```

This command lists all internship applications that contain the keyword "Software Engineer" in either its company or
role details.

---

### Setting/Changing username: `username`

Use this command to set or change the username for the Internity application.

Format:

```
username USERNAME
```

Example:

```
username Yoshikage Kira
```

---

### Displaying dashboard: `dashboard`

Use this command to display a dashboard showing the current user's information, total internships, nearest deadline,
and a breakdown of internships by status.

Format:

```
dashboard
```

---

### Exit Internity: `exit`

Quit the program. Your data is saved.

Format:

```
exit
```

---

## FAQ

* **Q**: How do I transfer my data to another computer?  
  **A**: Make a copy of the data folder of the home directory that runs `Internity.jar` to the new computer. Ensure that
  the folder structure remains intact.

* **Q**: I have encountered an invalid command error. What do I do?  
  **A**: Refer to the [Features](#features) section for details on using a command.

* **Q**: Does Internity work without Internet?  
  **A**: Yes, Internity does not require an active internet connection, allowing you to manage your internships
  seamlessly!

---

## Command Summary

| **Action**              | **Command** | **Format**                                                                                                                                                  | **Example**                                                                       |
|-------------------------|-------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------|
| **Add Application**     | `add`       | `add company/COMPANY_NAME role/ROLE_NAME deadline/DEADLINE pay/PAY_AMOUNT`                                                                                  | `add company/Google role/Software Engineer Intern deadline/17-09-2025 pay/100000` |
| **Delete Application**  | `delete`    | `delete INDEX`                                                                                                                                              | `delete 2`                                                                        |
| **Update Application**  | `update`    | `update INDEX FIELD/VALUE`                                                                                                                                  | `update 1 status/Interviewing`                                                    |
| **List Applications**   | `list`      | `list` → list all applications in the order they were added <br> `list sort/ORDER` → sort applications by deadline ascending (`asc`) or descending (`desc`) | `list` <br> `list sort/asc` <br> `list sort/desc`                                 |
| **Find Application**    | `find`      | `find KEYWORD`                                                                                                                                              | `find Software Engineer`                                                          |
| **Set/Change username** | `username`  | `username USERNAME`                                                                                                                                         | `username Yoshikage Kira`                                                         |
| **Display Dashboard**   | `dashboard` | `dashboard`                                                                                                                                                 | `dashboard`                                                                       |
| **Exit Internity**      | `exit`      | `exit`                                                                                                                                                      | `exit`                                                                            |
