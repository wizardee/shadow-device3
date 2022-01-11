package net.herit.ami.base.bases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import net.herit.ami.commons.logger.call.log.CallLogger;

/**
 * SpringBootTest의 경우 필요한 의존성(@Autowired)을 여기에 선언해야 웹서버가 한번만 뜬다.
 * 하위 클래스에 선언시, 여러 개의 서버가 뜸.
 */
@Transactional
@SpringBootTest
public class RepositoryBase extends BaseTest {
	
	@Autowired
	protected CallLogger callLogger;

}
