// GLOBAL - CHANGE THIS TO CHANGE ALL FORMS
const ipServer = "172.16.51.126";

$(document).ready(main());

function main(){
    alterUrlPattern();
    defineEventListener();
}

function alterUrlPattern(){
    $(".toServer").each((index, form)=>{
        let urlPattern = $(form).attr("action");
        let newUrl = `http://${ipServer}:8080${urlPattern}`;
        $(form).attr("action", newUrl);        
   });
}

function defineEventListener(){
    $('.toServer').each((index, form)=>{
        let formData = $(form);
        $(".toServer input[type='submit']").on("click", (event)=>{

            event.preventDefault();

            callAjax(formData);
        });
    });
}

function convertToJson(form){
    const array = $(form).serializeArray(); // Encodes the set of form elements as an array of names and values.
    const json = {};
    $.each(array, function () {
      json[this.name] = this.value || "";
    });
    return json;
}

function callAjax(form){
    const bodyData = convertToJson(form);
    
    let header = {
        'method': form.attr('method'),
        headers: {
            'Content-Type': 'application/json'
        },
        'body': JSON.stringify(bodyData),
        
    }
    fetch(form.attr('action'), header);

    console.log(JSON.stringify(bodyData, null, 4));
}