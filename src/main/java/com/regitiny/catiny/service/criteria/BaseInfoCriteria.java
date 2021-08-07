package com.regitiny.catiny.service.criteria;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.ProcessStatus;
import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.InstantFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;
import tech.jhipster.service.filter.UUIDFilter;

/**
 * Criteria class for the {@link com.regitiny.catiny.domain.BaseInfo} entity. This class is used
 * in {@link com.regitiny.catiny.web.rest.BaseInfoResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /base-infos?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@GeneratedByJHipster
public class BaseInfoCriteria implements Serializable, Criteria {

  /**
   * Class for filtering ProcessStatus
   */
  @GeneratedByJHipster
  public static class ProcessStatusFilter extends Filter<ProcessStatus> {

    public ProcessStatusFilter() {}

    public ProcessStatusFilter(ProcessStatusFilter filter) {
      super(filter);
    }

    @Override
    public ProcessStatusFilter copy() {
      return new ProcessStatusFilter(this);
    }
  }

  private static final long serialVersionUID = 1L;

  private LongFilter id;

  private UUIDFilter uuid;

  private ProcessStatusFilter processStatus;

  private StringFilter modifiedClass;

  private InstantFilter createdDate;

  private InstantFilter modifiedDate;

  private BooleanFilter deleted;

  private LongFilter priorityIndex;

  private LongFilter countUse;

  private LongFilter historyId;

  private LongFilter createdById;

  private LongFilter modifiedById;

  private LongFilter ownerId;

  private LongFilter classInfoId;

  private LongFilter permissionId;

  public BaseInfoCriteria() {}

  public BaseInfoCriteria(BaseInfoCriteria other) {
    this.id = other.id == null ? null : other.id.copy();
    this.uuid = other.uuid == null ? null : other.uuid.copy();
    this.processStatus = other.processStatus == null ? null : other.processStatus.copy();
    this.modifiedClass = other.modifiedClass == null ? null : other.modifiedClass.copy();
    this.createdDate = other.createdDate == null ? null : other.createdDate.copy();
    this.modifiedDate = other.modifiedDate == null ? null : other.modifiedDate.copy();
    this.deleted = other.deleted == null ? null : other.deleted.copy();
    this.priorityIndex = other.priorityIndex == null ? null : other.priorityIndex.copy();
    this.countUse = other.countUse == null ? null : other.countUse.copy();
    this.historyId = other.historyId == null ? null : other.historyId.copy();
    this.createdById = other.createdById == null ? null : other.createdById.copy();
    this.modifiedById = other.modifiedById == null ? null : other.modifiedById.copy();
    this.ownerId = other.ownerId == null ? null : other.ownerId.copy();
    this.classInfoId = other.classInfoId == null ? null : other.classInfoId.copy();
    this.permissionId = other.permissionId == null ? null : other.permissionId.copy();
  }

  @Override
  public BaseInfoCriteria copy() {
    return new BaseInfoCriteria(this);
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

  public ProcessStatusFilter getProcessStatus() {
    return processStatus;
  }

  public ProcessStatusFilter processStatus() {
    if (processStatus == null) {
      processStatus = new ProcessStatusFilter();
    }
    return processStatus;
  }

  public void setProcessStatus(ProcessStatusFilter processStatus) {
    this.processStatus = processStatus;
  }

  public StringFilter getModifiedClass() {
    return modifiedClass;
  }

  public StringFilter modifiedClass() {
    if (modifiedClass == null) {
      modifiedClass = new StringFilter();
    }
    return modifiedClass;
  }

  public void setModifiedClass(StringFilter modifiedClass) {
    this.modifiedClass = modifiedClass;
  }

  public InstantFilter getCreatedDate() {
    return createdDate;
  }

  public InstantFilter createdDate() {
    if (createdDate == null) {
      createdDate = new InstantFilter();
    }
    return createdDate;
  }

  public void setCreatedDate(InstantFilter createdDate) {
    this.createdDate = createdDate;
  }

  public InstantFilter getModifiedDate() {
    return modifiedDate;
  }

  public InstantFilter modifiedDate() {
    if (modifiedDate == null) {
      modifiedDate = new InstantFilter();
    }
    return modifiedDate;
  }

  public void setModifiedDate(InstantFilter modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public BooleanFilter getDeleted() {
    return deleted;
  }

  public BooleanFilter deleted() {
    if (deleted == null) {
      deleted = new BooleanFilter();
    }
    return deleted;
  }

  public void setDeleted(BooleanFilter deleted) {
    this.deleted = deleted;
  }

  public LongFilter getPriorityIndex() {
    return priorityIndex;
  }

  public LongFilter priorityIndex() {
    if (priorityIndex == null) {
      priorityIndex = new LongFilter();
    }
    return priorityIndex;
  }

  public void setPriorityIndex(LongFilter priorityIndex) {
    this.priorityIndex = priorityIndex;
  }

  public LongFilter getCountUse() {
    return countUse;
  }

  public LongFilter countUse() {
    if (countUse == null) {
      countUse = new LongFilter();
    }
    return countUse;
  }

  public void setCountUse(LongFilter countUse) {
    this.countUse = countUse;
  }

  public LongFilter getHistoryId() {
    return historyId;
  }

  public LongFilter historyId() {
    if (historyId == null) {
      historyId = new LongFilter();
    }
    return historyId;
  }

  public void setHistoryId(LongFilter historyId) {
    this.historyId = historyId;
  }

  public LongFilter getCreatedById() {
    return createdById;
  }

  public LongFilter createdById() {
    if (createdById == null) {
      createdById = new LongFilter();
    }
    return createdById;
  }

  public void setCreatedById(LongFilter createdById) {
    this.createdById = createdById;
  }

  public LongFilter getModifiedById() {
    return modifiedById;
  }

  public LongFilter modifiedById() {
    if (modifiedById == null) {
      modifiedById = new LongFilter();
    }
    return modifiedById;
  }

  public void setModifiedById(LongFilter modifiedById) {
    this.modifiedById = modifiedById;
  }

  public LongFilter getOwnerId() {
    return ownerId;
  }

  public LongFilter ownerId() {
    if (ownerId == null) {
      ownerId = new LongFilter();
    }
    return ownerId;
  }

  public void setOwnerId(LongFilter ownerId) {
    this.ownerId = ownerId;
  }

  public LongFilter getClassInfoId() {
    return classInfoId;
  }

  public LongFilter classInfoId() {
    if (classInfoId == null) {
      classInfoId = new LongFilter();
    }
    return classInfoId;
  }

  public void setClassInfoId(LongFilter classInfoId) {
    this.classInfoId = classInfoId;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final BaseInfoCriteria that = (BaseInfoCriteria) o;
    return (
      Objects.equals(id, that.id) &&
      Objects.equals(uuid, that.uuid) &&
      Objects.equals(processStatus, that.processStatus) &&
      Objects.equals(modifiedClass, that.modifiedClass) &&
      Objects.equals(createdDate, that.createdDate) &&
      Objects.equals(modifiedDate, that.modifiedDate) &&
      Objects.equals(deleted, that.deleted) &&
      Objects.equals(priorityIndex, that.priorityIndex) &&
      Objects.equals(countUse, that.countUse) &&
      Objects.equals(historyId, that.historyId) &&
      Objects.equals(createdById, that.createdById) &&
      Objects.equals(modifiedById, that.modifiedById) &&
      Objects.equals(ownerId, that.ownerId) &&
      Objects.equals(classInfoId, that.classInfoId) &&
      Objects.equals(permissionId, that.permissionId)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hash(
      id,
      uuid,
      processStatus,
      modifiedClass,
      createdDate,
      modifiedDate,
      deleted,
      priorityIndex,
      countUse,
      historyId,
      createdById,
      modifiedById,
      ownerId,
      classInfoId,
      permissionId
    );
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "BaseInfoCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (uuid != null ? "uuid=" + uuid + ", " : "") +
            (processStatus != null ? "processStatus=" + processStatus + ", " : "") +
            (modifiedClass != null ? "modifiedClass=" + modifiedClass + ", " : "") +
            (createdDate != null ? "createdDate=" + createdDate + ", " : "") +
            (modifiedDate != null ? "modifiedDate=" + modifiedDate + ", " : "") +
            (deleted != null ? "deleted=" + deleted + ", " : "") +
            (priorityIndex != null ? "priorityIndex=" + priorityIndex + ", " : "") +
            (countUse != null ? "countUse=" + countUse + ", " : "") +
            (historyId != null ? "historyId=" + historyId + ", " : "") +
            (createdById != null ? "createdById=" + createdById + ", " : "") +
            (modifiedById != null ? "modifiedById=" + modifiedById + ", " : "") +
            (ownerId != null ? "ownerId=" + ownerId + ", " : "") +
            (classInfoId != null ? "classInfoId=" + classInfoId + ", " : "") +
            (permissionId != null ? "permissionId=" + permissionId + ", " : "") +
            "}";
    }
}
