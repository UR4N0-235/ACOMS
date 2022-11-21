$(document).ready(main());

function main() {
    preProcess();
}

// ##########################################
// # sub functions - call direct on main() #
// ##########################################


function defineEventsListeners() {

    simplePushDatasToServer(); // class .justPushToServer
    processLoginRequest(); // class .loginRequest ## WARNING USE INPUTS 'username' and 'password' !!!!!!!!!
}

// ######################################
// # generic functions - internal calls #
// ######################################


function simplePushDatasToServer(formData) {
    $('.justPushToServer').each((index, form) => {
        let formData = $(form);
        $(".justPushToServer input[type='submit']").on("click", (event) => {
            event.preventDefault();
            callAjaxAndReturnPromise(formData).then(); // promises ???
        });
    });
}

function processLoginRequest(formData) {
    $('.loginRequest').each((index, form) => {
        let formData = $(form);
        $(".loginRequest input[type='submit']").on("click", (event) => {
            event.preventDefault();
            callAjaxAndReturnPromise(formData)
                .then((response) => {
                    //console.log(response);
                    tryGetAll('http://172.16.51.126:8080/school/coordinators', response.token);
                });
        });
    });
}

// list try
async function tryGetAll(url, jwt) {
    var header = {
        'method': "get",
        headers: {
            'Authorization': `Bearer ${jwt}`
        }
    }

    console.log(`header ${header}`);
    //header.headers = { 'Content-Type': 'application/json' };
    const request = await fetch(url, header);
    const recived = request.json();
    recived.then((response) => {
        console.log(response)
    });
}

function preProcess() {
    addScripts();
    defineUrlAction();
    defineEventsListeners();
}

function addScripts() {
    $('head').append('<script src="https://cdn.jsdelivr.net/npm/js-cookie@3.0.1/dist/js.cookie.min.js"></script>');

    $('head').append('<script src="/FRONT-END/integrationFile/env.js"><script>');
    $('head').append('<script src="/FRONT-END/integrationFile/defineUrlAction.js"><script>');
    $('head').append('<script src="/FRONT-END/integrationFile/authController.js"><script>');
    $('head').append('<script src="/FRONT-END/integrationFile/sendRequest.js"><script>');

}