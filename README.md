# twitter-clone

## Class Diagram
![](./img/twitterClone.drawio.png)

## Users API design

| **HTTP Method** | **Path**                                       | **Description**              |
|-----------------|------------------------------------------------|------------------------------|
| GET             | /users                                         | Get all users                |
| GET             | /users/{username}                              | Get user by username         |
| POST            | /users                                         | Create new user              |
| PUT             | /users                                         | Update user data             |
| DELETE          | /users/{id}                                    | Delete user by id            |
| GET             | /users?pageSize=10&pageNo=1&sortingBy=username | Pagination and sorting users |

## Tweet API design

| **HTTP Method** | **Path**                                                  | **Description**                     |
|-----------------|-----------------------------------------------------------|-------------------------------------|
| GET             | /users/tweets?pageSize=10&pageNo=1&sortingBy=creationDate | Get all tweets paginated and sorted |
| GET             | /users/{username}/tweets                                  | Get tweets by user username         |
| POST            | /users/{username}/tweets                                  | Create new tweet                    |
| PUT             | /users/{username}/tweets/{id}                             | Update tweet content                |
| DELETE          | /users/{username}/tweets/{id}                             | Delete tweet by id                  |

## Like API design

| **HTTP Method** | **Path**                            | **Description** |
|-----------------|-------------------------------------|-----------------|
| POST            | /users/{username}/tweets/{id}/likes | Create new like |
| DELETE          | /users/{username}/tweets/{id}/likes | Delete like     |

## Authentication API design

| **HTTP Method** | **Path**     | **Description** |
|-----------------|--------------|-----------------|
| POST            | /auth/login  | login           |
| POST            | /auth/signup | signup          |

## JWT configuration with spring security

- To customize Spring Security, we need a config class annotated with @EnableWebSecurity. That extends the WebSecurityConfigurerAdapter class and override both of its functions to customize the http security and authentication manager.
- In this class we will include our UserRepo class as a dependency in order to get the user information from our database.
- 