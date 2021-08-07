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
 * @what?            -> The MessageGroup entity.\n@why?             ->\n@use-to           -> Chứa thông tin các nhóm mà hiện tại người dùng đang ở trong đó (phần nhắn tin)\n@commonly-used-in -> Hiển thị các tin nhắn\n\n@describe         -> một nhóm tạo ra sẽ là một uuid . nếu nhắn tin cặp thì sẽ sắp xếp login sau đó hash md5 rồi chuyển thành định dạng uuid
 */
@Entity
@Table(name = "message_group")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "messagegroup")
@GeneratedByJHipster
public class MessageGroup implements Serializable {

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
   * groupName
   */
  @Column(name = "group_name")
  private String groupName;

  /**
   * avatar : @type Json -> ảnh đại diện của MessageGroup
   */
  @Lob
  @Column(name = "avatar")
  private String avatar;

  /**
   * addBy
   */
  @Column(name = "add_by")
  private String addBy;

  @JsonIgnoreProperties(value = { "histories", "createdBy", "modifiedBy", "owner", "classInfo", "permissions" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo info;

  @OneToMany(mappedBy = "group")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "info", "group" }, allowSetters = true)
  private Set<MessageContent> contents = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public MessageGroup id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public MessageGroup uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getGroupName() {
    return this.groupName;
  }

  public MessageGroup groupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public String getAvatar() {
    return this.avatar;
  }

  public MessageGroup avatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getAddBy() {
    return this.addBy;
  }

  public MessageGroup addBy(String addBy) {
    this.addBy = addBy;
    return this;
  }

  public void setAddBy(String addBy) {
    this.addBy = addBy;
  }

  public BaseInfo getInfo() {
    return this.info;
  }

  public MessageGroup info(BaseInfo baseInfo) {
    this.setInfo(baseInfo);
    return this;
  }

  public void setInfo(BaseInfo baseInfo) {
    this.info = baseInfo;
  }

  public Set<MessageContent> getContents() {
    return this.contents;
  }

  public MessageGroup contents(Set<MessageContent> messageContents) {
    this.setContents(messageContents);
    return this;
  }

  public MessageGroup addContent(MessageContent messageContent) {
    this.contents.add(messageContent);
    messageContent.setGroup(this);
    return this;
  }

  public MessageGroup removeContent(MessageContent messageContent) {
    this.contents.remove(messageContent);
    messageContent.setGroup(null);
    return this;
  }

  public void setContents(Set<MessageContent> messageContents) {
    if (this.contents != null) {
      this.contents.forEach(i -> i.setGroup(null));
    }
    if (messageContents != null) {
      messageContents.forEach(i -> i.setGroup(this));
    }
    this.contents = messageContents;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof MessageGroup)) {
      return false;
    }
    return id != null && id.equals(((MessageGroup) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "MessageGroup{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", groupName='" + getGroupName() + "'" +
            ", avatar='" + getAvatar() + "'" +
            ", addBy='" + getAddBy() + "'" +
            "}";
    }
}
