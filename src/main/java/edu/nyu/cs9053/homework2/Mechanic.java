package edu.nyu.cs9053.homework2;

import edu.nyu.cs9053.homework2.model.DiagnosticTroubleCode;
import java.util.*;

/**
 * User: blangel
 */
public class Mechanic {

  /**
   * @see {@literal https://en.wikipedia.org/wiki/OBD-II_PIDs#Mode_3_(no_PID_required)}
   * @implNote For simplification of this homework, contrary to the Wikipedia
   *           article {@code data} is not in the ISO 15765-2 protocol. It is
   *           simply an array of data where the length should be equal to
   *           {@code expectedAmount} times 2.
   * @implNote If {code data} is null, empty or not equal to
   *           {@code expectedAmount} times 2 then throw an
   *           {@linkplain IllegalArgumentException}; i.e.
   *           {@code throw new IllegalArgumentException}
   * @param data           to parse
   * @param expectedAmount of {@linkplain DiagnosticTroubleCode} to decode
   * @return the decoded {@linkplain DiagnosticTroubleCode} objects see
   *         {@linkplain DiagnosticTroubleCode#construct(String)} to create the
   *         object.
   */

  /**
   * DiagnosticTroubleCode cotain a element: String code
   *
   * @Goal: Translate the array of byte data to the array of translated string
   * @Solution: 1. Convert Byte to String 2. Mapping String to Translated String
   */
  public static DiagnosticTroubleCode[] decode(byte[] data, int expectedAmount) {
    if (data == null || data.length != expectedAmount * 2) {
      throw new IllegalArgumentException();
    }

    // DTC mapping rule
    Map<String, String> FirstDTC = new HashMap<String, String>();
    FirstDTC.put("00", "P");
    FirstDTC.put("01", "C");
    FirstDTC.put("10", "B");
    FirstDTC.put("11", "U");
    Map<String, String> SecDTC = new HashMap<String, String>();
    SecDTC.put("00", "0");
    SecDTC.put("01", "1");
    SecDTC.put("10", "2");
    SecDTC.put("11", "3");
    Map<String, String> ThirdDTC = new HashMap<String, String>();
    ThirdDTC.put("0000", "0");
    ThirdDTC.put("0001", "1");
    ThirdDTC.put("0010", "2");
    ThirdDTC.put("0011", "3");
    ThirdDTC.put("0100", "4");
    ThirdDTC.put("0101", "5");
    ThirdDTC.put("0110", "6");
    ThirdDTC.put("0111", "7");
    ThirdDTC.put("1000", "8");
    ThirdDTC.put("1001", "9");
    ThirdDTC.put("1010", "A");
    ThirdDTC.put("1011", "B");
    ThirdDTC.put("1100", "C");
    ThirdDTC.put("1101", "D");
    ThirdDTC.put("1110", "E");
    ThirdDTC.put("1111", "F");

    DiagnosticTroubleCode[] codes = new DiagnosticTroubleCode[expectedAmount];
    String[] dataString = new String[data.length]; // record the byte[] data in string type
    // fetch out the byte[] data to string
    for (int i = 0; i < data.length; i++) {
      dataString[i] = "";
      for (int j = 0; j < 8; j++) {
        dataString[i] += (data[i] >> (8 - (j % 8 + 1)) & 0x0001);
      }
    }

    int iterDataString = 0; // Iterater for dataString[]
    for (int k = 0; k < expectedAmount; k++) {
      String temp = "";
      if (dataString[iterDataString] != "" && dataString[iterDataString + 1] != "") {
        // Code[0] ~ Code[2]
        int i = 0;
        String first3charInbyte = dataString[iterDataString];
        StringBuilder match = new StringBuilder(); // match Code[0]
        match.append(first3charInbyte.charAt(i)).append(first3charInbyte.charAt(i + 1));
        temp += FirstDTC.get(match.toString());
        i += 2;
        match = new StringBuilder(); // match Code[1]
        match.append(first3charInbyte.charAt(i)).append(first3charInbyte.charAt(i + 1));
        temp += SecDTC.get(match.toString());
        i += 2;
        match = new StringBuilder(); // match Code[2]
        match.append(first3charInbyte.charAt(i)).append(first3charInbyte.charAt(i + 1))
            .append(first3charInbyte.charAt(i + 2)).append(first3charInbyte.charAt(i + 3));
        temp += ThirdDTC.get(match.toString());

        iterDataString++;

        // Code[3] ~ Code[4]
        String last2charInbyte = dataString[iterDataString];
        i = 0;
        match = new StringBuilder(); // match Code[3]
        match.append(last2charInbyte.charAt(i)).append(last2charInbyte.charAt(i + 1))
            .append(last2charInbyte.charAt(i + 2)).append(last2charInbyte.charAt(i + 3));
        temp += ThirdDTC.get(match.toString());
        i += 4;
        match = new StringBuilder(); // match Code[4]
        match.append(last2charInbyte.charAt(i)).append(last2charInbyte.charAt(i + 1))
            .append(last2charInbyte.charAt(i + 2)).append(last2charInbyte.charAt(i + 3));
        temp += ThirdDTC.get(match.toString());

        iterDataString++;
      } else { // if dataString is null keep iterating
        iterDataString += 2;
      }
      DiagnosticTroubleCode diagnosticTroubleCode = new DiagnosticTroubleCode(temp); // Newed a object used static
                                                                                     // method
      codes[k] = diagnosticTroubleCode; // assign the object to the array
    }
    return codes;

  }

}
