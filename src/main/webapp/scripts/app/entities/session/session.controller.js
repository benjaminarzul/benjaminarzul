'use strict';

angular.module('togafquizzApp')
    .controller('SessionController', function ($scope, Session) {
        $scope.sessions = [];
        $scope.loadAll = function() {
            Session.query(function(result) {
               $scope.sessions = result;
            });
        };
        $scope.loadAll();

        $scope.create = function () {
            Session.save($scope.session,
                function () {
                    $scope.loadAll();
                    $('#saveSessionModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.session = Session.get({id: id});
            $('#saveSessionModal').modal('show');
        };

        $scope.delete = function (id) {
            $scope.session = Session.get({id: id});
            $('#deleteSessionConfirmation').modal('show');
        };

        $scope.confirmDelete = function (id) {
            Session.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteSessionConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.clear = function () {
            $scope.session = {debut: null, fin: null, id: null};
        };
    });
