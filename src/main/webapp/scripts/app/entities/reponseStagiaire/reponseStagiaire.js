'use strict';

angular.module('togafquizzApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('reponseStagiaire', {
                parent: 'entity',
                url: '/reponseStagiaire',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/reponseStagiaire/reponseStagiaires.html',
                        controller: 'ReponseStagiaireController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('reponseStagiaire');
                        return $translate.refresh();
                    }]
                }
            })
            .state('reponseStagiaireDetail', {
                parent: 'entity',
                url: '/reponseStagiaire/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/reponseStagiaire/reponseStagiaire-detail.html',
                        controller: 'ReponseStagiaireDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('reponseStagiaire');
                        return $translate.refresh();
                    }]
                }
            });
    });
