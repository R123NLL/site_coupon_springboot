import axios from "axios";
import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import CouponComponent from "../components/Coupon/CouponComponent";
import FilterComponent from "../components/Filter/FilterComponent";

export default function CustomerPage() {
    const userId = useSelector(store => store.auth.id);
    
    const [couponList, setCouponList] = useState([]);
    const [filteredCoupons, setFilteredCoupons] = useState([]);
    const [filter, setFilter] = useState({});

    const fetchData = async () => {
        try {
            const apiUrl = process.env.REACT_APP_API_URL;
            const response = await axios.get(apiUrl + `/api/v1/customers/${userId}/coupons`);
            setCouponList(response.data);
            setFilteredCoupons(response.data);
        } catch (error) { }
    }

    const removeCoupone = async (coupon) => {
        try {
            const apiUrl = process.env.REACT_APP_API_URL;
            const response = await axios.delete(apiUrl + `/api/v1/customers/${userId}/remove/${coupon.id}`);
            console.log(response.data);
        } catch (e){
            console.log(e);
        } finally {
            fetchData();
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

    return (
        <>
            <h1 className="text-center">Customer Page</h1>
            <FilterComponent getFilter={setFilter} />
            <div className="container d-flex justify-content-evenly">
                {
                    filteredCoupons.map(c => {
                        return <CouponComponent key={c.id} btnClass={"danger"} btnText={"remove"} onClick={removeCoupone} coupon={c} />
                    })
                }
            </div>
        </>
    )
}