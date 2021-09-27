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
 * <p>@what?            -> The Video entity.\n<p>@why?             ->\n<p>@use-to           -> Lưu thông tin video mà người dùng upload lên\n<p>@commonly-used-in ->\n\n<p>@describe         ->
 */
@Entity
@Table(name = "video")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "video")
@GeneratedByJHipster
public class Video implements Serializable {

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

  @Column(name = "name")
  private String name;

  /**
   * width          : chiều rộng video
   */
  @Column(name = "width")
  private Integer width;

  /**
   * height         : chiều cao video
   */
  @Column(name = "height")
  private Integer height;

  /**
   * qualityImage   : chất lượng ảnh sau khi xử lý
   */
  @DecimalMin(value = "0")
  @DecimalMax(value = "1")
  @Column(name = "quality_image")
  private Float qualityImage;

  /**
   * qualityAudio   : chất lượng âm thanh sau khi xử lý
   */
  @DecimalMin(value = "0")
  @DecimalMax(value = "1")
  @Column(name = "quality_audio")
  private Float qualityAudio;

  /**
   * quality        : chất lượng chung sau khi xử lý
   */
  @DecimalMin(value = "0")
  @DecimalMax(value = "1")
  @Column(name = "quality")
  private Float quality;

  /**
   * pixelSize      : kích thước của ảnh
   */
  @Column(name = "pixel_size")
  private Integer pixelSize;

  /**
   * priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)
   */
  @Column(name = "priority_index")
  private Long priorityIndex;

  /**
   * dataSize       : kích thước file theo byte
   */
  @Column(name = "data_size")
  private Long dataSize;

  @JsonIgnoreProperties(value = { "info" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private FileInfo fileInfo;

  @JsonIgnoreProperties(value = { "histories", "createdBy", "modifiedBy", "owner", "classInfo", "permissions" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo info;

  @OneToMany(mappedBy = "original")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "fileInfo", "info", "processeds", "videoStream", "original" }, allowSetters = true)
  private Set<Video> processeds = new HashSet<>();

  @JsonIgnoreProperties(value = { "video", "info", "videoLiveStreamBuffers" }, allowSetters = true)
  @OneToOne(mappedBy = "video")
  private VideoStream videoStream;

  @ManyToOne
  @JsonIgnoreProperties(value = { "fileInfo", "info", "processeds", "videoStream", "original" }, allowSetters = true)
  private Video original;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public Video id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public Video uuid(UUID uuid) {
    this.setUuid(uuid);
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return this.name;
  }

  public Video name(String name) {
    this.setName(name);
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getWidth() {
    return this.width;
  }

  public Video width(Integer width) {
    this.setWidth(width);
    return this;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return this.height;
  }

  public Video height(Integer height) {
    this.setHeight(height);
    return this;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public Float getQualityImage() {
    return this.qualityImage;
  }

  public Video qualityImage(Float qualityImage) {
    this.setQualityImage(qualityImage);
    return this;
  }

  public void setQualityImage(Float qualityImage) {
    this.qualityImage = qualityImage;
  }

  public Float getQualityAudio() {
    return this.qualityAudio;
  }

  public Video qualityAudio(Float qualityAudio) {
    this.setQualityAudio(qualityAudio);
    return this;
  }

  public void setQualityAudio(Float qualityAudio) {
    this.qualityAudio = qualityAudio;
  }

  public Float getQuality() {
    return this.quality;
  }

  public Video quality(Float quality) {
    this.setQuality(quality);
    return this;
  }

  public void setQuality(Float quality) {
    this.quality = quality;
  }

  public Integer getPixelSize() {
    return this.pixelSize;
  }

  public Video pixelSize(Integer pixelSize) {
    this.setPixelSize(pixelSize);
    return this;
  }

  public void setPixelSize(Integer pixelSize) {
    this.pixelSize = pixelSize;
  }

  public Long getPriorityIndex() {
    return this.priorityIndex;
  }

  public Video priorityIndex(Long priorityIndex) {
    this.setPriorityIndex(priorityIndex);
    return this;
  }

  public void setPriorityIndex(Long priorityIndex) {
    this.priorityIndex = priorityIndex;
  }

  public Long getDataSize() {
    return this.dataSize;
  }

  public Video dataSize(Long dataSize) {
    this.setDataSize(dataSize);
    return this;
  }

  public void setDataSize(Long dataSize) {
    this.dataSize = dataSize;
  }

  public FileInfo getFileInfo() {
    return this.fileInfo;
  }

  public void setFileInfo(FileInfo fileInfo) {
    this.fileInfo = fileInfo;
  }

  public Video fileInfo(FileInfo fileInfo) {
    this.setFileInfo(fileInfo);
    return this;
  }

  public BaseInfo getInfo() {
    return this.info;
  }

  public void setInfo(BaseInfo baseInfo) {
    this.info = baseInfo;
  }

  public Video info(BaseInfo baseInfo) {
    this.setInfo(baseInfo);
    return this;
  }

  public Set<Video> getProcesseds() {
    return this.processeds;
  }

  public void setProcesseds(Set<Video> videos) {
    if (this.processeds != null) {
      this.processeds.forEach(i -> i.setOriginal(null));
    }
    if (videos != null) {
      videos.forEach(i -> i.setOriginal(this));
    }
    this.processeds = videos;
  }

  public Video processeds(Set<Video> videos) {
    this.setProcesseds(videos);
    return this;
  }

  public Video addProcessed(Video video) {
    this.processeds.add(video);
    video.setOriginal(this);
    return this;
  }

  public Video removeProcessed(Video video) {
    this.processeds.remove(video);
    video.setOriginal(null);
    return this;
  }

  public VideoStream getVideoStream() {
    return this.videoStream;
  }

  public void setVideoStream(VideoStream videoStream) {
    if (this.videoStream != null) {
      this.videoStream.setVideo(null);
    }
    if (videoStream != null) {
      videoStream.setVideo(this);
    }
    this.videoStream = videoStream;
  }

  public Video videoStream(VideoStream videoStream) {
    this.setVideoStream(videoStream);
    return this;
  }

  public Video getOriginal() {
    return this.original;
  }

  public void setOriginal(Video video) {
    this.original = video;
  }

  public Video original(Video video) {
    this.setOriginal(video);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Video)) {
      return false;
    }
    return id != null && id.equals(((Video) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Video{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", name='" + getName() + "'" +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", qualityImage=" + getQualityImage() +
            ", qualityAudio=" + getQualityAudio() +
            ", quality=" + getQuality() +
            ", pixelSize=" + getPixelSize() +
            ", priorityIndex=" + getPriorityIndex() +
            ", dataSize=" + getDataSize() +
            "}";
    }
}
