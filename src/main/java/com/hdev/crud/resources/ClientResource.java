package com.hdev.crud.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hdev.crud.dto.ClientDTO;
//import com.hdev.dscatalog.entities.Category; --antes do DTO
import com.hdev.crud.services.ClientService;

@RestController
@RequestMapping(value = "/clients")
public class ClientResource {
	
	@Autowired
	private ClientService service;
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAll(@RequestParam(value = "page", defaultValue = "0") Integer page,
	@RequestParam(value = "linesPerPage", defaultValue = "12") Integer size,
	@RequestParam(value = "direction", defaultValue = "ASC") String direction,
	@RequestParam(value = "orderBy", defaultValue = "name") String orderBy
	){
		
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);

		Page<ClientDTO> Page = service.findAllPaged(pageRequest);
		return ResponseEntity.ok().body(Page);
	}
	/* 
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll(){
		List<CategoryDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping (value = "/semente")
	public ResponseEntity<List<CategoryDTO>> semente(){
		List<CategoryDTO> list = new ArrayList<>();
		list.add (new CategoryDTO(1l,"Books"));
		list.add (new CategoryDTO(1l,"Eletronics"));
		return ResponseEntity.ok().body(list);
	}
*/
	@GetMapping (value = "/{id}")
	public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
		ClientDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<ClientDTO> insert(@RequestBody ClientDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto); 
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto); 
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClientDTO> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build(); //204 codigo http deu certo e no cropo da resposta esta vazio
	}

	

}
