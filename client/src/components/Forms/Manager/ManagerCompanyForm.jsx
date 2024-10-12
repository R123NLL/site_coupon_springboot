import React, { useState } from 'react';
import { Button, Container, Table } from 'react-bootstrap';
import axios from 'axios';
import '../../style/outline.css';
import CompanyRow from './Modal/CompanyRow';
import ModifyCompanyModal from './Modal/ModifyCompanyModal';
import DeleteCompanyModal from './Modal/DeleteCompanyModal';

const ManagerCompanyForm = ({ companies, setCompanies }) => {
    const [showModifyModal, setShowModifyModal] = useState(false);
    const [selectedCompany, setSelectedCompany] = useState(null);
    const [updatedName, setUpdatedName] = useState('');
    const [updatedEmail, setUpdatedEmail] = useState('');
    const [updatedPassword, setUpdatedPassword] = useState('');
    const [showDeleteModal, setShowDeleteModal] = useState(false);
    const [companyToDelete, setCompanyToDelete] = useState(null);

    const handleModify = (company) => {
        setSelectedCompany(company);
        setUpdatedName(company.name);
        setUpdatedEmail(company.email || '');
        setUpdatedPassword('');
        setShowModifyModal(true);
    };

    const handleSaveChanges = () => {
        const nameExists = companies.some(company => 
            company.id !== selectedCompany.id && company.name === updatedName
        );

        const emailExists = companies.some(company => 
            company.id !== selectedCompany.id && company.email === updatedEmail
        );

        if (nameExists) {
            alert("This company name already exists. Please use a different name.");
            return;
        }

        if (emailExists) {
            alert("This email already exists. Please use a different email.");
            return;
        }

        axios.put(`${process.env.REACT_APP_API_URL}/api/v1/admin/companies`, {
            companyId: selectedCompany.id,
            name: updatedName,
            email: updatedEmail,
            password: updatedPassword
        })
        .then(response => {
            setCompanies(companies.map(company => 
                company.id === selectedCompany.id 
                ? { ...company, name: updatedName, email: updatedEmail } 
                : company
            ));
            setShowModifyModal(false);
        })
        .catch(error => {
            console.error('Error updating company:', error);
        });
    };

    const handleDelete = (company) => {
        setCompanyToDelete(company.id); // Set the ID of the company to delete
        setShowDeleteModal(true); // Show the delete modal
    };

    return (
        <Container>
            <h1>Manage Companies</h1>
            <br />
            <h2>Current Companies</h2>
            <Table striped bordered hover size="lg">
                <thead>
                    <tr>
                        <th className="text-left py-1 px-2">Name</th>
                        <th className="text-left py-1 px-2">Email</th>
                        <th className="text-left py-1 px-2">Action</th>
                    </tr>
                </thead>
                <tbody>
                    {companies.map((company) => (
                        <CompanyRow 
                            key={company.id} 
                            company={company} 
                            onModify={handleModify} 
                            onDelete={handleDelete} 
                        />
                    ))}
                </tbody>
            </Table>

            <ModifyCompanyModal 
                show={showModifyModal} 
                onHide={() => setShowModifyModal(false)} 
                company={selectedCompany} 
                onSave={handleSaveChanges} 
                updatedName={updatedName} 
                setUpdatedName={setUpdatedName} 
                updatedEmail={updatedEmail} 
                setUpdatedEmail={setUpdatedEmail} 
                updatedPassword={updatedPassword} 
                setUpdatedPassword={setUpdatedPassword} 
            />

            <DeleteCompanyModal 
                show={showDeleteModal} 
                onHide={() => setShowDeleteModal(false)} 
                companyToDelete={companyToDelete} 
                setCompanyToDelete={setCompanyToDelete} 
                setCompanies={setCompanies} 
            />
        </Container>
    );
};

export default ManagerCompanyForm;
