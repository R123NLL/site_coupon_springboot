import React from 'react';
import { Button, Modal, Form } from 'react-bootstrap';

const ModifyCouponModal = ({ show, onHide, coupon, onSave, updatedCouponData, setUpdatedCouponData }) => {
    return (
        <Modal show={show} onHide={onHide}>
            <Modal.Header closeButton>
                <Modal.Title>Modify {coupon ? coupon.title : 'Coupon'}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Group controlId="formCouponTitle">
                        <Form.Label>Coupon Title</Form.Label>
                        <Form.Control 
                            type="text" 
                            value={updatedCouponData.title} 
                            onChange={(e) => setUpdatedCouponData({ ...updatedCouponData, title: e.target.value })} 
                        />
                    </Form.Group>

                    <Form.Group controlId="formCouponDescription">
                        <Form.Label>Coupon Description</Form.Label>
                        <Form.Control 
                            type="text" 
                            value={updatedCouponData.description} 
                            onChange={(e) => setUpdatedCouponData({ ...updatedCouponData, description: e.target.value })} 
                        />
                    </Form.Group>

                    <Form.Group controlId="formCouponCategory">
                        <Form.Label>Category</Form.Label>
                        <Form.Control 
                            type="text" 
                            value={updatedCouponData.category} 
                            onChange={(e) => setUpdatedCouponData({ ...updatedCouponData, category: e.target.value })} 
                        />
                    </Form.Group>

                    <Form.Group controlId="formCouponPrice">
                        <Form.Label>Price</Form.Label>
                        <Form.Control 
                            type="number" 
                            value={updatedCouponData.price} 
                            onChange={(e) => setUpdatedCouponData({ ...updatedCouponData, price: e.target.value })} 
                        />
                    </Form.Group>

                    <Form.Group controlId="formCouponAmount">
                        <Form.Label>Amount</Form.Label>
                        <Form.Control 
                            type="number" 
                            value={updatedCouponData.amount} 
                            onChange={(e) => setUpdatedCouponData({ ...updatedCouponData, amount: e.target.value })} 
                        />
                    </Form.Group>

                    <Form.Group controlId="formCouponStartDate">
                        <Form.Label>Start Date</Form.Label>
                        <Form.Control 
                            type="date" 
                            value={updatedCouponData.startDate} 
                            onChange={(e) => setUpdatedCouponData({ ...updatedCouponData, startDate: e.target.value })} 
                        />
                    </Form.Group>

                    <Form.Group controlId="formCouponEndDate">
                        <Form.Label>End Date</Form.Label>
                        <Form.Control 
                            type="date" 
                            value={updatedCouponData.endDate} 
                            onChange={(e) => setUpdatedCouponData({ ...updatedCouponData, endDate: e.target.value })} 
                        />
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={onHide}>
                    Close
                </Button>
                <Button variant="primary" onClick={onSave}>
                    Save Changes
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default ModifyCouponModal;
