import "./App.css";
import Homepage from "./Homepage";
import AboutLittleLemon from "./AboutLittleLemon";
import Contact from "./Contact"
// Have to import routes and Link for router to enable
import { Routes, Route, Link } from "react-router-dom";

function App() {
  return (
    <div> 
	  <nav>

      {/* When working with react you need to do Link tags instead of Anchor tags
      This is because react only changes certain elements of the page based on 
      what the component is coded to do. */}
      <Link to="/" className="nav-item">Homepage</Link>
      <Link to="/about" className="nav-item">About Little Lemon</Link>
      <Link to="/contact" className="nav-item">Contact Us</Link>
	  </nav>
      <Routes> 
        {/* You have to initiate each route and assign the path to it */}
        <Route path="/" element={<Homepage />}></Route>
        <Route path="/about" element={<AboutLittleLemon />}></Route>
        <Route path="/contact" element={<Contact />}></Route>
      </Routes>
    </div>
  );
};

export default App;
