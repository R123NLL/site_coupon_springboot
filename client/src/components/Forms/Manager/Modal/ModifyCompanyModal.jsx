import React from 'react';
import { Button, Modal, Form } from 'react-bootstrap';

const ModifyCompanyModal = ({ show, onHide, company, onSave, updatedName, setUpdatedName, updatedEmail, setUpdatedEmail, updatedPassword, setUpdatedPassword }) => {
    return (
        <Modal show={show} onHide={onHide}>
            <Modal.Header closeButton>
                <Modal.Title>Modify {company ? company.name : 'Company'}</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Group controlId="formCompanyName">
                        <Form.Label>Company Name</Form.Label>
                        <Form.Control 
                            type="text" 
                            value={updatedName} 
                            onChange={(e) => setUpdatedName(e.target.value)} 
                        />
                    </Form.Group>

                    <Form.Group controlId="formCompanyEmail">
                        <Form.Label>Company Email</Form.Label>
                        <Form.Control 
                            type="email" 
                            value={updatedEmail} 
                            onChange={(e) => setUpdatedEmail(e.target.value)} 
                        />
                    </Form.Group>

                    <Form.Group controlId="formCompanyPassword">
                        <Form.Label>New Password</Form.Label>
                        <Form.Control 
                            type="password" 
                            value={updatedPassword} 
                            onChange={(e) => setUpdatedPassword(e.target.value)} 
                            placeholder="Enter new password (optional)" 
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

export default ModifyCompanyModal;
