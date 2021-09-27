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
 * <p>@what?            -> The RankUser entity.\n<p>@why?             ->\n<p>@use-to           -> Xếp hạng của bản thân Trong toàn mạng , trong khu vực , trong nhóm người\n<p>@commonly-used-in -> thường thấy trong phần\n\n<p>@describe         ->
 */
@Entity
@Table(name = "rank_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "rankuser")
@GeneratedByJHipster
public class RankUser implements Serializable {

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

  @Column(name = "rating_points")
  private Float ratingPoints;

  @JsonIgnoreProperties(value = { "histories", "createdBy", "modifiedBy", "owner", "classInfo", "permissions" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo info;

  @ManyToOne
  @JsonIgnoreProperties(value = { "info", "rankUsers" }, allowSetters = true)
  private RankGroup rankGroup;

  @JsonIgnoreProperties(value = { "user", "myRank", "info", "permissions", "topicInterests", "owneds" }, allowSetters = true)
  @OneToOne(mappedBy = "myRank")
  private MasterUser owner;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public RankUser id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public RankUser uuid(UUID uuid) {
    this.setUuid(uuid);
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public Float getRatingPoints() {
    return this.ratingPoints;
  }

  public RankUser ratingPoints(Float ratingPoints) {
    this.setRatingPoints(ratingPoints);
    return this;
  }

  public void setRatingPoints(Float ratingPoints) {
    this.ratingPoints = ratingPoints;
  }

  public BaseInfo getInfo() {
    return this.info;
  }

  public void setInfo(BaseInfo baseInfo) {
    this.info = baseInfo;
  }

  public RankUser info(BaseInfo baseInfo) {
    this.setInfo(baseInfo);
    return this;
  }

  public RankGroup getRankGroup() {
    return this.rankGroup;
  }

  public void setRankGroup(RankGroup rankGroup) {
    this.rankGroup = rankGroup;
  }

  public RankUser rankGroup(RankGroup rankGroup) {
    this.setRankGroup(rankGroup);
    return this;
  }

  public MasterUser getOwner() {
    return this.owner;
  }

  public void setOwner(MasterUser masterUser) {
    if (this.owner != null) {
      this.owner.setMyRank(null);
    }
    if (masterUser != null) {
      masterUser.setMyRank(this);
    }
    this.owner = masterUser;
  }

  public RankUser owner(MasterUser masterUser) {
    this.setOwner(masterUser);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RankUser)) {
      return false;
    }
    return id != null && id.equals(((RankUser) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "RankUser{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", ratingPoints=" + getRatingPoints() +
            "}";
    }
}
