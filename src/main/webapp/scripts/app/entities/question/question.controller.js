'use strict';

angular.module('togafquizzApp')
    .controller('QuestionController', function ($scope, Question, Quizz, Reponse) {
        $scope.questions = [];
        $scope.quizzs = Quizz.query();
        $scope.reponses = Reponse.query();
        $scope.loadAll = function() {
            Question.query(function(result) {
               $scope.questions = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Question.save($scope.question,
                function () {
                    $scope.loadAll();
                    $('#saveQuestionModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.question = Question.get({id: id});
            $('#saveQuestionModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.question = Question.get({id: id});
            $('#deleteQuestionConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Question.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteQuestionConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.question = {libelle: null, scenario: null, id: null};
        };
    });
