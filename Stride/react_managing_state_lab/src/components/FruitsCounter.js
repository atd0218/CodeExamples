//add props to the function to get the passed in fruits from parent component "app"
function FruitsCounter(props) {
    return (

        // grab length of the fruits array from the parent component and display it. 
        <h2>Total fruits: {props.fruits.length}</h2>
    )
}

export default FruitsCounter;