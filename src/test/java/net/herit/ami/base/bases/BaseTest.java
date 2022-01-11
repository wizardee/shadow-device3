package net.herit.ami.base.bases;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.ActiveProfiles;

import net.herit.ami.commons.logger.context.LoggerContext;

@ActiveProfiles(value = {"local"})
public class BaseTest {
	
	@BeforeEach
	void setUp() {
		//LoggerContext.set();
	}

}
