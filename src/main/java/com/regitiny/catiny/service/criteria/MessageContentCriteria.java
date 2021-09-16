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
 * Criteria class for the {@link com.regitiny.catiny.domain.MessageContent} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.MessageContentResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /message-contents?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class MessageContentCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private StringFilter senderName;

  private LongFilter infoId;

  private LongFilter groupId;

  private Boolean distinct;

  public MessageContentCriteria() {}

  public MessageContentCriteria(MessageContentCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.senderName = other.senderName == null ? null : other.senderName.copy();
    this.infoId = other.infoId == null ? null : other.infoId.copy();
    this.groupId = other.groupId == null ? null : other.groupId.copy();
    this.distinct = other.distinct;
  }

  @Override
  public MessageContentCriteria copy() {
    return new MessageContentCriteria(this);
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

  public StringFilter getSenderName() {
    return senderName;
  }

  public StringFilter senderName() {
    if (senderName == null) {
      senderName = new StringFilter();
    }
    return senderName;
  }

  public void setSenderName(StringFilter senderName) {
    this.senderName = senderName;
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
    final MessageContentCriteria that = (MessageContentCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(senderName, that.senderName) &&
      Objects.equals(infoId, that.infoId) &&
      Objects.equals(groupId, that.groupId) &&
      Objects.equals(distinct, that.distinct)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, senderName, infoId, groupId, distinct);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "MessageContentCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (senderName != null ? "senderName=" + senderName + ", " : "") +
            (infoId != null ? "infoId=" + infoId + ", " : "") +
            (groupId != null ? "groupId=" + groupId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
