package io.miti.diesel.parser;

public final class Token
{
  TokenType tokenType = TokenType.LITERAL;
  String value = null;
  
  public Token() {
    super();
  }
  
  public Token(final String sValue) {
    // Set token type
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
  
  public String getValue() {
    return value;
  }
  
  public String getQString() {
    if (tokenType.equals(TokenType.QSTRING)) {
      return value.substring(1, value.length() - 1);
    }
    
    return null;
  }
  
  public boolean isEquals() {
    return tokenType.equals(TokenType.EQUALS);
  }
  
  public boolean isPeriod() {
    return tokenType.equals(TokenType.PERIOD);
  }
  
  public boolean isQString() {
    return tokenType.equals(TokenType.QSTRING);
  }
  
  public boolean isLiteral() {
    return tokenType.equals(TokenType.LITERAL);
  }
  
  public boolean isString() {
    return (isQString() || isLiteral());
  }
}
