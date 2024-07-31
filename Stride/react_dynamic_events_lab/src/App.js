import logo from './logo.svg';
import './App.css';

function App() {

  function handleClick () {

    //create a random number generator between 1 and 3
    let randomNum = Math.floor(Math.random() * 3) + 1;

    //log random number to console
    console.log(randomNum);

    //create a user input variable
    let userInput = prompt('type a number');

    //display whether the user was correct
    alert(`Computer number: ${randomNum}, Your guess: ${userInput}`);


  }


  return (
    <div className="App">
      
      {/* add a button with an onclick event to handle clicks. */}
      <button onClick={handleClick}>Guess the number between 1 and 3</button>
    </div>
  );
}

export default App;
