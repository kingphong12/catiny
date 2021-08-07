package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.ProcessStatus;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * BaseInfo
 */
@Entity
@Table(name = "base_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "baseinfo")
@GeneratedByJHipster
public class BaseInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  /**
   * uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)
   */
  @NotNull
  @Type(type = "uuid-char")
  @Column(name = "uuid", length = 36, nullable = false, unique = true)
  private UUID uuid;

  /**
   * processStatus *: @defaultValue( DONE ) -> tình trạng xử lý sử dụng trong phê duyệt
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "process_status")
  private ProcessStatus processStatus;

  /**
   * modifiedClass *: thực hiện sửa đổi bản ghi này ở service class nào
   */
  @Column(name = "modified_class")
  private String modifiedClass;

  /**
   * createdDate *  : thời gian tạo ra bản ghi này (lần đầu tiên)
   */
  @Column(name = "created_date")
  private Instant createdDate;

  /**
   * modifiedDate * : thời gian sửa bản ghi này
   */
  @Column(name = "modified_date")
  private Instant modifiedDate;

  /**
   * notes *        : @type Json -> chú thích thêm hoặc những lưu ý cho bản ghi này ở dưới dạng Json\"
   */
  @Lob
  @Column(name = "notes")
  private String notes;

  /**
   * deleted *      : @defaultValue( false ) -> đánh dấu là đã xóa
   */
  @Column(name = "deleted")
  private Boolean deleted;

  /**
   * priorityIndex *: chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)
   */
  @Column(name = "priority_index")
  private Long priorityIndex;

  /**
   * countUse *     : đếm số lần truy cập vào bản ghi này để xem sửa xóa
   */
  @Column(name = "count_use")
  private Long countUse;

  @OneToMany(mappedBy = "baseInfo")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo" }, allowSetters = true)
  private Set<HistoryUpdate> histories = new HashSet<>();

  @ManyToOne
  @JsonIgnoreProperties(value = { "user", "myRank", "info", "permissions", "topicInterests", "owneds" }, allowSetters = true)
  private MasterUser createdBy;

  @ManyToOne
  @JsonIgnoreProperties(value = { "user", "myRank", "info", "permissions", "topicInterests", "owneds" }, allowSetters = true)
  private MasterUser modifiedBy;

  @ManyToOne
  @JsonIgnoreProperties(value = { "user", "myRank", "info", "permissions", "topicInterests", "owneds" }, allowSetters = true)
  private MasterUser owner;

  @ManyToOne
  @JsonIgnoreProperties(value = { "baseInfos" }, allowSetters = true)
  private ClassInfo classInfo;

  @OneToMany(mappedBy = "baseInfo")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "owner" }, allowSetters = true)
  private Set<Permission> permissions = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BaseInfo id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public BaseInfo uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public ProcessStatus getProcessStatus() {
    return this.processStatus;
  }

  public BaseInfo processStatus(ProcessStatus processStatus) {
    this.processStatus = processStatus;
    return this;
  }

  public void setProcessStatus(ProcessStatus processStatus) {
    this.processStatus = processStatus;
  }

  public String getModifiedClass() {
    return this.modifiedClass;
  }

  public BaseInfo modifiedClass(String modifiedClass) {
    this.modifiedClass = modifiedClass;
    return this;
  }

  public void setModifiedClass(String modifiedClass) {
    this.modifiedClass = modifiedClass;
  }

  public Instant getCreatedDate() {
    return this.createdDate;
  }

  public BaseInfo createdDate(Instant createdDate) {
    this.createdDate = createdDate;
    return this;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }

  public Instant getModifiedDate() {
    return this.modifiedDate;
  }

  public BaseInfo modifiedDate(Instant modifiedDate) {
    this.modifiedDate = modifiedDate;
    return this;
  }

  public void setModifiedDate(Instant modifiedDate) {
    this.modifiedDate = modifiedDate;
  }

  public String getNotes() {
    return this.notes;
  }

  public BaseInfo notes(String notes) {
    this.notes = notes;
    return this;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public Boolean getDeleted() {
    return this.deleted;
  }

  public BaseInfo deleted(Boolean deleted) {
    this.deleted = deleted;
    return this;
  }

  public void setDeleted(Boolean deleted) {
    this.deleted = deleted;
  }

  public Long getPriorityIndex() {
    return this.priorityIndex;
  }

  public BaseInfo priorityIndex(Long priorityIndex) {
    this.priorityIndex = priorityIndex;
    return this;
  }

  public void setPriorityIndex(Long priorityIndex) {
    this.priorityIndex = priorityIndex;
  }

  public Long getCountUse() {
    return this.countUse;
  }

  public BaseInfo countUse(Long countUse) {
    this.countUse = countUse;
    return this;
  }

  public void setCountUse(Long countUse) {
    this.countUse = countUse;
  }

  public Set<HistoryUpdate> getHistories() {
    return this.histories;
  }

  public BaseInfo histories(Set<HistoryUpdate> historyUpdates) {
    this.setHistories(historyUpdates);
    return this;
  }

  public BaseInfo addHistory(HistoryUpdate historyUpdate) {
    this.histories.add(historyUpdate);
    historyUpdate.setBaseInfo(this);
    return this;
  }

  public BaseInfo removeHistory(HistoryUpdate historyUpdate) {
    this.histories.remove(historyUpdate);
    historyUpdate.setBaseInfo(null);
    return this;
  }

  public void setHistories(Set<HistoryUpdate> historyUpdates) {
    if (this.histories != null) {
      this.histories.forEach(i -> i.setBaseInfo(null));
    }
    if (historyUpdates != null) {
      historyUpdates.forEach(i -> i.setBaseInfo(this));
    }
    this.histories = historyUpdates;
  }

  public MasterUser getCreatedBy() {
    return this.createdBy;
  }

  public BaseInfo createdBy(MasterUser masterUser) {
    this.setCreatedBy(masterUser);
    return this;
  }

  public void setCreatedBy(MasterUser masterUser) {
    this.createdBy = masterUser;
  }

  public MasterUser getModifiedBy() {
    return this.modifiedBy;
  }

  public BaseInfo modifiedBy(MasterUser masterUser) {
    this.setModifiedBy(masterUser);
    return this;
  }

  public void setModifiedBy(MasterUser masterUser) {
    this.modifiedBy = masterUser;
  }

  public MasterUser getOwner() {
    return this.owner;
  }

  public BaseInfo owner(MasterUser masterUser) {
    this.setOwner(masterUser);
    return this;
  }

  public void setOwner(MasterUser masterUser) {
    this.owner = masterUser;
  }

  public ClassInfo getClassInfo() {
    return this.classInfo;
  }

  public BaseInfo classInfo(ClassInfo classInfo) {
    this.setClassInfo(classInfo);
    return this;
  }

  public void setClassInfo(ClassInfo classInfo) {
    this.classInfo = classInfo;
  }

  public Set<Permission> getPermissions() {
    return this.permissions;
  }

  public BaseInfo permissions(Set<Permission> permissions) {
    this.setPermissions(permissions);
    return this;
  }

  public BaseInfo addPermission(Permission permission) {
    this.permissions.add(permission);
    permission.setBaseInfo(this);
    return this;
  }

  public BaseInfo removePermission(Permission permission) {
    this.permissions.remove(permission);
    permission.setBaseInfo(null);
    return this;
  }

  public void setPermissions(Set<Permission> permissions) {
    if (this.permissions != null) {
      this.permissions.forEach(i -> i.setBaseInfo(null));
    }
    if (permissions != null) {
      permissions.forEach(i -> i.setBaseInfo(this));
    }
    this.permissions = permissions;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BaseInfo)) {
      return false;
    }
    return id != null && id.equals(((BaseInfo) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "BaseInfo{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", processStatus='" + getProcessStatus() + "'" +
            ", modifiedClass='" + getModifiedClass() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", modifiedDate='" + getModifiedDate() + "'" +
            ", notes='" + getNotes() + "'" +
            ", deleted='" + getDeleted() + "'" +
            ", priorityIndex=" + getPriorityIndex() +
            ", countUse=" + getCountUse() +
            "}";
    }
}
