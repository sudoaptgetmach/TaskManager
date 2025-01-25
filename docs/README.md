# Task Manager API Documentation

## **1. Overview**

The Task Manager API allows users to manage their tasks efficiently. This includes creating, updating, deleting, and retrieving tasks, as well as managing task statuses, categories, and users. The system is designed to be scalable and user-friendly, facilitating integration with web or mobile applications.

---

## **2. Base URL**

```
http://localhost:8080/api/v1
```

---

## **3. Authentication**

- **Method**: Token-based Authentication.
- Include the authentication token in the `Authorization` header of your requests:
  ```
  Authorization: Bearer <your-token>
  ```

---

## **4. API Endpoints**

### **4.1 Task Management**

#### 4.1.1 Create Task
- **Endpoint**: `/tasks`
- **Method**: POST
- **Description**: Creates a new task.
- **Request Body** (JSON):
  ```json
  {
      "title": "Finish project documentation",
      "description": "Complete the API documentation for the task manager project",
      "dueDate": "2025-02-01",
      "priority": "HIGH",
      "status": "PENDING",
      "categoryId": 1,
      "userId": 1
  }
  ```
- **Response**:
  ```json
  {
      "id": 10,
      "title": "Finish project documentation",
      "description": "Complete the API documentation for the task manager project",
      "dueDate": "2025-02-01",
      "priority": "HIGH",
      "status": "PENDING",
      "categoryId": 1,
      "userId": 1,
      "createdAt": "2025-01-25T10:00:00Z"
  }
  ```

#### 4.1.2 Get All Tasks
- **Endpoint**: `/tasks`
- **Method**: GET
- **Description**: Retrieves all tasks.

#### 4.1.3 Update Task
- **Endpoint**: `/tasks/{taskId}`
- **Method**: PUT

#### 4.1.4 Delete Task
- **Endpoint**: `/tasks/{taskId}`
- **Method**: DELETE

---

### **4.2 Category Management**

#### 4.2.1 Get All Categories
- **Endpoint**: `/categories`
- **Method**: GET

---

### **4.3 User Management**

#### 4.3.1 Register User
- **Endpoint**: `/users/register`
- **Method**: POST
- **Request Body** (JSON):
  ```json
  {
      "name": "John Doe",
      "email": "john.doe@example.com",
      "password": "securepassword"
  }
  ```
- **Response**:
  ```json
  {
      "id": 1,
      "name": "John Doe",
      "email": "john.doe@example.com",
      "createdAt": "2025-01-25T10:00:00Z"
  }
  ```

#### 4.3.2 Authenticate User
- **Endpoint**: `/users/login`
- **Method**: POST

---

## **5. Database Schema**

### **Users Table**
| Column Name | Type        | Constraints                  |
|------------|-------------|------------------------------|
| id         | INT         | PRIMARY KEY, AUTO_INCREMENT |
| name       | VARCHAR(255)| NOT NULL                    |
| email      | VARCHAR(255)| UNIQUE, NOT NULL            |
| password   | VARCHAR(255)| NOT NULL                    |
| created_at | TIMESTAMP   | DEFAULT CURRENT_TIMESTAMP   |

### **Tasks Table**
| Column Name   | Type        | Constraints                  |
|---------------|-------------|------------------------------|
| id            | INT         | PRIMARY KEY, AUTO_INCREMENT |
| title         | VARCHAR(255)| NOT NULL                    |
| description   | TEXT        | NULLABLE                    |
| due_date      | DATE        | NOT NULL                    |
| priority      | ENUM        | ["LOW", "MEDIUM", "HIGH"]  |
| status        | ENUM        | ["PENDING", "IN_PROGRESS", "COMPLETED"] |
| category_id   | INT         | FOREIGN KEY (categories.id) |
| user_id       | INT         | FOREIGN KEY (users.id)      |
| created_at    | TIMESTAMP   | DEFAULT CURRENT_TIMESTAMP   |

### **Categories Table**
| Column Name   | Type        | Constraints                  |
|---------------|-------------|------------------------------|
| id            | INT         | PRIMARY KEY, AUTO_INCREMENT |
| name          | VARCHAR(255)| NOT NULL                    |
| description   | TEXT        | NULLABLE                    |

---

## **6. Migrations**

### Create Users Table
```sql
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Create Categories Table
```sql
CREATE TABLE categories (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT NULL
);
```

### Create Tasks Table
```sql
CREATE TABLE tasks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    description TEXT NULL,
    due_date DATE NOT NULL,
    priority ENUM('LOW', 'MEDIUM', 'HIGH') NOT NULL,
    status ENUM('PENDING', 'IN_PROGRESS', 'COMPLETED') NOT NULL,
    category_id INT,
    user_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

---

## **7. Future Improvements**
- Add support for recurring tasks.
- User authentication with roles and permissions.
- Notification system for upcoming due dates.

