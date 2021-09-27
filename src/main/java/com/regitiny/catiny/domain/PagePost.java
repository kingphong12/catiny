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
 * <p>@what?            -> The PagePost entity.\n<p>@why?             ->\n<p>@use-to           -> Lưu các Trang người dùng tạo ra\n<p>@commonly-used-in -> Cũng tương tự như bài đăng của một người dùng những sẽ chuyên biệt về  một chủ đề\n\n<p>@describe         ->
 */
@Entity
@Table(name = "page_post")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "pagepost")
@GeneratedByJHipster
public class PagePost implements Serializable {

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
   * name           : tên của page này
   */
  @NotNull
  @Column(name = "name", nullable = false)
  private String name;

  /**
   * avatar : @type Json -> ảnh đại diện của Page
   */
  @Lob
  @Column(name = "avatar")
  private String avatar;

  /**
   * quickInfo      : @type Json ->thông tin nổi bật giới thiệu sơ qua về page
   */
  @Lob
  @Column(name = "quick_info")
  private String quickInfo;

  @JsonIgnoreProperties(value = { "info", "page" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private PageProfile profile;

  @JsonIgnoreProperties(value = { "histories", "createdBy", "modifiedBy", "owner", "classInfo", "permissions" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo info;

  @OneToMany(mappedBy = "page")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(
    value = { "info", "comments", "likes", "children", "group", "page", "parent", "newsFeeds", "topicInterests" },
    allowSetters = true
  )
  private Set<Post> posts = new HashSet<>();

  @OneToMany(mappedBy = "pageDetails")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "info", "pageDetails" }, allowSetters = true)
  private Set<FollowPage> followeds = new HashSet<>();

  @ManyToMany(mappedBy = "pagePosts")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "info", "posts", "pagePosts", "groupPosts", "masterUsers" }, allowSetters = true)
  private Set<TopicInterest> topicInterests = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public PagePost id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public PagePost uuid(UUID uuid) {
    this.setUuid(uuid);
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return this.name;
  }

  public PagePost name(String name) {
    this.setName(name);
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAvatar() {
    return this.avatar;
  }

  public PagePost avatar(String avatar) {
    this.setAvatar(avatar);
    return this;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getQuickInfo() {
    return this.quickInfo;
  }

  public PagePost quickInfo(String quickInfo) {
    this.setQuickInfo(quickInfo);
    return this;
  }

  public void setQuickInfo(String quickInfo) {
    this.quickInfo = quickInfo;
  }

  public PageProfile getProfile() {
    return this.profile;
  }

  public void setProfile(PageProfile pageProfile) {
    this.profile = pageProfile;
  }

  public PagePost profile(PageProfile pageProfile) {
    this.setProfile(pageProfile);
    return this;
  }

  public BaseInfo getInfo() {
    return this.info;
  }

  public void setInfo(BaseInfo baseInfo) {
    this.info = baseInfo;
  }

  public PagePost info(BaseInfo baseInfo) {
    this.setInfo(baseInfo);
    return this;
  }

  public Set<Post> getPosts() {
    return this.posts;
  }

  public void setPosts(Set<Post> posts) {
    if (this.posts != null) {
      this.posts.forEach(i -> i.setPage(null));
    }
    if (posts != null) {
      posts.forEach(i -> i.setPage(this));
    }
    this.posts = posts;
  }

  public PagePost posts(Set<Post> posts) {
    this.setPosts(posts);
    return this;
  }

  public PagePost addPost(Post post) {
    this.posts.add(post);
    post.setPage(this);
    return this;
  }

  public PagePost removePost(Post post) {
    this.posts.remove(post);
    post.setPage(null);
    return this;
  }

  public Set<FollowPage> getFolloweds() {
    return this.followeds;
  }

  public void setFolloweds(Set<FollowPage> followPages) {
    if (this.followeds != null) {
      this.followeds.forEach(i -> i.setPageDetails(null));
    }
    if (followPages != null) {
      followPages.forEach(i -> i.setPageDetails(this));
    }
    this.followeds = followPages;
  }

  public PagePost followeds(Set<FollowPage> followPages) {
    this.setFolloweds(followPages);
    return this;
  }

  public PagePost addFollowed(FollowPage followPage) {
    this.followeds.add(followPage);
    followPage.setPageDetails(this);
    return this;
  }

  public PagePost removeFollowed(FollowPage followPage) {
    this.followeds.remove(followPage);
    followPage.setPageDetails(null);
    return this;
  }

  public Set<TopicInterest> getTopicInterests() {
    return this.topicInterests;
  }

  public void setTopicInterests(Set<TopicInterest> topicInterests) {
    if (this.topicInterests != null) {
      this.topicInterests.forEach(i -> i.removePagePost(this));
    }
    if (topicInterests != null) {
      topicInterests.forEach(i -> i.addPagePost(this));
    }
    this.topicInterests = topicInterests;
  }

  public PagePost topicInterests(Set<TopicInterest> topicInterests) {
    this.setTopicInterests(topicInterests);
    return this;
  }

  public PagePost addTopicInterest(TopicInterest topicInterest) {
    this.topicInterests.add(topicInterest);
    topicInterest.getPagePosts().add(this);
    return this;
  }

  public PagePost removeTopicInterest(TopicInterest topicInterest) {
    this.topicInterests.remove(topicInterest);
    topicInterest.getPagePosts().remove(this);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PagePost)) {
      return false;
    }
    return id != null && id.equals(((PagePost) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PagePost{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", name='" + getName() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", quickInfo='" + getQuickInfo() + "'" +
            "}";
    }
}
