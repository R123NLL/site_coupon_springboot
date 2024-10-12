import React, { useState } from 'react';
import { Button, Form, Container, Alert } from 'react-bootstrap';
import axios from 'axios';

const AddCompanyForm = ({ onCompanyAdded, existingCompanies = [] }) => { // Default value for existingCompanies
    const [showForm, setShowForm] = useState(false); // State to toggle form visibility
    const [newCompanyData, setNewCompanyData] = useState({
        name: '',
        email: '',
        password: '',
        coupons: []
    });
    const [errorMessage, setErrorMessage] = useState('');

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewCompanyData({ ...newCompanyData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
    
        // Check if the company name already exists
        const companyExists = existingCompanies.some(company => 
            company.name && company.name.toLowerCase() === newCompanyData.name.toLowerCase()
        );
    
        if (companyExists) {
            setErrorMessage('The company name already exists. Please use a different name.');
            return;
        }
    
        // Check if the company email already exists
        const emailExists = existingCompanies.some(company => 
            company.email && company.email.toLowerCase() === newCompanyData.email.toLowerCase()
        );
    
        if (emailExists) {
            setErrorMessage('The email address already exists. Please use a different email.');
            return;
        }
    
        try {
            const apiUrl = process.env.REACT_APP_API_URL;
            const response = await axios.post(`${apiUrl}/api/v1/admin/companies`, newCompanyData);
            onCompanyAdded(response.data); // Notify parent to update the company list
            setNewCompanyData({ name: '', email: '', password: '', coupons: [] }); // Reset form
            setErrorMessage(''); // Clear any error messages
            setShowForm(false); // Hide form after successful submission
        } catch (error) {
            // This block will catch actual API errors
            console.error('Error adding company:', error);
            setErrorMessage('An error occurred while adding the company. Please try again.');
        }
    };
    
    
    

    return (
        <Container>
            <Button variant="primary" onClick={() => setShowForm(!showForm)}>
                {showForm ? "Cancel" : "Add New Company"}
            </Button>
            
            {showForm && (
                <Form onSubmit={handleSubmit}>
                    {errorMessage && <Alert variant="danger">{errorMessage}</Alert>}

                    <Form.Group controlId="companyName">
                        <Form.Label>Company Name</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Enter company name"
                            name="name"
                            value={newCompanyData.name}
                            onChange={handleInputChange}
                            required
                        />
                    </Form.Group>

                    <Form.Group controlId="companyEmail">
                        <Form.Label>Email</Form.Label>
                        <Form.Control
                            type="email"
                            placeholder="Enter company email"
                            name="email"
                            value={newCompanyData.email}
                            onChange={handleInputChange}
                            required
                        />
                    </Form.Group>

                    <Form.Group controlId="companyPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control
                            type="password"
                            placeholder="Enter company password"
                            name="password"
                            value={newCompanyData.password}
                            onChange={handleInputChange}
                            required
                        />
                    </Form.Group>
                    <br></br>
                    <Button variant="success" type="submit">
                        Add Company
                    </Button>
                </Form>
            )}
        </Container>
    );
};

export default AddCompanyForm;
