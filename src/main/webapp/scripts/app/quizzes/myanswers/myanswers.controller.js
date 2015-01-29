'use strict';

angular.module('togafquizzApp')
    .controller('MyAnswersController', function ($scope) {
    	$scope.quizzs = [
    	                 {libelle: "quizz 1", dureeEnMinutes: "10"},
    	                 {libelle: "quizz 2", dureeEnMinutes: "15"},
    	                 {libelle: "quizz 3", dureeEnMinutes: "45"}
    	                ];
    });
