package com.regitiny.catiny.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class VideoDTOTest {

  @Test
  void dtoEqualsVerifier() throws Exception {
    TestUtil.equalsVerifier(VideoDTO.class);
    VideoDTO videoDTO1 = new VideoDTO();
    videoDTO1.setId(1L);
    VideoDTO videoDTO2 = new VideoDTO();
    assertThat(videoDTO1).isNotEqualTo(videoDTO2);
    videoDTO2.setId(videoDTO1.getId());
    assertThat(videoDTO1).isEqualTo(videoDTO2);
    videoDTO2.setId(2L);
    assertThat(videoDTO1).isNotEqualTo(videoDTO2);
    videoDTO1.setId(null);
    assertThat(videoDTO1).isNotEqualTo(videoDTO2);
  }
}
