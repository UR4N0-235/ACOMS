runController();

function runController(){
    getLoggedStudentInfos();
}

async function getLoggedStudentInfos() {
    let jwt = getAuthCookie();
    let url = `http://${getIpServer()}:8080/student`;

    var header = {
        'method': "get",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${jwt}`
        }
    }

    await fetch(url, header).then(async (response) => {
        if (response.status == 200) {
            let loggedStudent  = await response.json();
            defineStudentFields(loggedStudent);
        } else {
            console.log(`error on fetch ${url} status code ${response.status}`);
            // window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html");
        }
    }).catch((err) =>{
        console.log("error1 " + err);
        // window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html"); 
    });
}

function defineStudentFields(loggedStudent){
    console.log(JSON.stringify(loggedStudent, undefined, 4));
    $(".studentName").each((index, student) => {
        $(student).text(loggedStudent.name);
    });

    $(".schoolName").each((index, school) => {
        $(school).text(loggedStudent.school.name);
    });

    $(".parentName").each((index, parent) => {
        $(parent).text(loggedStudent.parent.name);
    });
    
    $(".studentRm").each((index, student) => {
        $(student).text(loggedStudent.rmStudent);
    });

    
}