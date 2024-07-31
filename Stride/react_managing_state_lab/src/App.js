import Fruits from "./components/Fruits";
import FruitsCounter from "./components/FruitsCounter";
import {useState} from 'react';
import React from 'react';

function App() {

  //move the fruits decleration with useState to the parent component
  const [fruits] = React.useState([
        {fruitName: 'apple', id: 1},
        {fruitName: 'apple', id: 2},
        {fruitName: 'plum', id: 3},
    ]);


  return (
    <div className="App">
      <h1>Where should the state go?</h1>

      {/* pass fruits variable to the fruits component */}
      <Fruits fruits={fruits}/>

       {/* pass fruits variable to the fruits counter component */}
      <FruitsCounter fruits={fruits}/>
    </div>
  );
}

export default App;