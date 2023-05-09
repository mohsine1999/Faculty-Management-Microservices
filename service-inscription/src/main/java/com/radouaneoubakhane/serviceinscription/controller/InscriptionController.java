package com.radouaneoubakhane.serviceinscription.controller;


import com.radouaneoubakhane.serviceinscription.dto.*;
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

    // ====================  GET REQUESTS ====================
    @GetMapping("/mst")
    @ResponseStatus(HttpStatus.OK)
    public List<InscriptionResponse> getAllInscriptions() {
        return inscriptionService.getAllInscriptionsMST();
    }
    @GetMapping("/deust")
    @ResponseStatus(HttpStatus.OK)
    public List<InscriptionResponseDEUST> getAllInscriptionsDEUST() {
        return inscriptionService.getAllInscriptionsDEUST();
    }
    @GetMapping("/lst")
    @ResponseStatus(HttpStatus.OK)
    public List<InscriptionResponseLST> getAllInscriptionsLST() {
        return inscriptionService.getAllInscriptionsLST();
    }



    // ====================  GET REQUESTS BY ID ====================
    @GetMapping("/{inscriptionId}")
    @ResponseStatus(HttpStatus.OK)
    public InscriptionResponse getInscriptionById(@PathVariable Long inscriptionId) {
        return inscriptionService.getInscriptionById(inscriptionId);
    }




    // ====================  POST REQUESTS ====================
    @PostMapping("/mst")
    @ResponseStatus(HttpStatus.CREATED)
    public InscriptionResponse createInscription(@RequestBody InscriptionRequest inscriptionRequest) {
        return inscriptionService.createInscription(inscriptionRequest);
    }
    @PostMapping("/deust")
    @ResponseStatus(HttpStatus.CREATED)
    public InscriptionResponseDEUST createInscriptionDEUST(@RequestBody InscriptionRequestDEUST inscriptionRequest) {
        return inscriptionService.createInscriptionDEUST(inscriptionRequest);
    }
    @PostMapping("/lst")
    @ResponseStatus(HttpStatus.CREATED)
    public InscriptionResponseLST createInscriptionLST(@RequestBody InscriptionRequestLST inscriptionRequest) {
        return inscriptionService.createInscriptionLST(inscriptionRequest);
    }



    // ====================  PUT REQUESTS ====================
    @PutMapping("/mst/{inscriptionId}")
    @ResponseStatus(HttpStatus.OK)
    public InscriptionResponse updateInscription(@PathVariable Long inscriptionId, @RequestBody InscriptionRequest inscriptionRequest) {
        return inscriptionService.updateInscription(inscriptionId, inscriptionRequest);
    }
    @PutMapping("/deust/{inscriptionId}")
    @ResponseStatus(HttpStatus.OK)
    public InscriptionResponseDEUST updateInscriptionDEUST(@PathVariable Long inscriptionId, @RequestBody InscriptionRequestDEUST inscriptionRequest) {
        return inscriptionService.updateInscriptionDEUST(inscriptionId, inscriptionRequest);
    }
    @PutMapping("/lst/{inscriptionId}")
    @ResponseStatus(HttpStatus.OK)
    public InscriptionResponseLST updateInscriptionLST(@PathVariable Long inscriptionId, @RequestBody InscriptionRequestLST inscriptionRequest) {
        return inscriptionService.updateInscriptionLST(inscriptionId, inscriptionRequest);
    }



    // ====================  DELETE REQUESTS ====================
    @DeleteMapping("/{inscriptionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInscription(@PathVariable Long inscriptionId) {
        inscriptionService.deleteInscription(inscriptionId);
    }



    // ====================  ACCEPT/REJECT/CANCEL REQUESTS ====================
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

    // ====================  ACCEPT/REJECT/CANCEL REQUESTS BY ID ====================
    @PostMapping("/{inscriptionId}/accept")
    @ResponseStatus(HttpStatus.OK)
    public void acceptInscription(@PathVariable Long inscriptionId) {
        inscriptionService.acceptInscription(inscriptionId);
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


    // ====================  ACCEPT/REJECT/CANCEL REQUESTS BY NUMBER ====================
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

}
