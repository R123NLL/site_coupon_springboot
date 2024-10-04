import axios from "axios";
import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import CouponComponent from "../components/Coupon/CouponComponent";
import FilterComponent from "../components/Filter/FilterComponent";
import { useNavigate } from "react-router-dom"; 
import '../components/Forms/WelcomePageBackground.css'; 

export default function WelcomePage() {
    const userId = useSelector(store => store.auth.id);
    const [couponList, setCouponList] = useState([]);
    const [filteredCoupons, setFilteredCoupons] = useState([]);
    const [filter, setFilter] = useState({});
    const navigate = useNavigate(); 

    const fetchData = async () => {
        try {
            const apiUrl = process.env.REACT_APP_API_URL;
            const response = await axios.get(`${apiUrl}/coupons/active`); // Fetch all available coupons
            setCouponList(response.data);
            setFilteredCoupons(response.data);
        } catch (error) {
            console.error("Error fetching coupons:", error);
        }
    }

    // Function to handle coupon purchase
    const handlePurchase = async (coupon) => {
        if (!userId) {
            // If not logged in, redirect to login page
            navigate("/login"); 
            return;
        }

        try {
            const apiUrl = process.env.REACT_APP_API_URL;
            await axios.post(`${apiUrl}/api/v1/customers/${userId}/purchase/${coupon.id}`); // Correct API endpoint for purchasing a coupon
            // Fetch updated coupon list after successful purchase
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
        if (Object.entries(filter).length === 0) {
            setFilteredCoupons([...couponList]); // Use spread "..." to create a new array
        } else {
            // Apply filters
            const temp = couponList.filter(coupon => {
                if (filter.filterBy === 'category') {
                    return coupon.category.toLowerCase() === filter.filterValue.toLowerCase();
                }
                if (filter.filterBy === 'price') {
                    return coupon.price <= filter.filterValue;
                }
                return true; // If no valid filter, include all
            });
            setFilteredCoupons(temp);
        }
    }, [filter, couponList]);

    return (
        <div className="welcomePageBackground">
            <h1 className="text-center">Available Coupons</h1>
            <FilterComponent getFilter={setFilter} />
            <div className="container d-flex justify-content-evenly flex-wrap">
                {
                    filteredCoupons.map(c => (
                        <CouponComponent 
                            key={c.id} 
                            btnClass={"primary"} // Button class
                            btnText={"Buy Now"} // Button text for purchasing the coupon
                            onClick={() => handlePurchase(c)} // Handle purchase
                            coupon={c} 
                        />
                    ))
                }
            </div>
        </div>
    );
}
