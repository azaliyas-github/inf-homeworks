var myForm = document.forms.myForm;

myForm.onsubmit = function () {
    if (myForm.name.value === "" || myForm.birth.value === "" || myForm.phone.value === ""
    || myForm.email.value === "" || myForm.password.value === "" || myForm.surname.value || myForm.address.value || myForm.phone.value
    || myForm.city.value) {
        alert("Please, enter forms");
        return false;
    } else {
        return true;
    }
}



// var app = angular.module('MyApp');
// app.controller('DemoCtrl', function($scope) {
//     $scope.user = {
//         first_name: '',
//         last_name: '',
//         gender: '',
//         age: '',
//         email: '',
//     };
// });
// app.directive("compareTo", function() {
//     return {
//         require: "ngModel",
//         scope: {
//             otherModelValue: "=compareTo"
//         },
//         link: function(scope, element, attributes, ngModel) {
//
//             ngModel.$validators.compareTo = function(modelValue) {
//                 return modelValue == scope.otherModelValue;
//             };
//
//             scope.$watch("otherModelValue", function() {
//                 ngModel.$validate();
//             });
//         }
//     };
// });
