package com.regitiny.catiny.common.utils;

import java.nio.charset.Charset;

public class StringPool
{

  public static final String AMPERSAND = "&";

  public static final String AMPERSAND_ENCODED = "&amp;";

  public static final String APOSTROPHE = "'";

  public static final String APOSTROPHE_ENCODED = "&apos;";

  public static final String[] ASCII_TABLE = new String[128];

  public static final String AT = "@";

  public static final String BACK_SLASH = "\\";

  public static final String BETWEEN = "BETWEEN";

  public static final String BLANK = "";

  public static final String CARET = "^";

  public static final String CDATA_CLOSE = "]]>";

  public static final String CDATA_OPEN = "<![CDATA[";

  public static final String CLOSE_BRACKET = "]";

  public static final String CLOSE_CURLY_BRACE = "}";

  public static final String CLOSE_PARENTHESIS = ")";

  public static final String COLON = ":";

  public static final String COMMA = ",";

  public static final String COMMA_AND_SPACE = ", ";

  public static final String CONTENT = "content";

  public static final String DASH = "-";

  public static final String DEFAULT_CHARSET_NAME = Charset.defaultCharset().name();

  public static final String DOLLAR = "$";

  public static final String DOLLAR_AND_OPEN_CURLY_BRACE = "${";

  public static final String DOUBLE_APOSTROPHE = "''";

  public static final String DOUBLE_BACK_SLASH = "\\\\";

  public static final String DOUBLE_CLOSE_BRACKET = "]]";

  public static final String DOUBLE_CLOSE_CURLY_BRACE = "}}";

  public static final String DOUBLE_DASH = "--";

  public static final String DOUBLE_DOLLAR = "$$";

  public static final String DOUBLE_OPEN_BRACKET = "[[";

  public static final String DOUBLE_OPEN_CURLY_BRACE = "{{";

  public static final String DOUBLE_PERIOD = "..";

  public static final String DOUBLE_QUOTE = "\"\"";

  public static final String DOUBLE_SLASH = "//";

  public static final String DOUBLE_SPACE = "  ";

  public static final String DOUBLE_UNDERLINE = "__";

  public static final String EIGHT_STARS = "********";

  public static final String[] EMPTY_ARRAY = new String[0];

  public static final String EQUAL = "=";

  public static final String EXCLAMATION = "!";

  public static final String FALSE = "false";

  public static final String FORWARD_SLASH = "/";

  public static final String FOUR_SPACES = "    ";

  public static final String GRAVE_ACCENT = "`";

  public static final String GREATER_THAN = ">";

  public static final String GREATER_THAN_OR_EQUAL = ">=";

  public static final String INVERTED_EXCLAMATION = "\u00A1";

  public static final String INVERTED_QUESTION = "\u00BF";

  public static final String IS_NOT_NULL = "IS NOT NULL";

  public static final String IS_NULL = "IS NULL";

  public static final String ISO_8859_1 = "ISO-8859-1";

  public static final String LAQUO = "&laquo;";

  public static final String LAQUO_CHAR = "\u00AB";

  public static final String LESS_THAN = "<";

  public static final String LESS_THAN_OR_EQUAL = "<=";

  public static final String LIKE = "LIKE";

  public static final String MINUS = "-";

  public static final String NBSP = "&nbsp;";

  public static final String NEW_LINE = "\n";

  public static final String NOT_EQUAL = "!=";

  public static final String NOT_LIKE = "NOT LIKE";

  public static final String NULL = "null";

  public static final String NULL_CHAR = "\u0000";

  public static final String OPEN_BRACKET = "[";

  public static final String OPEN_CURLY_BRACE = "{";

  public static final String OPEN_PARENTHESIS = "(";

  public static final String OS_EOL = System.getProperty("line.separator");

  public static final String PERCENT = "%";

  public static final String PERIOD = ".";

  public static final String PIPE = "|";

  public static final String PLUS = "+";

  public static final String POUND = "#";

  public static final String PRIME = "`";

  public static final String QUESTION = "?";

  public static final String QUOTE = "\"";

  public static final String QUOTE_ENCODED = "&quot;";

  public static final String RAQUO = "&raquo;";

  public static final String RAQUO_CHAR = "\u00BB";

  public static final String RETURN = "\r";

  public static final String RETURN_NEW_LINE = "\r\n";

  public static final String SEMICOLON = ";";

  public static final String SLASH = FORWARD_SLASH;

  public static final String SPACE = " ";

  public static final String STAR = "*";

  public static final String TAB = "\t";

  public static final String THREE_SPACES = "   ";

  public static final String TILDE = "~";

  public static final String TRIPLE_PERIOD = "...";

  public static final String TRUE = "true";

  public static final String UNDERLINE = "_";

  public static final String UTC = "UTC";

  public static final String UTF8 = "UTF-8";

  public static final String DOT_IN_REGEX = "\\.";

  public static final String UUID_REGEX = "[a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8}";

  public static final String CHARS_ID = charToGenId(); //a-zA-Z0-9\-\_

  private static String charToGenId()
  {
    var alphabetBuilder = new StringBuilder();                        // ""
    for (var i = 97; i < 123; i++)                                    //a-z
      alphabetBuilder.append(ASCII_TABLE[i]);
    alphabetBuilder.append(alphabetBuilder.toString().toUpperCase()); //a-zA-Z
    for (var i = 0; i < 10; i++)                                      //a-zA-Z0-9
      alphabetBuilder.append(i);
    alphabetBuilder.append("-_");                                     //a-zA-Z0-9\-\_
    return alphabetBuilder.toString();
  }

  static
  {
    for (int i = 0; i < 128; i++)
      ASCII_TABLE[i] = String.valueOf((char) i);
  }
}
