import {useRef} from "react"

function Results (props) 
{
   return (
        <div className="results">
            <p>Result</p>
            <p ref={props.reference}> 
                {/* add the value of the current total */}
                <span className="r">{props.result}</span>
            </p>
        </div>
        
    ) 
}

export default Results;