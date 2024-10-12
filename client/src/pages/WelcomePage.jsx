import axios from "axios";
import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom"; 
import FilterComponent from "../components/Filter/FilterComponent";
import CouponList from "../components/Coupon/CouponList";
import { Modal, Button } from 'react-bootstrap'; 
import '../components/Forms/Welcome/WelcomePageBackground.css'; 

export default function WelcomePage() {
    const userId = useSelector(store => store.auth.id);
    const [couponList, setCouponList] = useState([]);
    const [filteredCoupons, setFilteredCoupons] = useState([]);
    const [filter, setFilter] = useState({});
    const [showThankYou, setShowThankYou] = useState(false); 
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

    const onClickFunction = async (coupon) => { // Updated method name
        if (!userId) {
            navigate("/login"); 
            return;
        }

        try {
            const apiUrl = process.env.REACT_APP_API_URL;
            await axios.post(`${apiUrl}/api/v1/customers/${userId}/purchase/${coupon.id}`);
            setShowThankYou(true); // Show the thank you modal on successful purchase
            fetchData();
        } catch (error) {
            console.error("Error purchasing coupon:", error);
        }
    }

    // Fetch coupons on load
    useEffect(() => {
        fetchData();
    }, []);

    // Apply filter
    useEffect(() => {
        
        // If filter object is empty
        if(Object.entries(filter).length === 0){
            setFilteredCoupons([...couponList]); // Use spred "..." to create new array
        }

        if(filter.filterBy === 'category'){
            const temp = couponList.filter(coupon => coupon.category.toLowerCase() === filter.filterValue.toLowerCase());
            setFilteredCoupons(temp);
        }

        if(filter.filterBy === 'price'){
            const temp = couponList.filter(coupon => coupon.price <= filter.filterValue);
            setFilteredCoupons(temp);
        }
    }, [filter])

    const handleClose = () => setShowThankYou(false); // Function to close the modal

    return (
        <div className="welcomePageBackground">
            <h1 className="text-center">Available Coupons</h1>
            <FilterComponent getFilter={setFilter} />
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
