package com.atividade.apiRest.services;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.atividade.apiRest.exception.DataintegratyViolationException;
import com.atividade.apiRest.exception.ObjectNotFoundException;
import com.atividade.apiRest.models.Marca;
import com.atividade.apiRest.repositories.MarcaRepository;
import com.atividade.apiRest.repositories.NewMarcaRepository;

@Service
public class MarcaService {

	final MarcaRepository marcaRepo;
	
	final NewMarcaRepository newRepo;

	public MarcaService(MarcaRepository marcaRepository,  NewMarcaRepository newRepository) {
		this.newRepo = newRepository;
		this.marcaRepo = marcaRepository;
	}

//	public Page<Marca> find(Pageable pageable) {
//		return marcaRepo.findAll(pageable);
//	}

	public Marca findById(Integer id) {
		Optional<Marca> marca = marcaRepo.findById(id);
		return marca.orElseThrow(() -> new ObjectNotFoundException("ID Não possui nenhum cadastro vinculado !!"));
	}
	
	public Page<Marca> find(Pageable pageable) {
		return marcaRepo.findAll(pageable);
	}

	private Marca findByNome(Marca marca) {
		Marca obj = marcaRepo.findByNome(marca.getNome());
		if (obj != null) {
			return obj;
		}
		return null;
	}

	public Marca save(Marca marca) {
		if (findByNome(marca) != null) {
			throw new DataintegratyViolationException("Essa marca já possui cadastro");
		}
		marca.setId(null);
		return marcaRepo.save(marca);
	}

	public void delete(Integer id) {
		try {
			marcaRepo.deleteById(id);
		} catch(DataIntegrityViolationException d) {
			throw new DataintegratyViolationException("Não foi possivel deletar, pois possui Modelos associados");
		    
		} 
	}

//	public Marca update(Integer id, Marca marca) {
//		marca.setId(id);
//		Marca oldObj = findById(id);
//
//		oldObj = new Marca(marca);
//		return marcaRepo.save(oldObj);
//	}
//	
	public Page newFind(Integer id, String nome, Pageable pageable) {
		return newRepo.newFind(id, nome, pageable);
	}
	
	public Marca update(@PathVariable Integer id, @RequestBody Marca marcan) {
		Marca marca = marcaRepo.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("ID não associado a uma MARCA existente !! "));
//		if (findByNome(marcan) != null && findByNome(marcan).getId() != id) {
//			throw new DataintegratyViolationException(
//					"Não foi possível atualizar os dados, pois a MARCA já possui cadastro !!");
//		}
		if (marcan.getNome() != null) {
			marcan.setNome(marcan.getNome());
		}
		return marcaRepo.save(marca);
	}

//	@Transactional
//	public ResponseEntity<Object> save(Marca marca) {
//		var mc = new Marca();
//		BeanUtils.copyProperties(marca, mc);
//		marcaRepo.save(mc);
//		return ResponseEntity.ok(true);
//	}
//	public Marca update(@PathVariable Integer id, @Valid @RequestBody Marca marcan) {
//		Marca marca = marcaRepo.findById(id)
//				.orElseThrow(() -> new ObjectNotFoundException("ID não associado a uma MARCA existente !! "));
////		if (findByNome(marcan) != null && findByNome(marcan).getId() != id) {
////			throw new DataintegratyViolationException(
////					"Não foi possível atualizar os dados, pois a MARCA já possui cadastro !!");
////		}
//		if (marca.getNome() != null) {
//			marca.setNome(marca.getNome());
//		}
////		if (clienten.getDataDeNascimento() != null) {
////			cliente.setDataDeNascimento(clienten.getDataDeNascimento());
////		}
////		if (clienten.getRg() != null) {
////			cliente.setRg(clienten.getRg());
////		}
////		if (clienten.getCpf() != null) {
////			cliente.setCpf(clienten.getCpf());
////		}
////		if (clienten.getTelefone() != null) {
////			cliente.setTelefone(clienten.getTelefone());
////		}
//		return marcaRepo.save(marca);
//	}

}
