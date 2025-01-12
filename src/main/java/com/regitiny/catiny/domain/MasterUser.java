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
 * <p>@what?            -> The MasterUser entity.\n<p>@why?             -> User (mặc định của jhipster) không cho thêm cột (nếu thêm thì sau khó update)\n<p>@use-to:          -> Lưu thông tin cơ bản của một người dùng\n<p>@commonly-used-in -> Thường sử dụng khi thao tác với tài khoản trong service trên server\n\n<p>@describe      	  -> Những dữ liệu của tài khoản và thương xuyên sử dụng (trong service) sẽ được lưu ở đây
 */
@Entity
@Table(name = "master_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "masteruser")
@GeneratedByJHipster
public class MasterUser implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
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
   * fullName : tên hiển thị . thực ra chỉ là firstName + lastName . nhưng sẽ rất bất tiện
   */
  @NotNull
  @Column(name = "full_name", nullable = false)
  private String fullName;

  /**
   * nickname : biệt danh của người dùng
   */
  @Column(name = "nickname")
  private String nickname;

  /**
   * avatar : @type Json -> ảnh đại diện của người dùng
   */
  @Lob
  @Column(name = "avatar")
  private String avatar;

  /**
   * quickInfo      : @type Json -> thông tin nhanh về người dùng này dùng trong giới thiệu sơ khi chỉ chuột vào người dùng
   */
  @Lob
  @Column(name = "quick_info")
  private String quickInfo;

  @OneToOne
  @MapsId
  @JoinColumn(name = "id")
  private User user;

  @JsonIgnoreProperties(value = { "info", "rankGroup", "owner" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private RankUser myRank;

  @JsonIgnoreProperties(value = { "histories", "createdBy", "modifiedBy", "owner", "classInfo", "permissions" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo info;

  @OneToMany(mappedBy = "owner")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "baseInfo", "owner" }, allowSetters = true)
  private Set<Permission> permissions = new HashSet<>();

  @ManyToMany
  @JoinTable(
    name = "rel_master_user__topic_interest",
    joinColumns = @JoinColumn(name = "master_user_id"),
    inverseJoinColumns = @JoinColumn(name = "topic_interest_id")
  )
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "info", "posts", "pagePosts", "groupPosts", "masterUsers" }, allowSetters = true)
  private Set<TopicInterest> topicInterests = new HashSet<>();

  @OneToMany(mappedBy = "owner")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "histories", "createdBy", "modifiedBy", "owner", "classInfo", "permissions" }, allowSetters = true)
  private Set<BaseInfo> owneds = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public MasterUser id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public MasterUser uuid(UUID uuid) {
    this.setUuid(uuid);
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getFullName() {
    return this.fullName;
  }

  public MasterUser fullName(String fullName) {
    this.setFullName(fullName);
    return this;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getNickname() {
    return this.nickname;
  }

  public MasterUser nickname(String nickname) {
    this.setNickname(nickname);
    return this;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getAvatar() {
    return this.avatar;
  }

  public MasterUser avatar(String avatar) {
    this.setAvatar(avatar);
    return this;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getQuickInfo() {
    return this.quickInfo;
  }

  public MasterUser quickInfo(String quickInfo) {
    this.setQuickInfo(quickInfo);
    return this;
  }

  public void setQuickInfo(String quickInfo) {
    this.quickInfo = quickInfo;
  }

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public MasterUser user(User user) {
    this.setUser(user);
    return this;
  }

  public RankUser getMyRank() {
    return this.myRank;
  }

  public void setMyRank(RankUser rankUser) {
    this.myRank = rankUser;
  }

  public MasterUser myRank(RankUser rankUser) {
    this.setMyRank(rankUser);
    return this;
  }

  public BaseInfo getInfo() {
    return this.info;
  }

  public void setInfo(BaseInfo baseInfo) {
    this.info = baseInfo;
  }

  public MasterUser info(BaseInfo baseInfo) {
    this.setInfo(baseInfo);
    return this;
  }

  public Set<Permission> getPermissions() {
    return this.permissions;
  }

  public void setPermissions(Set<Permission> permissions) {
    if (this.permissions != null) {
      this.permissions.forEach(i -> i.setOwner(null));
    }
    if (permissions != null) {
      permissions.forEach(i -> i.setOwner(this));
    }
    this.permissions = permissions;
  }

  public MasterUser permissions(Set<Permission> permissions) {
    this.setPermissions(permissions);
    return this;
  }

  public MasterUser addPermission(Permission permission) {
    this.permissions.add(permission);
    permission.setOwner(this);
    return this;
  }

  public MasterUser removePermission(Permission permission) {
    this.permissions.remove(permission);
    permission.setOwner(null);
    return this;
  }

  public Set<TopicInterest> getTopicInterests() {
    return this.topicInterests;
  }

  public void setTopicInterests(Set<TopicInterest> topicInterests) {
    this.topicInterests = topicInterests;
  }

  public MasterUser topicInterests(Set<TopicInterest> topicInterests) {
    this.setTopicInterests(topicInterests);
    return this;
  }

  public MasterUser addTopicInterest(TopicInterest topicInterest) {
    this.topicInterests.add(topicInterest);
    topicInterest.getMasterUsers().add(this);
    return this;
  }

  public MasterUser removeTopicInterest(TopicInterest topicInterest) {
    this.topicInterests.remove(topicInterest);
    topicInterest.getMasterUsers().remove(this);
    return this;
  }

  public Set<BaseInfo> getOwneds() {
    return this.owneds;
  }

  public void setOwneds(Set<BaseInfo> baseInfos) {
    if (this.owneds != null) {
      this.owneds.forEach(i -> i.setOwner(null));
    }
    if (baseInfos != null) {
      baseInfos.forEach(i -> i.setOwner(this));
    }
    this.owneds = baseInfos;
  }

  public MasterUser owneds(Set<BaseInfo> baseInfos) {
    this.setOwneds(baseInfos);
    return this;
  }

  public MasterUser addOwned(BaseInfo baseInfo) {
    this.owneds.add(baseInfo);
    baseInfo.setOwner(this);
    return this;
  }

  public MasterUser removeOwned(BaseInfo baseInfo) {
    this.owneds.remove(baseInfo);
    baseInfo.setOwner(null);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MasterUser)) {
      return false;
    }
    return id != null && id.equals(((MasterUser) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "MasterUser{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", nickname='" + getNickname() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", quickInfo='" + getQuickInfo() + "'" +
            "}";
    }
}
