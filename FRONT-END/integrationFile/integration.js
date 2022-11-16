// GLOBAL - CHANGE THIS TO CHANGE ALL FORMS
const ipServer = "172.16.51.126";

$(document).ready(main());

function main(){
    defineUrlAction();
    defineEachFunction();
}

// ##########################################
// # sub functions - call direct on main() #
// ##########################################

function defineUrlAction(){
    $("form").each((index, form)=>{
        let urlPattern = $(form).attr("action");
        let newUrl = `http://${ipServer}:8080${urlPattern}`;
        $(form).attr("action", newUrl);        
   });
}

function defineEachFunction(){
    $('.justPushToServer').each((index, form)=>{
        simplePushDatasToServer($(form));
    });

    $('.loginRequest').each((index, form)=>{
        processLoginRequest($(form));
    });
}

// ######################################
// # generic functions - internal calls #
// ######################################
function convertFormToJson(form){
    const array = $(form).serializeArray(); // Encodes the set of form elements as an array of names and values.
    const json = {};
    $.each(array, function () {
      json[this.name] = this.value || "";
    });
    return json;
}

function callAjaxAndReturnPromise(form){
    // convert all form datas to json
    // each name will be an key, and value will be... value?
    const bodyData = convertFormToJson(form);
    
    //headers
    let header = {};
    header.headers = { 'Content-Type' : 'application/json'};

    if(form.attr('method') == "post"){
        header.method = form.attr('method')
        header.body = JSON.stringify(bodyData);
    }
    
    return fetch(form.attr('action'), header);
}

function simplePushDatasToServer(formData){
    $(".justPushToServer input[type='submit']").on("click", (event)=>{
        event.preventDefault();
        callAjaxAndReturnPromise(formData).then(); // promises ???
    });
}

function processLoginRequest(formData){
    $(".loginRequest input[type='submit']").on("click", (event)=>{
        console.log("aaaa");
        event.preventDefault();
        callAjaxAndReturnPromise(formData)
        .then((response) => response.json())
        .then((data) => console.log(data));
    });
}