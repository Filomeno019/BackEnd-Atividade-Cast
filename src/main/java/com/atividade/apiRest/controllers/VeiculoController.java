package com.atividade.apiRest.controllers;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import com.atividade.apiRest.exception.JpaObjectRetrievalFailureException;
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

import com.atividade.apiRest.models.Veiculo;
import com.atividade.apiRest.repositories.VeiculoRepository;
import com.atividade.apiRest.services.VeiculoService;

@RestController
@RequestMapping(value = "/veiculo")
@CrossOrigin("*")
public class VeiculoController {

	@Autowired
	private final VeiculoService veiculoServ;
	
	@Autowired
	private final VeiculoRepository veiculoRepo;

	public VeiculoController(VeiculoService veiculoService, VeiculoRepository veiculoRepository) {
		this.veiculoRepo = veiculoRepository;
		this.veiculoServ = veiculoService;
	}

	@GetMapping("/{id}")
	public Optional<Veiculo> BuscaPorId(@PathVariable Integer id) {
		return Optional.ofNullable(veiculoServ.findById(id));
	}

	@PostMapping
	public ResponseEntity<Veiculo> criar(@RequestBody Veiculo veiculo) {
		Veiculo obj = veiculoServ.save(veiculo);
		return ResponseEntity.ok().body(obj);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Veiculo> Apagar(@PathVariable Integer id) {
		BuscaPorId(id);
		veiculoServ.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public Page<Veiculo> Buscar(Pageable pageable) {
		return veiculoServ.find(pageable);
	}

	@GetMapping("/findNew")
	public Page findByNewVeiculo(@RequestParam(value = "idMarca", required = false) Integer idMarca, 
			@RequestParam(value = "idModelo", required = false) Integer idModelo,
			@RequestParam(value = "valorDe", required = false) BigDecimal valorDe,
			@RequestParam(value = "valorAte", required = false) BigDecimal valorAte, Pageable pageable) {
		
		return veiculoServ.newFind(idMarca, idModelo, valorDe, valorAte, pageable);
	}
	
	@PutMapping
	public Veiculo atualizar(@Valid @RequestBody Veiculo veiculon) {
		return veiculoServ.put(veiculon);
	}  
	

//	@PutMapping("/{id}")
//	public Marca atualizar(@PathVariable Integer id, @Valid @RequestBody Marca marcan) {
//		return marcaServ.update(id, marcan);
//	}
//	@GetMapping
//	public ResponseEntity<List<Veiculo>> BuscarTudo() {
//		List<Veiculo> veiculos = veiculoRepo.findAll();
//		return ResponseEntity.ok().body(veiculos);
//	}

}
