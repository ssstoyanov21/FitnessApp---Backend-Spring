package com.gym.gym.controller;

import com.gym.gym.dto.Requests.CreateClientRequest;
import com.gym.gym.dto.Requests.LoginRequest;
import com.gym.gym.dto.Requests.UpdateClientRequest;
import com.gym.gym.dto.Responses.*;

import com.gym.gym.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/client")
public class ClientController {
    private final ClientService clientService;
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateClientResponse> createClient(@RequestBody CreateClientRequest clientRequest) {
        CreateClientResponse response = clientService.createClient(clientRequest);
        if (response.isHasError()){
            return ResponseEntity.status(response.getStatusCode()).body(response);//ako ima greshka vurni mi status code
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();//201 uspeshno suzdaden obekt
    }

    @GetMapping("/all")
    public GetAllClientsResponse getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetClientResponse> getClientById(@PathVariable Long id) {
        GetClientResponse response = clientService.getClientById(id);
        if (response.isHasError()){
            return ResponseEntity.status(response.getStatusCode()).body(response);//ako ima greshka vurni mi status code
        }
        return ResponseEntity.ok(response);//vurni mi statuscode 200 s danite
    }
    @PutMapping("/update/{id}")//obrabotka nevalidni danni
    public ResponseEntity<BaseResponse> updateClient(@PathVariable Long id, @RequestBody UpdateClientRequest request) {
        BaseResponse response = clientService.updateClient(id, request);
        if (response.isHasError()){
            return ResponseEntity.status(response.getStatusCode()).body(response);//ako ima greshka vurni mi status code
        }
        return ResponseEntity.noContent().build();//204 uspeshen update
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BaseResponse> deleteClient(@PathVariable Long id) {
        BaseResponse response = clientService.deleteClient(id);
        if (response.isHasError()){
            return ResponseEntity.status(response.getStatusCode()).body(response);//ako ima greshka vurni mi status code
        }
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = clientService.login(request);
        if (response.isHasError()){
            return ResponseEntity.status(response.getStatusCode()).body(response);//ako ima greshka vurni mi status code
        }
        return ResponseEntity.ok(response);
    }
}
