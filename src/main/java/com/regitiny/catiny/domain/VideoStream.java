package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * @what?            -> The VideoStream entity.\n@why?             ->\n@use-to           -> Lưu thông tin video ... khi đã kết thúc stream\n@commonly-used-in -> Sau khi kết thức stream thì video lưu lai cũng chỉ tương tự như một video thông thường\n\n@describe         ->
 */
@Entity
@Table(name = "video_stream")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "videostream")
@GeneratedByJHipster
public class VideoStream implements Serializable {

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

  @Column(name = "is_livestreaming")
  private Boolean isLivestreaming;

  @JsonIgnoreProperties(value = { "fileInfo", "info", "processeds", "videoStream", "original" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private Video video;

  @JsonIgnoreProperties(value = { "histories", "createdBy", "modifiedBy", "owner", "classInfo", "permissions" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo info;

  @OneToMany(mappedBy = "videoStream")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "info", "videoStream" }, allowSetters = true)
  private Set<VideoLiveStreamBuffer> videoLiveStreamBuffers = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public VideoStream id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public VideoStream uuid(UUID uuid) {
    this.setUuid(uuid);
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public Boolean getIsLivestreaming() {
    return this.isLivestreaming;
  }

  public VideoStream isLivestreaming(Boolean isLivestreaming) {
    this.setIsLivestreaming(isLivestreaming);
    return this;
  }

  public void setIsLivestreaming(Boolean isLivestreaming) {
    this.isLivestreaming = isLivestreaming;
  }

  public Video getVideo() {
    return this.video;
  }

  public void setVideo(Video video) {
    this.video = video;
  }

  public VideoStream video(Video video) {
    this.setVideo(video);
    return this;
  }

  public BaseInfo getInfo() {
    return this.info;
  }

  public void setInfo(BaseInfo baseInfo) {
    this.info = baseInfo;
  }

  public VideoStream info(BaseInfo baseInfo) {
    this.setInfo(baseInfo);
    return this;
  }

  public Set<VideoLiveStreamBuffer> getVideoLiveStreamBuffers() {
    return this.videoLiveStreamBuffers;
  }

  public void setVideoLiveStreamBuffers(Set<VideoLiveStreamBuffer> videoLiveStreamBuffers) {
    if (this.videoLiveStreamBuffers != null) {
      this.videoLiveStreamBuffers.forEach(i -> i.setVideoStream(null));
    }
    if (videoLiveStreamBuffers != null) {
      videoLiveStreamBuffers.forEach(i -> i.setVideoStream(this));
    }
    this.videoLiveStreamBuffers = videoLiveStreamBuffers;
  }

  public VideoStream videoLiveStreamBuffers(Set<VideoLiveStreamBuffer> videoLiveStreamBuffers) {
    this.setVideoLiveStreamBuffers(videoLiveStreamBuffers);
    return this;
  }

  public VideoStream addVideoLiveStreamBuffer(VideoLiveStreamBuffer videoLiveStreamBuffer) {
    this.videoLiveStreamBuffers.add(videoLiveStreamBuffer);
    videoLiveStreamBuffer.setVideoStream(this);
    return this;
  }

  public VideoStream removeVideoLiveStreamBuffer(VideoLiveStreamBuffer videoLiveStreamBuffer) {
    this.videoLiveStreamBuffers.remove(videoLiveStreamBuffer);
    videoLiveStreamBuffer.setVideoStream(null);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof VideoStream)) {
      return false;
    }
    return id != null && id.equals(((VideoStream) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "VideoStream{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", isLivestreaming='" + getIsLivestreaming() + "'" +
            "}";
    }
}
