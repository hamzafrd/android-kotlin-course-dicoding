# API SPEC

## Create Product
Request :
- Method : Post
- Endpoint : `/api/products`
- Header :
  - Authorization : `Bearer <token>`
  - Content-Type : application/json
  - Accept : application/json

- Body :

```json
{
    "id" : "string, unique",
    "name" : "string",
    "price" : "long",
    "quantity" : "integer"
}
```

Response :
```json
{
    "code" : "number",
    "status" : "string",
    "data" : {
        "id" : "string, unique",
        "name" : "string",
        "price" : "long",
        "quantity" : "integer",
        "createdAt" : "date",
        "updatedAt" : "date"
    }
}
```

## Get Product
Request :
- Method : Post
- Endpoint : `/api/products/{id_product}`
- Header :
  - Authorization : `Bearer <token>`
  - Content-Type : application/json
  - Accept : application/json
  
Response :
```json
{
   "code" : "number",
    "status" : "string",
    "data" : {
        "id" : "string, unique",
        "name" : "string",
        "price" : "long",
        "quantity" : "integer",
        "createdAt" : "date",
        "updatedAt" : "date"
    }
}
```

## List Product
Request :
- Method : PUT/PATCH
- Endpoint : `/api/products`
- Header :
  - Authorization : `Bearer <token>`
  - Content-Type : application/json
  - Accept : application/json
  
- Query param :
  - size : "number",
  - page : "number"
  
Response :
```json
{
    "code" : "number",
    "status" : "string",
    "data" : [
        {
            "id" : "string, unique",
            "name" : "string",
            "price" : "long",
            "quantity" : "integer",
            "createdAt" : "date",
            "updatedAt" : "date"
        },
        {
            "id" : "string, unique",
            "name" : "string",
            "price" : "long",
            "quantity" : "integer",
            "createdAt" : "date",
            "updatedAt" : "date"
        }
    ]
}
```
## Update Product
Request :
- Method : PUT/PATCH
- Endpoint : `/api/products/{id_product}`
- Header :
  - Authorization : `Bearer <token>`
  - Content-Type : application/json
  - Accept : application/json
- Body :

```json
{
    "name" : "string",
    "price" : "long",
    "quantity" : "integer"
}
```
Response :
```json
{
    "code" : "number",
    "status" : "string",
    "data" : {
        "id" : "string, unique",
        "name" : "string",
        "price" : "long",
        "quantity" : "integer" ,
        "createdAt" : "date",
        "updatedAt" : "date"
    }
}
```

## Delete Product
Request :
- Method : DELETE
- Endpoint : `/api/products/{id_product}`
- Header :
  - Authorization : `Bearer <token>`
  - Content-Type : application/json
  - Accept : application/json

Response :
```json
{
    "code" : "number",
    "status" : "string"
}
```