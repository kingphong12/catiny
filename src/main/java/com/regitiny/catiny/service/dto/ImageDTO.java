package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.Image} entity.
 */
@ApiModel(
  description = "<p>@what?            -> The Image entity.\n<p>@why?             ->\n<p>@use-to           -> Lưu thông tin Ảnh mà người dùng upload lên\n<p>@commonly-used-in ->\n\n<p>@describe         ->"
)
@GeneratedByJHipster
public class ImageDTO implements Serializable {

  private Long id;

  /**
   * uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)
   */
  @NotNull
  @ApiModelProperty(
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)",
    required = true
  )
  private UUID uuid;

  /**
   * name           : tên của ảnh . muốn lấy ảnh sẽ gọi theo tên này. sẽ ra một danh sách các anh gồm (ảnh nguyên gốc , các ảnh đã tối ưu , cắt ... từ ảnh gốc đó)
   */
  @ApiModelProperty(
    value = "name           : tên của ảnh . muốn lấy ảnh sẽ gọi theo tên này. sẽ ra một danh sách các anh gồm (ảnh nguyên gốc , các ảnh đã tối ưu , cắt ... từ ảnh gốc đó)"
  )
  private String name;

  /**
   * width          : chiều rộng ảnh
   */
  @ApiModelProperty(value = "width          : chiều rộng ảnh")
  private Integer width;

  /**
   * height         : chiều cao ảnh
   */
  @ApiModelProperty(value = "height         : chiều cao ảnh")
  private Integer height;

  /**
   * quality        : chất lượng sau khi xử lý
   */
  @DecimalMin(value = "0")
  @DecimalMax(value = "1")
  @ApiModelProperty(value = "quality        : chất lượng sau khi xử lý")
  private Float quality;

  /**
   * pixelSize      : kích thước của ảnh
   */
  @ApiModelProperty(value = "pixelSize      : kích thước của ảnh")
  private Integer pixelSize;

  /**
   * priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)
   */
  @ApiModelProperty(value = "priorityIndex  : chỉ số ưu tiên (số lớn nhỏ ưu tiên càng cao)")
  private Long priorityIndex;

  /**
   * dataSize       : kích thước file theo byte
   */
  @ApiModelProperty(value = "dataSize       : kích thước file theo byte")
  private Long dataSize;

  private FileInfoDTO fileInfo;

  private BaseInfoDTO info;

  private ImageDTO original;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public Float getQuality() {
    return quality;
  }

  public void setQuality(Float quality) {
    this.quality = quality;
  }

  public Integer getPixelSize() {
    return pixelSize;
  }

  public void setPixelSize(Integer pixelSize) {
    this.pixelSize = pixelSize;
  }

  public Long getPriorityIndex() {
    return priorityIndex;
  }

  public void setPriorityIndex(Long priorityIndex) {
    this.priorityIndex = priorityIndex;
  }

  public Long getDataSize() {
    return dataSize;
  }

  public void setDataSize(Long dataSize) {
    this.dataSize = dataSize;
  }

  public FileInfoDTO getFileInfo() {
    return fileInfo;
  }

  public void setFileInfo(FileInfoDTO fileInfo) {
    this.fileInfo = fileInfo;
  }

  public BaseInfoDTO getInfo() {
    return info;
  }

  public void setInfo(BaseInfoDTO info) {
    this.info = info;
  }

  public ImageDTO getOriginal() {
    return original;
  }

  public void setOriginal(ImageDTO original) {
    this.original = original;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ImageDTO)) {
      return false;
    }

    ImageDTO imageDTO = (ImageDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, imageDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "ImageDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", name='" + getName() + "'" +
            ", width=" + getWidth() +
            ", height=" + getHeight() +
            ", quality=" + getQuality() +
            ", pixelSize=" + getPixelSize() +
            ", priorityIndex=" + getPriorityIndex() +
            ", dataSize=" + getDataSize() +
            ", fileInfo=" + getFileInfo() +
            ", info=" + getInfo() +
            ", original=" + getOriginal() +
            "}";
    }
}
