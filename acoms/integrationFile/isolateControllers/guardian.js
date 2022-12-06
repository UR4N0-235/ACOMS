runController();


function runController() {
    getLoggedGuardianInfos();
}

async function getLoggedGuardianInfos() {
    let jwt = getAuthCookie();
    let url = `http://${getIpServer()}:8080/guardian`;

    var header = {
        'method': "get",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${jwt}`
        }
    }

    await fetch(url, header).then(async (response) => {
        if (response.status == 200) {
            let loggedGuardian = await response.json();
            let childrens = await getGuardianChildres(header);

            defineGuardianFields(loggedGuardian);
            defineGuardianChildrens(childrens);

            // console.log("logged is" + JSON.stringify(loggedGuardian, undefined, 4));
            // console.log(`teste 1 ${childrens}`)
            // console.log("test get childrens " + JSON.stringify(childrens, undefined, 4));
        } else {
            console.log(`error on fetch ${url} status code ${response.status}`);
            // window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html");
        }
    }).catch((err) =>{
        console.log("error1 " + err);
        // window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html"); 
    });
}

async function getGuardianChildres(header){
    let url = `http://${getIpServer()}:8080/guardian/students`;
    var childrens = '';

    await fetch(url, header).then(async (response) => {
        if (response.status == 200) {
//            console.log(await response.json());
            childrens = await response.json(); 
        } else {
            console.log(`error on fetch ${url} status code ${response.status}`);
            // window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html");
        }
    }).catch((err) =>{
        console.log("error2 " + err);
        // window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html"); 
    });
    return childrens;
}

function defineGuardianFields(loggedGuardian) {
    $(".guardianName").each((index, guardian) => {
        $(guardian).text(loggedGuardian.name);
    });

    $(".schoolName").each((index, school) => {
        $(school).text(loggedGuardian.school.name);
    });

}

function defineGuardianChildrens(childrens){
    $(".childrensContainer").each((index, childrenContainer)=>{
        $(childrenContainer).text("");

        childrens.forEach(children => {
            $(".childrensContainer").append(`
            <div class="mb">
              <div class="card mb-3" id="div-aluno">
                <div class="d-flex text-muted pt-3">
                  <svg class="bd-placeholder-img flex-shrink-0 me-2 rounded" width="32" height="32"
                    xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 32x32"
                    preserveAspectRatio="xMidYMid slice" focusable="false">
                    <title>Placeholder</title>
                    <rect width="100%" height="100%" fill="#007bff" /><text x="50%" y="50%" fill="#007bff"
                      dy=".3em">32x32</text>
                  </svg>
                  <div class="pb-3 mb-0 small lh-sm border-bottom w-100">
                    <div class="d-flex justify-content-between">
                      <strong class="text-gray-dark">${children.name}</strong>
                      <button class="btn btn-primary" id="visualizar">Visualizar</button>
                    </div>
                    <span class="d-block">Série: 1° ano - Escola: ${children.school.name}</span>
                  </div>
                </div>
              </div>
            </div>
            `);
        });
    })
}