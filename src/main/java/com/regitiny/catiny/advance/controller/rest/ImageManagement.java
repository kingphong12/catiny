package com.regitiny.catiny.advance.controller.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping(ImageManagement.BASE_PATH)
public interface ImageManagement
{
  String BASE_PATH = "/api/o/images";


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
   * GET /api/o/images/{uuidOrName}
   * <p>
   * người dùng cung cấp id ảnh hoặc tên để lấy ảnh về
   * Todo v-2 : có tùy chọn kích thước ,dung lượng ,chất lượng
   *
   * @param uuidOrName không có uuid của ảnh thì phải cung cấp tên ảnh
   * @param option     dữ liệu dạng json (độ phân giải , dung lượng , chất lượng)
   * @param byUuid     true(path lấy theo uuid), false(path lấy theo name)
   * @return dữ liệu file để người dùng tải ảnh về
   */
  @GetMapping("/{uuidOrName}")
  default ResponseEntity<byte[]> fetchImage(@PathVariable(required = false) String uuidOrName,
    @RequestParam(required = false) String option,
    @RequestParam(required = false) Boolean byUuid)
  {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
  }
}
