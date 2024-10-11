import React, { useState } from 'react';
import { Button, Container, Form, Table } from 'react-bootstrap';
import axios from 'axios';
import useCouponInitialState from '../Coupon/CouponInitialState';



const CompanyCouponForm = ({ companyId, companyName, coupons, setCoupons }) => {
    const [newCouponData, setNewCouponData] = useCouponInitialState();
    const handleDelete = (id) => {
        const confirmDelete = window.confirm("Are you sure you want to delete this coupon?");
        if (confirmDelete) {
            axios.delete(`${process.env.REACT_APP_API_URL}/api/v1/companies/${companyId}/coupons/${id}`)
                .then(() => {
                    setCoupons(coupons.filter(coupon => coupon.id !== id)); // Update coupon list
                })
                .catch(error => {
                    console.error('Error deleting coupon:', error);
                });
        }
    };

    // Function to handle form input changes
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewCouponData({ ...newCouponData, [name]: value });
    };

    return (
        <Container> 
            <h1>{companyName}</h1>
            <br />
            <h2>Current Coupons</h2>
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
                    {coupons.map((coupon) => (
                        <tr key={coupon.id}>
                            <td>{coupon.title}</td>
                            <td>{coupon.description}</td>
                            <td>{coupon.category}</td>
                            <td>{coupon.price}</td>
                            <td>{coupon.amount}</td>
                            <td>{coupon.startDate}</td>
                            <td>{coupon.endDate}</td>
                            <td>
                                <Button variant="danger" onClick={() => handleDelete(coupon.id)}>Delete</Button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>
            </Container>
    );
};

export default CompanyCouponForm;
