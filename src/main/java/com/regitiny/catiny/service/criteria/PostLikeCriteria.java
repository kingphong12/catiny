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
 * Criteria class for the {@link com.regitiny.catiny.domain.PostLike} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.PostLikeResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /post-likes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class PostLikeCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private LongFilter infoId;

  private LongFilter postId;

  private LongFilter commentId;

  public PostLikeCriteria() {}

  public PostLikeCriteria(PostLikeCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.infoId = other.infoId == null ? null : other.infoId.copy();
    this.postId = other.postId == null ? null : other.postId.copy();
    this.commentId = other.commentId == null ? null : other.commentId.copy();
  }

  @Override
  public PostLikeCriteria copy() {
    return new PostLikeCriteria(this);
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

  public LongFilter getCommentId() {
    return commentId;
  }

  public LongFilter commentId() {
    if (commentId == null) {
      commentId = new LongFilter();
    }
    return commentId;
  }

  public void setCommentId(LongFilter commentId) {
    this.commentId = commentId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PostLikeCriteria that = (PostLikeCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(infoId, that.infoId) &&
      Objects.equals(postId, that.postId) &&
      Objects.equals(commentId, that.commentId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, infoId, postId, commentId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PostLikeCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (infoId != null ? "infoId=" + infoId + ", " : "") +
            (postId != null ? "postId=" + postId + ", " : "") +
            (commentId != null ? "commentId=" + commentId + ", " : "") +
            "}";
    }
}
