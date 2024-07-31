package com.secure.util;

import java.util.stream.Stream;

public class encryption {

  public static Stream<String> encrypt () {
    //force to lower case to ensure it works as expected
    String lower = "aaaa";

    char [] sChars = lower.toCharArray();
    StringBuilder newString = new StringBuilder();

    for (char currentchar : sChars) {
        //account for if the character is x or y or z and if so loop it to the start of the
        //alphabet
        if((int) currentchar == 120)
        {
          currentchar = 'a';
        }
        else if ((int) currentchar == 121)
        {
          currentchar = 'b';
        }
        else if ((int) currentchar == 122)
        {
          currentchar = 'c';
        }
        else {
          currentchar += 3;
          
        }
        newString.append(currentchar);
    }
    
    return Stream.of(newString.toString());
  }

  public String decrypt (String s) {
    //force to lower case to ensure it works as expected
    String lower = s.toLowerCase();

    char [] sChars = lower.toCharArray();
    StringBuilder newString = new StringBuilder();

    for (char currentchar : sChars) {
        //account for if the character is x or y or z and if so loop it to the start of the
        //alphabet
        if((int) currentchar == 99)
        {
          currentchar = 'z';
        }
        else if ((int) currentchar == 98)
        {
          currentchar = 'y';
        }
        else if ((int) currentchar == 97)
        {
          currentchar = 'x';
        }
        else {
          currentchar -= 3;
        }
        newString.append(currentchar);
    }
    
    return newString.toString();
  }

}
