package io.miti.diesel.executor;

import java.util.List;

import io.miti.diesel.app.DieselConsole;
import io.miti.diesel.parser.Token;

public final class CmdExecutor {
  
  /** The variables for this execution. */
  private Session session = null;
  
  /** Any output for the UI. */
  private String output = null;
  
  /**
   * Default constructor.
   */
  public CmdExecutor() {
    session = new Session();
  }
  
  /**
   * Execute the tokens in the list.
   * 
   * @param tokens the list of tokens in the last command
   */
  public void exec(final List<Token> tokens) throws Exception {
    // TODO Execute the command, or throw an exception
    output = null;
    
    // Check the tokens
    if ((tokens.size() == 4) && tokens.get(0).isLiteral() && tokens.get(0).getValue().equals("var") &&
        tokens.get(1).isLiteral() && tokens.get(2).isEquals() && tokens.get(3).isString()) {
      assignVariable(tokens);
    } else if ((tokens.size() == 2) && tokens.get(0).isLiteral() && tokens.get(0).getValue().equals("print") &&
        tokens.get(1).isString()) {
      printVariable(tokens);
    } else if (tokens.size() == 1 && tokens.get(0).isLiteral() &&
        (tokens.get(0).getValue().equals("exit") || (tokens.get(0).getValue().equals("quit")))) {
      DieselConsole.getApp().exitApp();
    } else {    
      throw new Exception("Unknown command");
    }
  }
  
  private void printVariable(List<Token> tokens) {
    if (tokens.get(1).isLiteral()) {
      String val = session.getVariable(tokens.get(1).getValue());
      output = val;
    } else {
      output = tokens.get(1).getValue();
    }
  }

  private void assignVariable(List<Token> tokens) {
    if (tokens.get(3).isLiteral()) {
      String val = session.getVariable(tokens.get(3).getValue());
      session.setVariable(tokens.get(1).getValue(), val);
    } else {
      session.setVariable(tokens.get(1).getValue(), tokens.get(3).getQString());
    }
  }

  /**
   * Return any output of the last command.
   * 
   * @return the output of the last command
   */
  public String getOutput() {
    return output;
  }
}
