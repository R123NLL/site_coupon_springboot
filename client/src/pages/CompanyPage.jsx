import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useSelector } from 'react-redux'; // Assuming you're using Redux for companyId
import AddCouponForm from '../components/Forms/AddCouponForm'; // Import the new component
import CompanyCouponForm from '../components/Forms/CompanyCouponForm'; // Import the CompanyCouponForm
import { Table, Container, Row, Col } from 'react-bootstrap';

export default function CompanyPage() {
    const companyId = useSelector(store => store.auth.id); 
    const [coupons, setCoupons] = useState([]);
    const [companyName, setCompanyName] = useState('');

    const fetchData = async () => {
        try {
            const apiUrl = process.env.REACT_APP_API_URL;
            const response = await axios.get(`${apiUrl}/api/v1/companies/${companyId}/coupons`);
            setCoupons(response.data);
            const companyResponse = await axios.get(`${apiUrl}/api/v1/companies/${companyId}`);
            setCompanyName(companyResponse.data.name);
        } catch (error) {
            console.error('Error fetching coupons:', error);
        }
    };

    useEffect(() => {
        fetchData();
    }, [companyId]);

    return (
        <Container>
            <Row>
                <Col>
                    <CompanyCouponForm 
                        companyId={companyId} 
                        companyName={companyName}
                        coupons={coupons} 
                        setCoupons={setCoupons} 
                    />
                </Col>
            </Row>
            <Row>
                <Col>
                    <h2>Add New Coupon</h2>
                    <AddCouponForm 
                        companyId={companyId} 
                        onCouponAdded={(newCoupon) => setCoupons([...coupons, newCoupon])} 
                    />
                </Col>
            </Row>
        </Container>
    );
}
