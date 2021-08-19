package com.regitiny.catiny.advance.controller.rest;

import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * các api quản lý video
 */
@RequestMapping("/api/o/videos")
public interface VideoManagement
{
  String BASE_PATH = "/api/o/videos";
  String PATH_UUID = "/uuid/{uuid}";
  String PATH_NAME = "/name/{name}";


  /**
   * POST /api/o/videos/upload : upload một hoặc nhiều video cùng lúc
   *
   * @param videoData   (list) dữ liệu của video
   * @param desiredName tên mong muốn đặt cho bộ video upload
   * @return (json) link video , uuid
   */
  @PostMapping(value = "/upload",
    produces = {"application/json"},
    consumes = {"multipart/form-data"})
  default ResponseEntity<Object> uploadVideos(HttpServletRequest httpServletRequest, @RequestPart List<MultipartFile> videoData, @RequestParam(required = false) String desiredName)
  {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
  }


  /**
   * GET /api/o/videos/uuid/{uuid}
   * <p>
   * Todo : người dùng cung cấp id ảnh hoặc tên để lấy ảnh về  (đây là api người dùng load ảnh trực tiếp trên web)
   * nhớ trả ra cả dataType
   * Todo v-2 : có tùy chọn kích thước ,dung lượng ,chất lượng
   *
   * @param uuid     uuid của ảnh nếu có
   * @param download (default : false) có download toàn bộ file video về không . mặc định = false là chỉ để tải 1 phần video để xem trực tiếp
   * @param option   dữ liệu dạng json (độ phân giải , dung lượng , chất lượng)
   * @return (dữ liệu 1 phần hoặc toàn bộ video)
   */
  @GetMapping(PATH_UUID)
  default ResponseEntity<ResourceRegion> fetchVideoByUuid(@PathVariable(required = false) UUID uuid
    , @RequestParam(required = false, defaultValue = "false") Boolean download,
    @RequestParam(required = false) String option,
    @RequestHeader(value = "Range", required = false) String range)
  {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
  }


  /**
   * GET /api/o/videos/name/{name}
   * <p>
   * Todo : người dùng cung cấp id ảnh hoặc tên để lấy ảnh về  (đây là api người dùng load ảnh trực tiếp trên web)
   * nhớ trả ra cả dataType
   * Todo v-2 : có tùy chọn kích thước ,dung lượng ,chất lượng
   *
   * @param name     không có uuid của ảnh thì phải cung cấp tên ảnh
   * @param download (default : false) có download toàn bộ file video về không . mặc định = false là chỉ để tải 1 phần video để xem trực tiếp
   * @param option   dữ liệu dạng json (độ phân giải , dung lượng , chất lượng)
   * @return (dữ liệu 1 phần hoặc toàn bộ video)
   */
  @GetMapping(PATH_NAME)
  default ResponseEntity<ResourceRegion> fetchVideo(@PathVariable(required = false) String name,
    @RequestParam(required = false, defaultValue = "false") Boolean download,
    @RequestParam(required = false) String option,
    @RequestHeader(value = "Range", required = false) String range)
  {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
  }
}
