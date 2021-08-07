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
 * @what?            -> The PostComment entity.\n@why?             ->\n@use-to           -> Lưu những bình luận của người dùng trong một bài đăng cụ thể\n@commonly-used-in -> được biết dưới dạng bình luận của các bài đăng\n\n@describe         ->
 */
@Entity
@Table(name = "post_comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "postcomment")
@GeneratedByJHipster
public class PostComment implements Serializable {

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

  @Lob
  @Column(name = "content")
  private String content;

  @JsonIgnoreProperties(value = { "histories", "createdBy", "modifiedBy", "owner", "classInfo", "permissions" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo info;

  @OneToMany(mappedBy = "comment")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "info", "post", "comment" }, allowSetters = true)
  private Set<PostLike> likes = new HashSet<>();

  @OneToMany(mappedBy = "parent")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "info", "likes", "replies", "post", "parent" }, allowSetters = true)
  private Set<PostComment> replies = new HashSet<>();

  @ManyToOne
  @JsonIgnoreProperties(
    value = { "info", "comments", "likes", "children", "group", "page", "parent", "newsFeeds", "topicInterests" },
    allowSetters = true
  )
  private Post post;

  @ManyToOne
  @JsonIgnoreProperties(value = { "info", "likes", "replies", "post", "parent" }, allowSetters = true)
  private PostComment parent;

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public PostComment id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public PostComment uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getContent() {
    return this.content;
  }

  public PostComment content(String content) {
    this.content = content;
    return this;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public BaseInfo getInfo() {
    return this.info;
  }

  public PostComment info(BaseInfo baseInfo) {
    this.setInfo(baseInfo);
    return this;
  }

  public void setInfo(BaseInfo baseInfo) {
    this.info = baseInfo;
  }

  public Set<PostLike> getLikes() {
    return this.likes;
  }

  public PostComment likes(Set<PostLike> postLikes) {
    this.setLikes(postLikes);
    return this;
  }

  public PostComment addLike(PostLike postLike) {
    this.likes.add(postLike);
    postLike.setComment(this);
    return this;
  }

  public PostComment removeLike(PostLike postLike) {
    this.likes.remove(postLike);
    postLike.setComment(null);
    return this;
  }

  public void setLikes(Set<PostLike> postLikes) {
    if (this.likes != null) {
      this.likes.forEach(i -> i.setComment(null));
    }
    if (postLikes != null) {
      postLikes.forEach(i -> i.setComment(this));
    }
    this.likes = postLikes;
  }

  public Set<PostComment> getReplies() {
    return this.replies;
  }

  public PostComment replies(Set<PostComment> postComments) {
    this.setReplies(postComments);
    return this;
  }

  public PostComment addReply(PostComment postComment) {
    this.replies.add(postComment);
    postComment.setParent(this);
    return this;
  }

  public PostComment removeReply(PostComment postComment) {
    this.replies.remove(postComment);
    postComment.setParent(null);
    return this;
  }

  public void setReplies(Set<PostComment> postComments) {
    if (this.replies != null) {
      this.replies.forEach(i -> i.setParent(null));
    }
    if (postComments != null) {
      postComments.forEach(i -> i.setParent(this));
    }
    this.replies = postComments;
  }

  public Post getPost() {
    return this.post;
  }

  public PostComment post(Post post) {
    this.setPost(post);
    return this;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public PostComment getParent() {
    return this.parent;
  }

  public PostComment parent(PostComment postComment) {
    this.setParent(postComment);
    return this;
  }

  public void setParent(PostComment postComment) {
    this.parent = postComment;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PostComment)) {
      return false;
    }
    return id != null && id.equals(((PostComment) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PostComment{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", content='" + getContent() + "'" +
            "}";
    }
}
