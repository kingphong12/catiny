package com.regitiny.catiny.tools.utils;

import org.jsoup.Jsoup;

/**
 *
 */
public class StringUtils
{

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
  public static String clean(String input)
  {
    return org.apache.commons.lang3.StringUtils.stripAccents(input.toLowerCase())
      .replace("đ", "d")
      .replaceAll("[^a-zA-Z0-9-_.]", "-")
      .replaceAll("[-]+", "-")
      .replaceAll("[_]+", "_");
  }
}
