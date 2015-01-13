'use strict';

angular.module('togafquizzApp')
    .controller('ReponseController', function ($scope, Reponse, Question) {
        $scope.reponses = [];
        $scope.questions = Question.query();
        $scope.loadAll = function() {
            Reponse.query(function(result) {
               $scope.reponses = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Reponse.save($scope.reponse,
                function () {
                    $scope.loadAll();
                    $('#saveReponseModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.reponse = Reponse.get({id: id});
            $('#saveReponseModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.reponse = Reponse.get({id: id});
            $('#deleteReponseConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Reponse.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteReponseConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.reponse = {libelle: null, explication: null, id: null};
        };
    });
