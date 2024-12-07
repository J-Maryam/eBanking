
# **eBanking REST API**
---
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)

---
A secure banking application built with **Spring Security** using **Basic Authentication**.

## **Table of Contents**
- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Endpoints](#endpoints)
- [Security Configuration](#security-configuration)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

---

## **Project Overview**
eBanking is a RESTful API project that secures user data and provides controlled access to banking services. The API uses **Spring Security** for authentication and role-based access control, ensuring a robust and secure user experience.
---

## **Features**
- **Public Endpoints**: Access notices and contact support without authentication.
- **User Authentication**: Secure login with Basic Authentication.
- **Role-Based Access Control**:
    - Admins can manage users and update roles.
    - Users can access their loans, accounts, cards, and balance details.
- **User Management**:
    - Registration of new users with default roles.
    - Password update functionality.
    - Role updates managed by Admin.
- **Exception Handling**:
    - Centralized exception management with meaningful error responses.
    - Custom exceptions for scenarios like duplicate usernames.

---

## **Technologies Used**
- **Spring Boot**: Application framework.
- **Spring Security**: Authentication and authorization.
- **REST API**: For communication between clients and servers.
- **JPA/Hibernate**: Database interaction.
- **PostgreSQL**: Main database.
- **Maven**: Build automation tool.

---

## **Installation**

1. **Clone the repository**:
   ```bash
   git clone https://github.com/J-Maryam/eBanking.git
   ```

2. **Navigate to the project directory**:
   ```bash
   cd eBanking
   ```

3. **Build the project using Maven**:
   ```bash
   mvn clean install
   ```

4. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```
---

## **Endpoints**

### **Public Endpoints**
| HTTP Method | Endpoint          | Description                    |
|-------------|-------------------|--------------------------------|
| GET         | `/api/notices`    | Fetch general notices.         |
| GET         | `/api/contact`    | Retrieve contact information.  |
| POST        | `/api/users/register` | Register a new user.         |

### **Secured Endpoints**
#### **For Users**
| HTTP Method | Endpoint          | Description                          |
|-------------|-------------------|--------------------------------------|
| GET         | `/api/myLoans`    | Fetch user loan details.             |
| GET         | `/api/myCards`    | Fetch user card details.             |
| GET         | `/api/myAccount`  | Fetch user account details.          |
| GET         | `/api/myBalance`  | Fetch user balance information.      |
| PUT         | `/api/users/{username}` | Update user password.          |

#### **For Admins**
| HTTP Method | Endpoint                  | Description                         |
|-------------|---------------------------|-------------------------------------|
| GET         | `/api/users`              | Fetch all users.                    |
| GET         | `/api/users/{username}`   | Fetch a user by username.           |
| PUT         | `/api/users/{username}/updateRole` | Update user role.             |
| DELETE      | `/api/users/{username}`   | Delete a user by username.          |

---

## **Security Configuration**
- **Authentication**:
    - Basic Authentication with custom `UserDetailsService`.
- **Authorization**:
    - Role-based access (`ROLE_USER`, `ROLE_ADMIN`).
- **Password Encoding**:
    - Passwords are hashed using **BCryptPasswordEncoder**.
- **Profiles**:
    - **`dev`**: Regular authentication with password validation.
    - **`test`**: Allows authentication with any password for testing purposes.
- **Stateless Session**:
    - Configured to avoid session creation (`STATELESS`).

---

## **Usage**
1. **Register a User**:
    - Use the `/api/users/register` endpoint to register a new user with default `ROLE_USER`.
2. **Login**:
    - Authenticate using Basic Authentication (username and password).
3. **Access Data**:
    - Use authorized endpoints based on your role (USER or ADMIN).

---

## **License**
This project is licensed under the MIT License.

---

## **Contact**
For any queries or issues, feel free to reach out:
- **Email**: maryamjammar1509@gmail.com
- **GitHub**: [My GitHub Profile](https://github.com/J-Maryam)

✨ **Happy Coding!**
