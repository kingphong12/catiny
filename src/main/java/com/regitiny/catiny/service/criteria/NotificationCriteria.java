package com.regitiny.catiny.service.criteria;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.NotifyType;
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
 * Criteria class for the {@link com.regitiny.catiny.domain.Notification} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.NotificationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /notifications?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class NotificationCriteria implements Serializable, Criteria {

  /**
   * Class for filtering NotifyType
   */
  @GeneratedByJHipster
  public static class NotifyTypeFilter extends Filter<NotifyType> {

    public NotifyTypeFilter() {}

    public NotifyTypeFilter(NotifyTypeFilter filter) {
      super(filter);
    }

    @Override
    public NotifyTypeFilter copy() {
      return new NotifyTypeFilter(this);
    }
  }

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private NotifyTypeFilter notifyType;

  private StringFilter title;

  private LongFilter infoId;

  private Boolean distinct;

  public NotificationCriteria() {}

  public NotificationCriteria(NotificationCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.notifyType = other.notifyType == null ? null : other.notifyType.copy();
    this.title = other.title == null ? null : other.title.copy();
    this.infoId = other.infoId == null ? null : other.infoId.copy();
    this.distinct = other.distinct;
  }

  @Override
  public NotificationCriteria copy() {
    return new NotificationCriteria(this);
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

  public NotifyTypeFilter getNotifyType() {
    return notifyType;
  }

  public NotifyTypeFilter notifyType() {
    if (notifyType == null) {
      notifyType = new NotifyTypeFilter();
    }
    return notifyType;
  }

  public void setNotifyType(NotifyTypeFilter notifyType) {
    this.notifyType = notifyType;
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
    final NotificationCriteria that = (NotificationCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(notifyType, that.notifyType) &&
      Objects.equals(title, that.title) &&
      Objects.equals(infoId, that.infoId) &&
      Objects.equals(distinct, that.distinct)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, uuid, notifyType, title, infoId, distinct);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "NotificationCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (notifyType != null ? "notifyType=" + notifyType + ", " : "") +
            (title != null ? "title=" + title + ", " : "") +
            (infoId != null ? "infoId=" + infoId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
