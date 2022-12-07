function buscaCep(){
    let cep = document.getElementById('txtCep').value;
    if (cep !== "") {
        let url = "https://brasilapi.com.br/api/cep/v1/" + cep;

        let req = new XMLHttpRequest;
        req.open("GET", url);
        req.send();

        //tratar a responta da requisição
        req.onload = function(){
            if(req.status === 200){
                let endereco = JSON.parse(req.response);
                document.getElementById("txtCidade").value = endereco.city;
                document.getElementById("txtEstado").value = endereco.state;
                document.getElementById("txtRua").value = endereco.street;
                document.getElementById("txtBairro").value = endereco.neighborhood;
            }
            else if(req.status === 404){
                alert("CEP inválido");
            }
            else{
                alert("Erro ao fazer a requisição");
            }
        }
        
    }
}

window.onload = function() {
    let txtCep = document.getElementById("txtCep");
    txtCep.addEventListener("blur", buscaCep);
}