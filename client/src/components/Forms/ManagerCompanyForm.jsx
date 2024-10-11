import React, { useState } from 'react';
import { Button, Container, Table, Modal, Form } from 'react-bootstrap';
import axios from 'axios';

const ManagerCompanyForm = ({ companies, setCompanies }) => {
    const [showModifyModal, setShowModifyModal] = useState(false); // State for modal visibility
    const [selectedCompany, setSelectedCompany] = useState(null); // State to hold the selected company
    const [updatedName, setUpdatedName] = useState(''); // State to hold the updated company name
    const [updatedEmail, setUpdatedEmail] = useState(''); // State to hold the updated email
    const [updatedPassword, setUpdatedPassword] = useState(''); // State to hold the updated password

    // Handle opening the modify modal and setting the selected company
    const handleModify = (company) => {
        setSelectedCompany(company);
        setUpdatedName(company.name); // Set current name in the input field
        setUpdatedEmail(company.email || ''); // Set current email in the input field
        setUpdatedPassword(''); // Password should be entered anew
        setShowModifyModal(true);
    };

    // Handle saving the modified company
    const handleSaveChanges = () => {
        axios.put(`${process.env.REACT_APP_API_URL}/api/v1/admin/companies/${selectedCompany.id}`, {
            ...selectedCompany,
            name: updatedName,
            email: updatedEmail,
            password: updatedPassword // Include password change if provided
        })
        .then(response => {
            setCompanies(companies.map(company => 
                company.id === selectedCompany.id 
                ? { ...company, name: updatedName } // Update name locally (email is not shown in table)
                : company
            ));
            setShowModifyModal(false); // Close modal
        })
        .catch(error => {
            console.error('Error updating company:', error);
        });
    };

    const handleDelete = (id) => {
        const confirmDelete = window.confirm("Are you sure you want to delete this company?");
        if (confirmDelete) {
            axios.delete(`${process.env.REACT_APP_API_URL}/api/v1/admin/companies/${id}`)
                .then(() => {
                    setCompanies(companies.filter(company => company.id !== id)); // Update company list
                })
                .catch(error => {
                    console.error('Error deleting company:', error);
                });
        }
    };

    return (
        <Container> 
            <h1>Manage Companies</h1>
            <br />
            <h2>Current Companies</h2>
            <Table striped bordered hover>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    {companies.map((company) => (
                        <tr key={company.id}>
                            <td>{company.name}</td>
                            <td>
                                <Button variant="primary" onClick={() => handleModify(company)}>Modify</Button>{' '}
                                <Button variant="danger" onClick={() => handleDelete(company.id)}>Delete</Button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>

            {/* Modal for Modifying Company */}
            <Modal show={showModifyModal} onHide={() => setShowModifyModal(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>Modify Company</Modal.Title>
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
                    <Button variant="secondary" onClick={() => setShowModifyModal(false)}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={handleSaveChanges}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
        </Container>
    );
};

export default ManagerCompanyForm;
