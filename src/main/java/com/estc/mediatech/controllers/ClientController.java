package com.estc.mediatech.controllers;
import com.estc.mediatech.dto.ClientRequestDto;
import com.estc.mediatech.dto.ClientResponseDto;
import com.estc.mediatech.exception.EntityNotFoundException;
import com.estc.mediatech.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {

//////////INJECTION DU SERVICE DANS LE CONTROLLEUR
    private ClientService clientService;
///////////// CONTROLLEUR/////////////////////////////////////
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
///////////////////////////////////////////////////////////////////////////////
    @GetMapping("")
    public ResponseEntity<List<ClientResponseDto>> getClients() {
        return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ClientResponseDto> save( @RequestBody() ClientRequestDto clientRequestDto) {
        ClientResponseDto clientResponseDto = clientService.save(clientRequestDto);
        return new ResponseEntity<>(clientResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ClientResponseDto> findById(@PathVariable("id") Integer id) {
        ClientResponseDto clientResponseDto = clientService.findById(id);
        return ResponseEntity.ok(clientResponseDto);
    }

    @GetMapping("/nom/{nom}")
    public ResponseEntity<ClientResponseDto> findByNom(@PathVariable() String nom) {
        ClientResponseDto clientResponseDto = clientService.findByNom(nom);
        return ResponseEntity.ok(clientResponseDto);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable() Integer id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<ClientResponseDto> update( @RequestBody() ClientRequestDto clientRequestDto, @PathVariable() Integer id) throws EntityNotFoundException {
        ClientResponseDto clientResponseDto = clientService.update(clientRequestDto, id);
        return ResponseEntity.accepted().body(clientResponseDto);
    }
}
