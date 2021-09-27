package com.regitiny.catiny.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.PostInType;
import com.regitiny.catiny.domain.enumeration.PostType;
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
 * <p>@what?            -> The Post entity\n<p>@why?             ->\n<p>@use-to           -> lưu các bài viết của người dùng\n<p>@commonly-used-in -> đăng và xem các bài viết\n\n<p>@describe         ->
 */
@Entity
@Table(name = "post")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "post")
@GeneratedByJHipster
public class Post implements Serializable {

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
   * postInType     : bài đăng này đăng ở đâu : người dùng , nhóm hay trang
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "post_in_type")
  private PostInType postInType;

  /**
   * postType       : bài đăng này đơn giản , phức tạp hay dùng froala
   */
  @Enumerated(EnumType.STRING)
  @Column(name = "post_type")
  private PostType postType;

  /**
   * content        : @type Json -> nội dùng bài đăng dữ liệu tùy theo postType
   */
  @Lob
  @Column(name = "content")
  private String content;

  @Lob
  @Column(name = "search_field")
  private String searchField;

  @JsonIgnoreProperties(value = { "histories", "createdBy", "modifiedBy", "owner", "classInfo", "permissions" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo info;

  @OneToMany(mappedBy = "post")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "info", "likes", "replies", "post", "parent" }, allowSetters = true)
  private Set<PostComment> comments = new HashSet<>();

  @OneToMany(mappedBy = "post")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "info", "post", "comment" }, allowSetters = true)
  private Set<PostLike> likes = new HashSet<>();

  @OneToMany(mappedBy = "parent")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(
    value = { "info", "comments", "likes", "children", "group", "page", "parent", "newsFeeds", "topicInterests" },
    allowSetters = true
  )
  private Set<Post> children = new HashSet<>();

  @ManyToOne
  @JsonIgnoreProperties(value = { "profile", "info", "posts", "followeds", "topicInterests" }, allowSetters = true)
  private GroupPost group;

  @ManyToOne
  @JsonIgnoreProperties(value = { "profile", "info", "posts", "followeds", "topicInterests" }, allowSetters = true)
  private PagePost page;

  @ManyToOne
  @JsonIgnoreProperties(
    value = { "info", "comments", "likes", "children", "group", "page", "parent", "newsFeeds", "topicInterests" },
    allowSetters = true
  )
  private Post parent;

  @OneToMany(mappedBy = "post")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "info", "post" }, allowSetters = true)
  private Set<NewsFeed> newsFeeds = new HashSet<>();

  @ManyToMany(mappedBy = "posts")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "info", "posts", "pagePosts", "groupPosts", "masterUsers" }, allowSetters = true)
  private Set<TopicInterest> topicInterests = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public Post id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public Post uuid(UUID uuid) {
    this.setUuid(uuid);
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public PostInType getPostInType() {
    return this.postInType;
  }

  public Post postInType(PostInType postInType) {
    this.setPostInType(postInType);
    return this;
  }

  public void setPostInType(PostInType postInType) {
    this.postInType = postInType;
  }

  public PostType getPostType() {
    return this.postType;
  }

  public Post postType(PostType postType) {
    this.setPostType(postType);
    return this;
  }

  public void setPostType(PostType postType) {
    this.postType = postType;
  }

  public String getContent() {
    return this.content;
  }

  public Post content(String content) {
    this.setContent(content);
    return this;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSearchField() {
    return this.searchField;
  }

  public Post searchField(String searchField) {
    this.setSearchField(searchField);
    return this;
  }

  public void setSearchField(String searchField) {
    this.searchField = searchField;
  }

  public BaseInfo getInfo() {
    return this.info;
  }

  public void setInfo(BaseInfo baseInfo) {
    this.info = baseInfo;
  }

  public Post info(BaseInfo baseInfo) {
    this.setInfo(baseInfo);
    return this;
  }

  public Set<PostComment> getComments() {
    return this.comments;
  }

  public void setComments(Set<PostComment> postComments) {
    if (this.comments != null) {
      this.comments.forEach(i -> i.setPost(null));
    }
    if (postComments != null) {
      postComments.forEach(i -> i.setPost(this));
    }
    this.comments = postComments;
  }

  public Post comments(Set<PostComment> postComments) {
    this.setComments(postComments);
    return this;
  }

  public Post addComment(PostComment postComment) {
    this.comments.add(postComment);
    postComment.setPost(this);
    return this;
  }

  public Post removeComment(PostComment postComment) {
    this.comments.remove(postComment);
    postComment.setPost(null);
    return this;
  }

  public Set<PostLike> getLikes() {
    return this.likes;
  }

  public void setLikes(Set<PostLike> postLikes) {
    if (this.likes != null) {
      this.likes.forEach(i -> i.setPost(null));
    }
    if (postLikes != null) {
      postLikes.forEach(i -> i.setPost(this));
    }
    this.likes = postLikes;
  }

  public Post likes(Set<PostLike> postLikes) {
    this.setLikes(postLikes);
    return this;
  }

  public Post addLike(PostLike postLike) {
    this.likes.add(postLike);
    postLike.setPost(this);
    return this;
  }

  public Post removeLike(PostLike postLike) {
    this.likes.remove(postLike);
    postLike.setPost(null);
    return this;
  }

  public Set<Post> getChildren() {
    return this.children;
  }

  public void setChildren(Set<Post> posts) {
    if (this.children != null) {
      this.children.forEach(i -> i.setParent(null));
    }
    if (posts != null) {
      posts.forEach(i -> i.setParent(this));
    }
    this.children = posts;
  }

  public Post children(Set<Post> posts) {
    this.setChildren(posts);
    return this;
  }

  public Post addChildren(Post post) {
    this.children.add(post);
    post.setParent(this);
    return this;
  }

  public Post removeChildren(Post post) {
    this.children.remove(post);
    post.setParent(null);
    return this;
  }

  public GroupPost getGroup() {
    return this.group;
  }

  public void setGroup(GroupPost groupPost) {
    this.group = groupPost;
  }

  public Post group(GroupPost groupPost) {
    this.setGroup(groupPost);
    return this;
  }

  public PagePost getPage() {
    return this.page;
  }

  public void setPage(PagePost pagePost) {
    this.page = pagePost;
  }

  public Post page(PagePost pagePost) {
    this.setPage(pagePost);
    return this;
  }

  public Post getParent() {
    return this.parent;
  }

  public void setParent(Post post) {
    this.parent = post;
  }

  public Post parent(Post post) {
    this.setParent(post);
    return this;
  }

  public Set<NewsFeed> getNewsFeeds() {
    return this.newsFeeds;
  }

  public void setNewsFeeds(Set<NewsFeed> newsFeeds) {
    if (this.newsFeeds != null) {
      this.newsFeeds.forEach(i -> i.setPost(null));
    }
    if (newsFeeds != null) {
      newsFeeds.forEach(i -> i.setPost(this));
    }
    this.newsFeeds = newsFeeds;
  }

  public Post newsFeeds(Set<NewsFeed> newsFeeds) {
    this.setNewsFeeds(newsFeeds);
    return this;
  }

  public Post addNewsFeed(NewsFeed newsFeed) {
    this.newsFeeds.add(newsFeed);
    newsFeed.setPost(this);
    return this;
  }

  public Post removeNewsFeed(NewsFeed newsFeed) {
    this.newsFeeds.remove(newsFeed);
    newsFeed.setPost(null);
    return this;
  }

  public Set<TopicInterest> getTopicInterests() {
    return this.topicInterests;
  }

  public void setTopicInterests(Set<TopicInterest> topicInterests) {
    if (this.topicInterests != null) {
      this.topicInterests.forEach(i -> i.removePost(this));
    }
    if (topicInterests != null) {
      topicInterests.forEach(i -> i.addPost(this));
    }
    this.topicInterests = topicInterests;
  }

  public Post topicInterests(Set<TopicInterest> topicInterests) {
    this.setTopicInterests(topicInterests);
    return this;
  }

  public Post addTopicInterest(TopicInterest topicInterest) {
    this.topicInterests.add(topicInterest);
    topicInterest.getPosts().add(this);
    return this;
  }

  public Post removeTopicInterest(TopicInterest topicInterest) {
    this.topicInterests.remove(topicInterest);
    topicInterest.getPosts().remove(this);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Post)) {
      return false;
    }
    return id != null && id.equals(((Post) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Post{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", postInType='" + getPostInType() + "'" +
            ", postType='" + getPostType() + "'" +
            ", content='" + getContent() + "'" +
            ", searchField='" + getSearchField() + "'" +
            "}";
    }
}
