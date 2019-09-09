package com.bseg.botapi.tests;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bseg.botapi.model.Messages;
import com.bseg.botapi.resource.MessageResource;
import com.fasterxml.jackson.databind.ObjectMapper;	

public class MessagesControllerTest extends BotApiApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private MessageResource messagesController;

	@Before		
	public void setUp(){
		this.mockMvc = MockMvcBuilders.standaloneSetup(messagesController).build();		
	}	

	@Test	
	public void testGETSaveMessagesController(){
		Messages messageTestSave = new Messages();
	//	messageTestSave.setConversationId(100F);
		messageTestSave.setFrom(10F);
		messageTestSave.setTo(1F);
		messageTestSave.setText("Teste para gravar mensagem");
		
		Date dataTimeStamp = new Date();		
		Timestamp time = new Timestamp(dataTimeStamp.getTime());
		
		messageTestSave.setTimesTamp(new Date(dataTimeStamp.getTime()));
		
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.post("/messages/inserir" , messageTestSave))
			.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGETIndexMessageController() {
		Messages messageTestGetIndex = new Messages();
			messageTestGetIndex.setId(1L);
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/messages/pesquisar/" + messageTestGetIndex.getId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testGETAllMessagescontroller() {
		Messages messageTestGetAll = new Messages();
			messageTestGetAll.setId(2L);
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/messages/pesquisartodas/" + messageTestGetAll.getId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}