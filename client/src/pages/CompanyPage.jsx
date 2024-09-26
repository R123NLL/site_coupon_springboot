import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Table, Container, Row, Col, Button, Form } from 'react-bootstrap';

const CompanyPage = ({ companyId, companyName }) => {
    const [coupons, setCoupons] = useState([]);
    const [filteredCoupons, setFilteredCoupons] = useState([]);
    const [newCouponData, setNewCouponData] = useState({
        title: '',
        description: '',
        category: 'FOOD', // Default category
        price: '',
        amount: '',
        startDate: '',
        endDate: '',
        image: ''
    });

    useEffect(() => {
        // Fetch coupons for the specific company
        axios.get(`http://localhost:8080/api/v1/companies/${companyId}/coupons`)
            .then(response => {
                setCoupons(response.data);
                setFilteredCoupons(response.data); // Initially show all coupons
            })
            .catch(error => {
                console.error('Error fetching coupons:', error);
            });
    }, [companyId]);
    //handles the deletion part of coupons in the page
    const handleDelete = (id) => {
        axios.delete(`http://localhost:8080/api/v1/companies/coupons/${id}`)
            .then(() => {
                setCoupons(coupons.filter(coupon => coupon.id !== id));
                setFilteredCoupons(filteredCoupons.filter(coupon => coupon.id !== id));
            })
            .catch(error => {
                console.error('Error deleting coupon:', error);
            });
    };
    //handles the additon part of coupons in  the page
    const handleAdding = () => {
        axios.post(`http://localhost:8080/api/v1/companies/${companyId}/coupons`, newCouponData)
            .then(response => {
                const newCoupon = response.data;  // Assuming the response contains the added coupon
                setCoupons([...coupons, newCoupon]);  // Add the new coupon to the list
                setFilteredCoupons([...filteredCoupons, newCoupon]);  // Update filtered list
                // Clear form after successful addition
                setNewCouponData({
                    title: '',
                    description: '',
                    category: 'FOOD',  // Reset to default category
                    price: '',
                    amount: '',
                    startDate: '',
                    endDate: '',
                    image: ''
                });
            })
            .catch(error => {
                console.error('Error adding coupon:', error);
            });
    };

    // Function to handle form input changes
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewCouponData({ ...newCouponData, [name]: value });
    };

    return (
        <Container>
            <Row>
                <Col>
                    <h1>{companyName} Coupons</h1>
                    {/* Coupon Table */}
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th>Title</th>
                                <th>Description</th>
                                <th>Category</th>
                                <th>Price</th>
                                <th>Amount</th>
                                <th>Start Date</th>
                                <th>End Date</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {filteredCoupons.map((coupon) => (
                                <tr key={coupon.id}>
                                    <td>{coupon.title}</td>
                                    <td>{coupon.description}</td>
                                    <td>{coupon.category}</td>
                                    <td>{coupon.price}</td>
                                    <td>{coupon.amount}</td>
                                    <td>{coupon.startDate}</td>
                                    <td>{coupon.endDate}</td>
                                    <td>
                                        <Button variant="danger" onClick={() => handleDelete(coupon.id)}>Delete</Button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </Col>
            </Row>
            <Row>
                <Col>
                    {/* Form to add new coupon */}
                    <h2>Add New Coupon</h2>
                    <Form>
                        <Form.Group controlId="formTitle">
                            <Form.Label>Title</Form.Label>
                            <Form.Control
                                type="text"
                                name="title"
                                value={newCouponData.title}
                                onChange={handleInputChange}
                                placeholder="Enter coupon title"
                            />
                        </Form.Group>
                        <Form.Group controlId="formDescription">
                            <Form.Label>Description</Form.Label>
                            <Form.Control
                                type="text"
                                name="description"
                                value={newCouponData.description}
                                onChange={handleInputChange}
                                placeholder="Enter coupon description"
                            />
                        </Form.Group>
                        <Form.Group controlId="formCategory">
                            <Form.Label>Category</Form.Label>
                            <Form.Control
                                as="select"
                                name="category"
                                value={newCouponData.category}
                                onChange={handleInputChange}
                            >
                                <option value="FOOD">FOOD</option>
                                <option value="ELECTRICITY">ELECTRICITY</option>
                                <option value="RESTAURANT">RESTAURANT</option>
                                <option value="VACATION">VACATION</option>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group controlId="formPrice">
                            <Form.Label>Price</Form.Label>
                            <Form.Control
                                type="number"
                                name="price"
                                value={newCouponData.price}
                                onChange={handleInputChange}
                                placeholder="Enter coupon price"
                            />
                        </Form.Group>
                        <Form.Group controlId="formAmount">
                            <Form.Label>Amount</Form.Label>
                            <Form.Control
                                type="number"
                                name="amount"
                                value={newCouponData.amount}
                                onChange={handleInputChange}
                                placeholder="Enter coupon amount"
                            />
                        </Form.Group>
                        <Form.Group controlId="formStartDate">
                            <Form.Label>Start Date</Form.Label>
                            <Form.Control
                                type="date"
                                name="startDate"
                                value={newCouponData.startDate}
                                onChange={handleInputChange}
                            />
                        </Form.Group>
                        <Form.Group controlId="formEndDate">
                            <Form.Label>End Date</Form.Label>
                            <Form.Control
                                type="date"
                                name="endDate"
                                value={newCouponData.endDate}
                                onChange={handleInputChange}
                            />
                        </Form.Group>
                        <Form.Group controlId="formImage">
                            <Form.Label>Image URL</Form.Label>
                            <Form.Control
                                type="text"
                                name="image"
                                value={newCouponData.image}
                                onChange={handleInputChange}
                                placeholder="Enter image URL"
                            />
                        </Form.Group>
                        <Button variant="primary" onClick={handleAdding}>
                            Add Coupon
                        </Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    );
};

export default CompanyPage;
