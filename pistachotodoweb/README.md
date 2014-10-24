# PistachoToDo Web
Implementation of PistachoToDo CMD app with web interface.

GET and POST petitions are handled by javascript:

* __load.js__
This script contains the function ```getTasks()```. This function is called upon loading index.html, making a GET petition to the servlet ```/loadServlet``` which checks for a JSON file with the list of TODO tasks, if it exists, the servlet forwards its response into a new JSP page with the table containing the rows of the tasks with its values. Then the script parses the JSP document, gets the rows and inserts them into the table.

* __submitNewRow.js__
This script contains the function ```submitNewRow()``` and is called upon fulfilling the new task form and clicking the submit button, takes the input values and makes a POST petition to the servlet ```/submitTask``` passing the input values as parameters. The servlet looks for a JSON file, if it does not exists then it's created, and the new task is appended. Instead of reloading the page, this scripts adds the row itself only after the petition is processed. This creates a "virtual" value of the JSON file content, allowing the user to have his result as soon as possible.

Search of content is handled by javascript too:

* __search.js__
This scripts takes values from search inputs and dynamically hides rows which don't match input.
More than one field can be used to filter rows.

This app is to be sent to a [Jetty](http://www.eclipse.org/jetty/) server with ```gradle jettyEclipseRun```. The web app is located at [http://localhost:8080/](http://localhost:8080/).