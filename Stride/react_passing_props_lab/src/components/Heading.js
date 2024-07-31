function Heading (props) { //pass in the props attribute
    return <h1>Hello there {props.firstName}</h1> 
    //call the firstName variable in the props Object
}

export default Heading;