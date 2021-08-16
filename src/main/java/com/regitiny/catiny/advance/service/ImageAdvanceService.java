package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.ImageAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.ImageAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.ImageAdvanceMapper;
import com.regitiny.catiny.domain.Image;
import com.regitiny.catiny.service.ImageQueryService;
import com.regitiny.catiny.service.ImageService;
import com.regitiny.catiny.service.dto.ImageDTO;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import org.springframework.web.multipart.MultipartFile;

/**
 * AdvanceService layer for {@link Image}.
 *
 * @see ImageService is base service generate by jhipster
 */
public interface ImageAdvanceService extends BaseSrvice<Image, ImageService, ImageQueryService, ImageAdvanceMapper, ImageAdvanceRepository, ImageAdvanceSearch>
{
  /**
   * Todo : lưu từng ảnh vào database -> mỗi ảnh tạo thêm một FileInfo để lưu thông tin file ảnh ở ổ đĩa (path ,name.+jpg/png/... , ...)
   *
   * @param imageData
   * @param desiredName
   * @return
   */
  Option<ImageDTO> upload(MultipartFile imageData, String desiredName);


  /**
   * Todo : lấy ảnh ra theo uuid(String) . nếu không phải uuid thì là tên , throw exception nếu uuidOrName là "" hoặc null
   * <p>
   * Tuple2 là trả về 2 biến cùng 1 lúc _1:chi tiết ảnh , _2:dữ liệu ảnh
   *
   * @param uuidOrName
   * @return
   */
  Tuple2<ImageDTO, Byte[]> fetchImage(String uuidOrName);
}
