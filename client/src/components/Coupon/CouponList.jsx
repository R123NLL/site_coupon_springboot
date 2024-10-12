import React from 'react';
import CouponComponent from './CouponComponent';

const CouponList = ({ coupons, onClickFunction, btnClass = "primary", btnText = "Buy Now" }) => { // Added props with default values
    return (
        <div className="container d-flex justify-content-start flex-wrap">
            {
                coupons.map(c => (
                    <CouponComponent 
                        key={c.id} 
                        btnClass={btnClass} // Pass btnClass prop
                        btnText={btnText} // Pass btnText prop
                        onClick={(coupon, quantity) => onClickFunction(coupon, quantity)} 
                        coupon={c} 
                    />
                ))
            }
        </div>
    );
};

export default CouponList;
