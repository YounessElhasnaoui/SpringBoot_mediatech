# Mediatech API Tester

## Overview

**Mediatech API Tester** is a Spring Boot application designed for managing entities like clients, products, invoices, and invoice lines. The application includes a dynamic front-end interface built with HTML, JavaScript, and Bootstrap, allowing users to interact with the API without the need for external tools like Postman.

---

## Features

- **CRUD Operations**: Perform Create, Read, Update, and Delete operations on all entities.
- **Dynamic UI**: Select entities and endpoints dynamically from a user-friendly interface.
- **Secure API**: Fully protected with CSRF tokens.
- **Error Handling**: Displays detailed error messages for a better user experience.
- **Responsive Design**: Built with Bootstrap for mobile-friendly usage.

---

## Technologies Used

### Backend:
- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Spring Security
- ModelMapper

### Frontend:
- HTML5
- CSS3 (Bootstrap 5)
- Vanilla JavaScript

---

## Getting Started

### Prerequisites

- Java 17 or later
- MySQL database
- Maven or Gradle
- A browser to access the application

### Installation Steps

1. **Clone the Repository**
   ```bash
   git clone https://github.com/YounessElhasnaoui/SpringBoot_mediatech.git
   cd SpringBoot_mediatech
   ```

2. **Set Up the Database**
   - Ensure MySQL is installed and running.
   - Create a database named `mediatech1`.

3. **Update Configuration**
   - Open `src/main/resources/application.properties` and update the database credentials:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/mediatech1
     spring.datasource.username=admin
     spring.datasource.password=admin123
     ```

4. **Build and Run the Application**
   - Using Maven:
     ```bash
     mvn clean install
     mvn spring-boot:run
     ```
   - Or using Gradle:
     ```bash
     gradle build
     gradle bootRun
     ```

5. **Access the Application**
   - Open your browser and navigate to `http://localhost:8080/`.
   - Log in with:
     - **Username**: `admin`
     - **Password**: `admin123`

---

## How to Use

1. **Login**: Enter your credentials to access the dashboard.
2. **Select Entity**: Choose an entity (Client, Product, Invoice, etc.) from the dropdown.
3. **Select Endpoint**: Pick the operation you want to perform (e.g., Create, Get All).
4. **Provide Inputs**: If the endpoint requires parameters or a request body, input them in the form fields.
5. **Execute Request**: Click the "Execute" button to send the request. The response will display in a table or as an error message if something goes wrong.
![image](https://github.com/user-attachments/assets/779c84aa-c6e9-4aa9-b1eb-10bba29b7d14)


---

## Error Handling

The app provides descriptive error messages for:
- **Not Found**: If an entity is not found, you'll see a 404 error with details.
- **Validation Errors**: Missing parameters or invalid data return a 400 error.
- **Server Errors**: Unexpected issues return a 500 error with the error message from the server.

---

## Contributing

Contributions are welcome! To contribute:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-name`).
3. Commit your changes (`git commit -m "Add feature"`).
4. Push to the branch (`git push origin feature-name`).
5. Open a pull request.

---

## License

This project is licensed under the MIT License. See `LICENSE` for more details.
