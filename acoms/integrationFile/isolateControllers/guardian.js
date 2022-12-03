$(document).ready(runController());

function runController(){
    getLoggedGuardianInfos();    
}

async function getLoggedGuardianInfos(){
    let jwt = getAuthCookie();
    let url = `${getIpServer()}:8080/guardian`;
    var header = {
        'method': "get",
        headers: {
            'Authorization': `Bearer ${jwt}`
        }
    }
    console.log(`header ${JSON.stringify(header)}`);
    //header.headers = { 'Content-Type': 'application/json' };
    const request = await fetch(url, header);
    const recived = request.json();
    recived.then((response) => {
        console.log(response)
    });
    
}