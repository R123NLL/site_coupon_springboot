import { useSelector } from 'react-redux';
import './App.css';
import FooterComponent from './components/FooterComponent';
import HeaderComponent from './components/HeaderComponent';
import LoginComponent from './components/Login/LoginComponent';
import '/node_modules/bootstrap/dist/css/bootstrap.min.css';
import { roles } from './shared/common';
import { Navigate, BrowserRouter, Routes, Route } from 'react-router-dom';
import NotFoundPage from './pages/NotFoundPage';
import WelcomePage from './pages/WelcomePage';
import CustomerPage from './pages/CustomerPage';
import CompanyPage from './pages/CompanyPage';
import ManagerPage from './pages/ManagerPage';

// function CheckRouteAccess({ children, role }) {
//   const userRole = useSelector(store => store.auth.role);

//   if (role === userRole)
//     return children;

//   return <Navigate to="/login" />
// }

function App() {
  return (
    <div className="App container">
      <BrowserRouter>
        <HeaderComponent />
        <Routes>
          <Route path='/' element={< CompanyPage />} />
          <Route path='/login' element={<LoginComponent />} />
          <Route path='/customer' element={
            //<CheckRouteAccess role={roles.customer}>
              < CustomerPage />
            //</CheckRouteAccess>
          } />
          <Route path='/company' element={
            //<CheckRouteAccess role={roles.company}>
              < CompanyPage />
            //</CheckRouteAccess>
          } />
          <Route path='/manager' element={
            //<CheckRouteAccess role={roles.administrator}>
              < ManagerPage />
            //</CheckRouteAccess>
          } />
          <Route path='*' element={<NotFoundPage />} />
        </Routes>
        <FooterComponent />
      </BrowserRouter>
    </div>
  );
}

export default App;
