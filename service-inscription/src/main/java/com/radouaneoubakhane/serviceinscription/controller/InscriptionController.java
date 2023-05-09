package com.radouaneoubakhane.serviceinscription.controller;


import com.radouaneoubakhane.serviceinscription.dto.InscriptionRequest;
import com.radouaneoubakhane.serviceinscription.dto.InscriptionResponse;
import com.radouaneoubakhane.serviceinscription.service.InscriptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inscription")
@RequiredArgsConstructor
@Slf4j
public class InscriptionController {

    private final InscriptionService inscriptionService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InscriptionResponse> getAllInscriptions() {
        return inscriptionService.getAllInscriptions();
    }

    @GetMapping("/{inscriptionId}")
    @ResponseStatus(HttpStatus.OK)
    public InscriptionResponse getInscriptionById(@PathVariable Long inscriptionId) {
        return inscriptionService.getInscriptionById(inscriptionId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InscriptionResponse createInscription(@RequestBody InscriptionRequest inscriptionRequest) {
        return inscriptionService.createInscription(inscriptionRequest);
    }

    @PutMapping("/{inscriptionId}")
    @ResponseStatus(HttpStatus.OK)
    public InscriptionResponse updateInscription(@PathVariable Long inscriptionId, @RequestBody InscriptionRequest inscriptionRequest) {
        return inscriptionService.updateInscription(inscriptionId, inscriptionRequest);
    }

    @DeleteMapping("/{inscriptionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInscription(@PathVariable Long inscriptionId) {
        inscriptionService.deleteInscription(inscriptionId);
    }

    @GetMapping("/accept")
    @ResponseStatus(HttpStatus.OK)
    public List<InscriptionResponse> getAllAcceptedInscriptions() {
        return inscriptionService.getAllAcceptedInscriptions();
    }

    @GetMapping("/reject")
    @ResponseStatus(HttpStatus.OK)
    public List<InscriptionResponse> getAllRejectedInscriptions() {
        return inscriptionService.getAllRejectedInscriptions();
    }

    @GetMapping("/cancel")
    @ResponseStatus(HttpStatus.OK)
    public List<InscriptionResponse> getAllCanceledInscriptions() {
        return inscriptionService.getAllCanceledInscriptions();
    }


    @PostMapping("/{inscriptionId}/accept")
    @ResponseStatus(HttpStatus.OK)
    public void acceptInscription(@PathVariable Long inscriptionId) {
        inscriptionService.acceptInscription(inscriptionId);
    }


    // select the first 10 inscriptions
    @PostMapping("/deust/accept/{number}")
    @ResponseStatus(HttpStatus.OK)
    public void acceptInscriptionDEUST(@PathVariable Integer number) {
        inscriptionService.acceptInscriptionDEUST(number);
    }

    @PostMapping("/lst/accept/{number}")
    @ResponseStatus(HttpStatus.OK)
    public void acceptInscriptionLST(@PathVariable Integer number) {
        inscriptionService.acceptInscriptionLST(number);
    }

    @PostMapping("/mst/accept/{number}")
    @ResponseStatus(HttpStatus.OK)
    public void acceptInscriptionMST(@PathVariable Integer number) {
        inscriptionService.acceptInscriptionMST(number);
    }



    @PostMapping("/{inscriptionId}/reject")
    @ResponseStatus(HttpStatus.OK)
    public InscriptionResponse rejectInscription(@PathVariable Long inscriptionId) {
        return inscriptionService.rejectInscription(inscriptionId);
    }

    @PostMapping("/{inscriptionId}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public InscriptionResponse cancelInscription(@PathVariable Long inscriptionId) {
        return inscriptionService.cancelInscription(inscriptionId);
    }
}
