import axios from "axios";

export const customerTypes = [
    { id: 1, name: 'מנהל' },
    { id: 2, name: 'עסק' },
    { id: 3, name: 'לקוח' },
];

export function validateLoginForm(loginData){
    return loginData.customerType > 0
    && loginData.username
    && loginData.password
    && loginData.username.length >= 3
    && loginData.password.length >= 3;
}

export function submit(loginData){
    console.log('Will send: ', loginData);
    //const token = axios.get('/', loginData);
}