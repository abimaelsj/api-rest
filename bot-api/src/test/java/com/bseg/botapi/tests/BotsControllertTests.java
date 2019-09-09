package com.bseg.botapi.tests;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bseg.botapi.model.Bots;
import com.bseg.botapi.resource.BotResource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
	
public class BotsControllertTests extends BotApiApplicationTests{
	
	private MockMvc mockMvc;
	
	@Autowired	
	private BotResource botsController;
		
	
	@Before	
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(botsController).build();
	}
	
	@Test
	public void testGETAllBotsController() {
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/bots/listar"))
				.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testGETSaveBotsController() {		
		Bots botsTestSave = new Bots("name");
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.post("/bots/cadastrar", botsTestSave))
				.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPutBotsController() {
		Bots botsTestPut = new Bots();
		botsTestPut.setId(10L);
		botsTestPut.setName("Teste Atualizar Bots");
		String requestJson = null;
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    
	    try {
			 	requestJson = ow.writeValueAsString(botsTestPut );
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
 		try {
			this.mockMvc.perform(MockMvcRequestBuilders.put("/bots/atualizar/" + botsTestPut.getId()))
					//.contentType(MediaType.APPLICATION_JSON_UTF8)
					//.content(requestJson))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}	
	}	
	
	@Test
	public void testGETIndexBotsController() {
		Bots botsTestIndex = new Bots();
		botsTestIndex.setId(10L);
		try {
			this.mockMvc.perform(MockMvcRequestBuilders.get("/bots/pesquisar/" + botsTestIndex.getId()))
				.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeletetBotsController() {
		Bots botsTestPut = new Bots("AtualizarBots");	
		botsTestPut.setId(10L);
 		try {
			this.mockMvc.perform(MockMvcRequestBuilders.delete("/bots/deletar/" + botsTestPut.getId()))
				.andExpect(MockMvcResultMatchers.status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}	
	}	
}	