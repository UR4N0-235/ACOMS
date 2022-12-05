$("input[name='username']").mask("000.000.000-00");

$("input[name='username']").on('keypress', changeInvalidLogin);
$("input[name='password']").on('keypress', changeInvalidLogin);
		
function changeInvalidLogin(){
    $("#errologin").css("display", "none");
}
		
function myFunction() {
    var x = document.getElementById("password");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}