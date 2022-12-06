$(document).ready(runController());
console.log("opa")
function runController() {
    console.log("controller logged");
    getLoggedGuardianInfos();
}

async function getLoggedGuardianInfos() {
    let jwt = getAuthCookie();
    let url = `http://${getIpServer()}:8080/guardian`;

    console.log("teste");

    var header = {
        'method': "get",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${jwt}`
        }
    }

    await fetch(url, header).then(async (response) => {
        if (response.status == 200) {
            loggedGuardian = await response.json();
            
            defineGuardianFields(loggedGuardian);
            // var childrens = await getGuardianChildres(header);
            console.log("logged is" + JSON.stringify(loggedGuardian, undefined, 4));
            console.log("test get childrens " = JSON.stringify(childrens, undefined, 4));
        } else {
            console.log("deu error" + response.status);
            window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html");
        }
    }).catch((err) =>{
        console.log("error " + err);
        window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html"); 
    });
}

// async function getGuardianChildres(header){
//     let url = `http://${getIpServer()}:8080/guardian/students`;

//     await fetch(url, header).then(async (response) => {
//         if (response.status == 200) {
//             loggedGuardian = await response.json();
//             return loggedGuardian;
//         } else {
//             console.log("deu error" + response.status);
//             window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html");
//         }
//     }).catch((err) =>{
//         console.log("error " + err);
//         window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html"); 
//     });
// }

function defineGuardianFields(loggedGuardian) {
    $(".guardianName").each((index, guardian) => {
        $(guardian).text(loggedGuardian.name);
    });

    $(".schoolName").each((index, school) => {
        $(school).text(loggedGuardian.school.name);
    });

}
