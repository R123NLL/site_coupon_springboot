import React, { useEffect, useState } from 'react';
import { Form, Button, Alert, Modal } from 'react-bootstrap';
import { clientTypes, submit, validateLoginForm } from './LoginFunctions';
import './LoginComponent.css';
import { useDispatch, useSelector } from 'react-redux';
import { setUser } from '../../state/auth/authSlice';
import { useNavigate } from 'react-router-dom'; 
import AddCustomerForm from '../Forms/Register/AddCustomerForm';

export default function LoginComponent() {
    const [loginData, setLoginData] = useState({
        clientType: "",
        email: "",
        password: ""
    });
    const [isValid, setIsValid] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');
    const [showRegisterForm, setShowRegisterForm] = useState(false); 
    const [successMessage, setSuccessMessage] = useState(''); // New state for success message

    const role = useSelector(state => state.auth.role);
    const navigate = useNavigate(); 
    const dispatch = useDispatch();

    useEffect(() => {
        if (role) navigate(`/${role}`);
    }, [role, navigate]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setLoginData(prevState => ({
            ...prevState,
            [name]: value
        }));
        setErrorMessage(''); // Clear the error message when input changes
    };

    const handleFormSubmit = async (e) => {
        e.preventDefault();
        try {
            const token = await submit(loginData);
            if (!token) {
                // If no token is returned, this indicates an error in login
                throw new Error('Invalid username or password');
            }
            dispatch(setUser(token));
        } catch (error) {
            console.error('Login error:', error);
            setErrorMessage('.אימייל או סיסמא שגויים, נא לנסות שוב');
        }
    };

    useEffect(() => {
        const validation = validateLoginForm(loginData);
        setIsValid(validation);
    }, [loginData]);

    return (
        <div className="LoginComponent login-bg">
            <div className="container d-flex align-items-center justify-content-center">
                <div className="form justify-content-center">
                    <Form onSubmit={handleFormSubmit}>
                        <Form.Group controlId='clientTypeSelector'>
                            <Form.Label>:סוג משתמש</Form.Label>
                            <Form.Control
                                as="select"
                                name='clientType'
                                value={loginData.clientType}
                                onChange={handleInputChange}
                                required
                            >
                                <option value="">נא לבחור</option>
                                {
                                    clientTypes.map(t => (
                                        <option key={t.id} value={t.role}>
                                            {t.name}
                                        </option>
                                    ))
                                }
                            </Form.Control>
                        </Form.Group>
                        <Form.Group controlId="email">
                            <Form.Label>:אימייל</Form.Label>
                            <Form.Control
                                type="text"
                                name="email"
                                value={loginData.email}
                                onChange={handleInputChange}
                                required
                            />
                        </Form.Group>
                        <Form.Group controlId="password">
                            <Form.Label>:סיסמה</Form.Label>
                            <Form.Control
                                type="password"
                                name="password"
                                value={loginData.password}
                                onChange={handleInputChange}
                                required
                            />
                        </Form.Group>
                        <Button variant="primary" type="submit" className='mt-3' disabled={!isValid}>
                            כניסה
                        </Button>
                        <div className="row mt-1">
                            <div className="mt- text-start">
                                <Button variant="link" onClick={() => setShowRegisterForm(true)}>
                                    להרשמה
                                </Button>
                            </div>
                        </div>
                    </Form>
                    {errorMessage && <Alert variant="danger" className="mt-3">{errorMessage}</Alert>}
                    {successMessage && <Alert variant="success" className="mt-3">{successMessage}</Alert>} {/* Display success message */}
                </div>
            </div>

            {/* Register Form */}
            <Modal show={showRegisterForm} onHide={() => setShowRegisterForm(false)}>
                <Modal.Header closeButton>
                    <Modal.Title>הרשמה</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <AddCustomerForm 
                        onCustomerAdded={(newCustomer) => {
                            console.log('Customer added:', newCustomer);
                            setShowRegisterForm(false); // Close the form after successful addition
                            setSuccessMessage('Registration complete!'); // Set success message
                        }} 
                    />
                </Modal.Body>
            </Modal>
        </div>
    );
}
