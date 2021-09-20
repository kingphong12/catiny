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
 * BASE_PATH: {@value BASE_PATH}
 * <p>
 * video management api
 */
@RequestMapping(VideoManagement.BASE_PATH)
public interface VideoManagement
{
  String BASE_PATH = "/api/o/videos"; //NOSONAR


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
   * GET /api/o/videos/{uuidOrName}
   * <p>
   * người dùng cung cấp id ảnh hoặc tên để lấy ảnh về
   * Todo v-2 : có tùy chọn kích thước ,dung lượng ,chất lượng
   *
   * @param uuidOrName không có uuid của ảnh thì phải cung cấp tên video
   * @param download   (default : false) có download toàn bộ file video về không . mặc định = false là chỉ để tải 1 phần video để xem trực tiếp
   * @param option     dữ liệu dạng json (độ phân giải , dung lượng , chất lượng)
   * @return (dữ liệu 1 phần hoặc toàn bộ video)
   */
  @GetMapping("/{uuidOrName}")
  default ResponseEntity<ResourceRegion> fetchVideo(@PathVariable(required = false) String uuidOrName,
    @RequestParam(required = false, defaultValue = "false") Boolean download,
    @RequestParam(required = false) String option,
    @RequestHeader(value = "Range", required = false) String range)
  {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
  }


  @PostMapping("/livestream")
  ResponseEntity<Object> initVideoLivestream();


  @PostMapping("/call/_init")
  void initCall(@RequestParam List<UUID> userIds, @RequestParam String keyConnect);


  @PostMapping("/call/{key}/_answer")
  ResponseEntity<Object> answerIncomingCall(@RequestParam Boolean isAccepted, @PathVariable String key);


}
