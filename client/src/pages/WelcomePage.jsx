import axios from "axios";
import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { Modal, Button } from 'react-bootstrap';

import { setPurchasedCoupons } from "../state/customer/customerSlice";
import FilterComponent from "../components/Filter/FilterComponent";
import CouponList from "../components/Coupon/CouponList";
import { Modal, Button } from 'react-bootstrap'; 
import '../components/Forms/Welcome/WelcomePageBackground.css';

export default function WelcomePage() {
    const userId = useSelector(store => store.auth.id);
    const purchasedCoupons = useSelector(store => store.customer.purchasedCoupons);
    const [couponList, setCouponList] = useState([]);
    const [filteredCoupons, setFilteredCoupons] = useState([]);
    const [filter, setFilter] = useState({});
    const [showThankYou, setShowThankYou] = useState(false);
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const fetchData = async () => {
        try {
            const apiUrl = process.env.REACT_APP_API_URL;
            const response = await axios.get(`${apiUrl}/coupons/active`);

            // Remove coupons that already purchaced
            const coupons = response.data.filter(
                c => !purchasedCoupons.some(p => c.id === p.id));

            setCouponList(coupons);
            setFilteredCoupons(couponList);
        } catch (error) {
            console.error("Error fetching coupons:", error);
        }
    }

    const onClickFunction = (coupon) => { // Updated method name
        if (!userId) {
            navigate("/login");
            return;
        }
        const apiUrl = process.env.REACT_APP_API_URL;
        axios.post(`${apiUrl}/api/v1/customers/${userId}/purchase/${coupon.id}`)
            .then(() => {
                dispatch(setPurchasedCoupons([...purchasedCoupons, coupon]));
                setShowThankYou(true); // Show the thank you modal on successful purchase
            }).catch(error => console.error("Error purchasing coupon:", error));
    }

    // Fetch coupons on load
    useEffect(() => {
        fetchData();
    }, [,purchasedCoupons]);

    // Apply filter
    useEffect(() => {
        if (Object.entries(filter).length === 0) {
            setFilteredCoupons([...couponList]);
        } else {
            const temp = couponList.filter(coupon => {
                if (filter.filterBy === 'category') {
                    return coupon.category.toLowerCase() === filter.filterValue.toLowerCase();
                }
                if (filter.filterBy === 'price') {
                    return coupon.price <= filter.filterValue;
                }
                return true;
            });
            setFilteredCoupons(temp);
        }
    }, [filter, couponList]);

    const handleClose = () => setShowThankYou(false); // Function to close the modal

    return (
        <div className="welcomePageBackground">
            <h1 className="text-center">Available Coupons</h1>
            <FilterComponent setFilter={setFilter} />
            <CouponList
                coupons={filteredCoupons}
                onClickFunction={onClickFunction}
                btnClass="success"
                btnText="Purchase"
            />

            {/* Thank You Modal */}
            <Modal show={showThankYou} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Thank You!</Modal.Title>
                </Modal.Header>
                <Modal.Body>Thank you for buying the coupon!</Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>
        </div>
    );
}
