/**
 * Created by pc on 3/5/16.
 */

var myapp = angular.module('myapp', []);

myapp.controller("Hello",function($scope, $http){

    /*$http.get('https://api.steampowered.com/IEconDOTA2_570/GetHeroes/V001/?key=E5A0A27C28A1F857062866825AF10520');*/

    var url = "/dota2/heroes";
    $http({
        method: 'GET',
        responseType: "json",
        url: url,
    })
    .success(function(data) {

        data.forEach(function(hero){

            hero.pic = "http://cdn.dota2.com/apps/dota2/images/heroes/"+hero.name+"_sb.png";
        })

        $scope.heroes = data;
    });

});