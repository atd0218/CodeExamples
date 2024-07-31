import { useMealsListContext } from "./Mealsprovider";

const MealsList = () => {

    //destruct the property from useMealsListContext
    //this contains one property meals with an array of today's meals
    const {meals} = useMealsListContext();

    return (
        <div>
            <h1>Meals list using Context API</h1>

            {/* map over the meals value and create an H2 for the meals array */}
            {meals.map((meal,index) => (
                <h2 key={index}>{meal}</h2>
            ))}
        </div>
    );
}

export default MealsList