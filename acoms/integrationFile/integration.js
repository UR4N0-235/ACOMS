$(document).ready(main());

function main() {
    preProcess();
}

// ##########################################
// # sub functions - call direct on main() #
// ##########################################
function preProcess() {
    addScripts();
    fixUrlAction();
    defineEventsListeners();
}

// ######################################
// # generic functions - internal calls #
// ######################################
function addScripts() {
    $('head').append('<script src="https://cdn.jsdelivr.net/npm/js-cookie@3.0.1/dist/js.cookie.min.js"></script>');
    
    $('head').append('<script src="/integrationFile/env.js"><script>');
    $('head').append('<script src="/integrationFile/authController.js"><script>');
    $('head').append('<script src="/integrationFile/sendRequest.js"><script>');
}

function fixUrlAction() {
    $("form").each((index, form) => {
        let urlPattern = $(form).attr("action");
        let newUrl = `http://${getIpServer()}:8080${urlPattern}`;
        $(form).attr("action", newUrl);
    });
}

function defineEventsListeners() {
    // on sendRequest.js
    simplePushDatasToServer(); // use class .justPushToServer
    
    // on authController.js
    processLoginRequest(); // use class .loginRequest ## WARNING USE INPUTS 'username' and 'password' !!!!!!!!!
    defineLogoutButtons();
    verifyHrefNecessaryAuth();
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

