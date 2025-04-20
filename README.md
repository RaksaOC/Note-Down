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
- **H2 / MySQL** (use whichever you picked)
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
   git clone https://github.com/your-username/notes-app.git
   cd notes-app
   ```

2. Run the Spring Boot app using your IDE or:
   ```bash
   ./mvnw spring-boot:run
   ```

3. The API will be available at:
   ```
   http://localhost:8080/api/notes
   ```

### Frontend

1. Open the `index.html` file located in the frontend folder (or the root if it's simple).

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
notes-app/
├── backend/
│   ├── src/
│   │   └── main/java/com/example/notes/...
│   └── pom.xml
├── frontend/
│   ├── index.html
│   ├── script.js
│   └── style.css
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

