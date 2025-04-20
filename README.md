# 📝 Notes App

A simple full-stack web application for taking and managing notes. Built with **Spring Boot** for the backend and **vanilla JavaScript + HTML/CSS** for the frontend. This project was created as a learning exercise to understand full-stack web development, REST APIs, and client-server communication.

---

## ✨ Features

- 📄 View all notes stored in the database
- ➕ Add a new note
- ✏️ Update existing notes
- ❌ Delete notes
- 👀 Select a note to view its full content
- 🧠 Clean and intuitive UX with disabled inputs that activate on selection

---

## 🔧 Tech Stack

### Backend
- **Spring Boot** (Java)
- **Spring Web / REST**
- **Spring Data JPA**
- **MySQL**
- RESTful API endpoints

### Frontend
- **HTML / CSS**
- **Vanilla JavaScript**
- `fetch()` API to communicate with backend

---

## 📦 Getting Started

### Backend (Spring Boot)

1. Clone the repo:
   ```bash
   git clone https://github.com/https://github.com/RaksaOC/Note-Down.git
   cd note-down
   ```

2. Run the Spring Boot app using your IDE or:
   ```bash
   ./mvnw spring-boot:run
   ```

### Frontend

1. Open the `index.html` file located in the resource/static folder.

2. It will connect to the backend API to fetch and display notes.

---

## 🔐 Planned Features

- User authentication (login/signup)
- Markdown formatting
- Image attachments
- Export notes to PDF/Markdown
- Dark mode
- AI features like auto-summarizing or tagging (future)

---

## 📁 Project Structure

```
.
├── HELP.md
├── README.md
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src
│   ├── client.http
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── notedown
│   │   │               ├── NoteDownApplication.java
│   │   │               ├── controller
│   │   │               │   └── NotesController.java
│   │   │               ├── dto
│   │   │               │   └── NoteDTO.java
│   │   │               ├── model
│   │   │               │   ├── Note.java
│   │   │               │   └── User.java
│   │   │               ├── repository
│   │   │               │   ├── NoteRepository.java
│   │   │               │   └── UserRepository.java
│   │   │               ├── security
│   │   │               └── service
│   │   │                   └── NoteService.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── static
│   │       │   ├── assets
│   │       │   │   └── add.png
│   │       │   ├── index.html
│   │       │   └── script.js
│   │       └── templates
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── notedown
│                       └── NoteDownApplicationTests.java
└── target
    ├── classes
    │   ├── application.properties
    │   ├── com
    │   │   └── example
    │   │       └── notedown
    │   │           ├── NoteDownApplication.class
    │   │           ├── controller
    │   │           │   └── NotesController.class
    │   │           ├── dto
    │   │           │   └── NoteDTO.class
    │   │           ├── model
    │   │           │   ├── Note.class
    │   │           │   └── User.class
    │   │           ├── repository
    │   │           │   ├── NoteRepository.class
    │   │           │   └── UserRepository.class
    │   │           └── service
    │   │               └── NoteService.class
    │   └── static
    │       ├── assets
    │       │   └── add.png
    │       ├── index.html
    │       └── script.js
    └── generated-sources
        └── annotations

```

---

## 🤔 Why This Project?

This was built as a personal challenge to:
- Learn Spring Boot from scratch
- Understand how frontend and backend communicate through REST APIs
- Practice fullstack development workflows
- Gain confidence working with Java in a modern web dev environment

---

## 📌 Author Notes

This project is a work in progress, and will continue to evolve with more features and polish. It's open source for anyone interested in contributing, learning, or giving feedback.

---

## 📃 License

[MIT](LICENSE)

---

THANKS!

