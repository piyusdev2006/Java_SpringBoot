# Spring Boot Interceptor Testing Guide

This document explains how to test the complete interceptor flow in the current Spring Boot project.

The project contains these interceptors:

1. `LoggingInterceptor`
2. `AuthenticationInterceptor`
3. `AuthorizationInterceptor`
4. `TimingInterceptor`

The controller being tested is:

```text
/api/students
```

---

# 1. Overall Request Flow

The intended interceptor execution order is:

```text
Client Request
     |
     v
LoggingInterceptor
     |
     v
AuthenticationInterceptor
     |
     |---- invalid/missing token ----> 401 Unauthorized -> STOP
     |
     v
AuthorizationInterceptor
     |
     |---- insufficient permission --> 403 Forbidden -> STOP
     |
     v
TimingInterceptor
     |
     v
StudentController
     |
     v
Response
```

## Meaning of each interceptor

### LoggingInterceptor

Logs request information such as:

- HTTP method
- Request URI
- Query string
- Client IP
- Token header
- Controller class
- Controller method
- Final response status

### AuthenticationInterceptor

Answers:

> Who are you?

It reads the token from the request header and validates it.

Example:

```http
token: 12345
```

If authentication fails:

```text
401 Unauthorized
```

and the controller is not executed.

### AuthorizationInterceptor

Answers:

> Are you allowed to perform this operation?

The authentication interceptor stores the user's role inside the request:

```java
request.setAttribute("role", "ADMIN");
```

The authorization interceptor reads it:

```java
String role = (String) request.getAttribute("role");
```

and checks whether the role is allowed to access the requested operation.

If authentication is successful but permission is insufficient:

```text
403 Forbidden
```

and the controller is not executed.

### TimingInterceptor

Measures how long the request takes to complete.

It stores the start time in the request:

```java
request.setAttribute("startTime", startTime);
```

and calculates the elapsed time in `afterCompletion()`.

Example response header:

```http
X-Response-Time: 12 ms
```

---

# 2. Application Base URL

Assuming the application is running on the default Spring Boot port:

```text
http://localhost:8080
```

Base API:

```text
http://localhost:8080/api/students
```

If your application uses another port, replace `8080` accordingly.

---

# 3. Current Authentication Rule

For the current demo implementation, the valid authentication token is:

```http
token: 12345
```

The current authentication interceptor assigns:

```text
role = ADMIN
```

after the token is successfully validated.

Therefore, with the current code:

```text
12345 -> authenticated -> ADMIN
```

Any other token is considered invalid.

> Note: This is a learning/demo setup. In a real application, authentication would normally use something like JWT/OAuth2/Spring Security rather than a hard-coded token.

---

# 4. Student Controller Endpoints

The controller exposes these endpoints:

| HTTP Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/students` | Create student |
| GET | `/api/students` | Get all students |
| GET | `/api/students/{id}` | Get student by ID |
| PUT | `/api/students/{id}` | Update student |
| PATCH | `/api/students/{id}` | Partially update student |
| DELETE | `/api/students/{id}` | Delete student |

Example ID:

```text
10
```

---

# 5. Postman Setup

## Step 1: Start the Spring Boot application

Run the application and make sure Spring Boot starts successfully.

Expected message will look similar to:

```text
Tomcat started on port 8080
```

## Step 2: Open Postman

Create a new request.

For requests that require authentication, go to:

```text
Headers
```

and add:

| Key | Value |
|---|---|
| token | 12345 |

For invalid-token tests, use:

```text
token: 99999
```

For missing-token tests, remove the `token` header completely.

---

# 6. Test 1 - POST Create Student With Valid Token

## Request

```http
POST http://localhost:8080/api/students
```

Header:

```http
token: 12345
```

## Expected interceptor flow

```text
Request
  |
  v
LoggingInterceptor
  |
  v
AuthenticationInterceptor
  |
  | token = 12345
  | valid
  | role = ADMIN
  v
AuthorizationInterceptor
  |
  | POST requires ADMIN
  | ADMIN -> allowed
  v
TimingInterceptor
  |
  v
StudentController
```

## Expected controller output

```text
POST - controller called
```

or, if using the original method:

```text
controller called
```

## Expected response

```text
Status: 200 OK
```

Body:

```text
Student Created
```

## Expected response headers

You should see headers similar to:

```http
X-Auth-Interceptor: AuthenticationInterceptor
X-Authenticated: true
X-Authorization: Allowed
X-Response-Time: <some number> ms
```

The exact timing value will vary.

---

# 7. Test 2 - POST Create Student With Invalid Token

## Request

```http
POST http://localhost:8080/api/students
```

Header:

```http
token: 99999
```

## Flow

```text
Request
  |
  v
LoggingInterceptor
  |
  v
AuthenticationInterceptor
  |
  | token invalid
  v
401 Unauthorized
  |
  v
STOP
```

Authorization, Timing and Controller should not continue through normal successful flow.

## Expected response

```text
Status: 401 Unauthorized
```

Body:

```json
{
    "success": false,
    "message": "Unauthorized! Invalid or missing token"
}
```

## Important check

You must NOT see:

```text
controller called
```

in the console.

That proves the authentication interceptor blocked the request before the controller.

---

# 8. Test 3 - POST Create Student Without Token

## Request

```http
POST http://localhost:8080/api/students
```

Do not send the `token` header.

## Flow

```text
Request
  |
  v
LoggingInterceptor
  |
  v
AuthenticationInterceptor
  |
  | token = null
  v
401 Unauthorized
  |
  v
STOP
```

## Expected response

```text
Status: 401 Unauthorized
```

Body:

```json
{
    "success": false,
    "message": "Unauthorized! Invalid or missing token"
}
```

Controller must not execute.

---

# 9. Test 4 - GET All Students With Valid Token

## Request

```http
GET http://localhost:8080/api/students
```

Header:

```http
token: 12345
```

## Flow

```text
Request
  |
  v
LoggingInterceptor
  |
  v
AuthenticationInterceptor
  |
  | valid token
  | role = ADMIN
  v
AuthorizationInterceptor
  |
  | GET allowed
  v
TimingInterceptor
  |
  v
StudentController
```

## Expected response

```text
Status: 200 OK
```

Body:

```text
All Students
```

---

# 10. Test 5 - GET All Students With Invalid Token

Request:

```http
GET http://localhost:8080/api/students
```

Header:

```http
token: 99999
```

Expected:

```text
401 Unauthorized
```

Controller must not execute.

---

# 11. Test 6 - GET Student By ID

## Request

```http
GET http://localhost:8080/api/students/10
```

Header:

```http
token: 12345
```

## Expected response

```text
Status: 200 OK
```

Body:

```text
Student with ID: 10
```

## Flow

```text
Request
  |
  v
Logging
  |
  v
Authentication -> valid
  |
  v
Authorization -> allowed
  |
  v
Timing
  |
  v
Controller
```

---

# 12. Test 7 - GET Student By ID With Invalid Token

```http
GET http://localhost:8080/api/students/10
```

Header:

```http
token: 99999
```

Expected:

```text
401 Unauthorized
```

Controller must not execute.

---

# 13. Test 8 - PUT Update Student

## Request

```http
PUT http://localhost:8080/api/students/10
```

Header:

```http
token: 12345
```

## Flow

```text
Request
  |
  v
Logging
  |
  v
Authentication
  |
  | ADMIN
  v
Authorization
  |
  | PUT requires ADMIN
  | allowed
  v
Timing
  |
  v
Controller
```

## Expected response

```text
Status: 200 OK
```

Body:

```text
Student 10 Updated
```

---

# 14. Test 9 - PUT With Invalid Token

```http
PUT http://localhost:8080/api/students/10
```

Header:

```http
token: 99999
```

Expected:

```text
401 Unauthorized
```

Controller must not execute.

---

# 15. Test 10 - PATCH Partial Update

## Request

```http
PATCH http://localhost:8080/api/students/10
```

Header:

```http
token: 12345
```

## Expected response

```text
Status: 200 OK
```

Body:

```text
Student 10 Partially Updated
```

Flow:

```text
Request
  |
  v
Logging
  |
  v
Authentication -> valid
  |
  v
Authorization -> ADMIN allowed
  |
  v
Timing
  |
  v
Controller
```

---

# 16. Test 11 - PATCH With Invalid Token

```http
PATCH http://localhost:8080/api/students/10
```

Header:

```http
token: 99999
```

Expected:

```text
401 Unauthorized
```

Controller must not execute.

---

# 17. Test 12 - DELETE Student

## Request

```http
DELETE http://localhost:8080/api/students/10
```

Header:

```http
token: 12345
```

## Flow

```text
Request
  |
  v
Logging
  |
  v
Authentication -> valid
  |
  v
Authorization -> ADMIN allowed
  |
  v
Timing
  |
  v
Controller
```

## Expected response

```text
Status: 200 OK
```

Body:

```text
Student 10 Deleted
```

---

# 18. Test 13 - DELETE With Invalid Token

```http
DELETE http://localhost:8080/api/students/10
```

Header:

```http
token: 99999
```

Expected:

```text
401 Unauthorized
```

Controller must not execute.

---

# 19. Test 14 - Verify Timing Interceptor

Use any valid request, for example:

```http
GET http://localhost:8080/api/students
```

Header:

```http
token: 12345
```

## Console

You should see something similar to:

```text
Timer started...
Total Request Time: 10 ms
```

The exact value can be different:

```text
5 ms
12 ms
27 ms
```

etc.

## Response Header

Check the Postman `Headers` section for:

```http
X-Response-Time: 10 ms
```

The number is expected to vary.

---

# 20. Verify Authentication Headers

For a successful authentication request, the authentication interceptor may set:

```http
X-Auth-Interceptor: AuthenticationInterceptor
X-Authenticated: true
```

These are custom headers created by our code.

They are not responsible for authentication themselves.

The actual authentication decision is made by checking:

```java
String token = request.getHeader("token");
```

and then validating the token.

---

# 21. Understand `setHeader()`

Example:

```java
response.setHeader("X-Authenticated", "true");
```

means:

```text
Header name  = X-Authenticated
Header value = true
```

The header becomes part of the HTTP response.

It is metadata, not the response body.

Example response:

```text
HTTP/1.1 200 OK
X-Authenticated: true
Content-Type: text/plain

Student Created
```

---

# 22. Request Header vs Response Header

## Request header

Client -> Server

Example:

```http
token: 12345
```

Read in controller/interceptor using:

```java
request.getHeader("token");
```

## Response header

Server -> Client

Example:

```http
X-Authenticated: true
```

Set using:

```java
response.setHeader("X-Authenticated", "true");
```

---

# 23. Important Request Attribute Flow

Authentication can place internal Java data into the request:

```java
request.setAttribute("role", "ADMIN");
```

Authorization can later read it:

```java
String role = (String) request.getAttribute("role");
```

Flow:

```text
AuthenticationInterceptor
        |
        | setAttribute("role", "ADMIN")
        v
   HttpServletRequest
        |
        | getAttribute("role")
        v
AuthorizationInterceptor
```

This is different from sending a header to the client.

---

# 24. Authentication vs Authorization

## Authentication

Question:

```text
Who are you?
```

Example:

```text
token = 12345
```

Result:

```text
Authenticated
```

## Authorization

Question:

```text
Are you allowed to do this?
```

Example:

```text
role = ADMIN
operation = DELETE
```

Result:

```text
Allowed
```

---

# 25. 401 vs 403

This distinction is very important.

## 401 Unauthorized

Usually means the client has not successfully authenticated.

Examples:

```text
Token missing
Token invalid
```

Our application returns:

```text
401 Unauthorized
```

## 403 Forbidden

Means the identity is known/authenticated, but the user is not permitted to perform the operation.

Example:

```text
Authenticated USER tries DELETE
```

Result:

```text
403 Forbidden
```

---

# 26. Full CRUD Test Matrix

| # | Method | URL | Header | Expected |
|---:|---|---|---|---|
| 1 | POST | `/api/students` | `token: 12345` | 200 |
| 2 | POST | `/api/students` | `token: 99999` | 401 |
| 3 | POST | `/api/students` | none | 401 |
| 4 | GET | `/api/students` | `token: 12345` | 200 |
| 5 | GET | `/api/students` | `token: 99999` | 401 |
| 6 | GET | `/api/students/10` | `token: 12345` | 200 |
| 7 | GET | `/api/students/10` | `token: 99999` | 401 |
| 8 | PUT | `/api/students/10` | `token: 12345` | 200 |
| 9 | PUT | `/api/students/10` | `token: 99999` | 401 |
| 10 | PATCH | `/api/students/10` | `token: 12345` | 200 |
| 11 | PATCH | `/api/students/10` | `token: 99999` | 401 |
| 12 | DELETE | `/api/students/10` | `token: 12345` | 200 |
| 13 | DELETE | `/api/students/10` | `token: 99999` | 401 |
| 14 | GET | `/api/students` | none | 401 |

---

# 27. Authorization Testing With USER Role

The current authentication code assigns `ADMIN` after the only valid token `12345`, so the simplest way to demonstrate authorization failure is to temporarily modify the authentication code to support a second token.

For learning, you can use:

```text
user-token -> USER
admin-token -> ADMIN
```

Example logic:

```java
if (token.equals("user-token")) {
    request.setAttribute("role", "USER");
}
else if (token.equals("admin-token")) {
    request.setAttribute("role", "ADMIN");
}
else {
    // invalid token -> 401
}
```

Then test the following.

---

# 28. USER Role - GET Allowed

Request:

```http
GET http://localhost:8080/api/students
```

Header:

```http
token: user-token
```

Expected:

```text
200 OK
```

Reason:

```text
GET is allowed for USER
```

Flow:

```text
Request
  |
  v
Authentication
  |
  | role = USER
  v
Authorization
  |
  | GET allowed
  v
Timing
  |
  v
Controller
```

---

# 29. USER Role - POST Forbidden

Request:

```http
POST http://localhost:8080/api/students
```

Header:

```http
token: user-token
```

Expected:

```text
403 Forbidden
```

Body:

```json
{
    "success": false,
    "message": "Access denied. ADMIN role required."
}
```

Important:

```text
Authentication succeeds.
Authorization fails.
Controller does not execute.
```

Flow:

```text
Request
  |
  v
Authentication -> SUCCESS
  |
  | role = USER
  v
Authorization -> FAIL
  |
  v
403 Forbidden
  |
  v
STOP
```

---

# 30. ADMIN Role - POST Allowed

Request:

```http
POST http://localhost:8080/api/students
```

Header:

```http
token: admin-token
```

Expected:

```text
200 OK
```

Flow:

```text
Authentication -> ADMIN
        |
        v
Authorization -> ALLOWED
        |
        v
Timing
        |
        v
Controller
```

---

# 31. Recommended Console Verification

For a valid request, the console should show an order similar to:

```text
Incoming request......
HTTP Method: GET
Request URI: /api/students
Client IP: 127.0.0.1
Token Header: 12345
Authentication checking...
Token: 12345
Authentication successful
Authorization checking...
Role: ADMIN
HTTP Method: GET
Authorization successful
Timer started...
controller called
Total Request Time: 10 ms
Response Status: 200
```

The exact text depends on the current implementation, but the important ordering is:

```text
Logging
Authentication
Authorization
Timing
Controller
Response
```

---

# 32. Invalid Token Console Flow

Example request:

```http
GET /api/students
```

with:

```http
token: 99999
```

Expected conceptual flow:

```text
Incoming request......
Authentication checking...
Token: 99999
Authentication failed
```

Then:

```text
401 Unauthorized
```

You should NOT continue to the successful authorization/controller flow.

---

# 33. Authorization Failure Console Flow

With a `USER` token:

```text
Incoming request......
Authentication checking...
Token valid
Authentication successful
Authorization checking...
Role: USER
HTTP Method: DELETE
```

Then:

```text
403 Forbidden
```

The controller must not execute.

---

# 34. Verify That the Controller Is Actually Blocked

The easiest proof is the controller print statement.

For example:

```java
System.out.println("controller called");
```

### Valid request

You should see:

```text
controller called
```

### Invalid token

You should NOT see:

```text
controller called
```

### Authenticated USER attempting ADMIN operation

You should NOT see:

```text
controller called
```

This is the practical proof that `return false` from `preHandle()` stops the request before the controller.

---

# 35. Why `return false` Is Important

In an interceptor's `preHandle()`:

```java
return true;
```

means:

```text
Continue request processing
```

while:

```java
return false;
```

means:

```text
Stop normal handler execution
```

Authentication example:

```java
if (invalidToken) {
    response.setStatus(401);
    return false;
}
```

Authorization example:

```java
if (!allowed) {
    response.setStatus(403);
    return false;
}
```

---

# 36. Suggested Postman Collection Order

Run the tests in this order:

```text
01 - POST valid token
02 - POST invalid token
03 - POST missing token
04 - GET all valid token
05 - GET all invalid token
06 - GET by ID valid token
07 - GET by ID invalid token
08 - PUT valid token
09 - PUT invalid token
10 - PATCH valid token
11 - PATCH invalid token
12 - DELETE valid token
13 - DELETE invalid token
14 - Timing header check
15 - Authentication header check
16 - USER role authorization test (after adding USER token)
17 - ADMIN role authorization test
```

This order makes debugging easier because authentication is verified before authorization.

---

# 37. Final Expected Behaviour

## Case A - Valid + Authorized

```text
HTTP request
    ↓
Logging ✅
    ↓
Authentication ✅
    ↓
Authorization ✅
    ↓
Timing ✅
    ↓
Controller ✅
    ↓
200 OK
```

## Case B - Invalid Token

```text
HTTP request
    ↓
Logging ✅
    ↓
Authentication ❌
    ↓
401 Unauthorized
    ↓
STOP
```

## Case C - Valid Token But Insufficient Permission

```text
HTTP request
    ↓
Logging ✅
    ↓
Authentication ✅
    ↓
Authorization ❌
    ↓
403 Forbidden
    ↓
STOP
```

---

# 38. Quick Cheat Sheet

```text
Authentication = Who are you?
Authorization  = What are you allowed to do?

401 = Authentication failed
403 = Authentication succeeded, but permission denied

request.getHeader()       -> read request header
response.setHeader()      -> set response header
request.setAttribute()   -> store internal request data
request.getAttribute()   -> read internal request data

preHandle() return true   -> continue
preHandle() return false  -> stop controller execution
```

---

# 39. Important Note About Production Usage

This interceptor project is excellent for learning how the Spring MVC interceptor chain works.

However, for production authentication and authorization, avoid hard-coded tokens and manually managed roles. A real application normally uses:

```text
Spring Security
JWT / OAuth2
Role/Authority based access control
Secure password/token handling
Centralized exception handling
```

The current project should be treated as a conceptual and hands-on interceptor demonstration.
