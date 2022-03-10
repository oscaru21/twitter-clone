const login = async (username, password) => {
    const endpoint =  'http://localhost:8080/auth/login';
    
    const data = {
        usernameOrEmail: username,
        password: password
    }

    const response = await fetch(endpoint, {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),
    });

    return response;
};