package com.regitiny.catiny.advance.controller.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/o/images")
public interface ImageManagement
{
  /**
   * POST /api/o/image/ : upload một hoặc nhiều ảnh cùng lúc
   * <p>
   * Todo : uplaod ảnh lên xong rồi trả ra tên ảnh link ảnh id ảnh (chỉ uuid)
   *
   * @param imageDatas  (list) dữ liệu của ảnh
   * @param desiredName tên mong muốn đặt cho bộ ảnh upload
   * @return (json) link ảnh , uuid
   */
  @PostMapping(value = "/upload",
    produces = {"application/json"},
    consumes = {"multipart/form-data"})
  default ResponseEntity<Object> uploadImages(@RequestPart List<MultipartFile> imageDatas, @RequestParam(required = false) String desiredName)
  {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
  }


  /**
   * GET /api/o/images/download/id/{uuid}
   * <p>
   * Todo : người dùng cung cấp id ảnh hoặc tên để tải ảnh về
   * Todo version-2 : có tùy chọn kích thước ,dung lượng ,chất lượng
   *
   * @param uuid      uuid của ảnh nếu có
   * @param imageName không có uuid của ảnh thì phải cung cấp tên ảnh
   * @param option    dữ liệu dạng json (độ phân giải , dung lượng , chất lượng)
   * @return dữ liệu file để người dùng tải ảnh về
   */
  @GetMapping("/download/id/{uuid}")
  default ResponseEntity<Object> downloadImages(@PathVariable(required = false) UUID uuid, @RequestParam(required = false) String imageName, @RequestParam(required = false) String option)
  {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
  }


  /**
   * GET /api/o/images/{uuid}
   * <p>
   * Todo : người dùng cung cấp id ảnh hoặc tên để lấy ảnh về  (đây là api người dùng load ảnh trực tiếp trên web)
   * người dùng cung cấp uuid nếu không thì phải cung cấp image name , nhớ trả ra cả dataType
   * Todo v-2 : có tùy chọn kích thước ,dung lượng ,chất lượng
   *
   * @param uuid      uuid của ảnh nếu có
   * @param imageName không có uuid của ảnh thì phải cung cấp tên ảnh
   * @param option    dữ liệu dạng json (độ phân giải , dung lượng , chất lượng)
   * @return dữ liệu file để người dùng tải ảnh về
   */
  @GetMapping("/id/{uuid}")
  default ResponseEntity<byte[]> getImage(@PathVariable(required = false) UUID uuid, @RequestParam(required = false) String imageName, @RequestParam(required = false) String option)
  {
    return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
  }
}
