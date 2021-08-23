package com.regitiny.catiny.advance.controller.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RequestMapping(ImageManagement.BASE_PATH)
public interface ImageManagement
{
  String BASE_PATH = "/api/o/images";
  String PATH_UUID = "/uuid/{uuid}";
  String PATH_NAME = "/name/{name}";


  /**
   * POST /api/o/images/upload : upload một hoặc nhiều ảnh cùng lúc
   * <p>
   * Todo : uplaod ảnh lên xong rồi trả ra tên ảnh link ảnh id ảnh (chỉ uuid)
   *
   * @param imageData   (list) dữ liệu của ảnh
   * @param desiredName tên mong muốn đặt cho bộ ảnh upload
   * @return (json) link ảnh , uuid
   */
  @PostMapping(value = "/upload",
    produces = {"application/json"},
    consumes = {"multipart/form-data"})
  default ResponseEntity<Object> uploadImages(HttpServletRequest httpServletRequest, @RequestPart List<MultipartFile> imageData, @RequestParam(required = false) String desiredName)
  {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
  }


  /**
   * GET /api/o/images/uuid/{uuid}
   * <p>
   * Todo : người dùng cung cấp id ảnh hoặc tên để lấy ảnh về  (đây là api người dùng load ảnh trực tiếp trên web)
   * nhớ trả ra cả dataType
   * Todo v-2 : có tùy chọn kích thước ,dung lượng ,chất lượng
   *
   * @param uuid   uuid của ảnh nếu có
   * @param option dữ liệu dạng json (độ phân giải , dung lượng , chất lượng)
   * @return dữ liệu file để người dùng tải ảnh về
   */
  @GetMapping(PATH_UUID)
  default ResponseEntity<byte[]> fetchImageByUuid(@PathVariable(required = false) UUID uuid, @RequestParam(required = false) String option)
  {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
  }


  /**
   * GET /api/o/images/name/{name}
   * <p>
   * Todo : người dùng cung cấp id ảnh hoặc tên để lấy ảnh về  (đây là api người dùng load ảnh trực tiếp trên web)
   * nhớ trả ra cả dataType
   * Todo v-2 : có tùy chọn kích thước ,dung lượng ,chất lượng
   *
   * @param name không có uuid của ảnh thì phải cung cấp tên ảnh
   * @param option    dữ liệu dạng json (độ phân giải , dung lượng , chất lượng)
   * @return dữ liệu file để người dùng tải ảnh về
   */
  @GetMapping(PATH_NAME)
  default ResponseEntity<byte[]> fetchImage(@PathVariable(required = false) String name, @RequestParam(required = false) String option)
  {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
  }
}