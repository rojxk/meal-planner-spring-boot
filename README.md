![Logo](assets/images/meal-planner-logo.png)

## 👉 About The Project
Meal Planner is a web application built with Spring Boot that simplifies the process of storing and managing your meal recipes. With its user-friendly interface, this app makes your culinary journey smoother and more enjoyable.

### Built With

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-323330?style=for-the-badge&logo=javascript&logoColor=F7DF1E)

## ✨ Features

- User Authentication
- Create, Read, Update, and Delete meals
- Sort and search for meals
- Responsive desktop design

## 🔎 Overview
🔓 *User authentication:*
![gif](/assets/gifs/login-page.gif)

➕ *Add meal:*
![gif](/assets/gifs/add-meal.gif)

🔎 *Search:*
![gif](/assets/gifs/search.gif)

❌ *Delete meal:*
![gif](/assets/gifs/delete-meal.gif)


## 📘 Database Schema

The application uses a MySQL database. The full schema can be found in the [database_schema.sql](assets/database/database_schema.sql) file.

Here's a brief overview of the main tables:

- `userdata`: Stores user account information
- `roles`: Stores user account information
- `meal`: Contains details about individual meals
- `category`: Associates meals with users and dates
- `measure`: Manages shopping lists for users
- `meal_description`: Stores individual items in shopping lists
- `ingredient`: Stores individual items in shopping lists

You can also view the ER diagram of the database schema [here](assets/database/entity_relationship_diagram.png).

## 🚀 Installation
Follow these steps to run Meal Planner locally:

### Prerequisites

- Java JDK 11 or later
- Maven 3.6.3 or later
- MySQL 8.0 or later

### Steps
1. **Clone repository**
```
git clone https://github.com/rojxk/meal-planner-spring-boot.git
cd meal-planner
```
2. **Configure database**
- Create a MySQL database named `meal_planner`
- [Here](assets/database/database_schema.sql) change credentials ('INSERT_USERNAME_HERE' and 'INSERT_BCRYPT_HASH_HERE')
- Run the setup script

**IMPORTANT:** Never commit or share your actual password or its hash. The setup script is for local development only.
- Update the application.properties file in src/main/resources with your MySQL username and password:
```
spring.datasource.url=jdbc:mysql://localhost:3306/meal_planner
spring.datasource.username=your_username
spring.datasource.password=your_password
```
3. **Build the project**
```
mvn clean install
```
4. **Run the application**
```
mvn spring-boot:run
```
5. **Access the application**

Open a web browser and navigate to http://localhost:8080



