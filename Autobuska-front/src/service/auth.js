import Axios from '../apis/Axios'
import jwt_decode from 'jwt-decode';

export const login = (username, password) => {
    
    const dto = {
        username: username,
        password: password
    }
    
    Axios.post("/korisnici/auth", dto)
    .then(res => {
        const decoded_jwt=jwt_decode(res.data)
        window.localStorage.setItem("jwt", res.data)
        window.localStorage.setItem("role", decoded_jwt.role.authority)
        window.localStorage.setItem("sub", decoded_jwt.sub)
        window.location.replace("http://localhost:3000")
        })
        .catch(err =>{
            console.log(err)
            alert("Login failed, try again!")
        })
}

export const logout = () => {
    window.localStorage.removeItem("jwt")
    window.location.replace("http://localhost:3000")
}

export const IsLoggedIn = () => {
    let ulogovan = false;

    if (window.localStorage["jwt"]) {
        ulogovan = true;
    } 
    return ulogovan;
}

export const Rola = () => {
    let rola = "";

    if (window.localStorage['role'] == 'ROLE_ADMIN') {
        rola = "admin";
    } 
    if (window.localStorage['role'] == 'ROLE_KORISNIK') {
        rola = "korisnik";
    } 
    return rola;
}

export const korisnickoIme = () => {
    return window.localStorage['sub'] 
}