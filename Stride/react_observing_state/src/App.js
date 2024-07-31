import logo from './logo.svg';
import './App.css';
import React, {useState} from  'react';
import Heading from './components/Heading';

function App() {

  //set a state variable to capture words
  //word - used to access the current state of the variable
  //setWord - used to update the state of the variable
  const [word,setWord] = React.useState('Eat');

  //you can't update state from your js code directly so the below line is invalid
  //setWord('Drink');

  //create function to change the word
  function buttonHandler() {
    //it can now be set this way since I am handling setting it through an event. 
    setWord('Drink');
  }

  return (
    <div className="App">
        <Heading message={word + " at Little Lemon"}/>
        <button onClick={buttonHandler}>Change Word</button>
    </div>
  );
}

export default App;
