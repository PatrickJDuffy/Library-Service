# Library-Service
Backend system built in a SOA (Service Oriented Architecture) way, to manage a small Library. This system is composed by a backend service, responsible for book entry, update and search and responsible for keeping track of books borrowed.

# The Task
This is to be created in Java with the Spring Boot framework, and to expose REST APIs for the
following features:
- Add book (see attributes bellow)
- Add authors (see attributes bellow)
- Update book status (from AVAILABLE or BORROWED and vice versa)
- Search books (3 ways to search, by ISBN, by title or by author)
- Register a borrow of a book
The service should have a database and cater for the below.
A Book has the following attributes:
- ID (unique identifier)
- ISBN (link)
- Title
- Status (AVAILABLE or BORROWED)
- Authors
- 
An Author has the following attributes:
- Name

A Borrow has the the following attributes:
- ID (unique identifier)
- BookID
- Username (simple string like “JohnDoe”)
- DateTime (when it was borrowed)

Notes:
- A Book can have multiple Authors
- An Author can write multiple Books
- You can only Borrow a Book if there is any available copies
- After the Book is Borrowed its status should be updated
- There is no return for a book feature

# Implementation

Languages/Tools
- Java 17
- SpringBoot
- JPA
- Hibernate 2

Structure
The project is built with a Control/Service/Repository flow, where the client sends HTTP requests to the Controller, and it routes traffic through the service layer and queries the JPARepository layer and undertakes the requested action.

Entities
- Books
- Authors
- Borrow
- BookMetaData
- Status (Enum)

Entity mapping 
Books can have many Authors and Auhtors can write many books. Books have an Author attribute, but Authors don’t have a Books Attribute. 
To solve this, I created a Many to Many relationship where the Book is the owner of the unidirectional relationship and paired the bookID attribute to the AuthorID inverse attribute. 

Testing
For this project, I used 2 different testing libraries, Jupiter and TestNG. I used Jupiter to test the Repository layer and TestNG to automatically test some of the logic.
  Issues - I tried to automate all tests however I ran into difficulty testing the JPA interface with the TestNG library. Instead, I used the JPA testing library in jupiter however that resulted in the TestNG testing suite skipping those tests when building. As the @Test Annotations were picked up as not a TestNG annotation rather a jupiter Annotation

# Improvements 
I chose the JPA Database implementation because it was a quick and easy deployment option. However, testing issues arose from that decision. If I were to re-do this project, I would instead consider a DBMS such as Mongo/MySQL/Postgres. 
In terms of features, I would add user functionality (userbase, Authentication etc.) then a book return functionality as well as a frontend for maximum impact, to introduce basic library functions. 
Also possibly add a purchasing service where it takes note of demand and supply of inventory and creates order lists. Maybe even use ML to check the sentiment of books online on applications such as TikTok and Twitter. 

# Conclusion
I have learned a lot from this project and really enjoyed using Spring Boot. I have encountered issues I have never dealt with before which has made me a better developer. 

