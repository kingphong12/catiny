package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.NotifyType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.Notification} entity.
 */
@ApiModel(
  description = "<p>@what?            -> The Notification entity.\n<p>@why?             ->\n<p>@use-to           -> Chứa những thông báo đến người dùng\n<p>@commonly-used-in -> Thường xuất hiện trong chức năng thông báo của người dùng\n\n<p>@describe         ->"
)
@GeneratedByJHipster
public class NotificationDTO implements Serializable {

  private Long id;

  /**
   * uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)
   */
  @NotNull
  @ApiModelProperty(
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)",
    required = true
  )
  private UUID uuid;

  /**
   * notifyType     : loại thông báo
   */
  @ApiModelProperty(value = "notifyType     : loại thông báo")
  private NotifyType notifyType;

  private String title;

  @Lob
  private String content;

  private BaseInfoDTO info;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public NotifyType getNotifyType() {
    return notifyType;
  }

  public void setNotifyType(NotifyType notifyType) {
    this.notifyType = notifyType;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public BaseInfoDTO getInfo() {
    return info;
  }

  public void setInfo(BaseInfoDTO info) {
    this.info = info;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof NotificationDTO)) {
      return false;
    }

    NotificationDTO notificationDTO = (NotificationDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, notificationDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "NotificationDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", notifyType='" + getNotifyType() + "'" +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", info=" + getInfo() +
            "}";
    }
}
