'use strict';

angular.module('togafquizzApp')
    .controller('MyAnswersController', function ($scope, Quizz) {
    	$scope.quizzs = [];
        $scope.loadAll = function() {
            Quizz.query(function(result) {
               $scope.quizzs = result;
            });
        };

        $scope.loadAll();
    });
