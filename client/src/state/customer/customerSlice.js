import { createSlice } from "@reduxjs/toolkit"

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
    }
});

export const { setPurchasedCoupons } = customerSlice.actions;
export default customerSlice.reducer;