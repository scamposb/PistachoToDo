# Hello Websockets Server 
This project contains a demonstration of a web service build with [WebSockets](https://tyrus.java.net/). 

Just write in your terminal ``gradle server```.

# Websocket message syntax

This application uses a JSON object as the message the client sends to the server.

There are 5 types of requests possible (bold implies mandatory):

| Operation | Syntax |
| ------------- | ------------- |
| Start | _{code: 0_ [, task: null] [, index: null]_}_ |
| Stop | _{code: 1_ [, task: null] [, index: null]_}_ |
| GET | _{code: 2_ [, task: null] [, index: null]_}_ |
| POST {task} | _{code: 3, task: {task: "taskName"_ [, context: "foo"] [, project: "bar"]_, priority:0}_ [, index: null]_}_ |
| DELETE {id} | _{code: 4_ [, task: null]_, index: 3}_ |

Also, there are X types of response possible:

| Header | Syntax |
| ------------- | ------------- |
| BAD SYNTAX | {code: 0, message: "The request is unacceptable, check syntax"} |
| BAD CODE | {code: 1, message: "Requested operation unavailable"} |
| BAD TASK | {code: 2, message: "Task syntax wrong"} |
| BAD INDEX | {code: 3, message: "Task index wrong"} |
| CORRECT | {code: 4, message: "Operation successful"} |
| HELLO | {code: 5, message: "Hello world!"} |
