'use strict';

angular.module('togafquizzApp')
    .controller('QuizzDetailController', function ($scope, $stateParams, Quizz) {
        $scope.quizz = {};
        $scope.load = function (id) {
            Quizz.get({id: id}, function(result) {
              $scope.quizz = result;
            });
        };
        $scope.load($stateParams.id);
    });
