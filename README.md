A simple API for rating system.
Allows manipulating the rating database through requesting http's.
POST http://localhost:8080/api/ratings
{
    "score": 4,
    "category": "Электроника",
    "comment": "Продолжение руки",
    "productId": 12345,
    "rating": 4.5,
    "productName": "Xiaomi Poco F3"
}
GET http://localhost:8080/api/ratings?page=0&size=10
GET http://localhost:8080/api/ratings/category?category=Electronics&page=0&size=5
GET http://localhost:8080/api/ratings/score?min=3&max=5&page=0&size=10
GET http://localhost:8080/api/ratings/product?productId=12345&page=0&size=10
GET http://localhost:8080/api/ratings/1
PUT http://localhost:8080/api/ratings/1
DELETE http://localhost:8080/api/ratings/1
Above are the examples of a variety requests.
