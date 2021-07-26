package com.regitiny.catiny.advance.controller.rest;

import com.regitiny.catiny.service.dto.MessageGroupDTO;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

@RequestMapping("/api/o/users")
public interface UserManagement
{
  /**
   * {@code POST  /message-groups} : Create a new messageGroup.
   * <p>
   * //@param userIds danh sách những user được thêm vào trong lúc tạo group
   *
   * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new messageGroupDTO, or with status {@code 400 (Bad Request)} if the messageGroup has already an ID.
   * @throws URISyntaxException if the Location URI syntax is incorrect.
   */
  @PostMapping("/message-groups/new-group")
  ResponseEntity<List<MessageGroupDTO>> searchUser() throws URISyntaxException;


  /**
   * POST /file/videos/upload/original-data : uploads an video or list video (original data)
   *
   * @param videoDatas video to upload (optional)
   * @return successfully uploaded (status code 200)
   * or Invalid videoData value (status code 405)
   */
  @ApiOperation(value = "uploads an video or list video (original data)",
    nickname = "uploadVideosOriginalData",
    notes = "",
    response = Object.class,
    authorizations = {@Authorization(value = "jwt")},
    tags = {"video",})
  @ApiResponses(value = {
    @ApiResponse(code = 200, message = "successfully uploaded", response = Object.class),
    @ApiResponse(code = 405, message = "Invalid videoData value")})
  @PostMapping(value = "/file/videos/upload/original-data",
    produces = {"application/json"},
    consumes = {"multipart/form-data"})
  default ResponseEntity<Object> uploadVideosOriginalData(@ApiParam(value = "video to upload") @Valid @RequestPart(value = "videoDatas", required = false) List<MultipartFile> videoDatas)
  {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
  }
}
