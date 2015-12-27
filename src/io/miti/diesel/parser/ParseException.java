package io.miti.diesel.parser;

public final class ParseException extends Exception
{
  /** Default version ID. */
  private static final long serialVersionUID = 1L;
  
  /**
   * Default constructor.
   */
  public ParseException() {
    super();
  }
  
  /**
   * Constructor taking a message.
   * 
   * @param message the exception message
   */
  public ParseException(final String message) {
    super(message);
  }
}
