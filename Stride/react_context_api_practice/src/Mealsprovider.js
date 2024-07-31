import React from 'react';


//create a context variable for the meals array
const MealsContext = React.createContext();

//set an array list of todays meals
const todaysMeals = ["Baked Beans", "Baked Potatoes", "Baked Celery"]

//arrow function that accepts the children parameter
//
const MealsProvider = ({children}) => {

    //set a state variable to capture the state of meals and update it when needed. 
    const [meals, setMealsList] = React.useState(todaysMeals);

    return (
        
        //assign the meals object to the value for the MealsContext.Provider
        <MealsContext.Provider value={{meals}} >
            {children}
        </MealsContext.Provider>
    )
}

//allows us to destructure the context easier in other places
export const useMealsListContext = () => React.useContext(MealsContext);

export default MealsProvider;