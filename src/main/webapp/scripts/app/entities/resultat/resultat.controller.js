'use strict';

angular.module('togafquizzApp')
    .controller('ResultatController', function ($scope, Resultat, User, Quizz) {
        $scope.resultats = [];
        $scope.users = User.query();
        $scope.quizzs = Quizz.query();
        $scope.loadAll = function() {
            Resultat.query(function(result) {
               $scope.resultats = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Resultat.save($scope.resultat,
                function () {
                    $scope.loadAll();
                    $('#saveResultatModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.resultat = Resultat.get({id: id});
            $('#saveResultatModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.resultat = Resultat.get({id: id});
            $('#deleteResultatConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Resultat.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteResultatConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.resultat = {pourcentageReussite: null, id: null};
        };
    });
