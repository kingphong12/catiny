package com.regitiny.catiny.service.criteria;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.PostInType;
import com.regitiny.catiny.domain.enumeration.PostType;
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
 * Criteria class for the {@link com.regitiny.catiny.domain.Post} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.PostResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /posts?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class PostCriteria implements Serializable, Criteria {

  /**
   * Class for filtering PostInType
   */
  @GeneratedByJHipster
  public static class PostInTypeFilter extends Filter<PostInType> {

    public PostInTypeFilter() {}

    public PostInTypeFilter(PostInTypeFilter filter) {
      super(filter);
    }

    @Override
    public PostInTypeFilter copy() {
      return new PostInTypeFilter(this);
    }
  }

  /**
   * Class for filtering PostType
   */
  @GeneratedByJHipster
  public static class PostTypeFilter extends Filter<PostType> {

    public PostTypeFilter() {}

    public PostTypeFilter(PostTypeFilter filter) {
      super(filter);
    }

    @Override
    public PostTypeFilter copy() {
      return new PostTypeFilter(this);
    }
  }

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private PostInTypeFilter postInType;

  private PostTypeFilter postType;

  private LongFilter infoId;

  private LongFilter commentId;

  private LongFilter likeId;

  private LongFilter childrenId;

  private LongFilter groupId;

  private LongFilter pageId;

  private LongFilter parentId;

  private LongFilter newsFeedId;

  private LongFilter topicInterestId;

  public PostCriteria() {}

  public PostCriteria(PostCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.postInType = other.postInType == null ? null : other.postInType.copy();
    this.postType = other.postType == null ? null : other.postType.copy();
    this.infoId = other.infoId == null ? null : other.infoId.copy();
    this.commentId = other.commentId == null ? null : other.commentId.copy();
    this.likeId = other.likeId == null ? null : other.likeId.copy();
    this.childrenId = other.childrenId == null ? null : other.childrenId.copy();
    this.groupId = other.groupId == null ? null : other.groupId.copy();
    this.pageId = other.pageId == null ? null : other.pageId.copy();
    this.parentId = other.parentId == null ? null : other.parentId.copy();
    this.newsFeedId = other.newsFeedId == null ? null : other.newsFeedId.copy();
    this.topicInterestId = other.topicInterestId == null ? null : other.topicInterestId.copy();
  }

  @Override
  public PostCriteria copy() {
    return new PostCriteria(this);
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

  public PostInTypeFilter getPostInType() {
    return postInType;
  }

  public PostInTypeFilter postInType() {
    if (postInType == null) {
      postInType = new PostInTypeFilter();
    }
    return postInType;
  }

  public void setPostInType(PostInTypeFilter postInType) {
    this.postInType = postInType;
  }

  public PostTypeFilter getPostType() {
    return postType;
  }

  public PostTypeFilter postType() {
    if (postType == null) {
      postType = new PostTypeFilter();
    }
    return postType;
  }

  public void setPostType(PostTypeFilter postType) {
    this.postType = postType;
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

  public LongFilter getChildrenId() {
    return childrenId;
  }

  public LongFilter childrenId() {
    if (childrenId == null) {
      childrenId = new LongFilter();
    }
    return childrenId;
  }

  public void setChildrenId(LongFilter childrenId) {
    this.childrenId = childrenId;
  }

  public LongFilter getGroupId() {
    return groupId;
  }

  public LongFilter groupId() {
    if (groupId == null) {
      groupId = new LongFilter();
    }
    return groupId;
  }

  public void setGroupId(LongFilter groupId) {
    this.groupId = groupId;
  }

  public LongFilter getPageId() {
    return pageId;
  }

  public LongFilter pageId() {
    if (pageId == null) {
      pageId = new LongFilter();
    }
    return pageId;
  }

  public void setPageId(LongFilter pageId) {
    this.pageId = pageId;
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

  public LongFilter getNewsFeedId() {
    return newsFeedId;
  }

  public LongFilter newsFeedId() {
    if (newsFeedId == null) {
      newsFeedId = new LongFilter();
    }
    return newsFeedId;
  }

  public void setNewsFeedId(LongFilter newsFeedId) {
    this.newsFeedId = newsFeedId;
  }

  public LongFilter getTopicInterestId() {
    return topicInterestId;
  }

  public LongFilter topicInterestId() {
    if (topicInterestId == null) {
      topicInterestId = new LongFilter();
    }
    return topicInterestId;
  }

  public void setTopicInterestId(LongFilter topicInterestId) {
    this.topicInterestId = topicInterestId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final PostCriteria that = (PostCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(postInType, that.postInType) &&
      Objects.equals(postType, that.postType) &&
      Objects.equals(infoId, that.infoId) &&
      Objects.equals(commentId, that.commentId) &&
      Objects.equals(likeId, that.likeId) &&
      Objects.equals(childrenId, that.childrenId) &&
      Objects.equals(groupId, that.groupId) &&
      Objects.equals(pageId, that.pageId) &&
      Objects.equals(parentId, that.parentId) &&
      Objects.equals(newsFeedId, that.newsFeedId) &&
      Objects.equals(topicInterestId, that.topicInterestId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(
      id,
      uuid,
      postInType,
      postType,
      infoId,
      commentId,
      likeId,
      childrenId,
      groupId,
      pageId,
      parentId,
      newsFeedId,
      topicInterestId
    );
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PostCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (postInType != null ? "postInType=" + postInType + ", " : "") +
            (postType != null ? "postType=" + postType + ", " : "") +
            (infoId != null ? "infoId=" + infoId + ", " : "") +
            (commentId != null ? "commentId=" + commentId + ", " : "") +
            (likeId != null ? "likeId=" + likeId + ", " : "") +
            (childrenId != null ? "childrenId=" + childrenId + ", " : "") +
            (groupId != null ? "groupId=" + groupId + ", " : "") +
            (pageId != null ? "pageId=" + pageId + ", " : "") +
            (parentId != null ? "parentId=" + parentId + ", " : "") +
            (newsFeedId != null ? "newsFeedId=" + newsFeedId + ", " : "") +
            (topicInterestId != null ? "topicInterestId=" + topicInterestId + ", " : "") +
            "}";
    }
}
