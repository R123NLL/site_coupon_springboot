import React, { useEffect, useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { customerTypes, submit, validateLoginForm as validateLoginForm } from './LoginFunctions';
import './LoginComponent.css';

export default function LoginComponent() {

    const [loginData, setLoginData] = useState({});
    const [isValid, setIsValid] = useState(false);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setLoginData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleFormSubmit = (e) => {
        e.preventDefault();
        submit(loginData);
    }

    useEffect(() => {
        const validation = validateLoginForm(loginData);
        setIsValid(validation)
    }, [loginData])

    return (
        <div className="LoginComponent login-bg">

            <div className="container d-flex align-items-center justify-content-center">
                <div className="form justify-content-center">
                    <Form onSubmit={() => null}>
                        <Form.Group controlId='customerTypeSelector'>
                            <Form.Label>סוג משתמש:</Form.Label>
                            <Form.Control
                                as="select"
                                name='customerType'
                                value={loginData.customerType}
                                onChange={handleInputChange}
                                required
                            >
                                <option value="">נא לבחור</option>
                                {
                                    customerTypes.map(t => (
                                        <option key={t.id} value={t.id}>
                                            {t.name}
                                        </option>
                                    ))
                                }
                            </Form.Control>
                        </Form.Group>
                        <Form.Group controlId="username">
                            <Form.Label>שם משתמש:</Form.Label>
                            <Form.Control
                                type="text"
                                name="username"
                                value={loginData.username}
                                onChange={handleInputChange}
                                required
                            />
                        </Form.Group>
                        <Form.Group controlId="password">
                            <Form.Label>סיסמה:</Form.Label>
                            <Form.Control
                                type="password"
                                name="password"
                                value={loginData.password}
                                onChange={handleInputChange}
                                required
                            />
                        </Form.Group>
                        <Button variant="primary" onClick={handleFormSubmit} type="submit" className='mt-3' disabled={!isValid}>
                            כניסה
                        </Button>
                    </Form>
                </div>
            </div>

        </div>
    );
}