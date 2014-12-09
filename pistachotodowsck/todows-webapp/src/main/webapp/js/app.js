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
            $urlRouterProvider.otherwise('inicio')
        })

    .factory('todos', [function(){
        var o = {
            todos: [
                /*{
                    _id: 1,
                    task: "task1",
                    context: "context1",
                    project: "project1",
                    priority: 1
                },
                {
                    _id: 2,
                    task: "task2",
                    context: "context2",
                    project: "project2",
                    priority: 2
                },
                {
                    _id: 3,
                    task: "task3",
                    context: "context3",
                    project: "project3",
                    priority: 3
                },
                {
                    _id: 4,
                    task: "derp",
                    context: "contrest",
                    project: "wtf",
                    priority: 6
                }*/
            ]
        };
        return o;
    }])

    .controller('MainCtrl', [ '$scope', 'todos', function($scope, todos){
        //$scope.todos = todos.todos;
        $scope.todos = [ ];

        //websocket uris
        var uri_get = "ws://localhost:8025/websockets/get";
        var uri_post = "ws://localhost:8026/websockets/post";
        var uri_delete = "ws://localhost:8027/websockets/delete";

        //websocket init
        var ws_get = new WebSocket(uri_get);
        var ws_post = new WebSocket(uri_post);
        var ws_delete = new WebSocket(uri_delete);

        /* GET WEBSOCKET */

        ws_get.onopen = function(){
            console.log("GET SOCKET OPENED");
            ws_get.send("list");
        };

        ws_get.onerror = function(){
            ws_get.close();
            console.log("WS_GET ERROR");
        };

        ws_get.onmessage = function(event){
            console.log("GOT TASK LIST");
            var taskList = JSON.parse(event.data);
            console.log(taskList);

            $scope.$apply(function(){
                $scope.todos = taskList;
            });
        };

        ws_get.onclose = function(){
            console.log("GET SOCKET CLOSED");
        };

        /* POST WEBSOCKET */

        ws_post.onopen = function(){
            console.log("POST SOCKET OPENED");
        };

        ws_post.onerror = function(){
            ws_post.close();
            console.log("WS_POST ERROR");
        };

        ws_post.onmessage = function(event){
            console.log("GOT NEW TASK");
            var newTask = JSON.parse(event.data);
            console.log(newTask);

            $scope.$apply(function(){
                $scope.todos.push(newTask);
            });
        };

        ws_post.onclose = function(){
            console.log("POST SOCKET CLOSED");
        };

        /* DELETE WEBSOCKET */

        ws_delete.onopen = function(){
            console.log("DELETE SOCKET OPENED");
        };

        ws_delete.onerror = function(){
            ws_delete.close();
            console.log("WS_DELETE ERROR");
        };

        ws_delete.onmessage = function(event){

        };

        ws_delete.onclose = function(){
            console.log("DELETE SOCKET CLOSED");
        };

        $scope.createToDo = function(){
            var newTask = {
                task: $scope.taskName,
                context: $scope.taskContext,
                project: $scope.taskProject,
                priority: $scope.taskPriority
            };
            //console.log("CLIENT SIDE WANTS TO PUSH: ");
            console.log(newTask);
            //todos.todos.push(newTask);

            //websocket post
            ws_post.send(JSON.stringify(newTask));
        };

        $scope.removeToDo = function(index){
            //websocket delete
            ws_delete.send(index);

            for(var i = 0; i < $scope.todos.length; i++){
                if($scope.todos[i]._id === index){
                    $scope.todos.splice(i,1);
                }
            }
        };
    }]);