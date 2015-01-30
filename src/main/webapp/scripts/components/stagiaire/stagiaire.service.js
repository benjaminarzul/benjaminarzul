'use strict';

'use strict';

angular.module('togafquizzApp')
    .factory('Stagiaire', function ($http) {
        return {
        	findTraineeResults: function (login) {
                return $http.get('api/stagiaire/resultats/' + login).then(function (response) {
                    return angular.fromJson(response.data);
                });
            }
        };
    });