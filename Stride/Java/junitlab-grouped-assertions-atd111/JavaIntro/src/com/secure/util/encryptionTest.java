/**
 * @name: Ashton Daniels
 * @date: 04/01/2024
 * JDK Version: 8 
 * Description: 
 * 
 * encryption.java
 * 
 * Created a class that takes has two methods encrypt and decrypt
 * 
 * encrypt takes a string and adjusts each character in the string by 3 characters in the alphabet.
 * It returns a Stream of strings to be passed into the parameterized test in the Test class. 
 * 
 * decrpyt takes a string and adjusts it negative 3 character spaces in the alphabet. 
 * 
 * 
 * encryptionTest.java
 * 
 * This class was created to perform JUnit tests for both encrypt and decrypt 
 * 
 * All tests were performed using Grouped Assertions and more specifically assertAll. 
 * 
 * The encrypt tests also used Parameterized Tests as I wanted to explore those further. 
 */

package com.secure.util;

import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.Assert.assertEquals;

@DisplayName("Test Encryption Class!")
public class encryptionTest {

  @DisplayName("Test Decrypt Method!")
  @Test
  void testDecrypt() {
    encryption e = new encryption();
    String value = e.decrypt("DDDD");
    String value2 = e.decrypt("LMNO");
    String checker = "AAAA";
    String checker2 = "IJKL";
    assertAll(
      () -> assertEquals(checker.toLowerCase(), value),
      () -> assertEquals(checker2.toLowerCase(), value2)
      //failed test cases
      // () -> assertEquals(value, value2), 
      // () -> assertEquals(checker.toLowerCase(), value2)
    );
    
    System.out.println("Decryption Test Using Grouped Assertions");
  }
  // @DisplayName("Test Encrypt Method!")
  // @Test
  // void testEncrypt() {
  //   encryption e = new encryption();
  //   String value = e.encrypt("AAAA");
  //   String value2 = e.encrypt("QRS");
  //   String checker = "DDDD";
  //   String checker2 = "TUV";
  //   assertAll(
  //     () -> assertEquals(checker.toLowerCase(), value),
  //     () -> assertEquals(checker2.toLowerCase(), value2)
  //   );
  //   System.out.println("Encryption Test Using Grouped Assertions");
  // }

  @DisplayName("Test Parameterized Encrypt Method!")
  @ParameterizedTest
  @MethodSource("com.secure.util.encryption#encrypt")
  void testEncryptParam(String input) {
    String checker = "DDDD";
    String checker2 = "TUV";
    assertAll(
      () -> assertEquals(checker.toLowerCase(), input),
      //failed on purpose so show a fail with grouped assertions and parameterized Test
      () -> assertEquals(checker2.toLowerCase(), input)
    );
    System.out.println("Encryption Test Using Grouped Assertions");
  }
}
