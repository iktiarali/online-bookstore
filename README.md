# online-bookstore
REST API for an online bookstore with CRUD operations - Spring Boot Implementation

Implementation -

http://localhost:8080/bookstore

http://localhost:8080/bookstore/welcome

http://localhost:8080/bookstore/addbook

http://localhost:8080/bookstore/getallbooks

http://localhost:8080/bookstore/getbook/{Id}

http://localhost:8080/bookstore/updatebook

http://localhost:8080/bookstore/deletebook/{Id}

http://localhost:8080/bookstore/buybooks


Database - H2 DB In Mememory Spring Boot BuiltIn

http://localhost:8080/h2-console/

JDBC URL - jdbc:h2:mem:testdb

Username/Password - sa/admin

Assumptions -
1. App is storing the books in  H2 DB In Mememory (Spring Boot BuiltIn)
2. Need to add books to the DB once after starting the App to perform buy operation apart form CRUD operation
3. Book entity(List of Books) is the request input for buying books
4. Order entity is the response output after successfull buy operation with order details and total payable amount. Not storing in DB
