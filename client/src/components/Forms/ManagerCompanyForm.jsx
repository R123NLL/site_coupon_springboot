import React from 'react';
import { Button, Container, Table } from 'react-bootstrap';
import axios from 'axios';

const ManagerCompanyForm = ({ companies, setCompanies }) => {
    
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
                                <Button variant="danger" onClick={() => handleDelete(company.id)}>Delete</Button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </Table>
        </Container>
    );
};

export default ManagerCompanyForm;
