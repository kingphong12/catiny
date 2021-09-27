package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.RankGroup} entity.
 */
@ApiModel(
  description = "<p>@what?            -> The RankGroup entity.\n<p>@why?             ->\n<p>@use-to           -> Xếp hạng theo nhóm : toàn mạng ,khu vực , nhóm người (khoảng 30-100 người)\n<p>@commonly-used-in ->\n\n<p>@describe         ->"
)
@GeneratedByJHipster
public class RankGroupDTO implements Serializable {

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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof RankGroupDTO)) {
      return false;
    }

    RankGroupDTO rankGroupDTO = (RankGroupDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, rankGroupDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "RankGroupDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", info=" + getInfo() +
            "}";
    }
}
