import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useSelector } from 'react-redux'; 
import { Container, Row, Col } from 'react-bootstrap';
import ManagerCompanyForm from '../components/Forms/Manager/ManagerCompanyForm';
import AddCompanyForm from '../components/Forms/Manager/AddCompanyForm';
import '../components/Forms/Manager/ManagerCompanyForm.css'; 



export default function ManagerPage() {
    const companyId = useSelector(store => store.auth.id); 
    const [companies, setCompanies] = useState([]);
    
    const fetchData = async () => {
        try {
            const apiUrl = process.env.REACT_APP_API_URL;
            const response = await axios.get(`${apiUrl}/api/v1/admin/companies`);
            setCompanies(response.data);
        } catch (error) {
            console.error('Error fetching companies:', error);
        }
    };

    const handleCompanyAdded = (newCompany) => {
        setCompanies([...companies, newCompany]); // Add the new company to the existing company list
    };

    useEffect(() => {
        fetchData();
    }, [companyId]);

    return (
        <div className="managerBackground">
        <Container>
            <Row>
                <Col>
                    <ManagerCompanyForm 
                        companies={companies} 
                        setCompanies={setCompanies} 
                    />
                </Col>
            </Row>
            <Row>
                <Col>
                    <AddCompanyForm 
                        onCompanyAdded={handleCompanyAdded} 
                        existingCompanies={companies}
                    />
                </Col>
            </Row>
        </Container>
        </div>
    );
}
