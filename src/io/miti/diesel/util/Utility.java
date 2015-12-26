package io.miti.diesel.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * Utility methods.
 * 
 * @author mwallace
 * @version 1.0
 */
public final class Utility
{  
  /**
   * Number of milliseconds per minute.
   */
  private static final long MSECS_PER_MIN  = 60000L;
  
  /**
   * Number of milliseconds per hour.
   */
  private static final long MSECS_PER_HOUR = MSECS_PER_MIN * 60L;
  
  /**
   * Number of milliseconds per day.
   */
  private static final long MSECS_PER_DAY  = MSECS_PER_HOUR * 24L;
  
  /**
   * Number of milliseconds per week.
   */
  private static final long MSECS_PER_WEEK  = MSECS_PER_DAY * 7L;
  
  /**
   * Number of milliseconds per year.
   */
  private static final long MSECS_PER_YEAR = 31556926000L;
  
  /**
   * The line separator for this OS.
   */
  private static String lineSep = null;
  
  
  /**
   * Default constructor.
   */
  private Utility()
  {
    super();
  }
  
  
  /**
   * Return the application name.
   * 
   * @return the application name
   */
  public static String getAppName()
  {
    return "Diesel Console";
  }
  
  
  /**
   * Return the line separator for this OS.
   * 
   * @return the line separator for this OS
   */
  public static String getLineSeparator()
  {
    // See if it's been initialized
    if (lineSep == null)
    {
      lineSep = System.getProperty("line.separator");
    }
    
    return lineSep;
  }
  
  
  /**
   * Convert a string into an integer.
   * 
   * @param sInput the input string
   * @param defaultValue the default value
   * @param emptyValue the value to return for an empty string
   * @return the value as an integer
   */
  public static int getStringAsInteger(final String sInput,
                                       final int defaultValue,
                                       final int emptyValue)
  {
    // This is the variable that gets returned
    int value = defaultValue;
    
    // Check the input
    if (sInput == null)
    {
      return emptyValue;
    }
    
    // Trim the string
    final String inStr = sInput.trim();
    if (inStr.length() < 1)
    {
      // The string is empty
      return emptyValue;
    }
    
    // Convert the number
    try
    {
      value = Integer.parseInt(inStr);
    }
    catch (NumberFormatException nfe)
    {
      value = defaultValue;
    }
    
    // Return the value
    return value;
  }
  
  
  /**
   * Make the application compatible with Apple Macs.
   */
  public static void makeMacCompatible()
  {
    // Set the system properties that a Mac uses
    System.setProperty("apple.awt.brushMetalLook", "true");
    System.setProperty("apple.laf.useScreenMenuBar", "true");
    System.setProperty("apple.awt.showGrowBox", "true");
    System.setProperty("com.apple.mrj.application.apple.menu.about.name",
                       getAppName());
  }
  
  
  /**
   * Store the properties object to the filename.
   * 
   * @param filename name of the output file
   * @param props the properties to store
   */
  public static void storeProperties(final String filename,
                                     final Properties props)
  {
    // Write the properties to a file
    FileOutputStream outStream = null;
    try
    {
      // Open the output stream
      outStream = new FileOutputStream(filename);
      
      // Save the properties
      props.store(outStream, "Properties file for Diesel Console");
      
      // Close the stream
      outStream.close();
      outStream = null;
    }
    catch (FileNotFoundException fnfe)
    {
      Logger.error("File not found: " + fnfe.getMessage());
    }
    catch (IOException ioe)
    {
      Logger.error("IOException: " + ioe.getMessage());
    }
    finally
    {
      if (outStream != null)
      {
        try
        {
          outStream.close();
        }
        catch (IOException ioe)
        {
          Logger.error("IOException: " + ioe.getMessage());
        }
        
        outStream = null;
      }
    }
  }
  
  
  /**
   * Load the properties object.
   * 
   * @param filename the input file name
   * @return the loaded properties
   */
  public static Properties getProperties(final String filename)
  {
    // The object that gets returned
    Properties props = null;
    
    InputStream propStream = null;
    try
    {
      // Open the input stream as a file
      propStream = new FileInputStream(filename);
      
      // Check for an error
      if (propStream != null)
      {
        // Load the input stream
        props = new Properties();
        props.load(propStream);
        
        // Close the stream
        propStream.close();
        propStream = null;
      }
    }
    catch (IOException ioe)
    {
      props = null;
    }
    finally
    {
      // Make sure we close the stream
      if (propStream != null)
      {
        try
        {
          propStream.close();
        }
        catch (IOException e)
        {
          Logger.error(e.getMessage());
        }
        
        propStream = null;
      }
    }
    
    // Return the properties
    return props;
  }
  
  /**
   * Get the specified date as a string.
   * 
   * @param time the date and time
   * @return the date as a string
   */
  public static String getDateTimeString(final long time)
  {
    // Check the input
    if (time <= 0)
    {
      return "Invalid time (" + Long.toString(time) + ")";
    }
    
    // Convert the time into a Date object
    Date date = new Date(time);
    
    // Declare our formatter
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    
    // Return the date/time as a string
    return formatter.format(date);
  }
  
  
  /**
   * Format the date as a string, using a standard format.
   * 
   * @param date the date to format
   * @return the date as a string
   */
  public static String getDateString(final Date date)
  {
    // Declare our formatter
    SimpleDateFormat formatter = new SimpleDateFormat("MMMM d, yyyy");
    
    if (date == null)
    {
      return formatter.format(new Date());
    }
      
    // Return the date/time as a string
    return formatter.format(date);
  }
  
  
  /**
   * Format the date and time as a string, using a standard format.
   * 
   * @return the date as a string
   */
  public static String getDateTimeString()
  {
    // Declare our formatter
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    
    // Return the date/time as a string
    return formatter.format(new Date());
  }
  
  
  /**
   * Convert the input argument into a string describing the time span.
   * 
   * @param interv the time interval, in milliseconds
   * @return a String describing the time span
   */
  public static String millisToTimeSpan(final long interv)
  {
    // Declare our string buffer
    StringBuffer buf = new StringBuffer(100);
    
    // Check for zero and negative values
    long lMillis = interv;
    if (lMillis <= 0L)
    {
      // The value is either illegal, or zero, so return
      buf.append("0 seconds");
      return buf.toString();
    }
    
    // Get the number of years
    final long lYears = (long) (lMillis / MSECS_PER_YEAR);
    lMillis = lMillis % MSECS_PER_YEAR;
    
    // Get the number of weeks
    final long lWeeks = (long) (lMillis / MSECS_PER_WEEK);
    
    // Update lMillis with the remainder
    lMillis = lMillis % MSECS_PER_WEEK;
    
    // Get the number of days
    final long lDays = (long) (lMillis / MSECS_PER_DAY);
    
    // Update lMillis with the remainder
    lMillis = lMillis % MSECS_PER_DAY;
    
    // Get the number of hours
    final long lHours = (long) (lMillis / MSECS_PER_HOUR);
    
    // Update lMillis with the remainder
    lMillis = lMillis % MSECS_PER_HOUR;
    
    // Get the number of minutes
    final long lMinutes = (long) (lMillis / MSECS_PER_MIN);
    
    // Update lMillis with the remainder
    lMillis = lMillis % MSECS_PER_MIN;
    
    // Get the number of seconds
    final float fSeconds = (float) (((float) lMillis) / 1000.0F);
    
    if (lYears > 0L)
    {
      // Add the number and unit
      buf.append(Long.toString(lYears)).append(" year");
      
      // Make the unit plural, if necessary
      if (lYears > 1L)
      {
        buf.append('s');
      }
    }
    
    // Now generate the string. First check if there are any weeks.
    if (lWeeks > 0L)
    {
      // Append a leading comma, if necessary, and then add the number and unit
      if (buf.length() > 0)
      {
        buf.append(", ");
      }
      
      // Add the number and unit
      buf.append(Long.toString(lWeeks)).append(" week");
      
      // Make the unit plural, if necessary
      if (lWeeks > 1L)
      {
        buf.append('s');
      }
    }
    
    // Check if there are any days.
    if (lDays > 0L)
    {
      // Append a leading comma, if necessary, and then add the number and unit
      if (buf.length() > 0)
      {
        buf.append(", ");
      }
      buf.append(Long.toString(lDays)).append(" day");
      
      // Make the unit plural, if necessary
      if (lDays > 1L)
      {
        buf.append('s');
      }
    }
    
    // Check if there are any hours.
    if (lHours > 0L)
    {
      // Append a leading comma, if necessary, and then add the number and unit
      if (buf.length() > 0)
      {
        buf.append(", ");
      }
      buf.append(Long.toString(lHours)).append(" hour");
      
      // Make the unit plural, if necessary
      if (lHours > 1L)
      {
        buf.append('s');
      }
    }
    
    // Check if there are any minutes.
    if (lMinutes > 0L)
    {
      // Append a leading comma, if necessary, and then add the number and unit
      if (buf.length() > 0)
      {
        buf.append(", ");
      }
      buf.append(Long.toString(lMinutes)).append(" minute");
      
      // Make the unit plural, if necessary
      if (lMinutes > 1L)
      {
        buf.append('s');
      }
    }
    
    // Check if there are any seconds.
    if (Float.compare(fSeconds, 0.0F) > 0)
    {
      // Append a leading comma, if necessary
      if (buf.length() > 0)
      {
        buf.append(", ");
      }
      
      // Format it because it's a floating point number
      DecimalFormat df = new DecimalFormat();
      df.setDecimalSeparatorAlwaysShown(false);
      df.setMaximumFractionDigits(3);
      buf.append(df.format((double) fSeconds)).append(" second");
      
      // Make the unit plural, if necessary (if the number is anything but 1.0)
      if (Float.compare(fSeconds, 1.0F) != 0)
      {
        buf.append('s');
      }
    }
    
    // Return the string
    return buf.toString();
  }
  
  
  /**
   * Initialize the application's Look And Feel with the default
   * for this OS.
   */
  public static void initLookAndFeel()
  {
    // Use the default look and feel
    try
    {
      javax.swing.UIManager.setLookAndFeel(
        javax.swing.UIManager.getSystemLookAndFeelClassName());
    }
    catch (Exception e)
    {
      Logger.error("Exception: " + e.getMessage());
    }
  }
}
