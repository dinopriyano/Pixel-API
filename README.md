# Pixel API (On Progress)

 RESTFul API for Pixel (Upcoming open source Apps)

## Setup

- ### Setup Database :

  Set your database in `src/main/resources/application.properties` 

- ### Start Docker : 
  
  - First, pull MySQL Docker Images using `docker pull mysql` 
  - And then start Pixel Docker Container using `docker-compose -f docker-compose.yml up -d`

- ### Start Spring Boot Apps (Gradle) :

  Type on terminal `gradle bootRun`



## API Spec

<details>
  <summary>Authentication</summary>

## Login

Request :

- Method : POST
- Endpoint : `/api/auth/login`
- Header :
  - Content-Type: multipart/form-data
  - Accept: application/json
- Body :

```json
{
    "email" : "string",
    "password" : "string"
}
```
  

Response :

  ```json
  {
      "code" : "number",
      "error" : "boolean",
      "message" : "string",
      "data" : {
          "id" : "string, unique",
          "name" : "string",
          "email" : "string",
          "gender" : "string",
          "phone" : "string",
          "photo" : "string",
          "updated_at" : "Date",
          "deleted_at" : "Date",
          "token" : "string"
      }
  }
  ```


## Register

Request :

- Method : POST
- Endpoint : `/api/auth/register`
- Header :
  - Content-Type: multipart/form-data
  - Accept: application/json
- Body :

```json
{
    "name" : "string",
    "email" : "string",
    "password" : "string",
    "gender" : "string",
    "phone" : "string",
    "photo" : "file"
}
```

Response :

  ```json
  {
      "code" : "number",
      "error" : "boolean",
      "message" : "string",
      "data" : {
          "id" : "string, unique",
          "name" : "string",
          "email" : "string",
          "gender" : "string",
          "phone" : "string",
          "photo" : "string",
          "updated_at" : "Date",
          "deleted_at" : "Date"
      }
  }
  ```

</details>

<details>
  <summary>Image</summary>

  ## Get Image
  
  Request :
  
  - Method : GET
  - Endpoint : `/api/images/{id_image}`
  - Header :
    - Accept: application/json
  
  Response : Image

</details>

<details>
  <summary>Users</summary>

## Authentication

All API in users must use this authentication

- Header :
  - Authorization : `Bearer your_api_key`

## Create User

Request :

- Method : POST
- Endpoint : `/api/users`
- Header :
  - Content-Type: multipart/form-data
  - Accept: application/json
- Body :

```json
{
    "name" : "string",
    "email" : "string",
    "password" : "string",
    "gender" : "string",
    "phone" : "string",
    "photo" : "file"
}
```

  Response :

  ```json
  {
      "code" : "number",
      "error" : "boolean",
      "message" : "string",
      "data" : {
          "id" : "string, unique",
          "name" : "string",
          "email" : "string",
          "gender" : "string",
          "phone" : "string",
          "photo" : "string",
          "updated_at" : "Date",
          "deleted_at" : "Date"
      }
  }
  ```

  ## Get User

  Request :

  - Method : GET
  - Endpoint : `/api/users/{id_user}`
  - Header :
    - Accept: application/json

  Response :

  ```json
  {
      "code" : "number",
      "error" : "boolean",
      "message" : "string",
      "data" : {
          "id" : "string, unique",
          "name" : "string",
          "email" : "string",
          "gender" : "string",
          "phone" : "string",
          "photo" : "string",
          "updated_at" : "Date",
          "deleted_at" : "Date"
      }
  }
  ```

  ## List User

  Request :

  - Method : GET
  - Endpoint : `/api/users`
  - Header :
    - Accept: application/json
  - Query Params
    - page : number, def = 1
    - size : number, def = 10

  Response :

  ```json
  {
      "code" : "number",
      "error" : "boolean",
      "message" : "string",
      "currentPage" : "number",
      "isLast" : "boolean",
      "totalPage" : "number",
      "data" : [
          {
              "id" : "string, unique",
              "name" : "string",
              "email" : "string",
              "gender" : "string",
              "phone" : "string",
              "photo" : "string",
              "updated_at" : "Date",
              "deleted_at" : "Date"
          }
      ]
  }
  ```

  ## Update User

  Request :

  - Method : PUT
  - Endpoint : `/api/users/{id_user}`
  - Header :
    - Content-Type: multipart/form-data
    - Accept: application/json
  - Body :

  ```json
  {
      "name" : "string",
      "email" : "string",
      "password" : "string",
      "gender" : "string",
      "phone" : "string",
      "photo" : "file"
  }
  ```

  Response :

  ```json
  {
      "code" : "number",
      "error" : "boolean",
      "message" : "string",
      "data" : {
          "id" : "string, unique",
          "name" : "string",
          "email" : "string",
          "gender" : "string",
          "phone" : "string",
          "photo" : "string",
          "updated_at" : "Date",
          "deleted_at" : "Date"
      }
  }
  ```

  ## Change Password

  Request :

  - Method : PUT
  - Endpoint : `/api/users/{id_user}/changepassword`
  - Header :
    - Content-Type: multipart/form-data
    - Accept: application/json
  - Body :

  ```json
  {
      "oldPassword" : "string",
      "newPassword" : "string"
  }
  ```

  Response :

  ```json
  {
      "code" : "number",
      "error" : "boolean",
      "message" : "string",
      "data" : {
          "id" : "string, unique",
          "name" : "string",
          "email" : "string",
          "gender" : "string",
          "phone" : "string",
          "photo" : "string",
          "updated_at" : "Date",
          "deleted_at" : "Date"
      }
  }
  ```

  ## Delete User

  Request :

  - Method : DELETE
  - Endpoint : `/api/users/{id_user}`
  - Header :
    - Accept: application/json

  Response :

  ```json
  {
      "code" : "number",
      "error" : "boolean",
      "message" : "string",
      "data" : {}
  }
  ```
</details>


<details>
  <summary>Post</summary>

## Authentication

All API in post must use this authentication

- Header :
  - Authorization : `Bearer your_api_key`

## Create Post

Request :

- Method : POST
- Endpoint : `/api/post`
- Header :
    - Content-Type: multipart/form-data
    - Accept: application/json
- Body :

```json
{
    "title" : "string",
    "description" : "string",
    "users_id" : "string",
    "image" : "file"
}
```

  Response :

  ```json
  {
      "code" : "number",
      "error" : "boolean",
      "message" : "string",
      "data" : {
          "id" : "string, unique",
          "title" : "string",
          "description" : "string",
          "image" : "string",
          "updated_at" : "Date",
          "deleted_at" : "Date"
      }
  }
  ```

## Detail Post

Request :

- Method : GET
- Endpoint : `/api/post/{id_post}`
- Header :
  - Accept: application/json

Response :

  ```json
  {
      "code" : "number",
      "error" : "boolean",
      "message" : "string",
      "data" : {
          "id": "string",
          "title": "string",
          "description": "string",
          "image": "string",
          "created_at": "date",
          "updated_at": "date"
      }
  }
  ```

## List Post

Request :

- Method : GET
- Endpoint : `/api/users`
- Header :
  - Accept: application/json
- Query Params
  - page : number, def = 1
  - size : number, def = 10
  - users_id : string, def = null

Response :

  ```json
  {
      "code" : "number",
      "error" : "boolean",
      "message" : "string",
      "currentPage" : "number",
      "isLast" : "boolean",
      "totalPage" : "number",
      "data" : [
          {
            "id": "string",
            "title": "string",
            "description": "string",
            "image": "string",
            "created_at": "date",
            "updated_at": "date",
            "users": {
                "name": "string",
                "photo": "string",
                "id": "string"
            }
          }
      ]
  }
  ```

## Update Post

Request :

- Method : PUT
- Endpoint : `/api/post/{id_post}`
- Header :
  - Content-Type: multipart/form-data
  - Accept: application/json
- Body :

```json
{
    "title" : "string",
    "description" : "string",
    "users_id" : "string",
    "image" : "file, allow null"
}
```

Response :

  ```json
  {
      "code" : "number",
      "error" : "boolean",
      "message" : "string",
      "data" : {
          "id" : "string, unique",
          "title" : "string",
          "description" : "string",
          "image" : "string",
          "updated_at" : "Date",
          "deleted_at" : "Date"
      }
  }
  ```

## Delete Post

Request :

- Method : DELETE
- Endpoint : `/api/post/{id_post}`
- Header :
  - Accept: application/json

Response :

  ```json
  {
      "code" : "number",
      "error" : "boolean",
      "message" : "string",
      "data" : {}
  }
  ```
</details>