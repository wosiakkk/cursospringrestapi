package curso.api.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Usuario;
import curso.api.rest.repository.UsuarioRepository;

/*Para que seja possível uma classe java aceitar métodos rest é necessário
 * marcar com a anotação @RestController*/

@RestController
@RequestMapping(value = "/usuario") //mapeando a url a ser interceptada
public class IndexController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	/*A classe ResponseEntity é sempre usada para dar a resposta, par aela receber parametros
	 * utilizamos a anotação @RequestParam, informando o valor e o tipo. Caso o nome não
	 * seja informado na URL é possível definir um valor default com o atributo defaultValue.
	 * Também é possível setar o required do parâmetro com o atributo required.
	 * Os pring boot retorna dados para copor da página, em formato json, que pode ser interpretado
	 * por qualquer framework javascript .
	 * O pathvariable pega um dado na arquitetura rest, ou seja na URL.
	 * O método GET é sempre usado apra consulta de dados*/
	@GetMapping(value = "/{id}", produces = "application/json") //o retorno sempre produz um json
	public ResponseEntity<Usuario> init(@PathVariable(value = "id") Long id) {
		
		Optional<Usuario> usuario =  usuarioRepository.findById(id);
		
		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Usuario>> usuario(){
		
		List<Usuario> list = (List<Usuario>)usuarioRepository.findAll();
		
		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
	}
	
	/*O retorno seria um relatório(exemplo)*/
	@GetMapping(value = "/{id}/codigovenda/{venda}", produces = "application/json") //o retorno sempre produz um json
	public ResponseEntity<Usuario> relatorio(@PathVariable(value = "id") Long id,
											@PathVariable(value = "venda") Long venda) {
		
		Optional<Usuario> usuario =  usuarioRepository.findById(id);
		
		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}
	
	/*O método POST é sempre utilizado para gravar dados.
	 * Apesar do mapeamento da url ser o mesmo da consulta de usuário, os métodos http
	 * são diferentes, um sendo interceptado no get e outro pelo post.
	 * O request body converte o json enviado por post em um objeto usuário, desde que
	 * todos os atributos do json e do usuário sejam iguais em quantidade e nome.*/
	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario){
		//recebendo o usuário que será salvo
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		//dando o retorno e informando ostatus da requisição
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}
	
	/*O metodo PUT é utulizada para atualizar dados.*/
	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario){
		/*o método save, quando não recebe um id identifica queé apra salvar
		 * um novo objeto, entretando quando recebe ele compreende que irá fazer 
		 * um update. Por isso sempre que for realizado um put,
		 * o id deve ser enviado, pelo corpo do json ou pela url e setando no objeto
		 * posteriormente*/
		Usuario usuarioAtualizado = usuarioRepository.save(usuario);
		//dando o retorno e informando ostatus da requisição
		return new ResponseEntity<Usuario>(usuarioAtualizado, HttpStatus.OK);
	}
	
	/*O método DELETE é utilizado apra remover objetos do bd*/
	@DeleteMapping(value = "/{id}", produces = "application/text")
	public String remover(@PathVariable(value = "id") Long id) {
		
		usuarioRepository.deleteById(id);
		
		return "ok";
	}
}
