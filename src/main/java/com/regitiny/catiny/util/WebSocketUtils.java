package com.regitiny.catiny.util;

import com.regitiny.catiny.common.utils.StringPool;

public class WebSocketUtils
{
  public static final String TOPIC_CONSUMER = "/topic.consumer";
  public static final String TOPIC_PRODUCER = "/topic.producer";

  public static String topicConsumer(String topic)
  {
    topic = topic.startsWith("/") ? topic.substring(1) : topic;
    return TOPIC_CONSUMER + StringPool.SLASH + topic;
  }

  public static String topicProducer(String topic)
  {
    topic = topic.startsWith("/") ? topic.substring(1) : topic;
    return TOPIC_PRODUCER + StringPool.SLASH + topic;
  }
}
