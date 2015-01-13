'use strict';

angular.module('togafquizzApp')
    .controller('ReponseStagiaireController', function ($scope, ReponseStagiaire, Resultat, Question, Reponse) {
        $scope.reponseStagiaires = [];
        $scope.resultats = Resultat.query();
        $scope.questions = Question.query();
        $scope.reponses = Reponse.query();
        $scope.loadAll = function() {
            ReponseStagiaire.query(function(result) {
               $scope.reponseStagiaires = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            ReponseStagiaire.save($scope.reponseStagiaire,
                function () {
                    $scope.loadAll();
                    $('#saveReponseStagiaireModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.reponseStagiaire = ReponseStagiaire.get({id: id});
            $('#saveReponseStagiaireModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.reponseStagiaire = ReponseStagiaire.get({id: id});
            $('#deleteReponseStagiaireConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            ReponseStagiaire.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteReponseStagiaireConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.reponseStagiaire = {id: null};
        };
    });
