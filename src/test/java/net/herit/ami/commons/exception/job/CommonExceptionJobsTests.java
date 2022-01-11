package net.herit.ami.commons.exception.job;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.herit.ami.base.bases.SpringBootBase;
import net.herit.ami.commons.exception.ExceptionType;

class CommonExceptionJobsTests extends SpringBootBase {
	
	@Test
	void commonExceptionJobTest() {
		boolean result = commonExceptionJobs.commonExceptionJob(ExceptionType.DEFAULT_EXCEPTION);
		assertTrue(result);
	}

}
