# 📝 Task Tracker Application

Task Tracker is a full-stack web application that helps users manage task lists, track task status, and visualize progress in real time.

This project was built to practice real-world backend and frontend integration using **Spring Boot** and **Angular**.

---

## 🚀 Features

- Create and manage multiple task lists
- Add, update, and delete tasks
- Change task status (OPEN / CLOSED)
- Automatic progress calculation per task list
- RESTful API architecture
- Clean and responsive UI

---

## 🛠 Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- REST APIs
- Oracle (configurable)

### Frontend
- Angular
- TypeScript
- HTML / CSS
- Angular Router
- HTTP Client

---

## 📐 Architecture Overview

- Backend exposes RESTful APIs
- Frontend consumes APIs using Angular HTTP Client
- Progress is a **derived value** calculated from task status
- Clear separation between Controller, Service, and Repository layers

---

## 📊 Progress Calculation Logic

```
progress = (number of CLOSED tasks / total tasks) * 100
```

---

## 🔗 API Endpoints (Sample)

### Task Lists
- GET /api/task-lists
- GET /api/task-lists/{id}
- POST /api/task-lists
- DELETE /api/task-lists/{id}

### Tasks
- POST /api/task-lists/{listId}/tasks
- PUT /api/task-lists/{listId}/tasks/{taskId}
- DELETE /api/task-lists/{listId}/tasks/{taskId}

---

## ▶️ How to Run the Project

### Backend
```
./mvnw spring-boot:run
```

### Frontend
```
npm install
ng serve
```

---

## 🧪 Future Improvements

- Authentication & Authorization (JWT)
- User-specific task lists
- Pagination and filtering
- Docker support

---

## 👤 Author

**Ibrahim**  
Junior Backend / Full-Stack Developer  
Egypt
