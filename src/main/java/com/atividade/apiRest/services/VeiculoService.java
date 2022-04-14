package com.atividade.apiRest.services;

import java.math.BigDecimal;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.atividade.apiRest.exception.JpaObjectRetrievalFailureException;
import com.atividade.apiRest.exception.ObjectNotFoundException;
import com.atividade.apiRest.models.Veiculo;
import com.atividade.apiRest.repositories.NewVeiculoRepository;
import com.atividade.apiRest.repositories.VeiculoRepository;

@Service
public class VeiculoService {

	final VeiculoRepository veiculoRepo;
	
	final NewVeiculoRepository newRepo;

	public VeiculoService(VeiculoRepository veiculoRepository,  NewVeiculoRepository newRepository) {
		this.newRepo = newRepository;
		this.veiculoRepo = veiculoRepository;
	}

	public Veiculo findById(Integer id) {
		Optional<Veiculo> veiculo = veiculoRepo.findById(id);
		return veiculo.orElseThrow(() -> new ObjectNotFoundException("ID Não possui nenhum cadastro vinculado !!"));
	}

	public Page<Veiculo> find(Pageable pageable) {
		return veiculoRepo.findAll(pageable);
	}

	public Veiculo save(Veiculo veiculo) {
		veiculo.setId(null);
		return veiculoRepo.save(veiculo);
	}

	public void delete(Integer id) {
		veiculoRepo.deleteById(id);
	}

	public Veiculo update(Integer id, Veiculo veiculo) {
		veiculo.setId(id);
		Veiculo oldObj = findById(id);

		oldObj = new Veiculo(veiculo);
		return veiculoRepo.save(oldObj);
	}
	
	public Page newFind(Integer idMarca, Integer idModelo, BigDecimal valorDe, BigDecimal valorAte, Pageable pageable) {
		return newRepo.newFind(idMarca, idModelo, valorDe, valorAte, pageable);
	}
	
	public Veiculo put(Veiculo veiculon) {
		
		try {
			veiculoRepo.save(veiculon);
			
		} catch (JpaObjectRetrievalFailureException e) {
			throw new JpaObjectRetrievalFailureException("Não foi possivel Alterar, pois o campo nome não existe !!");
		}
		return null;
	} 


//	@Transactional
//	public ResponseEntity<Object> save(Veiculo veiculo) {
//		var mc = new Veiculo();
//		BeanUtils.copyProperties(veiculo, mc);
//		veiculoRepo.save(mc);
//		return ResponseEntity.ok(true);
//	}

}
