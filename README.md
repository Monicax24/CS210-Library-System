# Library Management System (Java)

## Description
A **console-based Library Management System** implemented in Java that simulates core library operations such as managing books, members, and borrowing activity.
Through this project, I learned **Java fundamentals**, **OOP principles**, and **data structures** in a real-world–style application.

## Features
- Add and manage books with multiple copies
- Register library members with unique auto-generated IDs
- Search for books by:
  - Title
  - Author
  - ISBN
- Issue and return books with availability validation
- Track borrowed books per member
- Prevents invalid actions (ex. borrowing unavailable books, duplicate borrowing)

## Technologies and Concepts
- **Language:** Java  
- **Core Concepts:**
  - Object-Oriented Programming (encapsulation, class interaction)
  - Java Collections (`HashMap`, `HashSet`)
  - Input handling using `Scanner`
  - Control flow and validation logic

## Architecture
The system follows a modular design:
- **Book**
  - Stores metadata (title, author, ISBN)
  - Tracks total and available copies
  - Handles borrow and return logic
- **Member**
  - Stores member details and borrowed books
  - Ensures members cannot borrow the same book twice
- **LibraryManagement**
  - Acts as the controller and entry point
  - Manages user input, program flow, and system state

## Project Structure
```
LibraryManagementSystem/
├── Book.java
├── Member.java
└── LibraryManagement.java
```

## How to Run
Compile and run the program from the terminal:
```bash
javac Book.java Member.java LibraryManagement.java
java LibraryManagement
```

## Sample Functionality
- Issue a book only if copies are available
- Automatically update available copies when books are borrowed or returned
- Display all books currently borrowed by a specific member

## Limitations & Future Enhancements
- No persistent storage (data resets on program exit)
- Book issuing is title-based
- Potential improvements:
  - File or database persistence
  - Due dates and overdue tracking
  - Advanced search (partial matches, case-insensitive)
  - Graphical user interface (GUI)
