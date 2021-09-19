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
 * @what?            -> The Image entity.\n@why?             ->\n@use-to           -> Lưu thông tin Ảnh mà người dùng upload lên\n@commonly-used-in ->\n\n@describe         ->
 */
@Entity
@Table(name = "image")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "image")
@GeneratedByJHipster
public class Image implements Serializable {

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
   * name           : tên của ảnh . muốn lấy ảnh sẽ gọi theo tên này. sẽ ra một danh sách các anh gồm (ảnh nguyên gốc , các ảnh đã tối ưu , cắt ... từ ảnh gốc đó)
   */
  @Column(name = "name")
  private String name;

  /**
   * width          : chiều rộng ảnh
   */
  @Column(name = "width")
  private Integer width;

  /**
   * height         : chiều cao ảnh
   */
  @Column(name = "height")
  private Integer height;

  /**
   * quality        : chất lượng sau khi xử lý
   */
  @DecimalMin(value = "0")
  @DecimalMax(value = "1")
  @Column(name = "quality")
  private Float quality;

  /**
   * pixelSize      : kích thước của ảnh
   */
  @Column(name = "pixel_size")
  private Integer pixelSize;

  /**
   * priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)
   */
  @Column(name = "priority_index")
  private Long priorityIndex;

  /**
   * dataSize       : kích thước file theo byte
   */
  @Column(name = "data_size")
  private Long dataSize;

  @JsonIgnoreProperties(value = { "info" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private FileInfo fileInfo;

  @JsonIgnoreProperties(value = { "histories", "createdBy", "modifiedBy", "owner", "classInfo", "permissions" }, allowSetters = true)
  @OneToOne
  @JoinColumn(unique = true)
  private BaseInfo info;

  @OneToMany(mappedBy = "original")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "fileInfo", "info", "processeds", "original", "albums" }, allowSetters = true)
  private Set<Image> processeds = new HashSet<>();

  @ManyToOne
  @JsonIgnoreProperties(value = { "fileInfo", "info", "processeds", "original", "albums" }, allowSetters = true)
  private Image original;

  @ManyToMany(mappedBy = "images")
  @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
  @JsonIgnoreProperties(value = { "info", "images" }, allowSetters = true)
  private Set<Album> albums = new HashSet<>();

  // jhipster-needle-entity-add-field - JHipster will add fields here

  public Long getId() {
    return this.id;
  }

  public Image id(Long id) {
    this.setId(id);
    return this;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public Image uuid(UUID uuid) {
    this.setUuid(uuid);
    return this;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return this.name;
  }

  public Image name(String name) {
    this.setName(name);
    return this;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getWidth() {
    return this.width;
  }

  public Image width(Integer width) {
    this.setWidth(width);
    return this;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return this.height;
  }

  public Image height(Integer height) {
    this.setHeight(height);
    return this;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public Float getQuality() {
    return this.quality;
  }

  public Image quality(Float quality) {
    this.setQuality(quality);
    return this;
  }

  public void setQuality(Float quality) {
    this.quality = quality;
  }

  public Integer getPixelSize() {
    return this.pixelSize;
  }

  public Image pixelSize(Integer pixelSize) {
    this.setPixelSize(pixelSize);
    return this;
  }

  public void setPixelSize(Integer pixelSize) {
    this.pixelSize = pixelSize;
  }

  public Long getPriorityIndex() {
    return this.priorityIndex;
  }

  public Image priorityIndex(Long priorityIndex) {
    this.setPriorityIndex(priorityIndex);
    return this;
  }

  public void setPriorityIndex(Long priorityIndex) {
    this.priorityIndex = priorityIndex;
  }

  public Long getDataSize() {
    return this.dataSize;
  }

  public Image dataSize(Long dataSize) {
    this.setDataSize(dataSize);
    return this;
  }

  public void setDataSize(Long dataSize) {
    this.dataSize = dataSize;
  }

  public FileInfo getFileInfo() {
    return this.fileInfo;
  }

  public void setFileInfo(FileInfo fileInfo) {
    this.fileInfo = fileInfo;
  }

  public Image fileInfo(FileInfo fileInfo) {
    this.setFileInfo(fileInfo);
    return this;
  }

  public BaseInfo getInfo() {
    return this.info;
  }

  public void setInfo(BaseInfo baseInfo) {
    this.info = baseInfo;
  }

  public Image info(BaseInfo baseInfo) {
    this.setInfo(baseInfo);
    return this;
  }

  public Set<Image> getProcesseds() {
    return this.processeds;
  }

  public void setProcesseds(Set<Image> images) {
    if (this.processeds != null) {
      this.processeds.forEach(i -> i.setOriginal(null));
    }
    if (images != null) {
      images.forEach(i -> i.setOriginal(this));
    }
    this.processeds = images;
  }

  public Image processeds(Set<Image> images) {
    this.setProcesseds(images);
    return this;
  }

  public Image addProcessed(Image image) {
    this.processeds.add(image);
    image.setOriginal(this);
    return this;
  }

  public Image removeProcessed(Image image) {
    this.processeds.remove(image);
    image.setOriginal(null);
    return this;
  }

  public Image getOriginal() {
    return this.original;
  }

  public void setOriginal(Image image) {
    this.original = image;
  }

  public Image original(Image image) {
    this.setOriginal(image);
    return this;
  }

  public Set<Album> getAlbums() {
    return this.albums;
  }

  public void setAlbums(Set<Album> albums) {
    if (this.albums != null) {
      this.albums.forEach(i -> i.removeImage(this));
    }
    if (albums != null) {
      albums.forEach(i -> i.addImage(this));
    }
    this.albums = albums;
  }

  public Image albums(Set<Album> albums) {
    this.setAlbums(albums);
    return this;
  }

  public Image addAlbum(Album album) {
    this.albums.add(album);
    album.getImages().add(this);
    return this;
  }

  public Image removeAlbum(Album album) {
    this.albums.remove(album);
    album.getImages().remove(this);
    return this;
  }

  // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Image)) {
      return false;
    }
    return id != null && id.equals(((Image) o).id);
  }

  @Override
  public int hashCode() {
    // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
    return getClass().hashCode();
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "Image{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", name='" + getName() + "'" +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", quality=" + getQuality() +
            ", pixelSize=" + getPixelSize() +
            ", priorityIndex=" + getPriorityIndex() +
            ", dataSize=" + getDataSize() +
            "}";
    }
}
