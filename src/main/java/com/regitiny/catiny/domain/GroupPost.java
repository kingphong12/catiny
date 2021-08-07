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
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @what?            -> The GroupPost entity\n@why?             -> mọi người cần tạo ra một nhóm riêng hoặc chung để có thể trao đổi\n@use-to           -> quản lý nhóm\n@commonly-used-in -> các nhóm\n\n@describe         ->
 */
@Entity
@Table(name = "group_post")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "grouppost")
@GeneratedByJHipster
public class GroupPost implements Serializable {

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
   * name           : tên của group này
   */
  @NotNull
  @Column(name = "name", nullable = false)
  private String name;

  /**
   * avatar : @type Json -> ảnh đại diện của Group
   */
  @Lob
  @Column(name = "avatar")
  private String avatar;

  /**
   * quickInfo      : @type Json -> thông tin giới thiệu sơ qua của group này
   */
  @Lob
  @Column(name = "quick_info")
  private String quickInfo;

  @JsonIgnoreProperties(value = { "info", "group" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private GroupProfile profile;

  @JsonIgnoreProperties(value = { "histories", "createdBy", "modifiedBy", "owner", "classInfo", "permissions" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo info;

  @OneToMany(mappedBy = "group")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(
    value = { "info", "comments", "likes", "children", "group", "page", "parent", "newsFeeds", "topicInterests" },
    allowSetters = true
  )
  private Set<Post> posts = new HashSet<>();

  @OneToMany(mappedBy = "groupDetails")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "info", "groupDetails" }, allowSetters = true)
  private Set<FollowGroup> followeds = new HashSet<>();

  @ManyToMany(mappedBy = "groupPosts")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "info", "posts", "pagePosts", "groupPosts", "masterUsers" }, allowSetters = true)
  private Set<TopicInterest> topicInterests = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public GroupPost id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public GroupPost uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return this.name;
  }

  public GroupPost name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAvatar() {
    return this.avatar;
  }

  public GroupPost avatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getQuickInfo() {
    return this.quickInfo;
  }

  public GroupPost quickInfo(String quickInfo) {
    this.quickInfo = quickInfo;
    return this;
  }

  public void setQuickInfo(String quickInfo) {
    this.quickInfo = quickInfo;
  }

  public GroupProfile getProfile() {
    return this.profile;
  }

  public GroupPost profile(GroupProfile groupProfile) {
    this.setProfile(groupProfile);
    return this;
  }

  public void setProfile(GroupProfile groupProfile) {
    this.profile = groupProfile;
  }

  public BaseInfo getInfo() {
    return this.info;
  }

  public GroupPost info(BaseInfo baseInfo) {
    this.setInfo(baseInfo);
    return this;
  }

  public void setInfo(BaseInfo baseInfo) {
    this.info = baseInfo;
  }

  public Set<Post> getPosts() {
    return this.posts;
  }

  public GroupPost posts(Set<Post> posts) {
    this.setPosts(posts);
    return this;
  }

  public GroupPost addPost(Post post) {
    this.posts.add(post);
    post.setGroup(this);
    return this;
  }

  public GroupPost removePost(Post post) {
    this.posts.remove(post);
    post.setGroup(null);
    return this;
  }

  public void setPosts(Set<Post> posts) {
    if (this.posts != null) {
      this.posts.forEach(i -> i.setGroup(null));
    }
    if (posts != null) {
      posts.forEach(i -> i.setGroup(this));
    }
    this.posts = posts;
  }

  public Set<FollowGroup> getFolloweds() {
    return this.followeds;
  }

  public GroupPost followeds(Set<FollowGroup> followGroups) {
    this.setFolloweds(followGroups);
    return this;
  }

  public GroupPost addFollowed(FollowGroup followGroup) {
    this.followeds.add(followGroup);
    followGroup.setGroupDetails(this);
    return this;
  }

  public GroupPost removeFollowed(FollowGroup followGroup) {
    this.followeds.remove(followGroup);
    followGroup.setGroupDetails(null);
    return this;
  }

  public void setFolloweds(Set<FollowGroup> followGroups) {
    if (this.followeds != null) {
      this.followeds.forEach(i -> i.setGroupDetails(null));
    }
    if (followGroups != null) {
      followGroups.forEach(i -> i.setGroupDetails(this));
    }
    this.followeds = followGroups;
  }

  public Set<TopicInterest> getTopicInterests() {
    return this.topicInterests;
  }

  public GroupPost topicInterests(Set<TopicInterest> topicInterests) {
    this.setTopicInterests(topicInterests);
    return this;
  }

  public GroupPost addTopicInterest(TopicInterest topicInterest) {
    this.topicInterests.add(topicInterest);
    topicInterest.getGroupPosts().add(this);
    return this;
  }

  public GroupPost removeTopicInterest(TopicInterest topicInterest) {
    this.topicInterests.remove(topicInterest);
    topicInterest.getGroupPosts().remove(this);
    return this;
  }

  public void setTopicInterests(Set<TopicInterest> topicInterests) {
    if (this.topicInterests != null) {
      this.topicInterests.forEach(i -> i.removeGroupPost(this));
    }
    if (topicInterests != null) {
      topicInterests.forEach(i -> i.addGroupPost(this));
    }
    this.topicInterests = topicInterests;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof GroupPost)) {
      return false;
    }
    return id != null && id.equals(((GroupPost) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "GroupPost{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", name='" + getName() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", quickInfo='" + getQuickInfo() + "'" +
            "}";
    }
}
