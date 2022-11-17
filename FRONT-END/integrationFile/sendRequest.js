function convertFormToJson(form) {
    const array = $(form).serializeArray(); // Encodes the set of form elements as an array of names and values.
    const json = {};
    $.each(array, function() {
        json[this.name] = this.value || "";
    });
    return json;
}

// just send and retorne an promisse, you need to implement 
// .then() to read an manipulate server response
async function callAjaxAndReturnPromise(form) {
    // convert all form datas to json
    // each name will be an key, and value will be... value?
    const bodyData = convertFormToJson(form);

    //headers
    let header = {};
    header.headers = { 'Content-Type': 'application/json' };

    if (form.attr('method') == "post") {
        header.method = form.attr('method')
        header.body = JSON.stringify(bodyData);
    }

    const request = await fetch(form.attr('action'), header);
    return request.json();
}