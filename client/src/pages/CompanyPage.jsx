import { useState, useEffect } from 'react';
import axios from 'axios';
import { Table, Container, Row, Col, Button } from 'react-bootstrap';
import './css/text-left.css'; // Import the CSS file

const CompanyPage = ({ companyId, companyName }) => {
    const [coupons, setCoupons] = useState([]);
    const [filteredCoupons, setFilteredCoupons] = useState([]);

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

    return (
        <Container>
            <Row>
                <Col>
                <h1 className='text-left'>{companyName}</h1> {/* Display company name */}
                    <h2 className='text-left'>Coupons Table</h2>
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th className="text-left">Actions</th>
                                <th className="text-left">Image</th>
                                <th className="text-left">Start Date</th>
                                <th className="text-left">End Date</th>
                                <th className="text-left">Description</th>
                                <th className="text-left">Amount</th>
                                <th className="text-left">Price</th>
                                <th className="text-left">Category</th>
                                <th className="text-left">Title</th>
                                <th className="text-left">ID</th>
                            </tr>
                        </thead>
                        <tbody>
                            {filteredCoupons.map(coupon => (
                                <tr key={coupon.id}>
                                    <td className="text-left">
                                        <Button
                                            variant="danger"
                                            onClick={() => handleDelete(coupon.id)}
                                        >
                                            Remove Coupon
                                        </Button>
                                    </td>
                                    <td className="text-left">
                                        <img 
                                            src={coupon.image} 
                                            alt={coupon.title} 
                                            style={{ width: '50px', height: '50px' }}
                                        />
                                    </td>
                                    <td className="text-left">{coupon.startDate}</td>
                                    <td className="text-left">{coupon.endDate}</td>
                                    <td className="text-left">{coupon.description}</td>
                                    <td className="text-left">{coupon.amount}</td>
                                    <td className="text-left">${coupon.price.toFixed(2)}</td>
                                    <td className="text-left">{coupon.category}</td>
                                    <td className="text-left">{coupon.title}</td>
                                    <td className="text-left">{coupon.id}</td>
                                </tr>
                            ))}
                        </tbody>
                    </Table>
                </Col>
            </Row>
        </Container>
    );
};

export default CompanyPage;
