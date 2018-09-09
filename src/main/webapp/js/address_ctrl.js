var app = angular.module('app',[]);

app.controller('AddressCtrl', ['$scope', 'AddressService', function ($scope, AddressService) {


    $scope.addresses = [];

    var newAddressFormMaster = {
        "street": "",
        "number": null,
        "zipCode": "",
        "city": "",
        "state": "",
        "district": "",
        "complement": ""
    }

    $scope.message = '';
    $scope.errorMessage = '';

    $scope.addressForm = angular.copy(newAddressFormMaster);

    $scope.teste = function() {
        alert('XABARACUNAIA');
    }

    $scope.loadAddress = function() {

        AddressService.findAllAddress()
            .then(function success(response) {
                    console.log(JSON.stringify(response));
                    $scope.message = 'Dados retornados com sucesso!';
                    $scope.addresses = response.data;
            }, function error(response) {
                    $scope.errorMessage = 'Error updating user!';
                    $scope.message = '';
            });
    }

    $scope.loadAddress();

    $scope.preNewAddressModal = function (id) {
        $scope.addressForm = angular.copy(newAddressFormMaster);
        $("#addAddressModel").modal("show");
    }

    $scope.addOrUpdateAddress = function () {
        AddressService.addOrUpdateAddress($scope.addressForm)
            .then(function success(response) {
                $scope.message = 'Dados retornados com sucesso!';
                console.log(JSON.stringify(response));
                $("#addAddressModel").modal("hide");
            }, function error(response) {
                $scope.errorMessage = response.data.errors[0].defaultMessage;
                $scope.message = '';
                //alert(JSON.stringify(response));
            }).finally(function() {
                $scope.addressForm = angular.copy(newAddressFormMaster);
                $scope.loadAddress();
        });
    }

    $scope.deleteAddress = function (id) {
        AddressService.deleteAddress(id)
            .then(function success(response) {
                $scope.message = 'Dados deletados com sucesso!';
                console.log(JSON.stringify(response));
            }, function error(response) {
                $scope.errorMessage = 'Error ao adicionar o endereço!';
                $scope.message = '';
                alert(JSON.stringify(response));
            }).finally(function() {
            $scope.addressForm = angular.copy(newAddressFormMaster);
            $scope.loadAddress();
        });
    }

    $scope.preUpdateAddressModal = function (id) {
        $scope.addressForm = angular.copy(newAddressFormMaster);
        AddressService.findById(id)
            .then(function success(response) {
                $scope.addressForm = response.data;
                $("#addAddressModel").modal("show");
            }, function error(response) {
                $scope.errorMessage = 'Error ao adicionar o endereço!';
                $scope.message = '';
                alert(JSON.stringify(response));
            });
    }

    $scope.detailsAddressModal = function (id) {
        $scope.addressForm = angular.copy(newAddressFormMaster);
        AddressService.findById(id)
            .then(function success(response) {
                $scope.addressForm = response.data;
                $("#detailAddressModel").modal("show");
            }, function error(response) {
                $scope.errorMessage = 'Error ao adicionar o endereço!';
                $scope.message = '';
                alert(JSON.stringify(response));
            });
    }


}]);