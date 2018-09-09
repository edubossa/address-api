app.service('AddressService',['$http', function ($http) {


    this.findAllAddress = function findAllAddress(){
        return $http({
            method: 'GET',
            url: 'api/addresses/'
        });
    }

    this.findById = function findById(id){
        return $http({
            method: 'GET',
            url: 'api/addresses/' + id
        });
    }

    this.createAddress = function createAddress(address){
        return $http({
            method: 'POST',
            url: 'api/addresses/',
            data: address
        });
    }

    this.updateAddress = function updateAddress(address){
        return $http({
            method: 'PUT',
            url: 'api/addresses/' + address.addressID,
            data: address
        });
    }

    this.addOrUpdateAddress = function addOrUpdateAddress(address){
        if (address.addressID) {
            return this.updateAddress(address)
        } else {
            return this.createAddress(address)
        }
    }

    this.deleteAddress = function createAddress(id){
        return $http({
            method: 'DELETE',
            url: 'api/addresses/' + id + '/void'
        });
    }


}]);