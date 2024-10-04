import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useSelector } from 'react-redux'; 
import AddCouponForm from '../components/Forms/AddCouponForm'; 
import CompanyCouponForm from '../components/Forms/CompanyCouponForm'; 
import { Table, Container, Row, Col } from 'react-bootstrap';
import '../components/Forms/CompanyCouponForm.css'; 

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
        <div className="background">
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
                    <AddCouponForm 
                        companyId={companyId} 
                        onCouponAdded={(newCoupon) => setCoupons([...coupons, newCoupon])} 
                    />
                </Col>
            </Row>
        </Container>
        </div>
    );
}
