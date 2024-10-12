import React, { useState } from 'react';
import { Container } from 'react-bootstrap';
import axios from 'axios';
import ModifyCouponModal from './Modal/ModifyCouponModal'; 
import CouponTable from './Modal/CouponTable'; 

const CompanyCouponForm = ({ companyId, companyName, coupons, setCoupons }) => {
    const [showModifyModal, setShowModifyModal] = useState(false);
    const [selectedCoupon, setSelectedCoupon] = useState(null);
    const [updatedCouponData, setUpdatedCouponData] = useState({});

    const handleDelete = (id) => {
        const confirmDelete = window.confirm("Are you sure you want to delete this coupon?");
        if (confirmDelete) {
            axios.delete(`${process.env.REACT_APP_API_URL}/api/v1/companies/${companyId}/coupons/${id}`)
                .then(() => {
                    setCoupons(coupons.filter(coupon => coupon.id !== id));
                })
                .catch(error => {
                    console.error('Error deleting coupon:', error);
                });
        }
    };

    const handleModify = (coupon) => {
        setSelectedCoupon(coupon);
        setUpdatedCouponData({ ...coupon });
        setShowModifyModal(true);
    };

    const handleSaveChanges = () => {
        const titleExists = coupons.some(coupon => 
            coupon.id !== selectedCoupon.id && coupon.title === updatedCouponData.title
        );

        if (titleExists) {
            alert("This coupon title already exists. Please use a different title.");
            return;
        }

        const couponRequestData = {
            couponId: selectedCoupon.id,
            title: updatedCouponData.title,
            description: updatedCouponData.description,
            category: updatedCouponData.category,
            startDate: updatedCouponData.startDate,
            endDate: updatedCouponData.endDate,
            amount: updatedCouponData.amount,
            price: updatedCouponData.price,
            image: updatedCouponData.image
        };

        axios.put(`${process.env.REACT_APP_API_URL}/api/v1/companies/${companyId}/coupons`, couponRequestData)
            .then(response => {
                setCoupons(coupons.map(coupon => coupon.id === selectedCoupon.id ? { ...coupon, ...updatedCouponData } : coupon));
                setShowModifyModal(false);
            })
            .catch(error => {
                console.error('Error updating coupon:', error);
                alert("An error occurred while updating the coupon. Please try again.");
            });
    };

    return (
        <Container> 
            <h1>{companyName}</h1>
            <br />
            <h2>Current Coupons</h2>
            <CouponTable 
                coupons={coupons} 
                handleModify={handleModify} 
                handleDelete={handleDelete} 
            />

            <ModifyCouponModal 
                show={showModifyModal} 
                onHide={() => setShowModifyModal(false)} 
                coupon={selectedCoupon} 
                onSave={handleSaveChanges} 
                updatedCouponData={updatedCouponData} 
                setUpdatedCouponData={setUpdatedCouponData} 
            />
        </Container>
    );
};

export default CompanyCouponForm;
