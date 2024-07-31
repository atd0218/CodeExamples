import logo from './logo.svg';
import './App.css';
import MealsList from './MealsList';
import Counter from './Counter';
import Mealsprovider from './Mealsprovider';
import {useReducer} from 'react';

//function takes the state and action and makes changes to the data. 
const reducer = (state, action) => {
  if (action.type === 'ride') return {money: state.money + 50};
  if (action.type === 'fuel') return {money: state.money - 50};
  return new Error();
}

function App() {

  //use reducer example
  const initialState = {money: 100};
  const [state, dispatch] = useReducer(reducer, initialState);

  return (
    // <div className="App">
    //   <Mealsprovider>
    //     <MealsList />
    //     <Counter />
    //   </Mealsprovider>
    // </div>

    //Use Reducer Example
    <div className="App">
      <h1>Wallet: {state.money}</h1>
      <div>
          {/* dispatch function is used set the type for the reducer function
          manipulate the data based on the button clicked. */}
          <button onClick={() => dispatch({type: 'ride'})}>
            A new customer!</button>
            <button onClick={() => dispatch({type: 'fuel'})}>
            Refill the tank!</button>
      </div>
    </div>
  );
}

export default App;
