import React, { useEffect, useState } from 'react';
import { Button, Modal } from 'react-bootstrap';
import axios from 'axios';

const DeleteCompanyModal = ({ show, onHide, companyToDelete, setCompanyToDelete, setCompanies }) => {
    const [activeCouponsCount, setActiveCouponsCount] = useState(0);

    useEffect(() => {
        if (companyToDelete) {
            // Fetch active coupons when the modal is opened
            axios.get(`${process.env.REACT_APP_API_URL}/api/v1/admin/companies/${companyToDelete}/coupons`)
                .then(response => {
                    setActiveCouponsCount(response.data.length);
                })
                .catch(error => {
                    console.error('Error fetching active coupons:', error);
                });
        }
    }, [companyToDelete]);

    const confirmDelete = () => {
        if (!companyToDelete) return;

        axios.delete(`${process.env.REACT_APP_API_URL}/api/v1/admin/companies/${companyToDelete}`)
            .then(() => {
                setCompanies(companies => companies.filter(company => company.id !== companyToDelete));
                onHide(); // Close the modal after deletion
                setCompanyToDelete(null); // Reset the company to delete
            })
            .catch(error => {
                console.error('Error deleting company:', error);
            });
    };

    return (
        <Modal show={show} onHide={onHide}>
            <Modal.Header closeButton>
                <Modal.Title>Confirm Deletion</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                {activeCouponsCount > 0 ? (
                    <p>{`This company has ${activeCouponsCount} active coupons and cannot be deleted.`}</p>
                ) : (
                    <p>Are you sure you want to delete this company?</p>
                )}
            </Modal.Body>
            <Modal.Footer>
                <Button variant="secondary" onClick={onHide}>
                    Cancel
                </Button>
                <Button 
                    variant="danger" 
                    onClick={confirmDelete} 
                    disabled={activeCouponsCount > 0} // Disable if there are active coupons
                >
                    Delete
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default DeleteCompanyModal;
