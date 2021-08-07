package com.regitiny.catiny.service.dto;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.enumeration.PostInType;
import com.regitiny.catiny.domain.enumeration.PostType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.regitiny.catiny.domain.Post} entity.
 */
@ApiModel(
  description = "@what?            -> The Post entity\n@why?             ->\n@use-to           -> lưu các bài viết của người dùng\n@commonly-used-in -> đăng và xem các bài viết\n\n@describe         ->"
)
@GeneratedByJHipster
public class PostDTO implements Serializable {

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
   * postInType     : bài đăng này đăng ở đâu : người dùng , nhóm hay trang
   */
  @ApiModelProperty(value = "postInType     : bài đăng này đăng ở đâu : người dùng , nhóm hay trang")
  private PostInType postInType;

  /**
   * postType       : bài đăng này đơn giản , phức tạp hay dùng froala
   */
  @ApiModelProperty(value = "postType       : bài đăng này đơn giản , phức tạp hay dùng froala")
  private PostType postType;

  /**
   * content        : @type Json -> nội dùng bài đăng dữ liệu tùy theo postType
   */
  @ApiModelProperty(value = "content        : @type Json -> nội dùng bài đăng dữ liệu tùy theo postType")
  @Lob
  private String content;

  @Lob
  private String searchField;

  private BaseInfoDTO info;

  private GroupPostDTO group;

  private PagePostDTO page;

  private PostDTO parent;

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

  public PostInType getPostInType() {
    return postInType;
  }

  public void setPostInType(PostInType postInType) {
    this.postInType = postInType;
  }

  public PostType getPostType() {
    return postType;
  }

  public void setPostType(PostType postType) {
    this.postType = postType;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSearchField() {
    return searchField;
  }

  public void setSearchField(String searchField) {
    this.searchField = searchField;
  }

  public BaseInfoDTO getInfo() {
    return info;
  }

  public void setInfo(BaseInfoDTO info) {
    this.info = info;
  }

  public GroupPostDTO getGroup() {
    return group;
  }

  public void setGroup(GroupPostDTO group) {
    this.group = group;
  }

  public PagePostDTO getPage() {
    return page;
  }

  public void setPage(PagePostDTO page) {
    this.page = page;
  }

  public PostDTO getParent() {
    return parent;
  }

  public void setParent(PostDTO parent) {
    this.parent = parent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PostDTO)) {
      return false;
    }

    PostDTO postDTO = (PostDTO) o;
    if (this.id == null) {
      return false;
    }
    return Objects.equals(this.id, postDTO.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  // prettier-ignore
    @Override
    public String toString() {
        return "PostDTO{" +
            "id=" + getId() +
            ", uuid='" + getUuid() + "'" +
            ", postInType='" + getPostInType() + "'" +
            ", postType='" + getPostType() + "'" +
            ", content='" + getContent() + "'" +
            ", searchField='" + getSearchField() + "'" +
            ", info=" + getInfo() +
            ", group=" + getGroup() +
            ", page=" + getPage() +
            ", parent=" + getParent() +
            "}";
    }
}
