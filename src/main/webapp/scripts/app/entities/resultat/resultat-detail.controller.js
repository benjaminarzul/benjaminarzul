'use strict';

angular.module('togafquizzApp')
    .controller('ResultatDetailController', function ($scope, $stateParams, Resultat, User, Quizz) {
        $scope.resultat = {};
        $scope.load = function (id) {
            Resultat.get({id: id}, function(result) {
              $scope.resultat = result;
            });
        };
        $scope.load($stateParams.id);
    });
