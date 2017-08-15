router.factory('boatService',function ($http) {
    var boatService = {}

    boatService.register = function (boat) {
        return $http.post('api/register_boat',boat)
            .then(function (response) {
                console.log(response)
                return response
            })

    };

    boatService.getBoats = function (boat) {
        return $http.post('api/get_boats',boat)
            .then(function (response) {
                console.log(response)
                return response
            })

    };

    boatService.deleteBoat = function deleteBoat(id,userId,authToken) {
        return $http.delete('api/delete_boat/' + id + '/'+ userId,{headers: {'authToken': authToken}})


    };


    boatService.getBoat = function getBoat(id){
        return $http({
            method : 'GET',
            url : 'api/get_boat/' + id
        });
    };

    boatService.updateBoat = function (boat,authToken) {
        return $http.post('api/update_boat',boat,{headers: {'authToken': authToken}})
            .then(function (response) {
                console.log(response)
                return response
            })

    };

    return boatService;
});