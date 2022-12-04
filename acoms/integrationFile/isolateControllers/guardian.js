runController();

function runController() {
    getLoggedGuardianInfos();
}

async function getLoggedGuardianInfos() {
    console.log("logged test");
    let jwt = getAuthCookie();
    let url = `http://${getIpServer()}:8080/guardian`;
    var header = {
        'method': "get",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${jwt}`
        }
    }
    console.log(JSON.stringify(header));
    await fetch(url, header).then(async (response) => {
        if (response.status == 200) {
            loggedGuardian = await response.json();
            defineFields(loggedGuardian);
            console.log("logged is" + loggedGuardian);
        } else {
            console.log("deu error" + response.status);
            window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html");
        }
    }).catch((err) =>{
        console.log("error " + err);
        window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html"); 
    });

    console.log("ue, acabo");
}

function defineFields(loggedGuardian) {
    $(".guardianName").each((index, guardian) => {
        $(guardian).text(loggedGuardian.name);
    });

    $(".schoolName").each((index, school) => {
        $(school).text(loggedGuardian.school.name);
    });

}
