package io.miti.diesel.executor;

import java.util.List;

import io.miti.diesel.parser.Token;

public final class CmdExecutor {
  
  private String output = null;
  
  /**
   * Default constructor.
   */
  public CmdExecutor() {
    super();
  }
  
  /**
   * Execute the tokens in the list.
   * 
   * @param tokens the list of tokens in the last command
   */
  public void exec(final List<Token> tokens) {
    // TODO Execute the command, or throw an exception
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
