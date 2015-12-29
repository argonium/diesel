package io.miti.diesel.gui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JTextArea;

import io.miti.diesel.parser.ParseException;
import io.miti.diesel.parser.Parser;
import io.miti.diesel.parser.Token;

public final class DSLInterpreter implements Interpreter {

  /** Declare the date formatter used to format the time for the prompt. */
  private static final SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm:ss] > ");
  
  /** The command parser. */
  private Parser parser = null;
  
  /**
   * Public constructor.
   */
  public DSLInterpreter() {
    parser = new Parser();
  }
  
  @Override
  public void writeBanner(final JTextArea text) {
    text.append("Diesel Console.  Enter 'help' to see available commands.\n");
  }

  @Override
  public void writePrompt(final JTextArea text) {
    String time = sdf.format(new Date());
    text.append(time);
  }

  @Override
  public void processCommand(final JTextArea text, final String cmd) {
    // Tokenize and execute the command
    try {
      // Parse the command
      final List<Token> tokens = parser.parse(cmd);
      
      // Look for a match on the command
      boolean executed = executeCommand(text, tokens);
      if (!executed) {
        text.append("\nUnknown command\n");
      }
    } catch (ParseException e) {
      text.append("\nParsing error: " + e.getMessage() + "\n");
    }
  }
  
  /**
   * See if we handle this command; if so, execute it.
   * 
   * @param tokens the list of tokens in the command
   * @return whether the command was executed
   */
  private boolean executeCommand(final JTextArea text, final List<Token> tokens) {
    // TODO Look for a match, and execute it
    ;
    
    // Check the output of the command
//    final String output = parser.getOutput();
//    if ((output == null) || output.isEmpty()) {
//      text.append("\n");
//      return true;
//    } else {
//      text.append("\n" + output + "\n");
//    }
    
    // Return success
    return true;
  }
}
