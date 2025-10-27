# Internity User Guide

## Introduction

Internity is a CLI based app for managing internship applications. Internity can help you manage and keep track 
of your internship applications more efficiently, perfect for Computer Science students who need to 
manage hundreds of applications. This guide explains how to install and use Internity to the fullest.

# Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding an application : `add`](#adding-an-application-add)
  - [Deleting an application : `delete`](#deleting-an-application-delete)
  - [Updating an application : `update`](#updating-an-application-update)
  - [Listing all applications: `list`](#listing-all-applications-list)
  - [Finding by keyword : `[COMING SOON]`](#finding-by-keyword)
  - [Setting/Changing username : `username`](#settingchanging-username-username)
  - [Displaying dashboard : `dashboard`](#displaying-dashboard-dashboard)
- [FAQ](#faq)
- [Command Summary](#command-summary)

---

## Quick Start

1. **Install Java 17.** Confirm you have Java 17 installed by running `java -version` in your terminal.  
   If you do not have Java 17 installed, download it from [here](https://www.oracle.com/java/technologies/downloads/#java17).
2. **Download Internity.** Download the latest version of `Internity.jar` from [Github](https://github.com/AY2526S1-CS2113-W14-4/tp/releases), onto anywhere on your computer.
3. **Run the program.** Open your terminal, navigate to the directory where you saved `Internity.jar`, and run the command:  
   `java -jar Internity.jar`
4. **Start using Internity!** You can now start adding, deleting, updating and viewing your internship applications.  

For a list of available commands, type `help` in the terminal.

---

## Features

<div style="background-color: #002b5c; color: #cce6ff; padding: 15px; border-radius: 8px; border-left: 5px solid #3399ff;">
<h4>Notes about the command format</h4>
<ul>
<li>Words in <strong>UPPER_CASE</strong> are placeholders that must be supplied by you.<br>
Example: <code>delete INDEX</code> → <code>delete 1</code></li>
<li>If using a PDF version, be careful when copying commands that span multiple lines as spaces surrounding line-breaks may be omitted.</li>
</ul>
</div>


### Adding an application: `add`
Add a new internship application with company, role, deadline and pay details

Format
```
add company/COMPANY_NAME role/ROLE_NAME deadline/DEADLINE pay/PAY_DETAILS
```

Example:
```
add company/Google role/Software Engineer deadline/2025-09-17 pay/100000
```
This command adds an internship application at Google for the role of Software Engineer with a deadline of 17 September 2025, and a monthly salary of $100000.

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
For v1.0, only the `status` field can be updated. Updating other fields (company, role, deadline, pay) will be added in future iterations.

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

### Finding by keyword

*details coming soon....*

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

## FAQ

**Q: How do I transfer my data to another computer?** <br>
A: Your internships are saved in a file name `internships.txt` in the directory `./data` within your project folder. To transfer your data, simply copy this file to the same location on your new computer.

**Q: My add command seems to be in the correct format. Why is it throwing an error?** <br>
A: First, ensure that the format exactly follows the specified format in this guide. Common mistakes include missing prefixes (e.g., `company/`, `role/`), incorrect date formats for deadlines, or missing fields. Double-check your command for any typos or omissions. Second, ensure that your parameters meet the character limits of each field.



---

## Command Summary

| **Action**              | **Command** | **Format**                                                                                                                                                  | **Example**                                                                     |
|-------------------------|-------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------|
| **Add Application**     | `add`       | `add company/COMPANY_NAME role/ROLE_NAME deadline/DEADLINE pay/PAY_DETAILS`                                                                                 | `add company/Google role/Software Engineer Intern deadline/2025-10-20 pay/1000` |
| **Delete Application**  | `delete`    | `delete INDEX`                                                                                                                                              | `delete 2`                                                                      |
| **Update Application**  | `update`    | `update INDEX FIELD/VALUE`                                                                                                                                  | `update 1 status/Interviewing`                                                  |
| **List Applications**   | `list`      | `list` → list all applications in the order they were added <br> `list sort/ORDER` → sort applications by deadline ascending (`asc`) or descending (`desc`) | `list` <br> `list sort/asc` <br> `list sort/desc`                               |
| **Find Application**    | `find`      |                                                                                                                                                             |                                                                                 |
| **Set/Change username** | `username`  | `username USERNAME`                                                                                                                                         | `username Yoshikage Kira`                                                       |
| **Display Dashboard**   | `dashboard` | `dashboard`                                                                                                                                                 | `dashboard`                                                                     |

