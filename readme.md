# Cinema

Service for the purchase of tickets.
To use the service, the user must register on the platform.  
Then, after selecting the desired movie and session, the user can purchase tickets.
The service allows you to buy or hand over the beatets, as well as edit your data.

# Examples

Endpoint for user registration:

POST http://localhost:8081/api/v1/user

JSON body:

{"login": "test_b7",
"password": "123456",
"firstName": "test_73",
"email": "test@test.by",
"dateBirthday": "2000-06-14"}

Response:

![](images/user.bmp)

Endpoint for information about the film from "Kinopoisk.ru":

GET http://localhost:8081/api/v1/movieInfo/535341

Response:

![](images/info.bmp)