import axios from "axios";
import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom"; 
import WelcomeHeader from "../components/WelcomHeader.js"
import FilterComponent from "../components/Filter/FilterComponent";
import CouponList from "../components/Coupon/CouponList";
import { Modal, Button } from 'react-bootstrap'; // Import Modal and Button from react-bootstrap
import '../components/Forms/WelcomePageBackground.css'; 

export default function WelcomePage() {
    const userId = useSelector(store => store.auth.id);
    const [couponList, setCouponList] = useState([]);
    const [filteredCoupons, setFilteredCoupons] = useState([]);
    const [filter, setFilter] = useState({});
    const [showThankYou, setShowThankYou] = useState(false); // State for the modal
    const navigate = useNavigate(); 

    const fetchData = async () => {
        try {
            const apiUrl = process.env.REACT_APP_API_URL;
            const response = await axios.get(`${apiUrl}/coupons/active`);
            setCouponList(response.data);
            setFilteredCoupons(response.data);
        } catch (error) {
            console.error("Error fetching coupons:", error);
        }
    }

    const handlePurchase = async (coupon, quantity) => {
        if (!userId) {
            navigate("/login"); 
            return;
        }

        try {
            const apiUrl = process.env.REACT_APP_API_URL;
            await axios.post(`${apiUrl}/api/v1/customers/${userId}/purchase/${coupon.id}/${quantity}`);
            setShowThankYou(true); // Show the thank you modal on successful purchase
            fetchData();
        } catch (error) {
            console.error("Error purchasing coupon:", error);
        }
    }

    useEffect(() => {
        fetchData();
    }, []);

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
            <WelcomeHeader />
            <FilterComponent setFilter={setFilter} />
            <CouponList coupons={filteredCoupons} handlePurchase={handlePurchase} />

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
