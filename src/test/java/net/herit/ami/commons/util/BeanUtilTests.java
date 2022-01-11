package net.herit.ami.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import net.herit.ami.base.bases.SpringBootBase;

class BeanUtilTests extends SpringBootBase {
	
	@Test
	@DisplayName("bean 획득 테스트")
	void getBeanTest() {
		Object obj = BeanUtil.getBean("mockMvc");
		assertNotNull(obj);
		assertEquals(obj, mockMvc);
	}	

}
