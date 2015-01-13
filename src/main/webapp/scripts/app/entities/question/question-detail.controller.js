'use strict';

angular.module('togafquizzApp')
    .controller('QuestionDetailController', function ($scope, $stateParams, Question, Quizz, Reponse) {
        $scope.question = {};
        $scope.load = function (id) {
            Question.get({id: id}, function(result) {
              $scope.question = result;
            });
        };
        $scope.load($stateParams.id);
    });
