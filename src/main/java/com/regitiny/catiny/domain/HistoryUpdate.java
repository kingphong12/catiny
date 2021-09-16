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
 * A HistoryUpdate.
 */
@Entity
@Table(name = "history_update")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "historyupdate")
@GeneratedByJHipster
public class HistoryUpdate implements Serializable {

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
   * version        : phiên bản (bắt đầu từ 0)
   */
  @Column(name = "version")
  private Integer version;

  /**
   * content        : @type Json -> lịch sử cập nhật bản ghi này, những bản ghi cũ sẽ được lưu lại ở đây dưới dạng json
   */
  @Lob
  @Column(name = "content")
  private String content;

  @ManyToOne
  @JsonIgnoreProperties(value = { "histories", "createdBy", "modifiedBy", "owner", "classInfo", "permissions" }, allowSetters = true)
  private BaseInfo baseInfo;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public HistoryUpdate id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public HistoryUpdate uuid(UUID uuid) {
    this.setUuid(uuid);
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public Integer getVersion() {
    return this.version;
  }

  public HistoryUpdate version(Integer version) {
    this.setVersion(version);
    return this;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getContent() {
    return this.content;
  }

  public HistoryUpdate content(String content) {
    this.setContent(content);
    return this;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public BaseInfo getBaseInfo() {
    return this.baseInfo;
  }

  public void setBaseInfo(BaseInfo baseInfo) {
    this.baseInfo = baseInfo;
  }

  public HistoryUpdate baseInfo(BaseInfo baseInfo) {
    this.setBaseInfo(baseInfo);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof HistoryUpdate)) {
      return false;
    }
    return id != null && id.equals(((HistoryUpdate) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "HistoryUpdate{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", version=" + getVersion() +
            ", content='" + getContent() + "'" +
            "}";
    }
}
