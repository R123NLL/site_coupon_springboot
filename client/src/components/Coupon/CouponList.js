import React from 'react';
import CouponComponent from './CouponComponent';

const CouponList = ({ coupons, handlePurchase }) => {
    return (
        <div className="container d-flex justify-content-evenly flex-wrap">
            {
                coupons.map(c => (
                    <CouponComponent 
                        key={c.id} 
                        btnClass={"primary"}
                        btnText={"Buy Now"}
                        onClick={(coupon, quantity) => handlePurchase(coupon, quantity)}
                        coupon={c} 
                    />
                ))
            }
        </div>
    );
};

export default CouponList;
