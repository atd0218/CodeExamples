import logo from './logo.svg';
import './App.css';

//import router packages in order to use routing
import {Routes, Route, Link} from 'react-router-dom';
import Homepage from './components/Homepage';
import AboutMe from './components/AboutMe';

function App() {
  return (
    <div className="App">
      <nav className="nav">

        <Link to="/" className="nav-item">Homepage</Link>
        <Link to="/about-me" className="nav-item">About Me</Link>

      </nav>

      {/* in order to enable routing you have to reference the other 
      components this way. */}
      <Routes>
        <Route path="/" element={<Homepage />}/>
        <Route path="/about-me" element={<AboutMe />}/>
      </Routes>
      

      {/* <Homepage />
      <AboutMe />   */}
    </div>
  );
}

export default App;
