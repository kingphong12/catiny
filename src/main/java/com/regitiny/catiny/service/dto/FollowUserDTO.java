package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.FollowUser} entity.
 */
@ApiModel(
  description = "<p>@what?            -> The FollowUser entity.\n<p>@why?             ->\n<p>@use-to           -> Quản lý những người mà người dùng đăng ký theo dõi\n<p>@commonly-used-in -> Chủ đề mà người dùng theo dõi\n\n<p>@describe         ->"
)
@GeneratedByJHipster
public class FollowUserDTO implements Serializable {

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

  private BaseInfoDTO info;

  private MasterUserDTO follow;

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

  public BaseInfoDTO getInfo() {
    return info;
  }

  public void setInfo(BaseInfoDTO info) {
    this.info = info;
  }

  public MasterUserDTO getFollow() {
    return follow;
  }

  public void setFollow(MasterUserDTO follow) {
    this.follow = follow;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FollowUserDTO)) {
      return false;
    }

    FollowUserDTO followUserDTO = (FollowUserDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, followUserDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "FollowUserDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", info=" + getInfo() +
            ", follow=" + getFollow() +
            "}";
    }
}
