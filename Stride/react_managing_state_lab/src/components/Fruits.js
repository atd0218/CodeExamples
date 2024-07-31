//add props to the function to get the passed in fruits from parent component "app"
function Fruits(props) {

    return (
        <div>
            {/* map over the list of fruits from the parent component
             and display the fruitName */}
            {props.fruits.map(f => <p key={f.id}>{f.fruitName}</p>)}
        </div>
    )
}

export default Fruits