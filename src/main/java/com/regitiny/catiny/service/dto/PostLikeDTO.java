package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.PostLike} entity.
 */
@ApiModel(
  description = "<p>@what?            -> The PostLike entity.\n<p>@why?             ->\n<p>@use-to           -> Lưu thông tin về những lượt like của bài đăng\n<p>@commonly-used-in ->\n\n<p>@describe         ->"
)
@GeneratedByJHipster
public class PostLikeDTO implements Serializable {

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

  private PostDTO post;

  private PostCommentDTO comment;

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

  public PostDTO getPost() {
    return post;
  }

  public void setPost(PostDTO post) {
    this.post = post;
  }

  public PostCommentDTO getComment() {
    return comment;
  }

  public void setComment(PostCommentDTO comment) {
    this.comment = comment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PostLikeDTO)) {
      return false;
    }

    PostLikeDTO postLikeDTO = (PostLikeDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, postLikeDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PostLikeDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", info=" + getInfo() +
            ", post=" + getPost() +
            ", comment=" + getComment() +
            "}";
    }
}
