package com.bseg.botapi.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.bseg.botapi.BotApiApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BotApiApplication.class)
@TestPropertySource(locations="classpath:application.properties")
public class BotApiApplicationTests {

	@Test
	public void contextLoads() {		
		
	}
}