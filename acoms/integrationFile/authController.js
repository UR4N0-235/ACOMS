function processLoginRequest() {
    $('.loginRequest').each((index, form) => {
        let formData = $(form);
        $(".loginRequest .submit").on("click", async (event) => {
            event.preventDefault();
            //await logOut();
            document.cookie = "ACOMs_auth=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            callAjaxAndReturnPromise(formData)
                .then(async (response) => {
                    if (response.status == 401) {
                        console.log("error login errado");
                        $("#errologin").css('display', 'inline');
                    } else {
                        let responseContent = await response.json();
                        // console.log(responseContent);
                        saveAuthCookie(responseContent);
                        let redirectTo = $(".loginRequest .submit").attr('formaction');
                        window.location.replace(redirectTo);
                    }
                });
        });
    });
}

function defineLogoutButtons() {
    $('.logout').each((index, button) => {
        $(button).on('click', async (event) => {
            event.preventDefault();
            await logOut();
            let redirectTo = 'http://127.0.0.1:5500/paginas/landingpage/landingpage.html';
            console.log(`now document.cookie == ` + document.cookie)
            window.location.replace(redirectTo);
        })
    });
}

function saveAuthCookie(auth) {
    // lib Cookies ta dando dor de cabeça !
    //    Cookies.set("ACOMs_auth", auth,  { expires: auth.defaultDaysToExpire, path: auth.path});

    const d = new Date();
    d.setTime(d.getTime() + (auth.defaultDaysToExpire * 24 * 60 * 60 * 1000));
    let expires = "expires=" + d.toUTCString();

    document.cookie = `ACOMs_auth=${auth.token};${expires};path=/;SameSite=None; Secure;`;
}

function getAuthCookie() {
    let name = "ACOMs_auth=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

async function logOut() {
    await fetch(`http://${getIpServer()}:8080/logout`);
    document.cookie = "ACOMs_auth=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
}

function verifyHrefNecessaryAuth() {
    let url = window.location.pathname;
    // if(getAuthCookie() == '')window.location.replace('http://127.0.0.1:5500/paginas/landingpage/landingpage.html');


    if (url.startsWith("/paginas/responsavel") || url.match("/paginas/comunicacao_coordenacao/comunicacaocoordenacaoresponsavel.html")) {
        $('head').append(`<script src="/integrationFile/isolateControllers/guardian.js"></script>`);
    }

    if (url.startsWith("/paginas/aluno")) {
        $('head').append('<script src="/integrationFile/isolateControllers/student.js"></script>');
    }

    if (url.startsWith("/paginas/login_adm/list_school/")) {
        $('head').append('<script src="/integrationFile/isolateControllers/admin.js"></script>');
    }

    // ainda falta : 

    // if(url.startsWith("/paginas/coordenacao")){
    //     $('head').append('<script src="/integrationFile/isolateControllers/guardian.js"></script>');
    // }

    // if(url.startsWith("/paginas/escola")){
    //     $('head').append('<script src="/integrationFile/isolateControllers/guardian.js"></script>');
    // }
}