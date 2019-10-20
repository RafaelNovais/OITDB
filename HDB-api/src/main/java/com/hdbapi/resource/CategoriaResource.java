package com.hdbapi.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hdbapi.model.Categoria;
import com.hdbapi.repository.CategoriaRepository;

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
	
	@PostMapping
	
	public ResponseEntity<Categoria>  Incluir(@RequestBody Categoria categoria, HttpServletResponse responde) {
		
		Categoria categoriaSalva = categoriaRepository.save(categoria);	
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(categoriaSalva.getCodigo()).toUri();
		responde.setHeader("Location", uri.toASCIIString());
		
		return ResponseEntity.created(uri).body(categoriaSalva);
		 
	}
	
	@GetMapping("/{codigo}")
	public Optional<Categoria> buscarCategoria(@PathVariable Long codigo) {
		
		return categoriaRepository.findById(codigo);
		
		
	}
	
	

}