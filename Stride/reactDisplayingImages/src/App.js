import logos from './logo.svg';
import './App.css';
//import image asset to use in my app
import logo from './assets/logo.png';

function App() {
  return (
    <div className="App">
      <h1>Task: Add an image below</h1>
      {/* place the image on the page that I imported above. */}
      <img 
        src={logo} 
        alt="Little Lemon Image"
        height={500}
        width={500}  
      />
    </div>
  );
}

export default App;
