import React, { useState } from 'react';

const useCouponInitialState = () => {
    const [newCouponData, setNewCouponData] = useState({
        title: '',
        description: '',
        category: 'FOOD', // Default category
        price: '',
        amount: '',
        startDate: '',
        endDate: '',
        image: ''
    });

    return { newCouponData, setNewCouponData };
};

export default useCouponInitialState;