import React, { useEffect, useState } from 'react';
import { Form, Button } from 'react-bootstrap';
import { clientTypes, submit, validateLoginForm } from './LoginFunctions';
import './LoginComponent.css';
import { useDispatch, useSelector } from 'react-redux';
import { setUser } from '../../state/auth/authSlice';
import { Navigate, useNavigate } from 'react-router-dom';

export default function LoginComponent() {

    const [loginData, setLoginData] = useState({
        clientType: "",
        email: "",
        password: ""
    });
    const [isValid, setIsValid] = useState(false);

    const navigate = useNavigate();

    const dispatch = useDispatch();
    const authCondition = useSelector(state => state.auth);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setLoginData(prevState => ({
            ...prevState,
            [name]: value
        }));
    };

    const handleFormSubmit = async (e) => {
        e.preventDefault();
        const token = await submit(loginData);
        dispatch(setUser(token));
        
        // TODO: remove it, this one only for test
        // navigation to the protected route
        navigate('/login');
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
                        <Form.Group controlId='clientTypeSelector'>
                            <Form.Label>סוג משתמש:</Form.Label>
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
                            <Form.Label>שם משתמש:</Form.Label>
                            <Form.Control
                                type="text"
                                name="email"
                                value={loginData.email}
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