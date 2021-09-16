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
 * @what?            -> The NewsFeed entity.\n@why?             -> người dùng mà xem trực tiếp các Post thì một số bài đăng sẽ không phù hợp dễ gây chán khi xem\n@use-to           -> Ở đây chứa thông tin của những Post hiển thị cho người dùng xem\n@commonly-used-in -> Được sử dụng trong phần hiển thị các bài đăng trên news feed\n\n@describe         -> trong phần bản tin thay vì hiển thị trực tiếp các Post cho người dùng xem\nta sẽ tính toán độ phù hợp và add vào bảng này sau đó cho người dùng xem
 */
@Entity
@Table(name = "news_feed")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "newsfeed")
@GeneratedByJHipster
public class NewsFeed implements Serializable {

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
   * priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)
   */
  @Column(name = "priority_index")
  private Long priorityIndex;

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

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public NewsFeed id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public NewsFeed uuid(UUID uuid) {
    this.setUuid(uuid);
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public Long getPriorityIndex() {
    return this.priorityIndex;
  }

  public NewsFeed priorityIndex(Long priorityIndex) {
    this.setPriorityIndex(priorityIndex);
    return this;
  }

  public void setPriorityIndex(Long priorityIndex) {
    this.priorityIndex = priorityIndex;
  }

  public BaseInfo getInfo() {
    return this.info;
  }

  public void setInfo(BaseInfo baseInfo) {
    this.info = baseInfo;
  }

  public NewsFeed info(BaseInfo baseInfo) {
    this.setInfo(baseInfo);
    return this;
  }

  public Post getPost() {
    return this.post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public NewsFeed post(Post post) {
    this.setPost(post);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof NewsFeed)) {
      return false;
    }
    return id != null && id.equals(((NewsFeed) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "NewsFeed{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", priorityIndex=" + getPriorityIndex() +
            "}";
    }
}
