package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.StatusName;
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

/**
 * @what?         	-> The AccountStatus entity.\n@why?          	->\n@use-to:       	-> Lưu , quản lý trạng thái của tài khoản đang on hay off ...\n@commonly-used-in -> Những nghiệp vũ nhắn tin,thông báo cần biết trạng thái của tài khoản ...\n\n@describe      	->
 */
@Entity
@Table(name = "account_status")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "accountstatus")
@GeneratedByJHipster
public class AccountStatus implements Serializable {

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
   * accountStatus  : thạng thái hiện tại của người dùng
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "account_status")
  private StatusName accountStatus;

  /**
   * lastVisited    : thời gian truy cập cuối cùng gần nhất
   */
  @Column(name = "last_visited")
  private Instant lastVisited;

  /**
   * statusComment  : người dùng comment lại trạng thái để hiển thị ra nếu muốn
   */
  @Column(name = "status_comment")
  private String statusComment;

  @JsonIgnoreProperties(value = { "histories", "createdBy", "modifiedBy", "owner", "classInfo", "permissions" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo info;

  @OneToMany(mappedBy = "accountStatus")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "info", "accountStatus" }, allowSetters = true)
  private Set<DeviceStatus> deviceStatuses = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public AccountStatus id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public AccountStatus uuid(UUID uuid) {
    this.setUuid(uuid);
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public StatusName getAccountStatus() {
    return this.accountStatus;
  }

  public AccountStatus accountStatus(StatusName accountStatus) {
    this.setAccountStatus(accountStatus);
    return this;
  }

  public void setAccountStatus(StatusName accountStatus) {
    this.accountStatus = accountStatus;
  }

  public Instant getLastVisited() {
    return this.lastVisited;
  }

  public AccountStatus lastVisited(Instant lastVisited) {
    this.setLastVisited(lastVisited);
    return this;
  }

  public void setLastVisited(Instant lastVisited) {
    this.lastVisited = lastVisited;
  }

  public String getStatusComment() {
    return this.statusComment;
  }

  public AccountStatus statusComment(String statusComment) {
    this.setStatusComment(statusComment);
    return this;
  }

  public void setStatusComment(String statusComment) {
    this.statusComment = statusComment;
  }

  public BaseInfo getInfo() {
    return this.info;
  }

  public void setInfo(BaseInfo baseInfo) {
    this.info = baseInfo;
  }

  public AccountStatus info(BaseInfo baseInfo) {
    this.setInfo(baseInfo);
    return this;
  }

  public Set<DeviceStatus> getDeviceStatuses() {
    return this.deviceStatuses;
  }

  public void setDeviceStatuses(Set<DeviceStatus> deviceStatuses) {
    if (this.deviceStatuses != null) {
      this.deviceStatuses.forEach(i -> i.setAccountStatus(null));
    }
    if (deviceStatuses != null) {
      deviceStatuses.forEach(i -> i.setAccountStatus(this));
    }
    this.deviceStatuses = deviceStatuses;
  }

  public AccountStatus deviceStatuses(Set<DeviceStatus> deviceStatuses) {
    this.setDeviceStatuses(deviceStatuses);
    return this;
  }

  public AccountStatus addDeviceStatus(DeviceStatus deviceStatus) {
    this.deviceStatuses.add(deviceStatus);
    deviceStatus.setAccountStatus(this);
    return this;
  }

  public AccountStatus removeDeviceStatus(DeviceStatus deviceStatus) {
    this.deviceStatuses.remove(deviceStatus);
    deviceStatus.setAccountStatus(null);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AccountStatus)) {
      return false;
    }
    return id != null && id.equals(((AccountStatus) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "AccountStatus{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", accountStatus='" + getAccountStatus() + "'" +
            ", lastVisited='" + getLastVisited() + "'" +
            ", statusComment='" + getStatusComment() + "'" +
            "}";
    }
}
