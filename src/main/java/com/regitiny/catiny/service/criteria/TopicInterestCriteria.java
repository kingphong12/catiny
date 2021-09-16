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
 * Criteria class for the {@link com.regitiny.catiny.domain.TopicInterest} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.TopicInterestResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /topic-interests?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class TopicInterestCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private StringFilter title;

  private LongFilter infoId;

  private LongFilter postId;

  private LongFilter pagePostId;

  private LongFilter groupPostId;

  private LongFilter masterUserId;

  private Boolean distinct;

  public TopicInterestCriteria() {}

  public TopicInterestCriteria(TopicInterestCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.title = other.title == null ? null : other.title.copy();
    this.infoId = other.infoId == null ? null : other.infoId.copy();
    this.postId = other.postId == null ? null : other.postId.copy();
    this.pagePostId = other.pagePostId == null ? null : other.pagePostId.copy();
    this.groupPostId = other.groupPostId == null ? null : other.groupPostId.copy();
    this.masterUserId = other.masterUserId == null ? null : other.masterUserId.copy();
    this.distinct = other.distinct;
  }

  @Override
  public TopicInterestCriteria copy() {
    return new TopicInterestCriteria(this);
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

  public StringFilter getTitle() {
    return title;
  }

  public StringFilter title() {
    if (title == null) {
      title = new StringFilter();
    }
    return title;
  }

  public void setTitle(StringFilter title) {
    this.title = title;
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

  public LongFilter getPostId() {
    return postId;
  }

  public LongFilter postId() {
    if (postId == null) {
      postId = new LongFilter();
    }
    return postId;
  }

  public void setPostId(LongFilter postId) {
    this.postId = postId;
  }

  public LongFilter getPagePostId() {
    return pagePostId;
  }

  public LongFilter pagePostId() {
    if (pagePostId == null) {
      pagePostId = new LongFilter();
    }
    return pagePostId;
  }

  public void setPagePostId(LongFilter pagePostId) {
    this.pagePostId = pagePostId;
  }

  public LongFilter getGroupPostId() {
    return groupPostId;
  }

  public LongFilter groupPostId() {
    if (groupPostId == null) {
      groupPostId = new LongFilter();
    }
    return groupPostId;
  }

  public void setGroupPostId(LongFilter groupPostId) {
    this.groupPostId = groupPostId;
  }

  public LongFilter getMasterUserId() {
    return masterUserId;
  }

  public LongFilter masterUserId() {
    if (masterUserId == null) {
      masterUserId = new LongFilter();
    }
    return masterUserId;
  }

  public void setMasterUserId(LongFilter masterUserId) {
    this.masterUserId = masterUserId;
  }

  public Boolean getDistinct() {
    return distinct;
  }

  public void setDistinct(Boolean distinct) {
    this.distinct = distinct;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final TopicInterestCriteria that = (TopicInterestCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(title, that.title) &&
      Objects.equals(infoId, that.infoId) &&
      Objects.equals(postId, that.postId) &&
      Objects.equals(pagePostId, that.pagePostId) &&
      Objects.equals(groupPostId, that.groupPostId) &&
      Objects.equals(masterUserId, that.masterUserId) &&
      Objects.equals(distinct, that.distinct)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, title, infoId, postId, pagePostId, groupPostId, masterUserId, distinct);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "TopicInterestCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (title != null ? "title=" + title + ", " : "") +
            (infoId != null ? "infoId=" + infoId + ", " : "") +
            (postId != null ? "postId=" + postId + ", " : "") +
            (pagePostId != null ? "pagePostId=" + pagePostId + ", " : "") +
            (groupPostId != null ? "groupPostId=" + groupPostId + ", " : "") +
            (masterUserId != null ? "masterUserId=" + masterUserId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
