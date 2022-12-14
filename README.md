# online-bookstore
**REST API for an online bookstore with CRUD operations - Spring Boot Implementation**

**Implementation-**

http://localhost:8080/bookstore

http://localhost:8080/bookstore/welcome

http://localhost:8080/bookstore/addbook

Example RequestBody -

{
    "bookName": "abc",
    "bookDescription": "abc book desc",
    "bookAuthor": "ali",
    "bookClassification": "comic",
    "bookPrice": 150,
    "bookISBN": "book-comic-003-abc",
    "bookQuantity": 20,
    "promotionalDiscountPercentage": 5
}

http://localhost:8080/bookstore/getallbooks

http://localhost:8080/bookstore/getbook/{Id}
e.g. - http://localhost:8080/bookstore/getbook/1

http://localhost:8080/bookstore/updatebook

Example RequestBody -

{
    "bookName": "abc updated",
    "bookDescription": "abc book desc updated",
    "bookAuthor": "ali",
    "bookClassification": "comic",
    "bookPrice": 300,
    "bookISBN": "book-comic-003-abc",
    "bookQuantity": 20,
    "promotionalDiscountPercentage": 10
}

http://localhost:8080/bookstore/deletebook/{Id}
e.g. http://localhost:8080/bookstore/getbook/1

http://localhost:8080/bookstore/buybooks

Example RequestBody -

[
    {
        "bookId": 1,
        "bookName": "abc",
        "bookDescription": "abc book desc",
        "bookAuthor": "ali",
        "bookClassification": "comic",
        "bookISBN": "book-comic-001-abc",
        "bookQuantity": 10,
        "promotionalDiscountPercentage": 5
    }
]

Database - H2 DB In Mememory Spring Boot BuiltIn

http://localhost:8080/h2-console/

JDBC URL - jdbc:h2:mem:testdb

Username/Password - sa/admin

**Assumptions-**
1. Using Spring Boot to implement the application
2. Using Maven as build and project management tool
3. Using persistence method for Books as H2 DB In Mememory (Spring Boot BuiltIn)
4. Need to add few books(add operation) to the DB once after starting the App to perform read, update, delete and buy operation. Can use add operation to add books to DB
5. Book entity(List of Books) is the request input for buying books
6. Order entity is the response output after successfull buy operation with order details and total payable amount. Not storing in DB

**Note-**
Unable to upload the packged jar file for the application due to big size. GitHub is allowing upto 25MB whereas the app jar is 42MB+. To run the application locally with the target executable jar file, run following maven command from app directory - "mvn clean package". Once jar get created under target folder can run in Docker as well.
