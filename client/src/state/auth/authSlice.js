import { createSlice } from "@reduxjs/toolkit"
import { jwtDecode } from "jwt-decode";

const initialState = {
    isAuthenticated: false,
    role: null,
    id: 0,
    token: null
}

const authSlice = createSlice({
    name: "auth",
    initialState,
    reducers: {
        setUser: (state, action) => {
            const token = action.payload;
            try {
                const decodedToken = jwtDecode(token);

                const r = decodedToken.role.toLowerCase()
                state.role = r;
                state.id = decodedToken.userId;
                state.isAuthenticated = true;
                state.exp = decodedToken.exp;
                state.token = token;
            } catch (error) {
                console.error("Invalid token", error);
            }
        },
        logout: (state, action) => {
            state = initialState;
        }
    }
});

export const { setUser, logout } = authSlice.actions;

export default authSlice.reducer;