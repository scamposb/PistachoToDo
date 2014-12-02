var app = angular.module('load',[]);

var nextId = 1;

(function(){
    app.controller('tableRowController', [ '$http', function($http){
        var tableRow = this;
        tableRow.items = [ ];

        $http({ method: 'GET', url: 'http://localhost:8080/PistachoToDo/' }).success(function(data){
            tableRow.items = data.toDoList;
            nextId = data.toDoList.length +1;
        });
    }]);
})();

(function(){
    app.controller('addRowController', [ '$scope', '$http', function($scope, $http){
        $scope.submitNewRow = function(){

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

            var task = {
                "task" : field1,
                "context" : field2,
                "project" : field3,
                "priority" : field4
            }

            $http.post('http://localhost:8080/PistachoToDo/',JSON.stringify(task)).success(function(data){
                console.log("Created new task");
            });

            // Retrieve table from document
            var table = document.getElementById("toDoTable");

            // Add new row to table
            var row = table.insertRow(table.rows.length);
//            row.id = "row"+(table.rows.length-2);
            row.id = "row"+nextId;
            row.className = "myTableContent";

            // Add cells to new row
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
	    var cell5 = row.insertCell(4);

            // Place text from inputs in row cells
            cell1.innerHTML = field1.toString();
            cell2.innerHTML = field2.toString();
            cell3.innerHTML = field3.toString();
            cell4.innerHTML = field4.toString();

	    //Place delete button
	    cell5.innerHTML = '<td ng-controller="delRowController"><button class="btn btn-danger" ng-click="deleteRow('+nextId+')">X</button></td>'

	    //Increase nextid
            nextId++;

            // table.rows.hide().fadeIn("slow");

            // Clear text inputs
            $(document.getElementById("newRowForm"))[0].reset();

            // Fade out addNewRowForm
            $(document.getElementById("addNewRowForm")).fadeOut(function() {
                // Fade in addNewRowButton
                $(document.getElementById("addNewRowButton")).fadeIn();
            });
        }
    }]);
})();

(function(){
    app.controller("delRowController", [ '$scope', '$http', function($scope, $http){
        $scope.deleteRow = function(id){

            $http.delete('http://localhost:8080/PistachoToDo/task/'+id).success(function(data){
                console.log("Deleted task");
            });

            document.getElementById("row"+id).style.display = "none";
        }
    }]);
})();
