runController();
function runController() {
    getSchoolList();
}

async function getSchoolList() {
    let jwt = getAuthCookie();
    let url = `http://${getIpServer()}:8080/admin/schools`;

    var header = {
        'method': "get",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${jwt}`
        }
    }

    await fetch(url, header).then(async (response) => {
        if (response.status == 200) {
            defineSchoolsList(await response.json());

        } else {
            console.log(`error on fetch ${url} status code ${response.status}`);
            // window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html");
        }
    }).catch((err) => {
        console.log("error1 " + err);
        // window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html"); 
    });
}
function defineSchoolsList(schoolsLoadded) {
    // console.log(schoolsLoadded);
    $(".schoolsContainer").each((index, schools) => {
        $(schools).text("");
        schoolsLoadded.forEach(school => {

            $(".schoolsContainer").append(`
            <li class="d-flex justify-content-between">
            <div class="d-flex flex-row align-items-center"><i class="fa fa-check-circle checkicon"></i>
                <div class="ml-2">
                    <h6 class="mb-0" style="font-size: 20px; ${school.statusBoolean ? 'color:green;':'color:red;'}">${school.name}</h6>
                    <div class="d-flex flex-row mt-1 text-black-50 date-time" style="font-size: 15px;">
                        <div><i class="fa fa-calendar-o"></i><span class="ml-2">Contrato: ${school.contrato} - R$${school.contrato === "VIP" ? '300,00' : school.contrato === "PROFISSIONAL" ? "200,00" : "100,00"}/mês</span></div>
                        <div class="ml-3"><i class="fa fa-clock-o"></i><span class="ml-2"></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="d-flex flex-row align-items-center">
                <div class="d-flex flex-column mr-2">
                    <div class="profile-image"><img class="rounded-circle">

                    <button class="btn btn-${school.statusBoolean ? 'secondary': 'primary' } btnChangeState" data-id="${school.idSchool}">
                        ${school.statusBoolean ? 'Suspender' : 'Ativar'}
                    </button>

                    
                </div>
                <i class="fa fa-ellipsis-h"></i>
            </div>
            </li>
            `);

        });
    });

    $(".btnChangeState").each((index, btn)=>{
        // console.log($(btn).data("id"));
        $(btn).on('click', ()=>{
            mudarStatusSchool($(btn).data("id"));
        });
    })
}

async function mudarStatusSchool(id){
    let jwt = getAuthCookie();
    let url = `http://${getIpServer()}:8080/admin/schools?mudarStatus=${id}`;

    var header = {
        'method': "get",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${jwt}`
        }
    }

    await fetch(url, header).then(async (response) => {
        // console.log(response);
        if (response.status == 200) {
            // console.log("deu certo!!!");
            getSchoolList();
        } else {
            console.log(`error on fetch ${url} status code ${response.status}`);
            // window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html");
        }
    }).catch((err) => {
        console.log("error1 " + err);
        // window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html"); 
    });
}