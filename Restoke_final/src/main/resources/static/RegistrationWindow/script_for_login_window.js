var myForm = document.forms.myForm;

myForm.onsubmit = function () {
    if (myForm.username.value === "" || myForm.password.value === "") {
        alert("Please, enter forms");
        return false;
    } else {
        return true;
    }
}