# Social Media Platform API

A modern, RESTful API built with Spring Boot, Spring Data JPA, Spring Security (with JWT), and PostgreSQL. This platform enables users to register, log in, create posts, comment, like posts, and follow/unfollow other users. The API is fully secured with JWT-based authentication.

---

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup & Installation](#setup--installation)
- [API Endpoints](#api-endpoints)
  - [User Authentication](#user-authentication)
  - [Posts](#posts)
  - [Comments](#comments)
  - [Likes](#likes)
  - [Follow/Unfollow](#followunfollow)
- [Usage Examples](#usage-examples)
- [Future Enhancements](#future-enhancements)
- [License](#license)

---

## Features

- **User Registration & Login:** Secure endpoints using JWT.
- **CRUD Operations:** Create, update, and delete posts, comments, and likes.
- **Social Interactions:** Follow/unfollow users to build a network.
- **Self-referencing Relationships:** Efficient handling of followers and following via a many-to-many relationship.
- **Robust Security:** Spring Security integrated with JWT for stateless authentication.

---

## Technologies Used

- **Java 21**
- **Spring Boot 2.7.x**
- **Spring Data JPA**
- **Spring Security (JWT)**
- **PostgreSQL**
- **Lombok**
- **JJWT (0.11.5)**
- **Maven**

---
