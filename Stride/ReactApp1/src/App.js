import logo from './logo.svg';
import './App.css';
import {Header} from "./Header" //import a named export from the other file. 

function Heading() {

  return <h1>This is an h1 heading</h1>;

}

function App() {

  return <Heading />;
  
}

export default App;
