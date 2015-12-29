package io.miti.diesel.gui;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

import io.miti.diesel.app.DieselConsole;

/**
 * Processes commands entered by the user.
 */
public final class CmdLineInterpreter implements Interpreter
{
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
}
