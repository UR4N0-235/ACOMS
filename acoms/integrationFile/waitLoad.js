const styleHtml = `
    #preloader {
        transform: translateZ(1px);
        height: 100vh;
        width: 100vw;
        background: #fff;
        position: fixed;
        z-index: 9999;

        display: flex;
        justify-content: center;
        align-items: center;
    }
    #preloader > img {
        display: inline-block;
        width: 200px;
        height: 200px;
        /*margin: 8px;*/
        border-radius: 50%;
        background: url('/img/logomarca.png');
        animation: acomsSpinner 20s cubic-bezier(0, 0.2, 0.8, 1) infinite;
        /*
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%,-50%);
        z-index: 10000;
        */
    }
    @keyframes acomsSpinner {
        0%, 100% {
            animation-timing-function: cubic-bezier(0.5, 0, 1, 0.5);
        }
        
        0% {
            transform: rotateY(0deg);
        }

        50% {
            transform: rotateY(1800deg);
            animation-timing-function: cubic-bezier(0, 0.5, 0.5, 1);
        }
        
        100% {
            transform: rotateY(360deg);
        }
    }`

  const style = document.createElement("style");
  style.innerHTML = styleHtml;
  document.head.appendChild(style);

const preloaderHtml = '<div id="preloader"><img src="/img/logomarca.png"/></div>'

var div = document.createElement('div');
div.innerHTML = preloaderHtml;

while (div.children.length > 0) {
    document.body.appendChild(div.children[0]);
}

function loaded(preloadOff) {
    preloadOff.style.display = 'none';
}

window.addEventListener('load', ()=>{
    setTimeout(loaded(document.getElementById("preloader")), 1000)
});
