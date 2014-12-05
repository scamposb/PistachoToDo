# Hello Websockets Client 
This project contains a demonstration of a  [WebSockets](https://tyrus.java.net/) client. 

Just write in your terminal ``gradle client```.

# Websocket message syntax

This application uses a JSON object as the message the client sends to the server.

There are 5 types of requests possible (bold implies mandatory):

| Operation | Syntax |
| ------------- | ------------- |
| Start | **{code: 0** [, task: null] [, index: null]**}** |
| Stop | **{code: 1** [, task: null] [, index: null]**}** |
| GET | **{code: 2** [, task: null] [, index: null]**}** |
| POST {task} | **{code: 3, task: {task: "taskName"** [, context: "foo"] [, project: "bar"]**, priority:0}** [, index: null]**}** |
| DELETE {id} | **{code: 4** [, task: null]**, index: 3}** |

Also, there are X types of response possible:

| Header | Syntax |
| ------------- | ------------- |
| BAD SYNTAX | {code: 0, message: "The request is unacceptable, check syntax"} |
| BAD CODE | {code: 1, message: "Requested operation unavailable"} |
| BAD TASK | {code: 2, message: "Task syntax wrong"} |
| BAD INDEX | {code: 3, message: "Task index wrong"} |
| CORRECT | {code: 4, message: "Operation successful"} |
| HELLO | {code: 5, message: "Hello world!"} |
