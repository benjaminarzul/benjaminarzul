'use strict';

angular.module('togafquizzApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('reponse', {
                parent: 'entity',
                url: '/reponse',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/reponse/reponses.html',
                        controller: 'ReponseController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('reponse');
                        return $translate.refresh();
                    }]
                }
            })
            .state('reponseDetail', {
                parent: 'entity',
                url: '/reponse/:id',
                data: {
                    roles: ['ROLE_USER']
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/reponse/reponse-detail.html',
                        controller: 'ReponseDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('reponse');
                        return $translate.refresh();
                    }]
                }
            });
    });
