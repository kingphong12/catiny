package com.regitiny.catiny.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

@GeneratedByJHipster
class EventTest {

  @Test
  void equalsVerifier() throws Exception {
    TestUtil.equalsVerifier(Event.class);
    Event event1 = new Event();
    event1.setId(1L);
    Event event2 = new Event();
    event2.setId(event1.getId());
    assertThat(event1).isEqualTo(event2);
    event2.setId(2L);
    assertThat(event1).isNotEqualTo(event2);
    event1.setId(null);
    assertThat(event1).isNotEqualTo(event2);
  }
}
