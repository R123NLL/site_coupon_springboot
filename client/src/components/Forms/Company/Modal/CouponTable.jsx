import React from 'react';
import {Table} from 'react-bootstrap';
import CouponRow from './CouponRow'; 

const CouponTable = ({ coupons, handleModify, handleDelete }) => {
    return (
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Category</th>
                    <th>Price</th>
                    <th>Amount</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                {coupons.map(coupon => (
                    <CouponRow
                        key={coupon.id}
                        coupon={coupon}
                        handleModify={handleModify}
                        handleDelete={handleDelete}
                    />
                ))}
            </tbody>
        </Table>
    );
};

export default CouponTable;
