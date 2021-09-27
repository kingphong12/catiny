package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.PostComment} entity.
 */
@ApiModel(
  description = "<p>@what?            -> The PostComment entity.\n<p>@why?             ->\n<p>@use-to           -> Lưu những bình luận của người dùng trong một bài đăng cụ thể\n<p>@commonly-used-in -> được biết dưới dạng bình luận của các bài đăng\n\n<p>@describe         ->"
)
@GeneratedByJHipster
public class PostCommentDTO implements Serializable {

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

  @Lob
  private String content;

  private BaseInfoDTO info;

  private PostDTO post;

  private PostCommentDTO parent;

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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
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

  public PostCommentDTO getParent() {
    return parent;
  }

  public void setParent(PostCommentDTO parent) {
    this.parent = parent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PostCommentDTO)) {
      return false;
    }

    PostCommentDTO postCommentDTO = (PostCommentDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, postCommentDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PostCommentDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", content='" + getContent() + "'" +
            ", info=" + getInfo() +
            ", post=" + getPost() +
            ", parent=" + getParent() +
            "}";
    }
}
