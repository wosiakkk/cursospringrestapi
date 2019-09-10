package curso.api.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*Para que seja possível uma classe java aceitar métodos rest é necessário
 * marcar com a anotação @RestController*/

@RestController
@RequestMapping(value = "/usuario") //mapeando a url a ser interceptada
public class IndexController {
	
	/*A classe ResponseEntity é sempre usada para dar a resposta, par aela receber parametros
	 * utilizamos a anotação @RequestParam, informando o valor e o tipo. Caso o nome não
	 * seja informado na URL é possível definir um valor default com o atributo defaultValue.
	 * Também é possível setar o required do parâmetro com o atributo required */
	@GetMapping(value = "/", produces = "application/json") //o retorno sempre produz um json
	public ResponseEntity<String> init(@RequestParam(value = "nome", required = false, defaultValue = "Nome não foi informado") String nome,
			@RequestParam(value = "salario") String salario) {
		System.out.println("Parametro sendo recebido " + nome);
		return new ResponseEntity<String>("Olá Usuário REST Spring Boot seu nome é: "+nome+" e salário: "+ salario,HttpStatus.OK);
	}
	
}
