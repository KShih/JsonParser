package edu.nyu.cs9053.homework2;

import edu.nyu.cs9053.homework2.model.EngineLightAlert;
import edu.nyu.cs9053.homework2.model.DiagnosticTroubleCode;

/**
 * User: blangel
 *
 * @see {@literal https://www.json.org/}
 * @see {@literal https://en.wikipedia.org/wiki/JSON}
 */
public class JsonParser {

  /**
   * @param alert to serialize into {@literal JSON}
   * @implNote a null value should be an {@linkplain IllegalArgumentException};
   *           i.e. {@code throw new IllegalArgumentException}
   * @return the serialized {@literal JSON} representation of {@code alert}
   */
  public static String toJson(EngineLightAlert alert) {
    if (alert == null) {
      throw new IllegalArgumentException();
    }

    String returnStr = "";
    DiagnosticTroubleCode[] codes = alert.getCodes();
    String vehicleId = alert.getVehicleId();
    long dateTime = alert.getDateTime();
    Boolean noVehicleId = true;

    if (vehicleId != null && vehicleId.length() != 0) {
      vehicleId = vehicleId.replaceAll("\\\"", "\\\\\""); // deal with -> \"abcdefg\" <-
      returnStr = "{\"vehicleId\":\"" + vehicleId;
      noVehicleId = false;
    }
    if (!noVehicleId) { // if VehicleId is null, dateTime should be the first JSON element
      returnStr += "\",\"dateTime\":" + dateTime;
    } else {
      returnStr = "{\"dateTime\":" + dateTime;
    }

    if (codes != null && codes.length != 0) {
      returnStr += ",\"codes\":[";
      for (DiagnosticTroubleCode code : codes) {
        if (code != null) {
          if (code.getCode() == null) {
            returnStr += "{}";
          } else if (code.getCode() == "") {
            returnStr += "{\"code\":\"";
            returnStr += "[]";
            returnStr += "\"},";
          } else {
            returnStr += "{\"code\":\"";
            returnStr += code.getCode();
            returnStr += "\"},";
          }
        }
      }
      if (returnStr.charAt(returnStr.length() - 1) == ',') // delete the last ','
        returnStr = returnStr.substring(0, returnStr.length() - 1);
      returnStr += ']';
    }
    returnStr += "}";
    return returnStr;
  }
}
