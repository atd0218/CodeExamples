import { useState } from "react";

export default function App() {
  const [giftCard, setGiftCard] = useState(
    //create a state object that holds multiple properties and values
    {
        firstName: "Jennifer",
        lastName: "Smith",
        text: "Free dinner for 4 guests",
        valid: true,
        instructions: "To use your coupon, click the button below.",
    }
  );

  function spendGiftCard(e) {
    //prevent the default behavior of happening in this case the get request and url change
    e.preventDefault();

    //call set gift card and first get a copy of giftCard using the spread operator
    //then set the text and instruction lines to match what should be shown after press
    setGiftCard({...giftCard, text: "Your coupon has been used. ", valid: false,
    instructions: "Please visit our restaurant to renew your gift card"})
  }

  return (
    // display the web page style and call the state variable properties above
    <div style={{padding: '40px'}}>
      <h1>
        Gift Card Page
      </h1>
      <h2>
        Customer: {giftCard.firstName} {giftCard.lastName}
      </h2>
      <h3>
        {giftCard.text}
      </h3>
      <p>
        {giftCard.instructions}
      </p>
      {
        // this is checking to see if the valid property is true and if it
        //is true then you will see the button if it is false you will not
        giftCard.valid && (
          <button onClick={spendGiftCard}>
            Spend Gift Card
          </button>
        )
      }
    </div>
  );
}
