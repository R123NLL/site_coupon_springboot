import React, { useState } from 'react';
import { Button, Form, Modal } from 'react-bootstrap';
import axios from 'axios';
import useCouponInitialState from '../../Coupon/CouponInitialState';

const AddCouponForm = ({ companyId, onCouponAdded }) => {
    const { newCouponData, setNewCouponData } = useCouponInitialState();
    const [showModal, setShowModal] = useState(false); 

    // Function to check if the coupon already exists
    const checkCouponExists = async (title) => {
        const apiUrl = process.env.REACT_APP_API_URL;
        try {
            const response = await axios.get(`${apiUrl}/api/v1/companies/${companyId}/coupons`); // Fetch existing coupons
            return response.data.some(coupon => coupon.title === title); // Check if the title exists
        } catch (error) {
            console.error('Error fetching existing coupons:', error);
            return false; // In case of an error, assume the coupon does not exist
        }
    };

    const handleAdding = async () => {
        const titleExists = await checkCouponExists(newCouponData.title);
        if (titleExists) {
            alert("This coupon already exists. Please use a different title."); // Alert if the coupon already exists
            return;
        }

        const apiUrl = process.env.REACT_APP_API_URL;
        const requestUrl = `${apiUrl}/api/v1/companies/${companyId}/coupons`;

        axios.post(requestUrl, newCouponData)
            .then(response => {
                const newCoupon = response.data;
                onCouponAdded(newCoupon);
                handleClose();
                setNewCouponData({
                    title: '',
                    description: '',
                    category: 'FOOD',
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

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewCouponData({ ...newCouponData, [name]: value });
    };

    const handleClose = () => {
        setShowModal(false);
    };

    const handleShow = () => {
        setShowModal(true);
    };

    return (
        <>
            <Button style={{ marginLeft: '12px' }} variant="primary" onClick={handleShow}>
                Add New Coupon
            </Button>

            <Modal show={showModal} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Add A New Coupon</Modal.Title>
                </Modal.Header>
                <Modal.Body>
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
                                <option value="CLOTHING">CLOTHING</option>
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
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={handleAdding}>
                        Add Coupon
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
};

export default AddCouponForm;
