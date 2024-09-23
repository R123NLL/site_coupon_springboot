import { useState, useEffect } from 'react';
import axios from 'axios';
import { Table, Container, Row, Col, Form, Button } from 'react-bootstrap';

const Coupons = () => {
    const [companies, setCompanies] = useState([]);
    const [coupons, setCoupons] = useState([]);
    const [filteredCoupons, setFilteredCoupons] = useState([]);
    const [selectedCompanies, setSelectedCompanies] = useState('all');

    useEffect(() => {
        axios.get('http://localhost:8080/companies')
        .then(response => {
            setCompanies(response.data);
        });

        axios.get('http://localhost:8080/coupons')
        .then(response => {
            setCoupons(response.data);
        });

        if (selectedCompanies === 'all') {
            setFilteredCoupons(coupons);
        } else {
            setFilteredCOupons(coupons.filter(coupon => coupon.company.id === Number(selectedCompanies)));
        }
    }, [selectedWriters, books]);
  
    const handleDelete = (id) => {
        axios.get(`http://localhost:8080/books/${id}`)
            .then(() => {
                setBooks(books.filter(book => book.id !== id));
            });
    };
    



export default function CompanyPage(){
    return (
        <h1>Company Page</h1>
    )
}