
const logoutLink = document.getElementById("logout");
logoutLink.addEventListener("click", (e) => {
    e.preventDefault();
    const logoutForm = document.getElementById("logout-form");
    logoutForm.submit();
});
