function myFunction() {
  var x = document.getElementById("senha");
  if (x.type === "password") {
    x.type = "text"
  } else {
    x.type = "password"
  }


new FormMask(document.getElementById("cpf"), "___.___.___-__", "_", [".", "-"])
}
