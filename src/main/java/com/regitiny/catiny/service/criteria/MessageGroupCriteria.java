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
 * Criteria class for the {@link com.regitiny.catiny.domain.MessageGroup} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.MessageGroupResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /message-groups?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class MessageGroupCriteria implements Serializable, Criteria {

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private StringFilter groupName;

  private StringFilter addBy;

  private LongFilter infoId;

  private LongFilter contentId;

  private Boolean distinct;

  public MessageGroupCriteria() {}

  public MessageGroupCriteria(MessageGroupCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.groupName = other.groupName == null ? null : other.groupName.copy();
    this.addBy = other.addBy == null ? null : other.addBy.copy();
    this.infoId = other.infoId == null ? null : other.infoId.copy();
    this.contentId = other.contentId == null ? null : other.contentId.copy();
    this.distinct = other.distinct;
  }

  @Override
  public MessageGroupCriteria copy() {
    return new MessageGroupCriteria(this);
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

  public StringFilter getGroupName() {
    return groupName;
  }

  public StringFilter groupName() {
    if (groupName == null) {
      groupName = new StringFilter();
    }
    return groupName;
  }

  public void setGroupName(StringFilter groupName) {
    this.groupName = groupName;
  }

  public StringFilter getAddBy() {
    return addBy;
  }

  public StringFilter addBy() {
    if (addBy == null) {
      addBy = new StringFilter();
    }
    return addBy;
  }

  public void setAddBy(StringFilter addBy) {
    this.addBy = addBy;
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

  public LongFilter getContentId() {
    return contentId;
  }

  public LongFilter contentId() {
    if (contentId == null) {
      contentId = new LongFilter();
    }
    return contentId;
  }

  public void setContentId(LongFilter contentId) {
    this.contentId = contentId;
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
    final MessageGroupCriteria that = (MessageGroupCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(groupName, that.groupName) &&
      Objects.equals(addBy, that.addBy) &&
      Objects.equals(infoId, that.infoId) &&
      Objects.equals(contentId, that.contentId) &&
      Objects.equals(distinct, that.distinct)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, groupName, addBy, infoId, contentId, distinct);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "MessageGroupCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (groupName != null ? "groupName=" + groupName + ", " : "") +
            (addBy != null ? "addBy=" + addBy + ", " : "") +
            (infoId != null ? "infoId=" + infoId + ", " : "") +
            (contentId != null ? "contentId=" + contentId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
