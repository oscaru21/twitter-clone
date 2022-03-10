//Responsible of all the DOM interaction and showing content
const form = document.querySelector('.login-form');

form.addEventListener('submit', e => {
    e.preventDefault();
    const username = e.target.username.value.trim();  
    const password = e.target.password.value.trim();

    form.reset();

    //login
    login(username, password);
});