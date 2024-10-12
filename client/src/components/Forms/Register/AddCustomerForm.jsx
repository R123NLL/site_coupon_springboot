import React, { useState, useEffect } from 'react';
import { Button, Form, Container, Alert } from 'react-bootstrap';
import axios from 'axios';

const AddCustomerForm = ({ onCustomerAdded }) => { 
    const [newCustomerData, setNewCustomerData] = useState({
        firstName: '',
        lastName: '',
        email: '',
        password: ''
    });
    const [errorMessage, setErrorMessage] = useState('');
    const [existingCustomers, setExistingCustomers] = useState([]);
    const [loadingCustomers, setLoadingCustomers] = useState(true);
    const apiUrl = process.env.REACT_APP_API_URL;

    // Fetch existing customers from the server
    useEffect(() => {
        const fetchCustomers = async () => {
            try {
                const response = await axios.get(`process.env.REACT_APP_API_URL/api/v1/admin/customers`); // Adjust this endpoint
                setExistingCustomers(response.data);
            } catch (error) {
                console.error('Error fetching customers:', error);
            } finally {
                setLoadingCustomers(false);
            }
        };

        fetchCustomers();
    }, []);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewCustomerData({ ...newCustomerData, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Prevent submission if loading customers
        if (loadingCustomers) {
            window.alert('Please wait while we load existing customers.');
            return;
        }

        // Check if the customer email already exists
        const emailExists = existingCustomers.some(customer => 
            customer.email && customer.email.toLowerCase() === newCustomerData.email.toLowerCase()
        );

        if (emailExists) {
            // Display an alert when the email is already in use
            window.alert('The email address is already in use. Please use a different email.');
            return;
        }
    
        try {
            const response = await axios.post(`${apiUrl}/api/v1/admin/customers`, newCustomerData);
            onCustomerAdded(response.data); // Notify parent to update the customer list
            setNewCustomerData({ firstName: '', lastName: '', email: '', password: '' }); // Reset form
            setErrorMessage(''); // Clear any error messages
        } catch (error) {
            console.error('Error adding customer:', error);
            setErrorMessage('An error occurred while adding the customer. Please try again.');
        }
    };

    return (
        <Container>
            <Form onSubmit={handleSubmit}>
                {errorMessage && <Alert variant="danger">{errorMessage}</Alert>}

                <Form.Group controlId="firstName">
                    <Form.Label>First Name:</Form.Label>
                    <Form.Control
                        type="text"
                        name="firstName"
                        value={newCustomerData.firstName}
                        onChange={handleInputChange}
                        required
                    />
                </Form.Group>

                <Form.Group controlId="lastName">
                    <Form.Label>Last Name:</Form.Label>
                    <Form.Control
                        type="text"
                        name="lastName"
                        value={newCustomerData.lastName}
                        onChange={handleInputChange}
                        required
                    />
                </Form.Group>

                <Form.Group controlId="email">
                    <Form.Label>Email:</Form.Label>
                    <Form.Control
                        type="email"
                        name="email"
                        value={newCustomerData.email}
                        onChange={handleInputChange}
                        required
                    />
                </Form.Group>

                <Form.Group controlId="password">
                    <Form.Label>Password:</Form.Label>
                    <Form.Control
                        type="password"
                        name="password"
                        value={newCustomerData.password}
                        onChange={handleInputChange}
                        required
                    />
                </Form.Group>
                <br />
                <Button variant="success" type="submit">
                    Add Customer
                </Button>
            </Form>
        </Container>
    );
};

export default AddCustomerForm;
