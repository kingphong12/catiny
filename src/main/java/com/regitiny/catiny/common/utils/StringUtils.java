package com.regitiny.catiny.common.utils;

import org.jsoup.Jsoup;

import java.util.Random;

import static com.regitiny.catiny.common.utils.StringPool.CHARS_ID;

/**
 *
 */
public class StringUtils
{
  private static final Random random = new Random();

  public static String cleanHTML(String input)
  {
    String afterCleanHTML = Jsoup.parse(input).text();
    String lowerCase = afterCleanHTML.toLowerCase();
    return org.apache.commons.lang3.StringUtils.stripAccents(lowerCase).replace("đ", "d");
  }

  /**
   * @param input (example): This>is~some(string,with $invalid*-chars)%con()-Mèo,máy.jpg
   * @return (example) this_is_some_string_with_invalid_-chars_-con-meo-may.jpg
   */
  public static String cleanFileName(String input)
  {
    return org.apache.commons.lang3.StringUtils.stripAccents(input.toLowerCase())
      .replace("đ", "d")
      .replaceAll("[^a-zA-Z0-9-_.]", "-")
      .replaceAll("[-]+", "-")
      .replaceAll("[_]+", "_");
  }

  public static String cleanCharVI(String input)
  {
    return org.apache.commons.lang3.StringUtils.stripAccents(input).replace("đ", "d");
  }

  public static String randomId(Integer length)
  {
    var sb = new StringBuilder();
    for (var i = 0; i < length; i++)
      sb.append(CHARS_ID.charAt(random.nextInt(CHARS_ID.length())));
    return sb.toString();
  }
}
