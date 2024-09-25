import axios from "axios";
import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import CouponComponent from "../components/Coupon/CouponComponent";
import FilterComponent from "../components/Filter/FilterComponent";

export default function CustomerPage() {
    const userId = useSelector(store => store.auth.id);
    const [couponList, setCouponList] = useState([]);

    const fetchData = async () => {
        try {
            const apiUrl = process.env.REACT_APP_API_URL;
            const response = await axios.get(apiUrl + `/api/v1/customers/${userId}/coupons`);
            setCouponList(response.data);
        } catch (error) { }
    }

    const removeCoupone = async (coupon) => {

    }

    useEffect(() => {
        fetchData();
    }, []);


    return (
        <>
            <h1 className="text-center">Customer Page</h1>

            <div className="container d-flex justify-content-evenly">
                {
                    couponList.map(c => {
                        return <CouponComponent key={c.id} btnClass={"danger"} btnText={"remove"} coupon={c} />
                    })
                }
            </div>
        </>
    )
}