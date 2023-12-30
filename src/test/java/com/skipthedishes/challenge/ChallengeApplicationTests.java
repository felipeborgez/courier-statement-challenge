package com.skipthedishes.challenge;

import org.apache.http.util.Asserts;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChallengeApplicationTests {

	@Test
	void contextLoads() {
		Asserts.check(true, "teste");
	}

}
