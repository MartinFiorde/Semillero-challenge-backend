# Semillero-challenge-backend

Table of contents
=================

  * [Features](#features)
  * [Warning](#warning)
  * [Getting started](#getting-started)
  * [Testing](#testing)
  
## Features
- [x] Users registration, with first user getting admin role, and all others student role
- [x] Admin role: full CRUD access to all users and courses information
- [x] Teacher role: full CRUD access to all courses information
- [x] Student role: only access to take courses
- [x] Any role: View and edit personal information and password.
- [x] Search user by name for admins
- [x] Asign/ remove teachers from courses for admins and teachers
- [x] Deactivate/ reactivate users and courses for admins
- [x] Full Login/ Logout functionality
- [x] Full access control functionality

## Warning
To propertly run the proyect you need to have Apache Maven 3.8.4(or higher), java/JDK 1.8 (specifically) and MySQL 8.0.27 (or higher) instaled and propertly configured in your computer and IDE.

## Getting started
### Step 1: 
Download the repository and open it with your IDE.

### Step 2: 
In file "...\src\main\resources\application.properties" put your credentials to conect with your local database.

### Step 3: 
CREATE SCHEMA with name semillero_challenge_backend in your MySQL database.

### Step 4: 
Execute a clean and build for the first time. This will also run all unit tests.

### Step 5: 
Run the proyect and access http://localhost:8080/ to start using the aplication.

## Testing
Execute a clean and build as needed.