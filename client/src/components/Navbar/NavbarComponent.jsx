import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { useSelector, useDispatch } from 'react-redux';
import { roles } from '../../shared/common';
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { logout } from '../../state/auth/authSlice';

export default function NavbarComponent() {

    const navigate = useNavigate();
    const [dynamicItems, setDynamicItems] = useState(<></>);
    const userRole = useSelector(store => store.auth.role);
    const isAuthenticated = useSelector(store => store.auth.isAuthenticated);
    const dispatch = useDispatch();

    useEffect(() => {setMenuItems(userRole)}, [userRole]);

    const navigateTo = path => {
        navigate(path);
    }

    function setMenuItems(role) {
        switch (role) {
            case roles.customer:
                setDynamicItems(<Nav.Link onClick={() => {navigateTo("/customer")}}>My coupons</Nav.Link>)
                break;
            case roles.company:
                setDynamicItems(<Nav.Link onClick={() => {navigateTo("/company")}}>My company</Nav.Link>)
                break;
            case roles.administrator:
                setDynamicItems(<Nav.Link onClick={() => {navigateTo("/administrator")}}>Administartions</Nav.Link>)
                break;
            default:
                setDynamicItems(<></>);
                break;
        }
    }

    return (
        <Navbar expand="lg" className="bg-body-tertiary">
            <Container>
                <Navbar.Brand onClick={() => {navigateTo("/")}}>Coupons Site</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="me-auto">
                        <Nav.Link onClick={() => {navigateTo("/")}}>Home</Nav.Link>
                        {dynamicItems}
                        {isAuthenticated && <Nav.Link onClick={() => {dispatch(logout())}}>logout</Nav.Link>}
                        {!isAuthenticated && <Nav.Link onClick={() => {navigateTo("/login")}}>Login</Nav.Link>}
                        <Nav.Link onClick={() => {alert('Modal not implemented')}}>About</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}