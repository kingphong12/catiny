package com.regitiny.catiny.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
/**
 * nghiêm cấm sử dụng trong trong các trường hợp :
 * bean khỏi tạo cùng hệ thống , bean auto load ban đầu.
 * nói chung chỉ sử dụng trong các method runtime
 */
public class ApplicationContextUtils
{
  private static ApplicationContext applicationContext;

  public static ApplicationContext getApplicationContext()
  {
    return applicationContext;
  }

  @Autowired
  private void setApplicationContext(ApplicationContext applicationContext)
  {
    ApplicationContextUtils.applicationContext = applicationContext;
  }
}
