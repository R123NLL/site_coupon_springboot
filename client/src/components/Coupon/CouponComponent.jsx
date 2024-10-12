import React, { useState } from "react";

export default function CouponComponent(props) {
    const coupon = props.coupon;
    const btn = props.btnClass ? props.btnClass : 'primary';
    const btnText = props.btnText ? props.btnText : 'Get'; // Default button text
    const btnClass = `btn btn-${btn} mt-1`;

    const onClickHandler = () => {
        const callback = props.onClick;
        if (typeof callback === 'function') {
            // Ensure quantity is a valid
            if (coupon.amount) {
                // Pass the coupon
                callback(coupon);
            } 
        }
    };

    return (
        <>
            <div className="card bg-light m-3" style={{ width: '18rem' }}>
                <img src="https://ps.w.org/add-coupon-by-link-for-woocommerce/assets/icon.svg?rev=2571233" className="card-img-top" alt="..." />
                <div className="card-body">
                    <h5 className="card-title">{coupon.title}</h5>
                    <div className="card-text">
                        <div>Description: {coupon.description}</div>
                        <div>Left in stock: {coupon.amount}</div>
                        <div>Category: {coupon.category}</div>
                        <div className="badge text-bg-primary text-wrap">Price: {coupon.price}</div>
                        <div>Start date: {coupon.startDate}</div>
                        <div>End date: {coupon.endDate}</div>
                    </div>
                    <a onClick={onClickHandler} className={btnClass}>{btnText}</a>
                </div>
            </div>
        </>
    );
}
