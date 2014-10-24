<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="pistachotodo.ToDoList"
    import="pistachotodo.ToDoTask"
    import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PistachoToDo</title>
<link rel="icon" type="image/png" href="<c:url value="img/favicon.png" />" />

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="<c:url value="/css/bootstrap.min.css" />" >
<link type="text/css" rel="stylesheet" href="<c:url value="/css/pistachocss.css" />" >

</head>
<body>

	<div class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-left">
					<li class="active"><a href="./index.html">ToDo</a></li>
					<!-- <li><a href="./about.html">About</a></li> -->
					<li><a href="http:\\www.pistachosoft.com">Back to
							PistachoSoft!</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="row myPanel">
			<div class="jumbotron">
				<h3>Welcome aboard PistachoToDo! 2</h3>
				<br>
				<!-- Show all TODO tasks -->
				<!-- <table class="table">
					<tbody>
						
					</tbody>
				</table>-->
				<div class="line-separator"></div>
				<table class="table" id="toDoTable">
					<tbody>
						<tr>
							<td><h3>Task</h3></td>
							<td><h3>Context</h3></td>
							<td><h3>Project</h3></td>
							<td><h3>Priority</h3></td>
						</tr>
						<!-- Repeat the rows below with JSP -->
						<%
							ToDoList taskList = (ToDoList) request.getAttribute("taskList");
							int i = 1;
							for(ToDoTask t : taskList.getToDoList()){
						%>
						<tr class="myTableContent" id="row<%=i%>">
							<td><%=t.getTask().trim()%></td>
							<%
								if(t.getContext()==null){
							%>
							<td></td>
							<%
								}else{
							%>
							<td><%=t.getContext().trim()%></td>
							<%
								}
							%>
							<%
								if(t.getProject()==null){
							%>
							<td></td>
							<%
								}else{
							%>
							<td><%=t.getProject().trim()%></td>
							<%
								}
							%>
							<td><%=t.getPriority()%></td>
						</tr>
						<%
							i++;
							}
						%>
						<!-- <tr id="myTableContent">
							<td>Create GUI</td>
							<td>Web Engineering</td>
							<td>Lab 0</td>
							<td>10</td>
						</tr>
						<tr id="myTableContent">
							<td>Finish lab 0</td>
							<td>Web Engineering</td>
							<td>Lab 0</td>
							<td>0</td>
						</tr>-->
					</tbody>
				</table>
				<div class="line-separator"></div>
				<br>
				<!-- Add new TODO task -->
				<input id="addNewRowButton" type="button" onClick="addNewRow()" value="Add new ToDo task!"
					class="btn btn-default" style="display: inline" />
				<!-- Hidden table row -->
				<div id="addNewRowForm" style="display: none">
					<p>Add your new ToDo!</p>
					<form method="POST" action="servlet-de-insercion" id="newRowForm"
						class="form-inline">
						<table class="table">
							<tbody>
								<tr>
									<td><input type="text" id="taskInput" class="form-control" placeholder="Task name" /></td>
									<td><input type="text" id="contextInput" class="form-control" placeholder="Context" /></td>
									<td><input type="text" id="projectInput" class="form-control" placeholder="Project" /></td>
									<td><input type="text" id="priorityInput" class="form-control" placeholder="Priority" /></td>
								</tr>
							</tbody>
						</table>
						<input class="btn btn-default" type="button" onClick="submitNewRow()" value="ToDo!">
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- js scripts -->
	<!-- addNewRow script -->
	<script type="text/javascript" src="<c:url value="/js/newRow.js" />" ></script>
	<!-- submitNewRow script -->
	<script type="text/javascript" src="<c:url value="/js/submitNewRow.js" />" ></script>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script type="text/javascript" src="<c:url value="/js/jquery.min.js" />" ></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script type="text/javascript" src="<c:url value="/js/bootstrap.min.js" />" ></script>
</body>
</html>