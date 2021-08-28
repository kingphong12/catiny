/**
 * nếu không phải json thì return luôn chuỗi string đó
 * nếu là json : tìm link
 *             : không thì tìm url
 * mặc định return: "content/images/logo/Catiny-logo.svg"
 *
 * @param jsonOrUrl json chứa chi tiết của ảnh hoặc url ảnh
 * @return "content/images/logo/Catiny-logo.svg" (default)
 */
export const imageUrl = (jsonOrUrl) =>
{

  if (jsonOrUrl)
  {
    const jsonParsed = JSON.parse(jsonOrUrl);
    if (!jsonParsed)
      return jsonOrUrl;
    if (jsonParsed)
      if (jsonParsed.link)
        return jsonParsed.link;
      else if (jsonParsed.url)
        return jsonParsed.url;
  }
  return "content/images/logo/Catiny-logo.svg";
}