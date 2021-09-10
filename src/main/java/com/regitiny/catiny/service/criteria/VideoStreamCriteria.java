package com.regitiny.catiny.service.criteria;

import com.regitiny.catiny.GeneratedByJHipster;
import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;
import tech.jhipster.service.filter.UUIDFilter;

/**
 * Criteria class for the {@link com.regitiny.catiny.domain.VideoStream} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.VideoStreamResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /video-streams?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class VideoStreamCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private BooleanFilter isLivestreaming;

  private LongFilter videoId;

  private LongFilter infoId;

  private LongFilter videoLiveStreamBufferId;

  public VideoStreamCriteria() {}

  public VideoStreamCriteria(VideoStreamCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.isLivestreaming = other.isLivestreaming == null ? null : other.isLivestreaming.copy();
    this.videoId = other.videoId == null ? null : other.videoId.copy();
    this.infoId = other.infoId == null ? null : other.infoId.copy();
    this.videoLiveStreamBufferId = other.videoLiveStreamBufferId == null ? null : other.videoLiveStreamBufferId.copy();
  }

  @Override
  public VideoStreamCriteria copy() {
    return new VideoStreamCriteria(this);
  }

  public LongFilter getId() {
    return id;
  }

  public LongFilter id() {
    if (id == null) {
      id = new LongFilter();
    }
    return id;
  }

  public void setId(LongFilter id) {
    this.id = id;
  }

  public UUIDFilter getUuid() {
    return uuid;
  }

  public UUIDFilter uuid() {
    if (uuid == null) {
      uuid = new UUIDFilter();
    }
    return uuid;
  }

  public void setUuid(UUIDFilter uuid) {
    this.uuid = uuid;
  }

  public BooleanFilter getIsLivestreaming() {
    return isLivestreaming;
  }

  public BooleanFilter isLivestreaming() {
    if (isLivestreaming == null) {
      isLivestreaming = new BooleanFilter();
    }
    return isLivestreaming;
  }

  public void setIsLivestreaming(BooleanFilter isLivestreaming) {
    this.isLivestreaming = isLivestreaming;
  }

  public LongFilter getVideoId() {
    return videoId;
  }

  public LongFilter videoId() {
    if (videoId == null) {
      videoId = new LongFilter();
    }
    return videoId;
  }

  public void setVideoId(LongFilter videoId) {
    this.videoId = videoId;
  }

  public LongFilter getInfoId() {
    return infoId;
  }

  public LongFilter infoId() {
    if (infoId == null) {
      infoId = new LongFilter();
    }
    return infoId;
  }

  public void setInfoId(LongFilter infoId) {
    this.infoId = infoId;
  }

  public LongFilter getVideoLiveStreamBufferId() {
    return videoLiveStreamBufferId;
  }

  public LongFilter videoLiveStreamBufferId() {
    if (videoLiveStreamBufferId == null) {
      videoLiveStreamBufferId = new LongFilter();
    }
    return videoLiveStreamBufferId;
  }

  public void setVideoLiveStreamBufferId(LongFilter videoLiveStreamBufferId) {
    this.videoLiveStreamBufferId = videoLiveStreamBufferId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final VideoStreamCriteria that = (VideoStreamCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(isLivestreaming, that.isLivestreaming) &&
      Objects.equals(videoId, that.videoId) &&
      Objects.equals(infoId, that.infoId) &&
      Objects.equals(videoLiveStreamBufferId, that.videoLiveStreamBufferId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, isLivestreaming, videoId, infoId, videoLiveStreamBufferId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "VideoStreamCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (isLivestreaming != null ? "isLivestreaming=" + isLivestreaming + ", " : "") +
            (videoId != null ? "videoId=" + videoId + ", " : "") +
            (infoId != null ? "infoId=" + infoId + ", " : "") +
            (videoLiveStreamBufferId != null ? "videoLiveStreamBufferId=" + videoLiveStreamBufferId + ", " : "") +
            "}";
    }
}
