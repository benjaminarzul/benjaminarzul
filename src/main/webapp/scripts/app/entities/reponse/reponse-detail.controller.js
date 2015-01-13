'use strict';

angular.module('togafquizzApp')
    .controller('ReponseDetailController', function ($scope, $stateParams, Reponse, Question) {
        $scope.reponse = {};
        $scope.load = function (id) {
            Reponse.get({id: id}, function(result) {
              $scope.reponse = result;
            });
        };
        $scope.load($stateParams.id);
    });
