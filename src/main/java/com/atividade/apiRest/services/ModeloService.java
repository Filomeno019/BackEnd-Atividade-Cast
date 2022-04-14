package com.atividade.apiRest.services;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atividade.apiRest.exception.DataintegratyViolationException;
import com.atividade.apiRest.exception.ObjectNotFoundException;
import com.atividade.apiRest.models.Modelo;
import com.atividade.apiRest.repositories.ModeloRepository;
import com.atividade.apiRest.repositories.NewModeloRepository;

@Service
public class ModeloService {

	final ModeloRepository modeloRepo;
	
	final NewModeloRepository newRepo;

	public ModeloService(ModeloRepository modeloRepository, NewModeloRepository newRepository) {
		this.newRepo = newRepository;
		this.modeloRepo = modeloRepository;
	}

	public Modelo findById(Integer id) {
		Optional<Modelo> modelo = modeloRepo.findById(id);
		return modelo.orElseThrow(() -> new ObjectNotFoundException("ID Não possui nenhum cadastro vinculado !!"));
	}

	public Page<Modelo> find(Pageable pageable) {
		return modeloRepo.findAll(pageable);
	}

	private Modelo findByNome(Modelo modelo) {
		Modelo obj = modeloRepo.findByNome(modelo.getNome());
		if (obj != null) {
			return obj;
		}
		return null;
	}

	public Modelo save(Modelo modelo) {
		if (findByNome(modelo) != null) {
			throw new DataintegratyViolationException("MODELO já possui cadastro !!");
		}
		modelo.setId(null);
		return modeloRepo.save(modelo);
	}

	public void delete(Integer id) {
		try {
			modeloRepo.deleteById(id);
		} catch(DataIntegrityViolationException d) {
			throw new DataintegratyViolationException("Não foi possivel deletar, pois possui Veiculos associados !!");
		    
		} 
	}
	
	public Page newFind(Integer id, String nome, Pageable pageable) {
		return newRepo.newFind(id, nome, pageable);
	}
	
	public Page findPattern(Integer id, String nome, Pageable pageable) {
		return newRepo.findPattern(id, nome, pageable);
	}
	
	
	 
//	@Transactional
//	public ResponseEntity<Object> save(Modelo modelo) {
//		var mc = new Modelo();
//		BeanUtils.copyProperties(modelo, mc);
//		modeloRepo.save(mc);
//		return ResponseEntity.ok(true);
//	}
//	public Modelo update(Integer id, Modelo modelo) {
//		modelo.setId(id);
//		Modelo oldObj = findById(id);
//
//		oldObj = new Modelo(modelo);
//		return modeloRepo.save(oldObj);
//	}

}
