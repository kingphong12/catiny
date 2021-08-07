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
 * Criteria class for the {@link com.regitiny.catiny.domain.MasterUser} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.MasterUserResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /master-users?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class MasterUserCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private StringFilter fullName;

  private StringFilter nickname;

  private LongFilter userId;

  private LongFilter myRankId;

  private LongFilter infoId;

  private LongFilter permissionId;

  private LongFilter topicInterestId;

  private LongFilter ownedId;

  public MasterUserCriteria() {}

  public MasterUserCriteria(MasterUserCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.fullName = other.fullName == null ? null : other.fullName.copy();
    this.nickname = other.nickname == null ? null : other.nickname.copy();
    this.userId = other.userId == null ? null : other.userId.copy();
    this.myRankId = other.myRankId == null ? null : other.myRankId.copy();
    this.infoId = other.infoId == null ? null : other.infoId.copy();
    this.permissionId = other.permissionId == null ? null : other.permissionId.copy();
    this.topicInterestId = other.topicInterestId == null ? null : other.topicInterestId.copy();
    this.ownedId = other.ownedId == null ? null : other.ownedId.copy();
  }

  @Override
  public MasterUserCriteria copy() {
    return new MasterUserCriteria(this);
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

  public StringFilter getFullName() {
    return fullName;
  }

  public StringFilter fullName() {
    if (fullName == null) {
      fullName = new StringFilter();
    }
    return fullName;
  }

  public void setFullName(StringFilter fullName) {
    this.fullName = fullName;
  }

  public StringFilter getNickname() {
    return nickname;
  }

  public StringFilter nickname() {
    if (nickname == null) {
      nickname = new StringFilter();
    }
    return nickname;
  }

  public void setNickname(StringFilter nickname) {
    this.nickname = nickname;
  }

  public LongFilter getUserId() {
    return userId;
  }

  public LongFilter userId() {
    if (userId == null) {
      userId = new LongFilter();
    }
    return userId;
  }

  public void setUserId(LongFilter userId) {
    this.userId = userId;
  }

  public LongFilter getMyRankId() {
    return myRankId;
  }

  public LongFilter myRankId() {
    if (myRankId == null) {
      myRankId = new LongFilter();
    }
    return myRankId;
  }

  public void setMyRankId(LongFilter myRankId) {
    this.myRankId = myRankId;
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

  public LongFilter getPermissionId() {
    return permissionId;
  }

  public LongFilter permissionId() {
    if (permissionId == null) {
      permissionId = new LongFilter();
    }
    return permissionId;
  }

  public void setPermissionId(LongFilter permissionId) {
    this.permissionId = permissionId;
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

  public LongFilter getOwnedId() {
    return ownedId;
  }

  public LongFilter ownedId() {
    if (ownedId == null) {
      ownedId = new LongFilter();
    }
    return ownedId;
  }

  public void setOwnedId(LongFilter ownedId) {
    this.ownedId = ownedId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final MasterUserCriteria that = (MasterUserCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(fullName, that.fullName) &&
      Objects.equals(nickname, that.nickname) &&
      Objects.equals(userId, that.userId) &&
      Objects.equals(myRankId, that.myRankId) &&
      Objects.equals(infoId, that.infoId) &&
      Objects.equals(permissionId, that.permissionId) &&
      Objects.equals(topicInterestId, that.topicInterestId) &&
      Objects.equals(ownedId, that.ownedId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, fullName, nickname, userId, myRankId, infoId, permissionId, topicInterestId, ownedId);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "MasterUserCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (fullName != null ? "fullName=" + fullName + ", " : "") +
            (nickname != null ? "nickname=" + nickname + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (myRankId != null ? "myRankId=" + myRankId + ", " : "") +
            (infoId != null ? "infoId=" + infoId + ", " : "") +
            (permissionId != null ? "permissionId=" + permissionId + ", " : "") +
            (topicInterestId != null ? "topicInterestId=" + topicInterestId + ", " : "") +
            (ownedId != null ? "ownedId=" + ownedId + ", " : "") +
            "}";
    }
}
