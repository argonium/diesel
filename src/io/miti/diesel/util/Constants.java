package io.miti.diesel.util;

/**
 * This class contains constants used by the other classes for
 * mathematical computations.
 * 
 * @version 1.0, 04/27/04
 * @author mwallace
 */
public final class Constants
{
  /**
   * Pi constant.
   */
  public static final double PI  = 3.14159265358979323846;

  /**
   * Pi divided by 2 constant.
   */
  public static final double PI2 = 1.57079632679489661923;

  /**
   * Convert radians to degrees.
   */
  public static final double RAD2DEG = 57.2957795130823231;
  
  /**
   * Convert radians to seconds.
   */
  public static final double RAD2SEC = 206264.806247096363;
  
  /**
   * Convert degrees to radians.
   */
  public static final double DEG2RAD = 0.0174532925199432951;
  
  /**
   * Convert seconds to radians.
   */
  public static final double SEC2RAD = 0.000004848136811095;
  
  /**
   * Convert kilometers to radians.
   */
  public static final double KM2RAD  = 0.00015703088;
  
  /**
   * Convert feet to meters.
   */
  public static final float  FT2M  = 0.3048F;
  
  /**
   * Convert meters to feet.
   */
  public static final double M2FT  = 3.28083989501312;
  
  /**
   * Convert miles to kilometers.
   */
  public static final double MI2KM = 1.609344;
  
  /**
   * Convert kilometers to miles.
   */
  public static final double KM2MI = 0.621371192237334;
  
  /**
   * Floating point precision.
   */
  public static final double FLOATPRECISION = 0.0000001;
  
  /**
   * Convert nautical miles to kilometers.
   */
  public static final double NAUTICAL_MILES2KM = 1.851999852;

  /**
   * This constant is designed for use by setters that are being
   * asked to store a value that has been converted from meters to 
   * feet and then back to meters. It defines the threshold of 'noise'
   * that could be generated when these metric values are converted to
   * english, rounded to three decimal places for display purposes and
   * then converted back to metric for storage. Values that change by
   * an amount less than this constant's value are not considered to be
   * changed by the user. It's value was determined by multiplying the
   * maximum error that could be generated by throwing away
   * everything after the third decimal place in feet (0.000499999999), 
   * times 0.3048 (the value of the FT2M constant in this header file).
   */
  public static final double FT2M_CONVERT_FLOATPRECISION = 0.0001523996952;
  
  /**
   * This constant is designed for use by setters that are being
   * asked to store a value that has been converted from miles to 
   * kilometers and then back to miles. It defines the threshold of 'noise'
   * that could be generated when these metric values are converted to
   * english, rounded to three decimal places for display purposes and
   * then converted back to metric for storage. Values that change by
   * an amount less than this constant's value are not considered to be
   * changed by the user. It's value was determined by multiplying the
   * maximum error that could be generated by throwing away
   * everything after the third decimal place in miles (0.000499999999), 
   * times 1.609344 (the value of the MI2KM constant in this header file).
   */
  public static final double MI2KM_CONVERT_FLOATPRECISION = 0.0008046703907;
}
