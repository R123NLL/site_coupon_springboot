import React, { useState } from "react";

export default function CouponComponent(props) {
    const coupon = props.coupon;
    const btn = props.btnClass ? props.btnClass : 'primary';
    const btnText = props.btnText ? props.btnText : 'Get'; // Default button text
    const btnClass = `btn btn-${btn} mt-1`;
    const [quantity, setQuantity] = useState(1);

    const onClickHandler = () => {
        const callback = props.onClick;
        if (typeof callback === 'function') {
            // Ensure quantity is a valid number and within the allowed range
            if (quantity > 0 && quantity <= coupon.amount) {
                // Pass the coupon and quantity to the callback
                callback(coupon, quantity);
            } else {
                alert("Please enter a valid quantity.");
            }
        }
    };

    return (
        <>
            <div className="card bg-light" style={{ width: '18rem' }}>
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
                    {/* Input for quantity */}
                    <div className="mt-2">
                        <label htmlFor={`quantity-${coupon.id}`} className="form-label">Quantity:</label>
                        <input
                            type="number"
                            id={`quantity-${coupon.id}`}
                            className="form-control"
                            min="1"
                            max={coupon.amount} // Limit quantity to available stock
                            value={quantity}
                            onChange={(e) => {
                                const value = Number(e.target.value);
                                // Check if value is valid and within range
                                if (!isNaN(value) && value >= 1 && value <= coupon.amount) {
                                    setQuantity(value);
                                }
                            }}
                        />
                    </div>
                    <a onClick={onClickHandler} className={btnClass}>{btnText}</a>
                </div>
            </div>
        </>
    );
}
