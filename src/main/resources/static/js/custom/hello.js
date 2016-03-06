/**
 * Created by pc on 3/5/16.
 */

var myapp = angular.module('myapp', []);

myapp.controller("Hello",function($scope, $http){

    /*$http.get('https://api.steampowered.com/IEconDOTA2_570/GetHeroes/V001/?key=E5A0A27C28A1F857062866825AF10520');*/

    var url = "/IEconDOTA2_570/GetHeroes/V001/?key=E5A0A27C28A1F857062866825AF10520";
    $http({
        method: 'GET',
        responseType: "json",
        url: url,
    }).
    success(function(data) {

        data.result.heroes.forEach(function(entry){
            entry.name = entry.name.substring(14);
        });
        $scope.greeting = data;
    });

});