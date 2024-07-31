import { useMealsListContext } from "./Mealsprovider";

const Counter = () => {

    //destruct the property from useMealsListContext
    //this contains one property meals with an array of today's meals
    const {meals} = useMealsListContext();

    return (
        <div>

            {/* print out the number of meals left for the day using .length */}
            <h3>Number of meals today: {meals.length}</h3>
        </div>
    )
}

export default Counter;