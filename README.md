# API Spec

#  User
___

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
    "id" : "string, unique",
    "name" : "string",
    "email" : "string",
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
        "phone" : "string"
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