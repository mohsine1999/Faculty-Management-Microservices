package com.student.StudentManagement;

import com.student.StudentManagement.enumurations.Diplomat;
import com.student.StudentManagement.model.Filiere;
import com.student.StudentManagement.model.ModuleF;
import com.student.StudentManagement.repository.FilierRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableDiscoveryClient
public class StudentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	@Bean
	CommandLineRunner start(FilierRepository filierRepository) {
		return args -> {

			Filiere filiere1 = Filiere.builder()
					.id(1L)
					.name("MIPC")
					.diplomat(Diplomat.DEUST)
					.build();
			filierRepository.save(filiere1);

			Filiere filiere2= Filiere.builder()
					.id(2L)
					.name("BCG")
					.diplomat(Diplomat.DEUST)
					.build();
			filierRepository.save(filiere2);

			Filiere filiere3 = Filiere.builder()
					.id(3L)
					.name("GE-GM")
					.diplomat(Diplomat.DEUST)
					.build();
			filierRepository.save(filiere3);

			/******************************************************/
			Filiere filiere4 = Filiere.builder()
					.id(4L)
					.name("Génie Mathématiques")
					.diplomat(Diplomat.LST)
					.build();
			filierRepository.save(filiere4);

			Filiere filiere5= Filiere.builder()
					.id(5L)
					.name("Ingénierie Electronique Et Télécommunication")
					.diplomat(Diplomat.LST)
					.build();
			filierRepository.save(filiere5);

			Filiere filiere6 = Filiere.builder()
					.id(6L)
					.name("Ingénierie Informatique Electonique et Automatique")
					.diplomat(Diplomat.LST)
					.build();
			filierRepository.save(filiere6);

			Filiere filiere7 = Filiere.builder()
					.id(7L)
					.name("Génie Mécaniques")
					.diplomat(Diplomat.LST)
					.build();
			filierRepository.save(filiere7);

			Filiere filiere8= Filiere.builder()
					.id(8L)
					.name("Protection de l'environnement")
					.diplomat(Diplomat.LST)
					.build();
			filierRepository.save(filiere8);

			Filiere filiere9 = Filiere.builder()
					.id(9L)
					.name("Informatique")
					.diplomat(Diplomat.LST)
					.build();
			filierRepository.save(filiere9);

			Filiere filiere10= Filiere.builder()
					.id(10L)
					.name("Géomatique et Aménagement du Territoire")
					.diplomat(Diplomat.LST)
					.build();
			filierRepository.save(filiere10);

			Filiere filiere11= Filiere.builder()
					.id(11L)
					.name("Sciences Biologiques Appliquées Biomédicale")
					.diplomat(Diplomat.LST)
					.build();
			filierRepository.save(filiere11);

			Filiere filiere12 = Filiere.builder()
					.id(12L)
					.name("Technologie et Qualité des Produits Agroalimentaires")
					.diplomat(Diplomat.LST)
					.build();
			filierRepository.save(filiere12);

			Filiere filiere13 = Filiere.builder()
					.id(13L)
					.name("Génie Chimie Des Matériaux")
					.diplomat(Diplomat.LST)
					.build();
			filierRepository.save(filiere13);

			Filiere filiere14= Filiere.builder()
					.id(14L)
					.name("Techniques d'Analyses et Controle Qualitées ")
					.diplomat(Diplomat.LST)
					.build();
			filierRepository.save(filiere14);
			/*********************************************/
			Filiere filiere15 = Filiere.builder()
					.id(15L)
					.name("International Sciences et Techniques en Neurosciences et Biotechnologie")
					.diplomat(Diplomat.MST)
					.build();
			filierRepository.save(filiere15);

			Filiere filiere16= Filiere.builder()
					.id(16L)
					.name("Informatiques Décisionnelle")
					.diplomat(Diplomat.MST)
					.build();
			filierRepository.save(filiere16);

			Filiere filiere17 = Filiere.builder()
					.id(17L)
					.name("Génie Mathématique et Applications")
					.diplomat(Diplomat.MST)
					.build();
			filierRepository.save(filiere17);

			Filiere filiere18 = Filiere.builder()
					.id(18L)
					.name("Géomatique Environnementale")
					.diplomat(Diplomat.MST)
					.build();
			filierRepository.save(filiere18);

			Filiere filiere19= Filiere.builder()
					.id(19L)
					.name("Techniques d’Analyse et Contrôle Qualité Industriel ")
					.diplomat(Diplomat.MST)
					.build();
			filierRepository.save(filiere19);

			Filiere filiere20 = Filiere.builder()
					.id(20L)
					.name("Santé et Environnement")
					.diplomat(Diplomat.MST)
					.build();
			filierRepository.save(filiere20);


			Filiere filiere21 = Filiere.builder()
					.id(21L)
					.name("Analyse Mathématique Avancée ")
					.diplomat(Diplomat.MST)
					.build();
			filierRepository.save(filiere21);

			Filiere filiere22= Filiere.builder()
					.id(22L)
					.name("Ingénierie des Matériaux: Option : Matériaux Organique, Polymère et Formulation ")
					.diplomat(Diplomat.MST)
					.build();
			filierRepository.save(filiere22);

			Filiere filiere23 = Filiere.builder()
					.id(23L)
					.name("Ingénierie des Matériaux Option: Matériaux Inorganiques, Physico-chimie et Analyse des Matériaux")
					.diplomat(Diplomat.MST)
					.build();
			filierRepository.save(filiere23);
			/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/





		};
	}
}
