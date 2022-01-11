package net.herit.ami.base.bases;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.herit.ami.commons.exception.job.CommonExceptionJobs;
import net.herit.ami.commons.exception.job.ExceptionJobs;

/**
 * SpringBootTest의 경우 필요한 의존성(@Autowired)을 여기에 선언해야 웹서버가 한번만 뜬다.
 * 하위 클래스에 선언시, 여러 개의 서버가 뜸.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
public class SpringBootBase extends BaseTest {

	@Autowired
	protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected CommonExceptionJobs commonExceptionJobs;
    
    @Autowired
    protected ExceptionJobs exceptionJobs;

}
