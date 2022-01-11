package net.herit.ami.base.bases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class MockitoBase extends BaseTest {

	protected MockMvc mockMvc;
    protected static ObjectMapper objectMapper;
    
    @BeforeEach
    public void setUp() {
    	objectMapper = new ObjectMapper();
    }
}
