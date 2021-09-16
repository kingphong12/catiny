package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * A Permission.
 */
@Entity
@Table(name = "permission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "permission")
@GeneratedByJHipster
public class Permission implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  @Column(name = "id")
  private Long id;

  /**
   * uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)
   */
  @NotNull
  @Type(type = "uuid-char")
  @Column(name = "uuid", length = 36, nullable = false, unique = true)
  private UUID uuid;

  /**
   * quyền đọc
   */
  @Column(name = "jhi_read")
  private Boolean read;

  /**
   * quyền ghi và sửa
   */
  @Column(name = "jhi_write")
  private Boolean write;

  /**
   * quyền chia sẻ
   */
  @Column(name = "share")
  private Boolean share;

  /**
   * quyền xóa
   */
  @Column(name = "jhi_delete")
  private Boolean delete;

  /**
   * quyền trao quyền cho user khác
   */
  @Column(name = "jhi_add")
  private Boolean add;

  /**
   * cấp độ 0->* số nhỏ hơn sẽ có quyền lớn hơn
   */
  @Column(name = "level")
  private Integer level;

  @ManyToOne
  @JsonIgnoreProperties(value = { "histories", "createdBy", "modifiedBy", "owner", "classInfo", "permissions" }, allowSetters = true)
  private BaseInfo baseInfo;

  @ManyToOne
  @JsonIgnoreProperties(value = { "user", "myRank", "info", "permissions", "topicInterests", "owneds" }, allowSetters = true)
  private MasterUser owner;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public Permission id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public Permission uuid(UUID uuid) {
    this.setUuid(uuid);
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public Boolean getRead() {
    return this.read;
  }

  public Permission read(Boolean read) {
    this.setRead(read);
    return this;
  }

  public void setRead(Boolean read) {
    this.read = read;
  }

  public Boolean getWrite() {
    return this.write;
  }

  public Permission write(Boolean write) {
    this.setWrite(write);
    return this;
  }

  public void setWrite(Boolean write) {
    this.write = write;
  }

  public Boolean getShare() {
    return this.share;
  }

  public Permission share(Boolean share) {
    this.setShare(share);
    return this;
  }

  public void setShare(Boolean share) {
    this.share = share;
  }

  public Boolean getDelete() {
    return this.delete;
  }

  public Permission delete(Boolean delete) {
    this.setDelete(delete);
    return this;
  }

  public void setDelete(Boolean delete) {
    this.delete = delete;
  }

  public Boolean getAdd() {
    return this.add;
  }

  public Permission add(Boolean add) {
    this.setAdd(add);
    return this;
  }

  public void setAdd(Boolean add) {
    this.add = add;
  }

  public Integer getLevel() {
    return this.level;
  }

  public Permission level(Integer level) {
    this.setLevel(level);
    return this;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public Permission baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  public MasterUser getOwner() {
    return this.owner;
  }

  public void setOwner(MasterUser masterUser) {
    this.owner = masterUser;
  }

  public Permission owner(MasterUser masterUser) {
    this.setOwner(masterUser);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Permission)) {
      return false;
    }
    return id != null && id.equals(((Permission) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Permission{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", read='" + getRead() + "'" +
            ", write='" + getWrite() + "'" +
            ", share='" + getShare() + "'" +
            ", delete='" + getDelete() + "'" +
            ", add='" + getAdd() + "'" +
            ", level=" + getLevel() +
            "}";
    }
}
