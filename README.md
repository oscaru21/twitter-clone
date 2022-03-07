# twitter-clone

## Class Diagram
![](./img/twitterClone.drawio.png)

## Users API design

| **HTTP Method** | **Path**                                       | **Description**              |
|-----------------|------------------------------------------------|------------------------------|
| GET             | /users                                         | Get all users                |
| GET             | /users/{username}                              | Get user by username         |
| GET             | /users                                         | Create new user              |
| POST            | /users/{id}                                    | Update user data             |
| PUT             | /users/{id}                                    | Delete user by id            |
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