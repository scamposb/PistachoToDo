var app = angular.module('load',[]);

var tableRow;

(function(){
    app.controller('tableRowController', [ '$http', function($http){
        tableRow = this;
        tableRow.items = [ ];

        $http({ method: 'GET', url: 'http://localhost:8080/PistachoToDo/' }).success(function(data){
            tableRow.items = data.toDoList;
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
                tableRow.items.push(data);
            });

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
        $scope.deleteRow = function(id, index){

            $http.delete('http://localhost:8080/PistachoToDo/task/'+id).success(function(data){
                console.log("Deleted task");
            });

            document.getElementById("toDoTable").deleteRow(document.getElementById("row"+index).rowIndex);
        }
    }]);
})();
