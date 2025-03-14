# Social Media Platform API

A RESTful API for a social media platform built with Spring Boot, Spring Data JPA, Spring Security (with JWT), and PostgreSQL. This API allows users to register, log in, create posts, comment, like posts, and manage follower/following relationships.

## Overview

The Social Media Platform API provides endpoints for:
- **User Management:**  
  Register, log in (JWT-based authentication), and view user profiles.
- **Social Interactions:**  
  Follow and unfollow other users through a self-referencing many-to-many relationship.
- **Content Creation:**  
  Create, update, and delete posts, as well as add comments and likes.
- **Security:**  
  Endpoints are secured using JWT tokens. Only authenticated users can perform actions like creating posts, comments, likes, or following/unfollowing users.

## Key Features

- **User Registration & Authentication:**  
  Users can register and log in to obtain a JWT token that must be provided in subsequent requests.

- **JWT-Based Security:**  
  The API uses JWT (via JJWT 0.11.5) to secure endpoints. Tokens are generated upon successful login and validated on every secured request.

- **Follower/Following Mechanism:**  
  The application implements a self-referencing many-to-many relationship to manage social connections:
  - **Followers:** Users who follow a given user.
  - **Following:** Users that a given user is following.
  The relationship is stored in an auto-generated join table named `user_followers`.

- **Content Management:**  
  Authenticated users can create posts, comment on posts, and like posts. The API automatically associates posts, comments, and likes with the authenticated user.

## Technologies Used

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security with JWT**
- **PostgreSQL** 
- **Lombok**
- **JJWT**
- **Maven**
