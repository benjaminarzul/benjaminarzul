'use strict';

angular.module('togafquizzApp')
    .controller('MyAnswersController', function ($scope, Principal, Quizz, Stagiaire) {
    	// chargemenet des quizz
    	$scope.quizzs = [];
        $scope.loadQuizzes = function() {
            Quizz.query(function(result) {
               $scope.quizzs = result;
            });
        };
        $scope.loadQuizzes();
        
        // chargement des résultats du stagiaire
        $scope.traineeResults = [];
        $scope.loadTraineeResults = function() {
        	Stagiaire.findTraineeResults(Principal.identity().$$state.value.login).then(function (data) {
        		$scope.traineeResults = data;
             });	
        };
        $scope.loadTraineeResults();     	
    });
