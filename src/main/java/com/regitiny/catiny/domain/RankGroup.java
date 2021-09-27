package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * <p>@what?            -> The RankGroup entity.\n<p>@why?             ->\n<p>@use-to           -> Xếp hạng theo nhóm : toàn mạng ,khu vực , nhóm người (khoảng 30-100 người)\n<p>@commonly-used-in ->\n\n<p>@describe         ->
 */
@Entity
@Table(name = "rank_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "rankgroup")
@GeneratedByJHipster
public class RankGroup implements Serializable {

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

  @OneToMany(mappedBy = "rankGroup")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "info", "rankGroup", "owner" }, allowSetters = true)
  private Set<RankUser> rankUsers = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public RankGroup id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public RankGroup uuid(UUID uuid) {
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

  public RankGroup info(BaseInfo baseInfo) {
    this.setInfo(baseInfo);
    return this;
  }

  public Set<RankUser> getRankUsers() {
    return this.rankUsers;
  }

  public void setRankUsers(Set<RankUser> rankUsers) {
    if (this.rankUsers != null) {
      this.rankUsers.forEach(i -> i.setRankGroup(null));
    }
    if (rankUsers != null) {
      rankUsers.forEach(i -> i.setRankGroup(this));
    }
    this.rankUsers = rankUsers;
  }

  public RankGroup rankUsers(Set<RankUser> rankUsers) {
    this.setRankUsers(rankUsers);
    return this;
  }

  public RankGroup addRankUser(RankUser rankUser) {
    this.rankUsers.add(rankUser);
    rankUser.setRankGroup(this);
    return this;
  }

  public RankGroup removeRankUser(RankUser rankUser) {
    this.rankUsers.remove(rankUser);
    rankUser.setRankGroup(null);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RankGroup)) {
      return false;
    }
    return id != null && id.equals(((RankGroup) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "RankGroup{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            "}";
    }
}
