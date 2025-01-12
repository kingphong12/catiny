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
 * <p>@what?            -> The FollowPage entity.\n<p>@why?             ->\n<p>@use-to           -> Quản lý các trang mà người dùng đăng ký theo dõi\n<p>@commonly-used-in -> Chủ đề mà người dùng theo dõi\n\n<p>@describe         ->
 */
@Entity
@Table(name = "follow_page")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "followpage")
@GeneratedByJHipster
public class FollowPage implements Serializable {

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

  @JsonIgnoreProperties(value = { "histories", "createdBy", "modifiedBy", "owner", "classInfo", "permissions" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo info;

  @ManyToOne
  @JsonIgnoreProperties(value = { "profile", "info", "posts", "followeds", "topicInterests" }, allowSetters = true)
  private PagePost pageDetails;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public FollowPage id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public FollowPage uuid(UUID uuid) {
    this.setUuid(uuid);
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public BaseInfo getInfo() {
    return this.info;
  }

  public void setInfo(BaseInfo baseInfo) {
    this.info = baseInfo;
  }

  public FollowPage info(BaseInfo baseInfo) {
    this.setInfo(baseInfo);
    return this;
  }

  public PagePost getPageDetails() {
    return this.pageDetails;
  }

  public void setPageDetails(PagePost pagePost) {
    this.pageDetails = pagePost;
  }

  public FollowPage pageDetails(PagePost pagePost) {
    this.setPageDetails(pagePost);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FollowPage)) {
      return false;
    }
    return id != null && id.equals(((FollowPage) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "FollowPage{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            "}";
    }
}
