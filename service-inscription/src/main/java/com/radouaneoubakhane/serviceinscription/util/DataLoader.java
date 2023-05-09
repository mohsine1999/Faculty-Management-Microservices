package com.radouaneoubakhane.serviceinscription.util;


import com.radouaneoubakhane.serviceinscription.repository.InscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final InscriptionRepository inscriptionRepository;

    @Override
    public void run(String... args) {
        if (inscriptionRepository.count() < 1) {
            inscriptionRepository.save(InscriptionDataGenerator.generateInscription());
        }
    }
}
