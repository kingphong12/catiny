package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.FriendType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.Friend} entity.
 */
@ApiModel(
  description = "<p>@what?            -> The Friend entity.\n<p>@why?             ->\n<p>@use-to           -> Quản lý phần kết bạn, các mối liên hệ bạn bè ...\n<p>@commonly-used-in -> Bạn bè và các liên kết bạn bè ...\n\n<p>@describe         ->"
)
@GeneratedByJHipster
public class FriendDTO implements Serializable {

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

  private FriendType friendType;

  private BaseInfoDTO info;

  private MasterUserDTO friend;

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

  public FriendType getFriendType() {
    return friendType;
  }

  public void setFriendType(FriendType friendType) {
    this.friendType = friendType;
  }

  public BaseInfoDTO getInfo() {
    return info;
  }

  public void setInfo(BaseInfoDTO info) {
    this.info = info;
  }

  public MasterUserDTO getFriend() {
    return friend;
  }

  public void setFriend(MasterUserDTO friend) {
    this.friend = friend;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FriendDTO)) {
      return false;
    }

    FriendDTO friendDTO = (FriendDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, friendDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "FriendDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", friendType='" + getFriendType() + "'" +
            ", info=" + getInfo() +
            ", friend=" + getFriend() +
            "}";
    }
}
