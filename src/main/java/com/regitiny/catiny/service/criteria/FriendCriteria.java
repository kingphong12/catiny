package com.regitiny.catiny.service.criteria;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.FriendType;
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
 * Criteria class for the {@link com.regitiny.catiny.domain.Friend} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.FriendResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /friends?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class FriendCriteria implements Serializable, Criteria {

  /**
   * Class for filtering FriendType
   */
  @GeneratedByJHipster
  public static class FriendTypeFilter extends Filter<FriendType> {

    public FriendTypeFilter() {}

    public FriendTypeFilter(FriendTypeFilter filter) {
      super(filter);
    }

    @Override
    public FriendTypeFilter copy() {
      return new FriendTypeFilter(this);
    }
  }

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private FriendTypeFilter friendType;

  private LongFilter infoId;

  private LongFilter friendId;

  private Boolean distinct;

  public FriendCriteria() {}

  public FriendCriteria(FriendCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.friendType = other.friendType == null ? null : other.friendType.copy();
    this.infoId = other.infoId == null ? null : other.infoId.copy();
    this.friendId = other.friendId == null ? null : other.friendId.copy();
    this.distinct = other.distinct;
  }

  @Override
  public FriendCriteria copy() {
    return new FriendCriteria(this);
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

  public FriendTypeFilter getFriendType() {
    return friendType;
  }

  public FriendTypeFilter friendType() {
    if (friendType == null) {
      friendType = new FriendTypeFilter();
    }
    return friendType;
  }

  public void setFriendType(FriendTypeFilter friendType) {
    this.friendType = friendType;
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

  public LongFilter getFriendId() {
    return friendId;
  }

  public LongFilter friendId() {
    if (friendId == null) {
      friendId = new LongFilter();
    }
    return friendId;
  }

  public void setFriendId(LongFilter friendId) {
    this.friendId = friendId;
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
    final FriendCriteria that = (FriendCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(friendType, that.friendType) &&
      Objects.equals(infoId, that.infoId) &&
      Objects.equals(friendId, that.friendId) &&
      Objects.equals(distinct, that.distinct)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, friendType, infoId, friendId, distinct);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "FriendCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (friendType != null ? "friendType=" + friendType + ", " : "") +
            (infoId != null ? "infoId=" + infoId + ", " : "") +
            (friendId != null ? "friendId=" + friendId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
