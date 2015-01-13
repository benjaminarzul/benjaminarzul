'use strict';

angular.module('togafquizzApp')
    .controller('SessionDetailController', function ($scope, $stateParams, Session) {
        $scope.session = {};
        $scope.load = function (id) {
            Session.get({id: id}, function(result) {
              $scope.session = result;
            });
        };
        $scope.load($stateParams.id);
    });
