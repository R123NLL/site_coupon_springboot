import React from 'react';
import { Button } from 'react-bootstrap';

const CompanyRow = ({ company, onModify, onDelete }) => {
    return (
        <tr>
            <td>{company.name}</td>
            <td>{company.email}</td>
            <td>
                <Button variant="primary" onClick={() => onModify(company)}>Modify</Button>{' '}
                <Button variant="danger" onClick={() => onDelete(company)}>Delete</Button>
            </td>
        </tr>
    );
};

export default CompanyRow;
