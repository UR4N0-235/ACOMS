function processLoginRequest() {
    $('.loginRequest').each((index, form) => {
        let formData = $(form);
        $(".loginRequest .submit").on("click", (event) => {
            event.preventDefault();
            callAjaxAndReturnPromise(formData)
                .then(async (response) => {
                    if(response.status == 401){
                        console.log("error login errado");
                    }else{
                        let responseContent = await response.json();
                        saveAuthCookie(responseContent);
                        let redirectTo = $(".loginRequest .submit").attr('formaction');
                       window.location.replace(redirectTo);
                    }
                });
        });
    });
}

function defineLogoutButtons(){
    $('.logout').each((index, button) =>{
        $(button).on('click', (event)=>{
            event.preventDefault();
            logOut();
            let redirectTo = 'http://127.0.0.1:5500/paginas/landingpage/landingpage.html';
            window.location.replace(redirectTo);
        })
    });
}

function saveAuthCookie(auth){
    // lib Cookies ta dando dor de cabeça !
    //    Cookies.set("ACOMs_auth", auth,  { expires: auth.defaultDaysToExpire, path: auth.path});

        const d = new Date();
        d.setTime(d.getTime() + (auth.defaultDaysToExpire * 24 * 60 * 60 * 1000));
        let expires = "expires="+d.toUTCString();
        
        document.cookie = `ACOMs_auth=${auth.token};${expires};path=/;`;
    }

function getAuthCookie(){
        let name = "ACOMs_auth=";
        let decodedCookie = decodeURIComponent(document.cookie);
        let ca = decodedCookie.split(';');
        for(let i = 0; i <ca.length; i++) {
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

async function logOut(){
    await fetch(`http://${getIpServer()}:8080/logout`);
    document.cookie = "ACOMs_auth=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";;
}

function verifyHrefNecessaryAuth(){
    var url = window.location.pathname;

    if(url.startsWith("/paginas/responsavel")){
        $('head').append('<script src="/integrationFile/isolateControllers/guardian.js"></script>');
    }

    // exemples : 
    // if(url.startsWith("/paginas/aluno")){
    //     $('head').append('<script src="/integrationFile/isolateControllers/guardian.js"></script>');
    //     runController();
    // }
    
    // if(url.startsWith("/paginas/coordenacao")){
    //     $('head').append('<script src="/integrationFile/isolateControllers/guardian.js"></script>');
    //     runController();
    // }

    // if(url.startsWith("/paginas/escola")){
    //     $('head').append('<script src="/integrationFile/isolateControllers/guardian.js"></script>');
    //     runController();
    // }
}