package net.herit.ami.commons.exception.job;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.herit.ami.base.bases.SpringBootBase;

class ExceptionJobsTests extends SpringBootBase {

	@Test
	void exceptionJobTest() {
		boolean result = exceptionJobs.exceptionJob(new NullPointerException());
		assertTrue(result);
	}
}
