package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.VideoStream} entity.
 */
@ApiModel(
  description = "<p>@what?            -> The VideoStream entity.\n<p>@why?             ->\n<p>@use-to           -> Lưu thông tin video ... khi đã kết thúc stream\n<p>@commonly-used-in -> Sau khi kết thức stream thì video lưu lai cũng chỉ tương tự như một video thông thường\n\n<p>@describe         ->"
)
@GeneratedByJHipster
public class VideoStreamDTO implements Serializable {

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

  private Boolean isLivestreaming;

  private VideoDTO video;

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

  public Boolean getIsLivestreaming() {
    return isLivestreaming;
  }

  public void setIsLivestreaming(Boolean isLivestreaming) {
    this.isLivestreaming = isLivestreaming;
  }

  public VideoDTO getVideo() {
    return video;
  }

  public void setVideo(VideoDTO video) {
    this.video = video;
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
    if (!(o instanceof VideoStreamDTO)) {
      return false;
    }

    VideoStreamDTO videoStreamDTO = (VideoStreamDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, videoStreamDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "VideoStreamDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", isLivestreaming='" + getIsLivestreaming() + "'" +
            ", video=" + getVideo() +
            ", info=" + getInfo() +
            "}";
    }
}
