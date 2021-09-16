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
 * @what?            -> The PostLike entity.\n@why?             ->\n@use-to           -> Lưu thông tin về những lượt like của bài đăng\n@commonly-used-in ->\n\n@describe         ->
 */
@Entity
@Table(name = "post_like")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "postlike")
@GeneratedByJHipster
public class PostLike implements Serializable {

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
  @JsonIgnoreProperties(
    value = { "info", "comments", "likes", "children", "group", "page", "parent", "newsFeeds", "topicInterests" },
    allowSetters = true
  )
  private Post post;

  @ManyToOne
  @JsonIgnoreProperties(value = { "info", "likes", "replies", "post", "parent" }, allowSetters = true)
  private PostComment comment;

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public PostLike id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public PostLike uuid(UUID uuid) {
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

  public PostLike info(BaseInfo baseInfo) {
    this.setInfo(baseInfo);
    return this;
  }

  public Post getPost() {
    return this.post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public PostLike post(Post post) {
    this.setPost(post);
    return this;
  }

  public PostComment getComment() {
    return this.comment;
  }

  public void setComment(PostComment postComment) {
    this.comment = postComment;
  }

  public PostLike comment(PostComment postComment) {
    this.setComment(postComment);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PostLike)) {
      return false;
    }
    return id != null && id.equals(((PostLike) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PostLike{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            "}";
    }
}
