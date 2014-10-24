# PistachoToDo task list with JSON
This project contains a demonstration of [GSON](https://code.google.com/p/google-gson/). Gson is a Java library that can be used to convert arbitrary Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object.

The following examples are included:
* __AddToDoTask__ (```gradle -q addTask```) tests first it the file ```todo_task_list.json``` exists. If it exists, the example unmarshalls its content into Java objects. Example code expects that the file contains a ```ToDoList``` instance. Then, code asks to the user the details of a ```ToDoTask``` and then the task is added to the task list. Finally, the task list is marshalled into a JSON file with name ```todo_task_list.json```.
* __ListToDoTasks__ (```gradle -q listTasks```) unmarshalls ```todo_task_list.json``` into Java objects and then dump the objects to the console.