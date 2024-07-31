import { useState, useEffect, useRef } from "react";
export default function App() {
  //useState being used to store state variable
  const [day, setDay] = useState("Monday");

  //prevDay calls the previous day function which stores the
  //current day before the button is pressed. 
  const prevDay = usePrevious(day);

  //when button is pressed it triggers this function
  //since prevDay is before it captures the previous day
  const getNextDay = () => {
    if (day === "Monday") {
      setDay("Tuesday")
    } else if (day === "Tuesday") {
      setDay("Wednesday")
    } else if (day === "Wednesday") {
      setDay("Thursday")
    } else if (day === "Thursday") {
      setDay("Friday")
    } else if (day === "Friday") {
      setDay("Monday")
    }
  }
  return (
    <div style={{padding: "40px"}}>
      <h1>
        Today is: {day}<br />
        {
          prevDay && (
            <span>Previous work day was: {prevDay}</span>
          )
        }
      </h1>
      <button onClick={getNextDay}>
        Get next day
      </button>
    </div>
  );
}
function usePrevious(val) {

  //useRef initialization with no value as initialState
  const ref = useRef();

  //useEffect hook used to store the current ref value and 
  //track the value in the dependencies array
  useEffect (() => {
    ref.current = val;
  }, [val])

  return ref.current;

}
