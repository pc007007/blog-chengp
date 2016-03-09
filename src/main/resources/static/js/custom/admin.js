/**
 * Created by pc on 3/7/16.
 */
var myapp = angular.module('myapp', []);

myapp.controller("admin",function($scope, $http){

    var url = "/admin/getusers";
    $http({
        method: 'GET',
        responseType: "json",
        url: url,
    })
        .success(function(data) {

            data.forEach(function(user){
                user.userRole.forEach(function(userrole){
                    userrole.role = userrole.role.substr(5);
                });
                user.flag = user.userRole[1] != null;
            });

            $scope.users = data;
        });

    $scope.deleteBtn = function(username) {
        $http({
            method: 'DELETE',
            responseType: "json",
            url: "/admin/users/"+username,
        });

        $scope.username = username;

    }


});