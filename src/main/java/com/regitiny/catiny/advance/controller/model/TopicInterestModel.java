package com.regitiny.catiny.advance.controller.model;

import com.regitiny.catiny.service.dto.BaseInfoDTO;
import com.regitiny.catiny.service.dto.GroupPostDTO;
import com.regitiny.catiny.service.dto.PagePostDTO;
import com.regitiny.catiny.service.dto.PostDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicInterestModel implements Serializable
{
  private Long id;

  @NotNull
  @ApiModelProperty(
    required = true,
    value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
  )
  private UUID uuid;

  private String title;

  @Lob
  private String content;

  private BaseInfoDTO info;

  private Set<PostDTO> posts;

  private Set<PagePostDTO> pagePosts;

  private Set<GroupPostDTO> groupPosts;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Request implements Serializable
  {
    private Long id;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
    )
    private UUID uuid;

    private String title;

    @Lob
    private String content;

    private BaseInfoDTO info;

    private Set<PostDTO> posts;

    private Set<PagePostDTO> pagePosts;

    private Set<GroupPostDTO> groupPosts;
  }

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Response implements Serializable
  {
    private Long id;

    @NotNull
    @ApiModelProperty(
      required = true,
      value = "uuid *         : this is reference key (client) .primary key được sử dụng trong các service còn uuid này để định danh giao tiếp với client(frontend)"
    )
    private UUID uuid;

    private String title;

    @Lob
    private String content;

    private BaseInfoDTO info;

    private Set<PostDTO> posts;

    private Set<PagePostDTO> pagePosts;

    private Set<GroupPostDTO> groupPosts;
  }
}
