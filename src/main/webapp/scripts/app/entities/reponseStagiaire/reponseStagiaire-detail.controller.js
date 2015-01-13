'use strict';

angular.module('togafquizzApp')
    .controller('ReponseStagiaireDetailController', function ($scope, $stateParams, ReponseStagiaire, Resultat, Question, Reponse) {
        $scope.reponseStagiaire = {};
        $scope.load = function (id) {
            ReponseStagiaire.get({id: id}, function(result) {
              $scope.reponseStagiaire = result;
            });
        };
        $scope.load($stateParams.id);
    });
