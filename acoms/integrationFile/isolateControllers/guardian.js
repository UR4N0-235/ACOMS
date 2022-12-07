runController();

function runController() {
    getLoggedGuardianInfos();
    updateGuardianListener();
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
            let coordinotars = await getGuardianCoordinators(header);

            defineGuardianFields(loggedGuardian);
            defineGuardianChildrens(childrens);
            defineContatctList(coordinotars);

            // console.log("logged is" + JSON.stringify(loggedGuardian, undefined, 4));
            // console.log(`teste 1 ${childrens}`)
            // console.log("test get childrens " + JSON.stringify(childrens, undefined, 4));
        } else {
            if (response.status == 451) {
                console.log("escola desligada");
                addEscolaDesligada();
            } else {
                console.log(`error on fetch ${url} status code ${response.status}`);
                // window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html");
            }
        }
    }).catch((err) => {
        console.log("error1 " + err);
        // window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html"); 
    });
}

async function getGuardianChildres(header) {
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
    }).catch((err) => {
        console.log("error2 " + err);
        // window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html"); 
    });
    return childrens;
}

async function getGuardianCoordinators(header) {
    let url = `http://${getIpServer()}:8080/guardian/coordinators`;
    var coordinators = '';
    // console.log("header = " + header);

    await fetch(url, header).then(async (response) => {
        if (response.status == 200) {
            coordinators = await response.json();
        } else {
            console.log(`error on fetch ${url} status code ${response.status}`);
            // window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html");
        }
    }).catch((err) => {
        console.log("error2 " + err);
        // window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html"); 
    });
    return coordinators;
}



function defineGuardianFields(loggedGuardian) {
    console.log(loggedGuardian)
    $(".guardianName").each((index, guardian) => {
        $(guardian).text(loggedGuardian.name);
    });

    $(".guardianCpf").each((index, guardian) => {
        $(guardian).text(loggedGuardian.cpf);
    });

    $(".guardianTelephone").each((index, guardian) => {
        $(guardian).text(loggedGuardian.telephoneNumber);
    });

    $(".guardianEmail").each((index, guardian) => {
        $(guardian).text(loggedGuardian.email);
    });

    $(".guardianAddress").each((index, guardian) => {
        $(guardian).text(loggedGuardian.address);
    });

    $(".guardianBirthDay").each((index, guardian) => {
        var date = new Date(loggedGuardian.dateOfBirthday);
        // console.log("moment "+moment.unix(loggedGuardian.dateOfBirthday).format("DD/MM/YYYY"));
        $(guardian).text(`${date.getDate()}/${date.getMonth()}/${date.getFullYear + 1}`);
    });

    $(".guardianAddress").each((index, guardian) => {
        $(guardian).text(loggedGuardian.address);
    });

    $(".guardianCpf").each((index, guardian) => {
        $(guardian).text(loggedGuardian.cpf);
    });

    $(".schoolName").each((index, school) => {
        $(school).text(loggedGuardian.school.name);
    });

    $(".inputGuardianName").each((index, guardian) => {
        $(guardian).attr('value', loggedGuardian.name);
    });

    $(".inputGuardianTelephone").each((index, guardian) => {
        $(guardian).attr('value', loggedGuardian.telephoneNumber);
    });

    $(".inputGuardianEmail").each((index, guardian) => {
        $(guardian).attr('value', loggedGuardian.email);
    });

    $(".inputGuardianAddress").each((index, guardian) => {
        $(guardian).attr('value', loggedGuardian.address);
    });
}

function defineGuardianChildrens(childrens) {
    $(".childrensContainer").each((index, childrenContainer) => {
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

function updateGuardianListener() {
    $('.updateGuardian').on((index, form) => {
        var formData = $(form);
        $(".updateGuardian .submit").on('click', async (event) => {
            event.preventDefault();
            console.log("teste");
        });
    });
}


function defineContatctList(coordintarosList) {
    console.log(coordintarosList);

    coordintarosList.forEach(coordinator => {
        $(".contatoCoordinatorContainer").append(`
        <div class="card mb-3" id="contato">
    
        <div class="d-flex text-muted pt-3">
    
            <svg class="bd-placeholder-img flex-shrink-0 me-2 rounded" width="32" height="32"
                xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 32x32"
                preserveAspectRatio="xMidYMid slice" focusable="false">
                <title>Placeholder</title>
                <rect width="100%" height="100%" fill="#007bff" /><text x="50%" y="50%"
                    fill="#007bff" dy=".3em">32x32</text>
            </svg>
    
            <div class="pb-3 mb-0 small lh-sm border-bottom w-100">
                <div class="d-flex justify-content-between">
                    <strong class="text-gray-dark">${coordinator.cargo}</strong>
                    <button class="btn btn-primary toggle-chat" data-toChatOpen="${coordinator.id}" id="visualizar">
                        Entrar em contato
                    </button>
                </div>
                <span class="d-block">${coordinator.name}</span>
            </div>
        </div>
    </div>

    <div style="display:none" data-chatId="${coordinator.id}">
            <div class="chatbox-message-wrapper">
                <div class="chatbox-message-header" style="background-image: linear-gradient(to right, #4562cc, #089bdf);">
                    <div class="chatbox-message-profile">
                        <img src="/paginas/coordenador/coordenador.png" alt="" class="chatbox-message-image">
                        <div>
                            <h4 class="chatbox-message-name" style="color: white;">João Otávio (Coordenador)</h4>
                            <p class="chatbox-message-status" style="color: white;">online</p>
                        </div>
                    </div>
                </div>
                <div class="chatbox-message-content">
                    <h4 class="chatbox-message-no-message">Você ainda não tem mensagens!</h4>
                    <!-- <div class="chatbox-message-item sent">
                    <span class="chatbox-message-item-text">
                        Bom dia, meu filho faltou hoje porque está doente e irá para o médico, portanto não comparecerá hoje na escola. Obrigado pela sua preocupação.
                    </span>
                    <span class="chatbox-message-item-time">08:30</span>
                </div>

                <div class="chatbox-message-item received">
                    <span class="chatbox-message-item-text">
                        Bom dia, senho reponsável, queria avisa-lo que seu filho está ausente hoje na escola, há algum motiva para a falta dele?
                    </span>
                    <span class="chatbox-message-item-time">08:30</span>
                </div> -->
                </div>
                <div class="chatbox-message-bottom" style="background-image: linear-gradient(to right, #4562cc, #089bdf);">
                    <form action="#" class="chatbox-message-form" id="form">
                        <textarea rows="1" placeholder="Escreva uma mensagem..." class="chatbox-message-input"
                            cols="50" id="usermsg"></textarea>
                        <button type="submit" class="chatbox-message-submit"><i class='bx bx-send'></i></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    `);
    });

    defineToggleChat();
}

function defineToggleChat() {
    $(".toggle-chat").each((index, chat) => {
        $(chat).on("click", () => {
            let chatId = $(chat).data("tochatopen");
            let chatCom = $(`div[data-chatid=${chatId}]`).css("display");
            console.log(chatCom);
            if (chatCom == "none") $(`div[data-chatid=${chatId}]`).css("display", "inline");
            if (chatCom != "none") $(`div[data-chatid=${chatId}]`).css("display", "none");
        })
    });
}

function addEscolaDesligada() {
    $("head").append(`<style type="text/css">
    .content {
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        width: 500px;
        marign: 10px 5px;
        text-align: center;
        background-color: #497bdf;
        box-sizing: border-box;
        padding: 10px;
        z-index: 100;
        display: none;
        /*to hide popup initially*/
    }
      
    .close-btn {
        position: absolute;
        right: 20px;
        top: 5px;
        color: white;
        box-sizing: border-box;
        padding: 2px;
        font-size:30px;
    }

    .close-btn:hover{
        cursor:pointer;
    }
</style>`);

    $("body").append(`<!-- div containing the popup -->
    <div class="content">
        
        <h3>Lamentamos
        <div onclick="togglePopup()" class="close-btn">X</div>
        </h3>
  
        <p>
Nós do ACOMs lamentamos informar que sua instituicao esta temporariamente suspensa
por favor, entre em contato com um admnistrador para mais detalhes!
        </p>
    </div>`);
    $(".content").toggle();
}

function togglePopup() {
    window.location.replace("http://127.0.0.1:5500/paginas/landingpage/landingpage.html");
        
}   
