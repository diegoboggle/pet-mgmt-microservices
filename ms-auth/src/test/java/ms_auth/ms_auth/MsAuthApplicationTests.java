package ms_auth.ms_auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
		"debug=false",
		"eureka.client.enabled=false",
		"logging.level.org.springframework=INFO",
		"logging.level.org.hibernate.SQL=OFF"
})
class MsAuthApplicationTests {

	@Test
	void contextLoads() {
	}

}
