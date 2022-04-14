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


import com.atividade.apiRest.models.Modelo;
import com.atividade.apiRest.repositories.ModeloRepository;
import com.atividade.apiRest.services.ModeloService;

@RestController
@RequestMapping(value = "/modelo")
@CrossOrigin("*")
public class ModeloController {

	@Autowired
	private final ModeloService modeloServ;

	@Autowired
	private ModeloRepository modeloRepo;

	public ModeloController(ModeloService modeloService, ModeloRepository modeloRepository) {
		this.modeloRepo = modeloRepository;
		this.modeloServ = modeloService;
	}

	@GetMapping
	public Page<Modelo> Buscar(Pageable pageable) {
		return modeloServ.find(pageable);
	}

	@GetMapping("/{id}")
	public Optional<Modelo> BuscaPorId(@PathVariable Integer id) {
		return Optional.ofNullable(modeloServ.findById(id));
	}

	@PostMapping
	public ResponseEntity<Modelo> Criar(@Valid @RequestBody Modelo modelo) {
		Modelo obj = modeloServ.save(modelo);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Modelo> Apagar(@PathVariable Integer id) {
		BuscaPorId(id);
		modeloServ.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/findNew")
	public Page BuscaIdMarcaComoParametro(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "nome", required = false) String nome, Pageable pageable) {
		return modeloServ.newFind(id, nome, pageable);
	}
	
//	@GetMapping("/findPattern")
//	public Page BuscaPadrao(@RequestParam(value = "id", required = false) Integer id,
//			@RequestParam(value = "nome", required = false) String nome, Pageable pageable) {
//		return modeloServ.findPattern(id, nome, pageable);
//	}
//	
	@PutMapping
	public Modelo atualizar(@Valid @RequestBody Modelo modelon) {
		return modeloRepo.save(modelon);
	} 
	
	@GetMapping("/findAll")
	public ResponseEntity<List<Modelo>> findAll() {
		List<Modelo> modelos = modeloRepo.findAll();
		return ResponseEntity.ok().body(modelos);
	}

//	@GetMapping
//	public ResponseEntity<List<Modelo>> BuscarTudo() {
//		List<Modelo> modelos = modeloRepo.findAll();
//		return ResponseEntity.ok().body(modelos);
//	}

}
