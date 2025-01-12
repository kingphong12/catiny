package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * <p>@what?            -> The VideoLiveStreamBuffer entity.\n<p>@why?             ->\n<p>@use-to           -> Lưu từng phần video dưới dạng base64 khi đang stream video\n<p>@commonly-used-in -> thường sử dụng khi đang live stream\n\n<p>@describe         -> stream xong và xử lý xong không cân thì xóa (đây chỉ là bảng tạm)
 */
@Entity
@Table(name = "video_live_stream_buffer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "videolivestreambuffer")
@GeneratedByJHipster
public class VideoLiveStreamBuffer implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  @Column(name = "id")
  private Long id;

  /**
   * uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)
   */
  @NotNull
  @Type(type = "uuid-char")
  @Column(name = "uuid", length = 36, nullable = false, unique = true)
  private UUID uuid;

  @Lob
  @Column(name = "buffer_data")
  private byte[] bufferData;

  @Column(name = "buffer_data_content_type")
  private String bufferDataContentType;

  @Column(name = "buffer_number")
  private Integer bufferNumber;

  @Column(name = "path")
  private String path;

  @JsonIgnoreProperties(value = { "histories", "createdBy", "modifiedBy", "owner", "classInfo", "permissions" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo info;

  @ManyToOne
  @JsonIgnoreProperties(value = { "video", "info", "videoLiveStreamBuffers" }, allowSetters = true)
  private VideoStream videoStream;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public VideoLiveStreamBuffer id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public VideoLiveStreamBuffer uuid(UUID uuid) {
    this.setUuid(uuid);
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public byte[] getBufferData() {
    return this.bufferData;
  }

  public VideoLiveStreamBuffer bufferData(byte[] bufferData) {
    this.setBufferData(bufferData);
    return this;
  }

  public void setBufferData(byte[] bufferData) {
    this.bufferData = bufferData;
  }

  public String getBufferDataContentType() {
    return this.bufferDataContentType;
  }

  public VideoLiveStreamBuffer bufferDataContentType(String bufferDataContentType) {
    this.bufferDataContentType = bufferDataContentType;
    return this;
  }

  public void setBufferDataContentType(String bufferDataContentType) {
    this.bufferDataContentType = bufferDataContentType;
  }

  public Integer getBufferNumber() {
    return this.bufferNumber;
  }

  public VideoLiveStreamBuffer bufferNumber(Integer bufferNumber) {
    this.setBufferNumber(bufferNumber);
    return this;
  }

  public void setBufferNumber(Integer bufferNumber) {
    this.bufferNumber = bufferNumber;
  }

  public String getPath() {
    return this.path;
  }

  public VideoLiveStreamBuffer path(String path) {
    this.setPath(path);
    return this;
  }

  public void setPath(String path) {
    this.path = path;
  }

  public BaseInfo getInfo() {
    return this.info;
  }

  public void setInfo(BaseInfo baseInfo) {
    this.info = baseInfo;
  }

  public VideoLiveStreamBuffer info(BaseInfo baseInfo) {
    this.setInfo(baseInfo);
    return this;
  }

  public VideoStream getVideoStream() {
    return this.videoStream;
  }

  public void setVideoStream(VideoStream videoStream) {
    this.videoStream = videoStream;
  }

  public VideoLiveStreamBuffer videoStream(VideoStream videoStream) {
    this.setVideoStream(videoStream);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof VideoLiveStreamBuffer)) {
      return false;
    }
    return id != null && id.equals(((VideoLiveStreamBuffer) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "VideoLiveStreamBuffer{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", bufferData='" + getBufferData() + "'" +
            ", bufferDataContentType='" + getBufferDataContentType() + "'" +
            ", bufferNumber=" + getBufferNumber() +
            ", path='" + getPath() + "'" +
            "}";
    }
}
