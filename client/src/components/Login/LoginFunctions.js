import axios from "axios";
import { roles } from "../../shared/common";

export const clientTypes = [
    { id: 1, name: 'מנהל', role: roles.administrator },
    { id: 2, name: 'עסק', role: roles.company },
    { id: 3, name: 'לקוח', role: roles.customer },
];

export function validateLoginForm(loginData){

    return loginData.clientType
    && loginData.email
    && loginData.password
    && loginData.email.length >= 3
    && loginData.password.length >= 3;
}

export async function submit(loginData){
    const apiUrl = process.env.REACT_APP_API_URL;

    // TODO: Server DTO input should accept role id (number), not string
    // Temp: work around
    let clientType = loginData.clientType;
    loginData.clientType = clientType.charAt(0).toUpperCase() 
        + clientType.slice(1).toLowerCase();

    const response = await axios.post(apiUrl + '/login', loginData );
    return response.data;
}