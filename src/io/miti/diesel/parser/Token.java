package io.miti.diesel.parser;

public final class Token
{
  TokenType tokenType = TokenType.LITERAL;
  String value = null;
  
  public Token() {
    super();
  }
  
  public Token(final String sValue) {
    // TODO Set token type
    value = sValue;
    if (value.equals("=")) {
      tokenType = TokenType.EQUALS;
    } else if (value.equals(".")) {
      tokenType = TokenType.PERIOD;
    } else if (value.startsWith("\"") || value.startsWith("'")) {
      tokenType = TokenType.QSTRING;
    } else {
      tokenType = TokenType.LITERAL;
    }
  }

  @Override
  public String toString() {
    return "Token [tokenType=" + tokenType + ", value=" + value + "]";
  }
}
