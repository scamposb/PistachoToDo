function submitNewRow() {
	
	// Get new values from inputs
	var field1 = document.getElementById("taskInput").value;
	var field2 = document.getElementById("contextInput").value;
	var field3 = document.getElementById("projectInput").value;
	var field4 = document.getElementById("priorityInput").value;

	// Check for wrong inputs
	while (field1 === "") {
		field1 = prompt(" - Task name field must not be empty! \n\n Please, set this task a name");
	}
	if(field2 === ""){
		field2 === " ";
	}
	if(field3 === ""){
		field3 === " ";
	}
	if (isNaN(field4) || field4 === "") {
		alert("Warning!\n - Priority field value is not a number! \n > Setting priority to default(0)");
		field4 = 0;
	}

	//Submit to server like a ninja!
	var xmlhttp = new XMLHttpRequest();
	var parameters = "task="+field1.toString()+"&context="+field2.toString()+"&project="+field3.toString()+"&priority="+field4.toString();
	xmlhttp.open("POST","/submitTask?"+parameters,true);
	//xmlhttp.send("task="+field1.toString()+"&context="+field2.toString()+"&project="+field3.toString()+"&priority="+field4.toString());
	xmlhttp.send();
	// Retrieve table from document
	var table = document.getElementById("toDoTable");

	// Add new row to table
	var row = table.insertRow(table.rows.length);
	row.id = "row"+(table.rows.length-1);
	row.className = "myTableContent";

	// Add cells to new row
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);

	// Place text from inputs in row cells
	cell1.innerHTML = field1.toString();
	cell2.innerHTML = field2.toString();
	cell3.innerHTML = field3.toString();
	cell4.innerHTML = field4.toString();

	// table.rows.hide().fadeIn("slow");

	// Clear text inputs
	$(document.getElementById("newRowForm"))[0].reset();

	// Fade out addNewRowForm
	$(document.getElementById("addNewRowForm")).fadeOut(function() {
		// Fade in addNewRowButton
		$(document.getElementById("addNewRowButton")).fadeIn();
	});
};