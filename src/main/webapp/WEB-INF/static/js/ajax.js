function changeProduct() {
    let url = "<spring:url value=\"/api/user\"/>"
    fetch(url, {
        method: 'GET'
    }).then(resp => {
        let model =  resp.json();
    }).catch()
}