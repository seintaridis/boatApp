router.controller('registerBoatController',function ($scope, $http,$state,$cookies, boatService,userService) {




    userService.validateUser($cookies.get('id'),$cookies.get('authToken'))
        .then(function (response){

            }, function (response){

            $state.go('home');
            }

        );



    console.log("register boat");
    $scope.register = function () {
        console.log($scope.boat);
        boatService.register($scope.boat)
            .then(function (response){
                console.log("register boat");
                $state.go('listBoats')

                }, function (response){}

            )
    }

})