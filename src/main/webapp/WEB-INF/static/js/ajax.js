function changeProduct() {
    let url = "http://176.114.11.149:8080/api/user";
    // let url = "http://localhost:8080/api/user";
    let product;
    fetch(url, {
        method: 'GET'
    }).then((resp) => {
        return resp.json();
    }).then((data) => {
        console.log(data);
        product = data;
        let title = product.title;
        let store = product.storeName;
        let price = product.price + " грн";
        let link = product.url;
        let imgLink = product.imgUrl;

        let main_link = document.getElementById('main_link');
        main_link.setAttribute('href', link);
        let title_img = document.getElementById('title_img');
        title_img.setAttribute('src', imgLink);
        let title_store = document.getElementById('title_store');
        title_store.innerText = store;
        title_store.setAttribute('href', link);
        let title_product = document.getElementById('title_product');
        title_product.innerText = title;
        title_product.setAttribute('href', link);
        let price_product = document.getElementById('price_product');
        price_product.innerText = price
        price_product.setAttribute('href', link);

    }).catch(function (Error) {
        console.log("Error", Error);
    });
}

changeProduct()
setInterval(changeProduct, 4000);



