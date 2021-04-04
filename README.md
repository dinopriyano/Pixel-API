# API Spec

<details>
  <summary>User</summary>


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
      "id" : "string, unique",
      "name" : "string",
      "email" : "string",
      "password" : "string",
      "gender" : "string",
      "phone" : "string"
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
    - page : number
    - size : number
  
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
      "phone" : "string"
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
    - Content-Type: application/json
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