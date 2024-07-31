import logo from './logo.svg';
import './App.css';
import Results from './components/Results'
import History from './components/History'
import {
  useState,
  useRef
} from "react"; 

function App() { 
  const inputRef = useRef(null); 
  const resultRef = useRef(null); 

  //store prevValue in array 
  const [prevValues, setPrevValues] = useState([]);
  const [result, setResult] = useState(0); 

  //To store multiple previous values I will want to create an array to store multiple previous values. 
  //Store string literal of what is last in history
 
  function plus(e) { 
    e.preventDefault(); 

    // move the result up so I can call it below.
    const res = result + Number(inputRef.current.value)

    //set Prev value by storing the variables as a string in the setPrevValues array. 

    //this way does not allow me to change certain result colors.
    // setPrevValues(prevValues => [`${result} + ${inputRef.current.value} = ${res}`, ...prevValues.slice(0, 19)]);

    //had to use this setup as it allows me to set spans easier to change colors. I was not sure of a way 
    //to accomplish this using the above method. 
    //using slice as it will allow me to limit the number of array items that will be shown on the page as it returns only a subset
    //of the values in the array. 
    setPrevValues(prevValues => [{oper1: result, oper2: inputRef.current.value, sign: '+', result: res }, ...prevValues.slice(0, 2)])

    //set the result value to the new value
    setResult(res); 
  }; 
 
  function minus(e) { 
  	// Add the code for the minus function 

    //prevent the default behavior from occuring and overwrite it with the below code.
    e.preventDefault(); 

    // move the result up so I can call it below.
    const res = result - Number(inputRef.current.value)

    //set Prev value by storing the variables as a string in the setPrevValues array. 
    // setPrevValues(prevValues => [`${result} - ${inputRef.current.value} = ${res}`, ...prevValues.slice(0, 19)]);

    //had to use this setup as it allows me to set spans easier to change colors. I was not sure of a way 
    //to accomplish this using the above method. 
    setPrevValues(prevValues => [{oper1: result, oper2: inputRef.current.value, sign: '-', result: res }, ...prevValues.slice(0, 2)])


    //set the result of the state variable
    //inside create an arrow function passing in result and subtract result
    //from the current value in the inputRef variable which uses the useRef Hook
    setResult((result) => result - Number(inputRef.current.value)); 
  };
 
  function times(e) { 
    // Add the code for the plus function 
    e.preventDefault(); 

    // move the result up so I can call it below.
    const res = result * Number(inputRef.current.value)

    //set Prev value by storing the variables as a string in the setPrevValues array. 
    // setPrevValues(prevValues => [`${result} * ${inputRef.current.value} = ${res}`, ...prevValues.slice(0, 19)]);

    //had to use this setup as it allows me to set spans easier to change colors. I was not sure of a way 
    //to accomplish this using the above method. 
    setPrevValues(prevValues => [{oper1: result, oper2: inputRef.current.value, sign: '*', result: res }, ...prevValues.slice(0, 2)])

    setResult((result) => result * Number(inputRef.current.value));

    
  }; 
 
  function divide(e) { 
    // Add the code for the divide function 
    e.preventDefault(); 

    // move the result up so I can call it below.
    const res = result / Number(inputRef.current.value)

    //set Prev value by storing the variables as a string in the setPrevValues array. 
    //setPrevValues(prevValues => [`${result} / ${inputRef.current.value} = ${res}`, ...prevValues.slice(0, 19)]);

    //had to use this setup as it allows me to set spans easier to change colors. I was not sure of a way 
    //to accomplish this using the above method. 
    setPrevValues(prevValues => [{oper1: result, oper2: inputRef.current.value, sign: '/', result: res }, ...prevValues.slice(0, 2)])


    setResult((result) => result / Number(inputRef.current.value));
  };
 
  function resetInput(e) { 
    // Add the code for the resetInput function 
    e.preventDefault();

    //set the curretn value for inputRef to 0
    setResult(inputRef.current.value = 0)
  }; 
 
  function resetResult(e) { 
  	// Add the code for the resetResult function
    e.preventDefault();

    //set current value for resultRef to 0
    setResult(resultRef.current.value = 0) 
  }; 

  // function resetHistory(e) { 
  // 	// Add the code for the resetResult function
  //   e.preventDefault();

  //   //set current value for resultRef to 0
  //   setPrevValues(0) 
  // }; 

 
  return ( 
    <div className="App"> 
      <div> 
        <h1 className="Title">Calculator</h1> 
      </div> 
      <form> 
        <Results result={result} reference={resultRef}/>
        <input
          pattern="[0-9]" 
          ref={inputRef} 
          type="number" 
          placeholder="0" 
        /> 
        <button className="Classes" onClick={plus}>add</button> 
        {/* Add the subtract button */} 
        <button className="Classes" onClick={minus}>subtract</button> 
        {/* Add the multiply button */} 
        <button className="Classes" onClick={times}>multiply</button> 
        {/* Add the divide button */} 
        <button className="Classes" onClick={divide}>divide</button> 
        {/* Add the resetInput button */} 
        <button className="Classes" onClick={resetInput}>clear</button> 
        {/* Add the resetResult button */} 
        <button className="Classes" onClick={resetResult}>clear result</button> 
      </form> 
      {/* Add the History reset button 
      Unable to finish this section as I was getting errors when attempting
      to reset history*/} 
      {/* <button onClick={resetHistory}>Reset History</button>  */}
      <History history={prevValues}/>
    </div> 
  ); 
} 

export default App;
