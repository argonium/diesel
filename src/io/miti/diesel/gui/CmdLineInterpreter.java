package io.miti.diesel.gui;

import io.miti.diesel.app.DieselConsole;
import io.miti.diesel.interpret.ConfigArg;
import io.miti.diesel.interpret.LineParser;
import io.miti.diesel.util.JarSearch;
import io.miti.diesel.util.Logger;
import io.miti.diesel.util.SystemInfo;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * Processes commands entered by the user.
 */
public final class CmdLineInterpreter implements Interpreter
{
  /** An iterator over the strings in the user's command. */
  private List<String> cmds = null;
  
  /** Whether the status bar was updated after command execution.  */
  private boolean barUpdated = false;
  
  /** Declare the date formatter used to format the time for the prompt. */
  private static final SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm:ss] > ");

  /**
   * Default constructor.
   */
  public CmdLineInterpreter()
  {
    super();
  }
  
  
  /**
   * Write the banner to the text area.
   * 
   * @param text the text area control
   */
  @Override
  public void writeBanner(final JTextArea text)
  {
    JConsole.addText("Diesel Console.  Enter 'help' to see available commands.\n");
  }
  
  
  /**
   * Write the prompt to the text area.
   * 
   * @param text the text area control
   */
  @Override
  public void writePrompt(final JTextArea text)
  {
    String prompt = sdf.format(new Date());
    JConsole.addText(prompt);
  }
  
  
  /**
   * Process a command entered by the user.
   * 
   * @param text the text area control
   * @param cmd the text command
   */
  @Override
  public void processCommand(final JTextArea text, final String cmd)
  {
    // Parse the input command
    cmds = new LineParser().parseIntoPhrases(cmd);
    
    // If no command, return
    if (cmds.size() < 1)
    {
      DieselConsole.getApp().setStatusBarText(null);
      JConsole.addText("\n");
      return;
    }
    
    // TODO
    JConsole.addText("\nHandle CmdLineInterpreter::processCommand\n");
    
    // Search cmds.txt for a match
//    final boolean result = processCommands();
//    if (!result)
//    {
//      JConsole.addText("\nUnknown command\n");
//    }
//    else
//    {
//      DieselConsole.getApp().setStatusBarText(null);
//      
//      // This should only happen if a command didn't set some text
//      if (!barUpdated)
//      {
//        writeSeparateResults(null, "OK");
//        JConsole.addText("\n");
//      }
//    }
  }
  
//  private boolean processCommands()
//  {
//	  boolean lineProcessed = false;
//	  
//	  Iterator<Command> cmds = CommandManager.getInstance().getIterator();
//	  while (cmds.hasNext()) {
//		  
//		Command cmd = cmds.next();
//		if (!processLine(cmd))
//		{
//		  // Stop processing
//		  lineProcessed = true;
//		  break;
//		}
//	  }
//	  
//	  return lineProcessed;
//  }
  
  
  /**
   * Write out different map and log window messages.
   * 
   * @param msgMap the message for the map's status window
   * @param msgLog the msg for the log window
   */
  public static void writeSeparateResults(final String msgMap,
                                          final String msgLog)
  {
    showResults(msgMap, msgLog);
  }
  
  
  /**
   * Write map and log window messages.
   * 
   * @param msgCommon the msg for both windows
   */
  public static void writeResults(final String msgCommon)
  {
    showResults(msgCommon, msgCommon);
  }
  
  
  /**
   * Write the result to the status bar and log window.
   * 
   * @param msgMap the string for the map window (status bar)
   * @param msgLog the string for the log window
   */
  private static synchronized void showResults(final String msgMap,
                                               final String msgLog)
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      @Override
      public void run()
      {
        DieselConsole.getApp().setStatusBarText(msgMap);
      }
    });
  }
  
  
  /**
   * Parse the line from the input file.
   * 
   * @param line the line from the file
   * @return whether to continue parsing the file
   */
//  public boolean processLine(final Command cmd)
//  {
//    // Parse the line from the command file
//    ConfigParser parser = new ConfigParser();
//    
//    // Get the functor
//    String functor = cmd.getMethod();
//    if ((functor == null) || functor.isEmpty())
//    {
//      return true;
//    }
//    
//    // Get the iterator over the config arguments
//    Iterator<ConfigArg> args = parser.parseArgs(cmd.getCommand());
//    
//    // See if this command matches the line from the input file
//    boolean keepReading = true;
//    Object[] functorData = matchData(args);
//    if (functorData.length > 0)
//    {
//      keepReading = false;
//      
//      // Call the method via reflection
//      dispatch(functor, functorData);
//    }
//    
//    return keepReading;
//  }
  
  
  /**
   * Check for a match between the command and the Config data.
   * 
   * @param args the list of config parameters from the file
   * @return the parsed data, if a match
   */
//  private Object[] matchData(final Iterator<ConfigArg> args)
//  {
//    Iterator<String> cmdsIter = cmds.iterator();
//    
//    // If they match, return an array of the parameters in args
//    List<Object> data = new ArrayList<Object>(20);
//    boolean ok = true;
//    while (ok && args.hasNext())
//    {
//      final ConfigArg config = args.next();
//      
//      // Check if more commands are available
//      if (!cmdsIter.hasNext())
//      {
//        // We ran out of commands, so this doesn't match
//        ok = false;
//        break;
//      }
//      final String cmd = cmdsIter.next();
//      
//      // Check if the data matches up
//      if (config.isToken())
//      {
//        ok = (config.getValue().equalsIgnoreCase(cmd));
//        if (ok)
//        {
//          data.add(cmd);
//        }
//      }
//      else if (config.isLong())
//      {
//        long value = getLong(cmd);
//        ok = (value != Long.MIN_VALUE);
//        if (ok)
//        {
//          data.add(Long.valueOf(value));
//        }
//      }
//      else if (config.isInteger())
//      {
//        int value = getInteger(cmd);
//        ok = (value != Integer.MIN_VALUE);
//        if (ok)
//        {
//          data.add(Integer.valueOf(value));
//        }
//      }
//      else if (config.isString())
//      {
//        ok = true;
//        data.add(cmd);
//      }
//      else if (config.isFloat())
//      {
//        float value = getFloat(cmd);
//        ok = (value != Float.NaN);
//        if (ok)
//        {
//          data.add(Float.valueOf(value));
//        }
//      }
//      else if (config.isDouble())
//      {
//        double value = getDouble(cmd);
//        ok = (value != Double.NaN);
//        if (ok)
//        {
//          data.add(Double.valueOf(value));
//        }
//      }
//    }
//    
//    // See if there are more cmds
//    if (cmdsIter.hasNext())
//    {
//      // There are, so the two lists don't match up
//      ok = false;
//    }
//    
//    // Check for an error
//    if (!ok)
//    {
//      return (new Object[0]);
//    }
//    
//    return data.toArray();
//  }
  
  
//  /**
//   * Return the Float in the String.
//   * 
//   * @param obj the string to parse
//   * @return the float value, else Float.NaN
//   */
//  private float getFloat(final String obj)
//  {
//    float value = Float.NaN;
//    try
//    {
//      value = Float.parseFloat(obj);
//    }
//    catch (NumberFormatException nfe)
//    {
//      value = Float.NaN;
//    }
//    
//    return value;
//  }
//  
//  
//  /**
//   * Return the Double in the String.
//   * 
//   * @param obj the string to parse
//   * @return the double value, else Double.NaN
//   */
//  private double getDouble(final String obj)
//  {
//    double value = Double.NaN;
//    try
//    {
//      value = Double.parseDouble(obj);
//    }
//    catch (NumberFormatException nfe)
//    {
//      value = Double.NaN;
//    }
//    
//    return value;
//  }
//  
//  
//  /**
//   * Return the Integer in the String.
//   * 
//   * @param obj the string to parse
//   * @return the integer value, else Integer.MIN_VALUE
//   */
//  private int getInteger(final String obj)
//  {
//    int value = Integer.MIN_VALUE;
//    try
//    {
//      value = Integer.parseInt(obj);
//    }
//    catch (NumberFormatException nfe)
//    {
//      value = Integer.MIN_VALUE;
//    }
//    
//    return value;
//  }
//  
//  
//  /**
//   * Return the Long in the String.
//   * 
//   * @param obj the string to parse
//   * @return the long value, else Long.MIN_VALUE
//   */
//  private long getLong(final String obj)
//  {
//    long value = Long.MIN_VALUE;
//    try
//    {
//      value = Long.parseLong(obj);
//    }
//    catch (NumberFormatException nfe)
//    {
//      value = Long.MIN_VALUE;
//    }
//    
//    return value;
//  }
  
  
  /**
   * Dispatch to the target method.
   * 
   * @param functor the method to call
   * @param functorData the data for the method
   */
  public void dispatch(final String functor,
                       final Object[] functorData)
  {
    try
    {
      // Call the method through reflection
      Class<? extends Interpreter> cls = this.getClass();
      Method method = cls.getMethod(functor, new Class[] {Object[].class});
      Object result = method.invoke(this, new Object[] {functorData});
      barUpdated = (Boolean) result;
    }
    catch (SecurityException e)
    {
      e.printStackTrace();
    }
    catch (NoSuchMethodException e)
    {
      e.printStackTrace();
    }
    catch (IllegalArgumentException e)
    {
      e.printStackTrace();
    }
    catch (IllegalAccessException e)
    {
      e.printStackTrace();
    }
    catch (InvocationTargetException e)
    {
      e.printStackTrace();
    }
  }
  
  
  /**
   * Quit the app.
   * 
   * @param args the array of arguments
   * @return whether the status bar text was modified
   */
  public boolean quit(final Object[] args)
  {
    DieselConsole.getApp().exitApp();
    return false;
  }
  
  
  /**
   * Show the version number.
   * 
   * @param args the array of arguments
   * @return whether the status bar text was modified
   */
  public boolean showVersion(final Object[] args)
  {
    JConsole.addText("\nDiesel Console, version 0.1 (26 Dec 2015)\n");
    return true;
  }
  
  
  /**
   * Print the current working directory.
   * 
   * @param args the array of arguments
   * @return whether the application updated the console
   */
  public boolean pwd(final Object[] args)
  {
	  String currDir = System.getProperty("user.dir");
	  JConsole.addText("\n" + currDir + "\n");
	  return true;
  }
  
  
  public boolean console(final Object[] args)
  {
	  // Check the OS
	  String cmd = null;
	  if (SystemInfo.isWindows()) {
		  cmd = "cmd /c start cmd.exe";
	  } else if (SystemInfo.isMac()) {
		  String currDir = System.getProperty("user.dir");
		  cmd = "/usr/bin/open -a Terminal " + currDir;
	  }
	  
	  if (cmd == null) {
		  JConsole.addText("\nUnable to launch a console\n");
	  } else {
		  try {
			Runtime.getRuntime().exec(cmd);
			JConsole.addText("\nConsole started.\n");
		} catch (IOException e) {
			Logger.error(e);
			JConsole.addText("Error starting console: " + e.getMessage());
		}
	  }
	  
	  return true;
  }
  
  
  /**
   * Show the help.
   * 
   * @param args the array of arguments
   * @return whether the status bar text was modified
   */
  public boolean help(final Object[] args)
  {
    JConsole.addText("\nHelp not yet available\n");
    
//    // Get the list of command help
//    List<String> cmds = CommandManager.getInstance().getHelp(null);
//    
//    // Sort the list and print the contents
//    Collections.sort(cmds);
//    final int size = cmds.size();
//    StringBuilder sb = new StringBuilder(300);
//    sb.append("\nThe following commands are supported:");
//    for (int i = 0; i < size; ++i)
//    {
//      sb.append("\n" + cmds.get(i));
//    }
//    sb.append("\n");
//    
//    // Show the text
//    JConsole.addText(sb.toString());
    
    return true;
  }
  
  
  /**
   * Clear the screen.
   * 
   * @param args the array of arguments
   * @return whether the status bar text was modified
   */
  public boolean clear(final Object[] args)
  {
    JConsole.setText("");
    return true;
  }
  
  
  /**
   * List the commands starting with some prefix.
   * 
   * @param args the array of arguments
   * @return whether the status bar text was modified
   */
  public boolean partialHelp(final Object[] args)
  {
    JConsole.addText("\nHelp not yet available\n");

//    // Get the command help
//    List<String> cmds = CommandManager.getInstance().getHelp(null);
//    
//    // Save the current size
//    int size = cmds.size();
//    
//    // Iterate over the list (in reverse) and remove any commands that
//    // start with any string other than the search criteria
//    final String criteria = ((String) args[1]).toUpperCase();
//    for (int i = (size - 1); i >= 0; --i)
//    {
//      final String temp = cmds.get(i).toUpperCase();
//      if (!temp.startsWith(criteria))
//      {
//        cmds.remove(i);
//      }
//    }
//    
//    // Update the list size
//    size = cmds.size();
//    
//    if (size == 0)
//    {
//      JConsole.addText("\nNo commands match the search criteria\n");
//      return true;
//    }
//    
//    // Sort the list and build into a list
//    Collections.sort(cmds);
//    StringBuilder sb = new StringBuilder(100);
//    for (int i = 0; i < size; ++i)
//    {
//      sb.append("\n" + cmds.get(i));
//    }
//    sb.append("\n");
//    
//    // Show the text
//    JConsole.addText(sb.toString());
    
    return true;
  }
  
  
  /**
   * Convert a date from a number into a formatted date.
   * 
   * @param args the array of arguments
   * @return whether the status bar text was modified
   */
  public boolean showDate(final Object[] args)
  {
    // Get the number passed to the method
    final Long date = (Long) args[1];
    
    // Format the date
    final SimpleDateFormat sdf =
                new SimpleDateFormat("hh:mm:ss aa 'on' MMMM dd, yyyy");
    String dateStr = sdf.format(new Date(date.longValue()));
    JConsole.addText("\nThe date is " + dateStr + "\n");
    return true;
  }
  
  
  /**
   * Convert a date from a number into a formatted date.
   * 
   * @param args the array of arguments
   * @return whether the status bar text was modified
   */
  public boolean showDateStr(final Object[] args)
  {
    // Get the number passed to the method
    final String date = (String) args[1];
    final String time = (String) args[2];
    
    final String target = date + " " + time;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss", Locale.ENGLISH);
    Date result = null;
    try {
		result = df.parse(target);
	} catch (ParseException e) {
		result = null;
	}
    
    if (result == null) {
    	JConsole.addText("\nInvalid format: Should be 'date MM/DD/YYYY HH:MM:SS'\n");
    	return true;
    }
    
    JConsole.addText("\nThe time for this date is " + result.getTime() + "\n");
    return true;
  }
  
  public boolean loadJar(final Object[] args)
  {
    // Save the name of the jar to load
    final String jarName = (String) args[2];
    
    // Confirm the filename
    if (!jarName.toLowerCase().endsWith(".jar"))
    {
      JConsole.addText("\nOnly JAR files can be added to the class path\n");
      return true;
    }
    
    final File file = new File(jarName);
    if (!file.exists())
    {
      JConsole.addText("\nThe JAR file could not be found\n");
      return true;
    }
    else if (!file.isFile())
    {
      JConsole.addText("\nThe specified directory cannot be added to the class path\n");
      return true;
    }
    
    // Add the jar to the class path
    try
    {
      URL urlJar = file.toURI().toURL();
      URLClassLoader.newInstance(new URL[] {urlJar});
      
      JConsole.addText("\nJAR file added to classpath successfully\n");
    }
    catch (MalformedURLException e)
    {
      JConsole.addText("\nException: " + e.getMessage());
    }
    
    return true;
  }
	
	public boolean jarSearch(final Object[] args)
	{
		final String base = (String) args[2];
		final String className = (String) args[3];
		
		// Check the base name to ensure it exists (as a file or directory)
		final File file = new File(base);
		if (!file.exists()) {
			JConsole.addText("\nError: The input file/directory " + base +
					         " was not found\n");
		} else {
		    JarSearch search = new JarSearch(base);
		    search.search(className);
		    final String msg = search.getResults();
		    JConsole.addText(msg);
		}
		
		return true;
	}
	
	
	public boolean printManifest(final Object[] args)
	{
		// Check the input file
		final String fname = (String) args[2];
		final File file = new File(fname);
		if (!file.exists() || !file.isFile()) {
			JConsole.addText("\nInput JAR file was not found\n");
			return true;
		}
		
		try {
			// Open the jar file and read its manifest
			final JarFile jarFile = new JarFile(fname);
			final Manifest mani = jarFile.getManifest();
			
			// Check for null/empty list of attributes
			final Attributes attrs = mani.getMainAttributes();
			if (attrs == null) {
				JConsole.addText("\nThe list of attributes is null\n");
			} else if (attrs.isEmpty()) {
				JConsole.addText("\nThe list of attributes is empty\n");
			} else {
				// Iterate over the main attributes
				StringBuilder sb = new StringBuilder(100);
				sb.append("\n");
				for (Entry<Object, Object> entry : attrs.entrySet()) {
					sb.append(entry.getKey().toString()).append(": ").append(entry.getValue().toString()).append("\n");
				}
				JConsole.addText(sb.toString());
			}
			
			// Close the jar file
			jarFile.close();
		} catch (IOException e) {
			JConsole.addText("\nException reading manifest: " + e.getMessage() + "\n");
		}
		
		return true;
	}
}
