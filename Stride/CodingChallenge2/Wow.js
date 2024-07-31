//Set use strict to ensure i declare variables

"use strict";

//OLD Header image that changes completely on mouseover.

// //Create a function that will change the image to the alliance symbol when mousedover
// var image = document.getElementById('emblem');
// var heading = document.querySelector('header > #mantra');

// //mouseover change to horde emblem 
// //? checks to see if value exists and returns blank in chrome if not. 
// image?.addEventListener('mouseover', function(){

//     heading.innerHTML = "For the Alliance!";
//     image.src = "Alliance.jpg";
// })
// //change image to alliance emblem if mouse is taken out
// image?.addEventListener('mouseout', function(){

//     heading.innerHTML = "For the Horde!";
//     image.setAttribute('src', 'Horde.jpg');
// })
// // change back to original image once moused over the heading. 
// heading?.addEventListener('mouseover', function(){

//     heading.innerHTML = "Hover over the image to see the two races!";
//     image.src = "wow.jpg";
// })

//Take buttons on the first page and when they are clicked have them display 
//the class in wow and a brief description.
const btn1 = document.querySelector("#hunt");
const btn2 = document.querySelector("#mage");
const btn3 = document.querySelector("#dru");
const btn4 = document.querySelector("#voke");
const btn5 = document.querySelector("#lock");

const img = document.querySelector("#wowClass")
const para = document.querySelector("#classInfo")

btn1.addEventListener('click', function() {

  img.src = "hunter.jpg";
  para.innerHTML = "A Hunter uses a bow or gun and has a pet to fight along side it.";


})
btn2.addEventListener('click', function() {

  img.src = "mage.jpg";
  para.innerHTML = "A Mage masters the elements to use as weapons against its enemies.";


})
btn3.addEventListener('click', function() {

  img.src = "druid.jpg";
  para.innerHTML = "A Druid can shapeshift into many forms and may Heal, Tank, or DPS.";


})
btn4.addEventListener('click', function() {

  img.src = "evoker.jpg";
  para.innerHTML = "The newest wow class that harnesses light and darkness to do combat with.";


})
btn5.addEventListener('click', function() {

  img.src = "lock.jpg";
  para.innerHTML = "A Warlock is master of demons and chaos!";


})

// let gameList = ["World of Warcraft", "Borderlands 2", "Pacify"];
// let urlList = ["wow.html", "bl2.html", "pac.html"];
// let colorList = ["#db7093", "#daa520", "#416931"];

// let loadNavBar = function() {
//     let navItemList = ["Home"].concat(gameList);
//     let navUrlList = ["index.html"].concat(urlList);
//     let navColorList = ["cornflowerblue"].concat(colorList);
//     // need to have <div class="navWrapper"></div> in your html
//     document.querySelector('.navWrapper').innerHTML = navItemList.map((item, idx) => 
//         `<div class="navItem" style="--clr:${navColorList[idx]};" onclick="window.location.href='${navUrlList[idx]}'"><span>${item}</span></div>`
//     ).join('');
// }


//function to auto center document on resize.
function resizer() {

  let body = document.querySelector('body');

  body.addEventListener("resize", function() {
    body.setAttribute('margin-left', 'auto');
    body.setAttribute('margin-right', 'auto');
  })
}


//New header image where only the part the mouse is over changes. 
window.onload = function() {
  const div = document.querySelector('.hole');
  let isIn = false;
  div.addEventListener('mouseover', function() {
    isIn = true;
  });
  div.addEventListener('mouseout', function() {
    isIn = false;
  });
  div.addEventListener('mousemove', function(event) {
    if (isIn) {
      // automatically set x and y positions so we know 
      // what part of the back image should be revealed. 
      div.style.setProperty('--x', event.offsetX + 'px');
      console.log(event.offsetX);
      div.style.setProperty('--y', event.offsetY + 'px');
      console.log(event.offsetY);
    }
  });

  resizer();

  // loadNavBar();
}