angular.module('starter', ['ui.router'])

    .config(function($stateProvider, $urlRouterProvider){
            $stateProvider

                .state('starter', {
                    url: "/",
                    templateUrl: "index.html"
                })

                .state('inicio', {
                    url: "/inicio",
                    templateUrl: "templates/inicio.html",
                    controller: "MainCtrl"
                });
            $urlRouterProvider.otherwise('inicio');
        })

    .controller('MainCtrl', [ '$scope', function($scope){
        $scope.todos = [ ];

        //websocket uris
        var uri_ws = "ws://localhost:8025/websockets/PistachoToDo";

        //websocket init
        var ws = new WebSocket(uri_ws);

        /* WEBSOCKET HANDLING */

        ws.onopen = function(){
            console.log("SOCKET OPENED");
            ws.send("{code: 2}");
        };

        ws.onerror = function(){
            ws.close();
            console.log("SOCKET ERROR");
        };

        ws.onmessage = function(event){
            var msg = JSON.parse(event.data);
            switch(msg.code){
                case 6:
                    console.log("GOT TASK LIST");
                    var taskList = JSON.parse(msg.data);
                    console.log(taskList);

                    $scope.$apply(function(){
                        $scope.todos = taskList;
                    });
                    break;
                case 7:
                    console.log("GOT NEW TASK");
                    var newTask = JSON.parse(msg.data);
                    console.log(newTask);

                    $scope.$apply(function(){
                        $scope.todos.push(newTask);
                    });
                    break;
                default:
                    console.log(msg.message);
                    break;
            }
        };

        ws.onclose = function(){
            console.log("SOCKET CLOSED");
        };

        $scope.createToDo = function(){
            var newTask = {
                task: $scope.taskName,
                context: $scope.taskContext,
                project: $scope.taskProject,
                priority: $scope.taskPriority
            };
            console.log(newTask);

            //websocket post
            ws.send("{code: 3, task: "+JSON.stringify(newTask)+"}");
        };

        $scope.removeToDo = function(index){
            //websocket delete
            ws.send("{code: 4, index: "+index+"}");

            for(var i = 0; i < $scope.todos.length; i++){
                if($scope.todos[i]._id === index){
                    $scope.todos.splice(i,1);
                }
            }
        };
    }]);