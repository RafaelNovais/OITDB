package com.hdbapi.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hdbapi.event.RecursoCriadoEvent;
import com.hdbapi.model.Categoria;
import com.hdbapi.repository.CategoriaRepository;
import com.hdbapi.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaResource {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<?> listar() {
		
		List<Categoria> categorias = categoriaRepository.findAll();
		
		return !categorias.isEmpty() ? ResponseEntity.ok(categorias) : ResponseEntity.noContent().build() ;
		
	}
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private CategoriaService categoriaService;
	 	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Categoria>  Incluir(@Valid @RequestBody Categoria categoria, HttpServletResponse responde) {
		
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, responde, categoriaSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
		 
	}
	
	@GetMapping("/{codigo}")
	public Optional<Categoria> buscarCategoria(@PathVariable Long codigo) {
		
		return categoriaRepository.findById(codigo);
		
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletarCategoria(@PathVariable Long codigo){
		
		categoriaRepository.deleteById(codigo);	
		 
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria) {
		

			Categoria categoriaAtualizada = categoriaService.atualizarCategoria(codigo, categoria);
			return ResponseEntity.ok(categoriaAtualizada);
			
			

	}
	
	

}
