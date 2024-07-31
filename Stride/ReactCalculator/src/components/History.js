import Pagination from "react-js-pagination";
import {useState} from 'react'

function History (props) {

    const [state, setState] = useState(0)

    //confirm history is being set over properly
    console.log(props.history);

    //if arrow function is on single line it will automatically return the paragraph 
    //otherwise return statement is required for multi line. 
    // const historyList = props.history.map((item, i) => {
    
    //     if(item == '') {
    //         return(<p></p>)
    //     }
    //     else {
    //         return(<p key={i}>{item}</p>)
    // }});

    const historyList = props.history.map((item, i) => {
    
        if(item == 0) {
            return(<p> </p>)
        }
        else {
            return(
            <p key={i}>
                <span className="oper">{item.oper1}</span>
                <span className="sign">{item.sign}</span>
                <span className="oper">{item.oper2}</span>
                <span className="sign">=</span>
                <span className="result">{item.result}</span>  
            </p>)
    }});

    console.log(historyList);
    
    

    return (
        <div className="history">
            <h2 className="hist">History</h2>
            <p> 
                {/* map through my array variables to display them. */}
                {historyList}
            </p>
        </div>
        
    ) 

}

export default History;