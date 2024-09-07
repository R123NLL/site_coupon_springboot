import './App.css';
import FooterComponent from './components/FooterComponent';
import HeaderComponent from './components/HeaderComponent';
import LoginComponent from './components/Login/LoginComponent';
import '/node_modules/bootstrap/dist/css/bootstrap.min.css';

function App() {
  return (
    <div className="App container">
      <HeaderComponent />
      <LoginComponent />
      <FooterComponent />
    </div>
  );
}

export default App;
