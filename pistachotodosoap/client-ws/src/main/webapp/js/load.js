var app = angular.module('load',[]);

(function(){
    app.controller('tableRowController', [ '$http', function($http){
        var tableRow = this;
        tableRow.items = [ ];

        $http({ method: 'GET', url: '/loadServlet' }).success(function(data){
            tableRow.items = data.toDoList;
            console.log(data.toDoList);
        });
    }]);
})();
