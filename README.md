# Project: Spring Social Media Blog API

## Background 

This project will be a backend for a hypothetical social media app, where we must manage our users’ accounts as well as any messages that they submit to the application. However, the functionality for this project will leverage a popular web application framework for Java known as Spring. The Spring framework allows for automatic injection and configuration of many features, including data persitence, endpoints and conventional data manipulation logic (CRUD operations).

In our hypothetical micro-blogging or messaging app, any user should be able to see all of the messages posted to the site, or they can see the messages posted by a particular user. In either case, we require a backend which is able to deliver the data needed to display this information as well as process actions like logins, registrations, message creations, message updates, and message deletions.

## User Stories

As a user, I should be able to create a new Account on the endpoint POST localhost:8080/register. The body will contain a representation of a JSON Account, but will not contain an accountId.

As a user, I should be able to verify my login on the endpoint POST localhost:8080/login. The request body will contain a JSON representation of an Account.

As a user, I should be able to submit a new post on the endpoint POST localhost:8080/messages. The request body will contain a JSON representation of a message, which should be persisted to the database, but will not contain a messageId.

As a user, I should be able to submit a GET request on the endpoint GET localhost:8080/messages.

As a user, I should be able to submit a GET request on the endpoint GET localhost:8080/messages/{messageId}.

As a User, I should be able to submit a DELETE request on the endpoint DELETE localhost:8080/messages/{messageId}.

As a user, I should be able to submit a PATCH request on the endpoint PATCH localhost:8080/messages/{messageId}. The request body should contain a new messageText values to replace the message identified by messageId. The request body can not be guaranteed to contain any other information.

As a user, I should be able to submit a GET request on the endpoint GET localhost:8080/accounts/{accountId}/messages.
