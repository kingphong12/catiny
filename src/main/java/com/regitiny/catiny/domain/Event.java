package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.EventType;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * @what?            -> The Event entity.\n@why?             ->\n@use-to           -> Lưu những sự kiện\n@commonly-used-in -> Tạo Sự kiện\n\n@describe         ->
 */
@Entity
@Table(name = "event")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "event")
@GeneratedByJHipster
public class Event implements Serializable {

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

  /**
   * title          : tiêu đề event
   */
  @Column(name = "title")
  private String title;

  /**
   * avatar         : @type Json -> ảnh đại diện event
   */
  @Lob
  @Column(name = "avatar")
  private String avatar;

  /**
   * content        : nội dung event
   */
  @Lob
  @Column(name = "content")
  private String content;

  /**
   * type           :loại event
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  private EventType type;

  /**
   * description    : mô tả chi tết về event
   */
  @Lob
  @Column(name = "description")
  private String description;

  /**
   * startTime      : thời gian bắt đầu
   */
  @Column(name = "start_time")
  private Instant startTime;

  /**
   * endTime        : thời gian kết thúc
   */
  @Column(name = "end_time")
  private Instant endTime;

  /**
   * tagLine        : thẻ cho event
   */
  @Column(name = "tag_line")
  private String tagLine;

  /**
   * imageCollection: @type Json -> tập ảnh của event
   */
  @Lob
  @Column(name = "image_collection")
  private String imageCollection;

  /**
   * videoCollection: @type Json -> tập video của event
   */
  @Lob
  @Column(name = "video_collection")
  private String videoCollection;

  @JsonIgnoreProperties(value = { "histories", "createdBy", "modifiedBy", "owner", "classInfo", "permissions" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo info;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public Event id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public Event uuid(UUID uuid) {
    this.setUuid(uuid);
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getTitle() {
    return this.title;
  }

  public Event title(String title) {
    this.setTitle(title);
    return this;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAvatar() {
    return this.avatar;
  }

  public Event avatar(String avatar) {
    this.setAvatar(avatar);
    return this;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getContent() {
    return this.content;
  }

  public Event content(String content) {
    this.setContent(content);
    return this;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public EventType getType() {
    return this.type;
  }

  public Event type(EventType type) {
    this.setType(type);
    return this;
  }

  public void setType(EventType type) {
    this.type = type;
  }

  public String getDescription() {
    return this.description;
  }

  public Event description(String description) {
    this.setDescription(description);
    return this;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Instant getStartTime() {
    return this.startTime;
  }

  public Event startTime(Instant startTime) {
    this.setStartTime(startTime);
    return this;
  }

  public void setStartTime(Instant startTime) {
    this.startTime = startTime;
  }

  public Instant getEndTime() {
    return this.endTime;
  }

  public Event endTime(Instant endTime) {
    this.setEndTime(endTime);
    return this;
  }

  public void setEndTime(Instant endTime) {
    this.endTime = endTime;
  }

  public String getTagLine() {
    return this.tagLine;
  }

  public Event tagLine(String tagLine) {
    this.setTagLine(tagLine);
    return this;
  }

  public void setTagLine(String tagLine) {
    this.tagLine = tagLine;
  }

  public String getImageCollection() {
    return this.imageCollection;
  }

  public Event imageCollection(String imageCollection) {
    this.setImageCollection(imageCollection);
    return this;
  }

  public void setImageCollection(String imageCollection) {
    this.imageCollection = imageCollection;
  }

  public String getVideoCollection() {
    return this.videoCollection;
  }

  public Event videoCollection(String videoCollection) {
    this.setVideoCollection(videoCollection);
    return this;
  }

  public void setVideoCollection(String videoCollection) {
    this.videoCollection = videoCollection;
  }

  public BaseInfo getInfo() {
    return this.info;
  }

  public void setInfo(BaseInfo baseInfo) {
    this.info = baseInfo;
  }

  public Event info(BaseInfo baseInfo) {
    this.setInfo(baseInfo);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Event)) {
      return false;
    }
    return id != null && id.equals(((Event) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Event{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", title='" + getTitle() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", content='" + getContent() + "'" +
            ", type='" + getType() + "'" +
            ", description='" + getDescription() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", tagLine='" + getTagLine() + "'" +
            ", imageCollection='" + getImageCollection() + "'" +
            ", videoCollection='" + getVideoCollection() + "'" +
            "}";
    }
}
