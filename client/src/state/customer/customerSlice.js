import { createSlice } from "@reduxjs/toolkit";
import { LOGOUT_ACTION } from "../commonActions";

const initialState = {
    purchasedCoupons: []
};

const customerSlice = createSlice({
    name: 'customer',
    initialState,
    reducers: {
        setPurchasedCoupons: (state, action) => {
            state.purchasedCoupons = action.payload;
        }
    },
    extraReducers: builder => {
        builder.addCase(LOGOUT_ACTION, () => initialState);
    }
});

export const { setPurchasedCoupons } = customerSlice.actions;
export default customerSlice.reducer;