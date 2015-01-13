'use strict';

angular.module('togafquizzApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
