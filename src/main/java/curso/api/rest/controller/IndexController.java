package curso.api.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	 * O pathvariable pega um dado na arquitetura rest, ou seja na URL*/
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
	
	
	
}
