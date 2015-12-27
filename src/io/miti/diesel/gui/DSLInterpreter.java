package io.miti.diesel.gui;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

public final class DSLInterpreter implements Interpreter {

  /** Declare the date formatter used to format the time for the prompt. */
  private static final SimpleDateFormat sdf = new SimpleDateFormat("[HH:mm:ss] > ");
  
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
    text.append("\nProcessing DSL command " + cmd + "\n");
  }
}
