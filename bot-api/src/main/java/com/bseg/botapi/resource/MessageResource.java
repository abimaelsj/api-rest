package com.bseg.botapi.resource;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bseg.botapi.model.Messages;
import com.bseg.botapi.respositroy.MessageRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;	

@Api
@RestController
@RequestMapping("/messages")
public class MessageResource {
 
	@Autowired
	private MessageRepository messageRepositroy;

	@ApiOperation(value= "Cadastar uma nova mensagem")
	@PostMapping("/inserir")
	public ResponseEntity<Messages> inserirConversa(@RequestBody @Valid Messages mensagem){
		if(mensagem != null) {
			try {
				Messages mensagenSalva = messageRepositroy.save(mensagem);
				return ResponseEntity.ok(mensagenSalva);

			}catch (Exception e) {
				e.getStackTrace();			
			}
		}
		return ResponseEntity.badRequest().build();
	}	

	@ApiOperation(value= "Pesquisr uma mensagem pelo id")
	@GetMapping("/pesquisar/{id}")
	public ResponseEntity<Messages> pesquisarConversa(@PathVariable Long id){
		
		Object mensangemPesquisada = messageRepositroy.findById(id);		
		return mensangemPesquisada != null ? ResponseEntity.ok((Messages) mensangemPesquisada) : ResponseEntity.notFound().build();		
	}

	@ApiOperation(value= "Pesquisar todas as mensagens de uma conversa pelo id")
	@GetMapping("pesquisartodas/{id}")
	public ResponseEntity<Messages> pesquisarTodasMensagens(@PathVariable Long id){
		List<Messages> mensangemsPesquisadas = messageRepositroy.findAllById(id);
		return mensangemsPesquisadas != null ?  ResponseEntity.ok((Messages) mensangemsPesquisadas) : ResponseEntity.notFound().build();
	}

}