import React from 'react';
import { Button } from 'react-bootstrap';

const CouponRow = ({ coupon, handleModify, handleDelete }) => {
    return (
        <tr>
            <td>{coupon.title}</td>
            <td>{coupon.description}</td>
            <td>{coupon.category}</td>
            <td>{coupon.price}</td>
            <td>{coupon.amount}</td>
            <td>{coupon.startDate}</td>
            <td>{coupon.endDate}</td>
            <td>
                <Button variant="primary" onClick={() => handleModify(coupon)}>Modify</Button>{' '}
                <Button variant="danger" onClick={() => handleDelete(coupon.id)}>Delete</Button>
            </td>
        </tr>
    );
};

export default CouponRow;
