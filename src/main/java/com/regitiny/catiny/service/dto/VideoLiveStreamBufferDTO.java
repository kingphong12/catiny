package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.VideoLiveStreamBuffer} entity.
 */
@ApiModel(
  description = "<p>@what?            -> The VideoLiveStreamBuffer entity.\n<p>@why?             ->\n<p>@use-to           -> Lưu từng phần video dưới dạng base64 khi đang stream video\n<p>@commonly-used-in -> thường sử dụng khi đang live stream\n\n<p>@describe         -> stream xong và xử lý xong không cân thì xóa (đây chỉ là bảng tạm)"
)
@GeneratedByJHipster
public class VideoLiveStreamBufferDTO implements Serializable {

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

  @Lob
  private byte[] bufferData;

  private String bufferDataContentType;
  private Integer bufferNumber;

  private String path;

  private BaseInfoDTO info;

  private VideoStreamDTO videoStream;

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

  public byte[] getBufferData() {
    return bufferData;
  }

  public void setBufferData(byte[] bufferData) {
    this.bufferData = bufferData;
  }

  public String getBufferDataContentType() {
    return bufferDataContentType;
  }

  public void setBufferDataContentType(String bufferDataContentType) {
    this.bufferDataContentType = bufferDataContentType;
  }

  public Integer getBufferNumber() {
    return bufferNumber;
  }

  public void setBufferNumber(Integer bufferNumber) {
    this.bufferNumber = bufferNumber;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public BaseInfoDTO getInfo() {
    return info;
  }

  public void setInfo(BaseInfoDTO info) {
    this.info = info;
  }

  public VideoStreamDTO getVideoStream() {
    return videoStream;
  }

  public void setVideoStream(VideoStreamDTO videoStream) {
    this.videoStream = videoStream;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof VideoLiveStreamBufferDTO)) {
      return false;
    }

    VideoLiveStreamBufferDTO videoLiveStreamBufferDTO = (VideoLiveStreamBufferDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, videoLiveStreamBufferDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "VideoLiveStreamBufferDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", bufferData='" + getBufferData() + "'" +
            ", bufferNumber=" + getBufferNumber() +
            ", path='" + getPath() + "'" +
            ", info=" + getInfo() +
            ", videoStream=" + getVideoStream() +
            "}";
    }
}
