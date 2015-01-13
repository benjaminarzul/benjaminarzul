'use strict';

angular.module('togafquizzApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('resultat', {
                parent: 'entity',
                url: '/resultat',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/resultat/resultats.html',
                        controller: 'ResultatController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('resultat');
                        return $translate.refresh();
                    }]
                }
            })
            .state('resultatDetail', {
                parent: 'entity',
                url: '/resultat/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/resultat/resultat-detail.html',
                        controller: 'ResultatDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('resultat');
                        return $translate.refresh();
                    }]
                }
            });
    });
