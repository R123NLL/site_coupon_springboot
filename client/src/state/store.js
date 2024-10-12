import { configureStore } from "@reduxjs/toolkit";
import authReducer from "./auth/authSlice";
import customerReducer from "./customer/customerSlice";

// TODO: need to figure out how to set up loaded state from storage for refresh
const loadState = () => {
    try {
        const serializedState = localStorage.getItem('state');
        if (serializedState === null) {
            return undefined;
        }
        return JSON.parse(serializedState);
    } catch (err) {
        return undefined;
    }
};

const persistedState = loadState();

export const store = configureStore({
    reducer: {
        auth: authReducer,
        customer: customerReducer
    },
    persistedState
});

const saveState = (state) => {
    try {
        const serializedState = JSON.stringify(state);
        localStorage.setItem('state', serializedState);
    } catch (err) { }
};

store.subscribe(() => {
    saveState(store.getState());
});