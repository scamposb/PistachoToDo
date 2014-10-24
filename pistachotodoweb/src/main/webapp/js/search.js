function openSearch() {
	$(document.getElementById('searchButton')).fadeOut(function() {
		$(document.getElementById('searchRowForm')).fadeIn();
	});
}

function findTaskInTable() {
	var searchValue = document.getElementById("taskSearchInput").value;

	findInTable(searchValue,0);
}

function findContextInTable() {
	var searchValue = document.getElementById("contextSearchInput").value;

	findInTable(searchValue,1);
}

function findProjectInTable() {
	var searchValue = document.getElementById("projectSearchInput").value;

	findInTable(searchValue,2);
}

function findPriorityInTable() {
	var searchValue = document.getElementById("prioritySearchInput").value;

	if(isNaN(searchValue.toString())){
		//error
	}else{
		findInTable(searchValue,3);
	}
}

function findInTable(searchValue, j){
	var table = document.getElementById("toDoTable");
	if (searchValue.toString() === "") {
		for ( var i = 1; i < table.rows.length; i++) {
			var thisRow = document.getElementById("row" + i);
			thisRow.style.display = "";
		}
	} else {
		for ( var i = 1; i < table.rows.length; i++) {
			var thisRow = document.getElementById("row" + i);
			var firstCell = thisRow.cells[j];
			var content = firstCell.innerHTML.toString();
			if (content.toUpperCase().indexOf(searchValue.toUpperCase()) > -1) {
				thisRow.style.display = "";
			} else {
				thisRow.style.display = "none";
			}
		}
	}
}