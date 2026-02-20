## Overview

DocumentIngester is a fullstack application that allows users to upload documents (PDF/DOCX), extract their content, and perform keyword-based searches through a GraphQL API.

The project is composed of:
- DocumentIngester: A Spring Boot backend with GraphQL
- A PostgreSQL database
- CandidatesWeb: An Angular frontend

## Tech Stack

Backend:
- Java
- Spring Boot
- GraphQL
- PostgreSQL

Frontend:
- Angular
- TypeScript

Tools:
- Postman
- pgAdmin4

## Architectural Decisions

- The backend follows a layered architecture separating controller, service, and repository layers.
- GraphQL was chosen instead of REST to allow flexible queries.
- File content is stored in the database for easier searching.

## DocumentIngester

Application developed in Spring Boot that uses PostgreSQL as its database.

It allows users to upload docx or pdf files and extract their content, storing it in the database along with a unique identifier, file name, and file extension.

It also allows searching through the uploaded files using a search criterion, returning the list of files that contain that criterion.

* Project structure and GraphQL schema:

![estructura](https://github.com/user-attachments/assets/71b2226f-1064-42dd-8ddf-ff6577a3da32)
![graphql](https://github.com/user-attachments/assets/0b419bfb-0634-48e7-a8b6-7fa6e2eea14d)

We clearly separated the logic into packages to keep the environment more organized.

---

## Running the App

> [!IMPORTANT]
> The application runs on port 8080 and the database on port 5432!

To run the application, the PostgreSQL server and the database must be defined. Otherwise, the IDE will not allow us to start it.

We use pgAdmin4 to manage the database.

We must create a server in pgAdmin4 with the name **BDDocumentIngester**.

To register the server, follow these steps:

![server1](https://github.com/user-attachments/assets/fc01ac8e-d713-4c21-8c59-cdd52e45ca61)
![server](https://github.com/user-attachments/assets/3ad810e4-faf9-4262-bed3-eefe57f834ce)

In the password field, enter `"123"`, although this value can be changed in the `application.properties` file in the project.

Once the server is created, create the database named **BDDocumentIngester**:

![creardb](https://github.com/user-attachments/assets/11c65c30-c93c-4938-9aaf-d6233f2a9efa)

> [!NOTE]
> You must have PostgreSQL installed in order to connect the server.

Done! It should now run correctly.

---

### Features with Postman

We provide a Postman collection to test the backend functionalities.

#### - Upload a file

First, import the collection included in the repository.

Then, with the backend running, select the **POST Upload File** request.

Go to **Body > form-data** and select the docx or pdf file you want in the **File** section as shown in the image below.

![guiaPostman](https://github.com/user-attachments/assets/da665d1b-e9ad-466c-83a2-a6c2e0383c9a)

Click **Send** and that’s it! A new file will be registered in the database.

---

#### - Search by keywords

This method is simpler. Just go to the **POST Search In Documents** request, select **Body > raw**, and replace the word `"CRITERION"` with your desired search term.

![guiaPostman2](https://github.com/user-attachments/assets/ea186ade-59c8-4701-9796-31dc8100ea70)

Click **Send**, and it will return the files whose content includes the given criterion.

---

## CandidatesWeb

Application developed in Angular that consumes the APIs provided by DocumentIngester.

It allows viewing all database data in a paginated table and performing searches on documents based on their content.

* Angular project structure:

![angular](https://github.com/user-attachments/assets/4fdfc9ed-d6ee-4fbe-b7c5-ca5140a26653)

We created two new components to handle the new functionalities:

> **ExpandTextComponent**

Used in cases where the file content is very long. It adds a "Read More" option next to the content, which expands when clicked, allowing the full text to be displayed.

> **FilesComponent**

This is where we retrieve all files from the database. In its HTML file, the table is created, and it also includes a pipe that provides the rest of the functionalities.

* Web view:

![Screenshot 2024-10-11 152343](https://github.com/user-attachments/assets/ba0e9675-8add-4034-86a0-993ceff42c4b)

---

## Running the App

> [!IMPORTANT]
> The application runs on port 4200!

To begin, navigate to the project folder:
`..\CandidatesWeb`

Then execute the following commands:

Command to install dependencies:

```
npm install
```

Command to run the application:

```
ng serve -o
```

---

### Features

#### - Filter by content

The application allows filtering documents, returning those whose content matches or contains the text entered in the textbox.

The document table updates automatically.

#### - Paginated Table

The table can display a defined number of items (this can be changed in the code).

It works correctly when updating based on content searches.


  
