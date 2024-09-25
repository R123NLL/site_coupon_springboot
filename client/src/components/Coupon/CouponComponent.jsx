import React from "react";

export default function CouponComponent(props) {

    const coupon = props.coupon;
    const btn = props.btnClass ? props.btnClass : 'primary';
    const btnText = props.btnText ? props.btnText : 'primary';
    const btnClass = `btn btn-${btn} mt-1`;
    const onClickHandler = () => {
        const callback = props.onClick;
        if (typeof callback == 'function') callback(coupon);
    }

    return (
        <>
            <div className="card bg-light" style={{ width: '18rem' }}>
                <img src="https://ps.w.org/add-coupon-by-link-for-woocommerce/assets/icon.svg?rev=2571233" className="card-img-top" alt="..." />
                <div className="card-body">
                    <h5 className="card-title">{coupon.title}</h5>
                    <div className="card-text">
                        <div>Description: {coupon.description}</div>
                        <div>left in stock: {coupon.amount}</div>
                        <div>category: {coupon.category}</div>
                        <div className="badge text-bg-primary text-wrap">price: {coupon.price}</div>
                        <div>start date: {coupon.startDate}</div>
                        <div>end date: {coupon.endDate}</div>
                    </div>
                    <a onClick={() => onClickHandler()} className={btnClass}>{btnText}</a>
                </div>
            </div>
        </>
    );
}