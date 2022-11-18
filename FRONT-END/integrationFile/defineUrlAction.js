function defineUrlAction() {
    $("form").each((index, form) => {
        let urlPattern = $(form).attr("action");
        let newUrl = `http://${getIpServer()}:8080${urlPattern}`;
        $(form).attr("action", newUrl);
    });
}