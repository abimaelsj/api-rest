package com.bseg.botapi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.bseg.botapi.model.Bots;
import com.bseg.botapi.respositroy.BotRepository;
import com.bseg.botapi.service.BotsServiceMonitoring;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;	


@Api(value = "Bots")
@RestController
@RequestMapping("/bots")
public class BotResource {

	@Autowired
	private BotRepository botsRepository;	
	@Autowired	
	private BotsServiceMonitoring serviceMonitoring;

	
	@ApiOperation(value= "Listar todos os Bots cadastrados")
	@SuppressWarnings("unchecked")
	@GetMapping("/listar")
	public List<Bots> listarBots() {
		return botsRepository.findAll();			
	}

	@ApiOperation(value= "Cadastra um novo Bot")
	@PostMapping("/cadastrar")
	public Bots inserirBots(@RequestBody @Valid  @ModelAttribute("bot") Bots bot){

		Bots botsSalvo = botsRepository.save(bot);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(botsSalvo.getId()).toUri();
		ResponseEntity.created(uri);
		return botsSalvo;
	}

	@ApiOperation(value= "Pesquisar um Bot por um id")
	@GetMapping("/pesquisar/{id}")	
	public Optional<Bots> obterBots (@PathVariable("id") Long id){				
		return  botsRepository.findById(id);
	}

	@ApiOperation(value= "Atualizar um Bot pelo id")
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Bots> atualizarbots(@PathVariable("id") Long id, @RequestBody @Valid Bots bots) {

		Optional<Bots> botsPesquisado = botsRepository.findById(id);

		if(!botsPesquisado.isPresent()){
			return ResponseEntity.notFound().build();			 
		}

		if(bots != null ) {
			try {
				BeanUtils.copyProperties(bots, botsPesquisado, "id");
				botsRepository.save(bots);	
				return ResponseEntity.ok().build();				
			}catch (Exception ex) {
				ex.getStackTrace();
			}
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value="Deleta um Bot pelo id")
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Bots> deletarBots(@PathVariable ("id") Long id){
		Optional<Bots> bot = botsRepository.findById(id);		
		if (bot != null && bot.get().getId() > 0) {
			botsRepository.deleteById(bot.get().getId());
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();	
	}	
	
	@ApiOperation(value= "Monitorar as requisições utilizando Circuit Braker")
	@GetMapping("/monitoring/{name}")
	public String TestMonitoring(@PathVariable String name) {		
		String monitor = serviceMonitoring.getRandomService();
		return monitor + name;		
	}	
}