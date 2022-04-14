package com.atividade.apiRest.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atividade.apiRest.models.Marca;
import com.atividade.apiRest.repositories.MarcaRepository;
import com.atividade.apiRest.services.MarcaService;

@RestController
@RequestMapping(value = "/marca")
@CrossOrigin("*")
public class MarcaController {

	@Autowired
	private final MarcaService marcaServ;

	@Autowired
	private MarcaRepository marcaRepo;

	public MarcaController(MarcaService marcaService, MarcaRepository marcaRepository) {
		this.marcaServ = marcaService;
		this.marcaRepo = marcaRepository;
	}

	@GetMapping
	public Page<Marca> Buscar(Pageable pageable) {
		return marcaServ.find(pageable);
	}

	@GetMapping("/{id}")
	public Optional<Marca> BuscaPorId(@PathVariable Integer id) {
		return Optional.ofNullable(marcaServ.findById(id));
	}

	@PostMapping
	public ResponseEntity<Marca> Criar(@ Valid @RequestBody Marca marca) {
		Marca obj = marcaServ.save(marca);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Marca> Apagar(@PathVariable Integer id) {
		BuscaPorId(id);
		marcaServ.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/findNew")
	public Page findByNewMarca(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "nome", required = false) String nome, Pageable pageable) {
		return marcaServ.newFind(id, nome, pageable);
	}
	
	@PutMapping
	public Marca atualizar(@Valid @RequestBody Marca marca) {
		return marcaRepo.save(marca);
	}  
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Marca>> findAll() {
		List<Marca> marcas = marcaRepo.findAll();
		return ResponseEntity.ok().body(marcas);
	}
//	@PutMapping("/{id}")
//	public Marca atualizar(@PathVariable Integer id, @RequestBody Marca marcan) {
//		return marcaRepo.save(id, marcan);
//	} 

//	@GetMapping
//	public ResponseEntity<List<Marca>> BuscarTudo() {
//		List<Marca> marcas = marcaRepo.findAll();
//		return ResponseEntity.ok().body(marcas);
//	}

}
