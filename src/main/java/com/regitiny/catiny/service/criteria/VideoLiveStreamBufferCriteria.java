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
 * Criteria class for the {@link com.regitiny.catiny.domain.VideoLiveStreamBuffer} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.VideoLiveStreamBufferResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /video-live-stream-buffers?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class VideoLiveStreamBufferCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private IntegerFilter bufferNumber;

  private StringFilter path;

  private LongFilter infoId;

  private LongFilter videoStreamId;

  public VideoLiveStreamBufferCriteria() {}

  public VideoLiveStreamBufferCriteria(VideoLiveStreamBufferCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.bufferNumber = other.bufferNumber == null ? null : other.bufferNumber.copy();
    this.path = other.path == null ? null : other.path.copy();
    this.infoId = other.infoId == null ? null : other.infoId.copy();
    this.videoStreamId = other.videoStreamId == null ? null : other.videoStreamId.copy();
  }

  @Override
  public VideoLiveStreamBufferCriteria copy() {
    return new VideoLiveStreamBufferCriteria(this);
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

  public IntegerFilter getBufferNumber() {
    return bufferNumber;
  }

  public IntegerFilter bufferNumber() {
    if (bufferNumber == null) {
      bufferNumber = new IntegerFilter();
    }
    return bufferNumber;
  }

  public void setBufferNumber(IntegerFilter bufferNumber) {
    this.bufferNumber = bufferNumber;
  }

  public StringFilter getPath() {
    return path;
  }

  public StringFilter path() {
    if (path == null) {
      path = new StringFilter();
    }
    return path;
  }

  public void setPath(StringFilter path) {
    this.path = path;
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

  public LongFilter getVideoStreamId() {
    return videoStreamId;
  }

  public LongFilter videoStreamId() {
    if (videoStreamId == null) {
      videoStreamId = new LongFilter();
    }
    return videoStreamId;
  }

  public void setVideoStreamId(LongFilter videoStreamId) {
    this.videoStreamId = videoStreamId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final VideoLiveStreamBufferCriteria that = (VideoLiveStreamBufferCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(bufferNumber, that.bufferNumber) &&
      Objects.equals(path, that.path) &&
      Objects.equals(infoId, that.infoId) &&
      Objects.equals(videoStreamId, that.videoStreamId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, bufferNumber, path, infoId, videoStreamId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "VideoLiveStreamBufferCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (bufferNumber != null ? "bufferNumber=" + bufferNumber + ", " : "") +
            (path != null ? "path=" + path + ", " : "") +
            (infoId != null ? "infoId=" + infoId + ", " : "") +
            (videoStreamId != null ? "videoStreamId=" + videoStreamId + ", " : "") +
            "}";
    }
}
