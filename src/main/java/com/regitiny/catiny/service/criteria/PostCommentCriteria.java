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
 * Criteria class for the {@link com.regitiny.catiny.domain.PostComment} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.PostCommentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /post-comments?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class PostCommentCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private LongFilter infoId;

  private LongFilter likeId;

  private LongFilter replyId;

  private LongFilter postId;

  private LongFilter parentId;

  private Boolean distinct;

  public PostCommentCriteria() {}

  public PostCommentCriteria(PostCommentCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.infoId = other.infoId == null ? null : other.infoId.copy();
    this.likeId = other.likeId == null ? null : other.likeId.copy();
    this.replyId = other.replyId == null ? null : other.replyId.copy();
    this.postId = other.postId == null ? null : other.postId.copy();
    this.parentId = other.parentId == null ? null : other.parentId.copy();
    this.distinct = other.distinct;
  }

  @Override
  public PostCommentCriteria copy() {
    return new PostCommentCriteria(this);
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

  public LongFilter getLikeId() {
    return likeId;
  }

  public LongFilter likeId() {
    if (likeId == null) {
      likeId = new LongFilter();
    }
    return likeId;
  }

  public void setLikeId(LongFilter likeId) {
    this.likeId = likeId;
  }

  public LongFilter getReplyId() {
    return replyId;
  }

  public LongFilter replyId() {
    if (replyId == null) {
      replyId = new LongFilter();
    }
    return replyId;
  }

  public void setReplyId(LongFilter replyId) {
    this.replyId = replyId;
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

  public LongFilter getParentId() {
    return parentId;
  }

  public LongFilter parentId() {
    if (parentId == null) {
      parentId = new LongFilter();
    }
    return parentId;
  }

  public void setParentId(LongFilter parentId) {
    this.parentId = parentId;
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
    final PostCommentCriteria that = (PostCommentCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(infoId, that.infoId) &&
      Objects.equals(likeId, that.likeId) &&
      Objects.equals(replyId, that.replyId) &&
      Objects.equals(postId, that.postId) &&
      Objects.equals(parentId, that.parentId) &&
      Objects.equals(distinct, that.distinct)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, infoId, likeId, replyId, postId, parentId, distinct);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PostCommentCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (infoId != null ? "infoId=" + infoId + ", " : "") +
            (likeId != null ? "likeId=" + likeId + ", " : "") +
            (replyId != null ? "replyId=" + replyId + ", " : "") +
            (postId != null ? "postId=" + postId + ", " : "") +
            (parentId != null ? "parentId=" + parentId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
