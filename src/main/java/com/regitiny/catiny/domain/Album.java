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
 * @what?            -> The Album entity\n@why?             ->\n@use-to           -> Lưu thông tin về một bộ album của người dùng\n@commonly-used-in -> Người dùng nhóm một bộ ảnh vào một album\n\n@describe         ->
 */
@Entity
@Table(name = "album")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "album")
@GeneratedByJHipster
public class Album implements Serializable {

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
   * name           : tên của album
   */
  @NotNull
  @Column(name = "name", nullable = false)
  private String name;

  /**
   * note           : trú thích của album (ví dụ album đại học)
   */
  @Column(name = "note")
  private String note;

  /**
   * avatar         : @type Json -> ảnh đại diện của Album
   */
  @Lob
  @Column(name = "avatar")
  private String avatar;

  @JsonIgnoreProperties(value = { "histories", "createdBy", "modifiedBy", "owner", "classInfo", "permissions" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo info;

  @ManyToMany
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JoinTable(name = "rel_album__image", joinColumns = @JoinColumn(name = "album_id"), inverseJoinColumns = @JoinColumn(name = "image_id"))
  @JsonIgnoreProperties(value = { "fileInfo", "info", "processeds", "original", "albums" }, allowSetters = true)
  private Set<Image> images = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Album id(Long id) {
    this.id = id;
    return this;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public Album uuid(UUID uuid) {
    this.uuid = uuid;
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return this.name;
  }

  public Album name(String name) {
    this.name = name;
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNote() {
    return this.note;
  }

  public Album note(String note) {
    this.note = note;
    return this;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public String getAvatar() {
    return this.avatar;
  }

  public Album avatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public BaseInfo getInfo() {
    return this.info;
  }

  public Album info(BaseInfo baseInfo) {
    this.setInfo(baseInfo);
    return this;
  }

  public void setInfo(BaseInfo baseInfo) {
    this.info = baseInfo;
  }

  public Set<Image> getImages() {
    return this.images;
  }

  public Album images(Set<Image> images) {
    this.setImages(images);
    return this;
  }

  public Album addImage(Image image) {
    this.images.add(image);
    image.getAlbums().add(this);
    return this;
  }

  public Album removeImage(Image image) {
    this.images.remove(image);
    image.getAlbums().remove(this);
    return this;
  }

  public void setImages(Set<Image> images) {
    this.images = images;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Album)) {
      return false;
    }
    return id != null && id.equals(((Album) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Album{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", name='" + getName() + "'" +
            ", note='" + getNote() + "'" +
            ", avatar='" + getAvatar() + "'" +
            "}";
    }
}
