package io.miti.diesel.parser;

import java.util.ArrayList;
import java.util.List;

public final class Parser {
  
  private String output = null;
  
  /**
   * Default constructor.
   */
  public Parser() {
    super();
  }

  private static boolean isWhitespace(final char ch) {
    return Character.isWhitespace(ch);
  }

  private static boolean isWord(final char ch) {
    return Character.isJavaIdentifierStart(ch);
  }

  private static void checkBuffer(final List<String> tokens, final StringBuilder sb) {
    if (sb.length() > 0) {
      tokens.add(sb.toString());
      sb.setLength(0);
    }
  }

  public List<Token> parse(final String line) throws ParseException {

    // Tokenize the line
    final List<String> tokens = tokenize(line);

    // Turn the list of tokens into tagged values
    final List<Token> data = lookupTokens(tokens);

    return data;
  }

  private List<Token> lookupTokens(final List<String> tokens) {
    if ((tokens == null) || tokens.isEmpty()) {
      return null;
    }

    List<Token> data = new ArrayList<>(tokens.size());
    for (String token : tokens) {
      data.add(new Token(token));
    }

    return data;
  }

  private List<String> tokenize(final String line) throws ParseException {

    List<String> tokens = new ArrayList<String>(10);

    // Check the input
    if ((line == null) || line.trim().isEmpty()) {
      return tokens;
    }

    // Tokenize
    boolean inQuote = false;
    boolean singleQuotes = false;
    boolean inWord = false;
    int i = 0;
    final int size = line.length();
    StringBuilder sb = new StringBuilder(100);
    char ch = 0;
    while (i < size) {
      ch = line.charAt(i);
      if (inQuote) {
        sb.append(ch);
        if (ch == (singleQuotes ? '\'' : '"')) {
          checkBuffer(tokens, sb);
          inQuote = false;
        }
      } else {
        if (ch == '#') {
          // We hit a comment, so abort
          checkBuffer(tokens, sb);
          break;
        } else if ((ch == '.') || (ch == '=')) {
          checkBuffer(tokens, sb);
          tokens.add(new String("" + ch));
          inWord = false;
        } else if (isWhitespace(ch)) {
          checkBuffer(tokens, sb);
          inWord = false;
        } else if (isWord(ch)) {
          inWord = true;
          sb.append(ch);
        } else if ((ch == '\'') || (ch == '"')) {
          if (inWord) {
            throw new ParseException("Did not expect quote at position " + (i + 1));
          }
          inQuote = true;
          singleQuotes = (ch == '\'');
          sb.append(ch);
        } else {
          throw new ParseException("Unknown character: " + ch + " at position " + (i + 1));
        }
      }

      ++i;
    }

    if (inQuote) {
      throw new ParseException("Unclosed quoted string");
    }

    checkBuffer(tokens, sb);

    return tokens;
  }
  
  public String getOutput() {
    return output;
  }
}
