import { useSelector } from 'react-redux';
import './App.css';
import FooterComponent from './components/FooterComponent';
import HeaderComponent from './components/HeaderComponent';
import LoginComponent from './components/Login/LoginComponent';
import Home from './pages/Home';
import AuthTest from './pages/AuthTest';
import store from './state/store';
import '/node_modules/bootstrap/dist/css/bootstrap.min.css';
import { roles } from './shared/common';
import { Navigate, BrowserRouter, Routes, Route } from 'react-router-dom';

function CheckRouteAccess({ children, role }) {
  const userRole = useSelector(store => store.auth.role);

  if (role === userRole)
    return children;

  return <Navigate to="/login" />
}

function App() {
  return (
    <div className="App container">
      <BrowserRouter>
        <HeaderComponent />
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/login' element={<LoginComponent />} />
          <Route path='/test' element={
            <CheckRouteAccess role={roles.customer}>
              <AuthTest />
            </CheckRouteAccess>
          } />
        </Routes>
        <FooterComponent />
      </BrowserRouter>
    </div>
  );
}

export default App;
