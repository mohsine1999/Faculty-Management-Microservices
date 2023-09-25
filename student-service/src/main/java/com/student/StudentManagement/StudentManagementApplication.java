package com.student.StudentManagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.student.StudentManagement.config.RsakeysConfig;
import com.student.StudentManagement.enumurations.Diplomat;
import com.student.StudentManagement.model.Filiere;
import com.student.StudentManagement.model.ModuleF;
import com.student.StudentManagement.model.StudentPojo;
import com.student.StudentManagement.repository.FilierRepository;
import com.student.StudentManagement.repository.ModuleFRepository;
import com.student.StudentManagement.services.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigurationProperties(RsakeysConfig.class)
public class StudentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

	//@Bean
	CommandLineRunner start(FilierRepository filierRepository, ModuleFRepository moduleFRepository, StudentService service) {
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
			/*:::::::::::::: Les modules :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/






			String[] modules = {
					"Langues et communication 1",
					"Algorithmique et Programmation 1",
					"Analyse 1 : Fonction d’une variable réelle",
					"Mécanique du point et optique géométrique",
					"Algorithmique et Programmation 2",
					"Analyse 2 : Calcul intégral et équations différentielles",
					"Semestre 2",
					"Langues et communication 2",
					"Electricité",
					"Algèbre 1 : Polynômes et espaces vectoriels",
					"Structure de la matière",
					"Circuits électriques et Electronique",
					"Algèbre 2 : Réduction des endomorphismes et formes quadratiques",
					"Gestion",
					"Mécanique des solides",
					"Analyse Numérique",
					"Electronique",
					"Thermodynamique",
					"Métrologie et instrumentation",
					"Électrotechnique",
					"Fabrication Mécanique",
					"Statistique descriptive/probabilités",
					"Automatique",
					"Informatique 3",
					"Construction mécanique"
			};

			for (String moduleName : modules) {
				ModuleF module = ModuleF.builder()
						.id(moduleFRepository.count() + 1)
						.name(moduleName)
						.filiere(filiere1)
						.build();
				moduleFRepository.save(module);
			}

			String[] modules2 = {
					"Langues et Communication",
					"Optique et Radioactivité",
					"Structure de la matière",
					"Algèbre",
					"Biologie cellulaire",
					"Cosmologie et Géodynamique interne",
					"Langues et Communication 2",
					"Thermodynamique / Mécanique des fluides",
					"Réactivité chimique",
					"Analyse",
					"Biologie animale",
					"Géodynamique externe",
					"Langues et Communication 3",
					"Electricité",
					"Bases de Données",
					"Probabilités et Statistiques",
					"Biologie végétale",
					"Stratigraphie, paléontologie-environnement",
					"Biochimie structurale",
					"Chimie Organique 1",
					"Chimie Minérale 1",
					"Microbiologie",
					"Tectonique",
					"Biochimie métabolique"
			};

			for (String moduleName : modules2) {
				ModuleF module = ModuleF.builder()
						.id(moduleFRepository.count() + 1)
						.name(moduleName)
						.filiere(filiere2)
						.build();
				moduleFRepository.save(module);
			}


			String[] modules3 = {
					"Recherche opérationnelle I",
					"Environnement professionnel",
					"Système Linux",
					"Base de données relationnelles",
					"Environnement Java",
					"Analyse de données et mathématiques financières",
					"Recherche opérationnelle II",
					"Administration des bases de données sous oracle",
					"Datamining",
					"Bases de données multimédia",
					"J2EE",
					"Administration réseaux",
					"Processus de décision Markovien",
					"Intelligence Artificielle",
					"Datawarehouse",
					"Web sémantique et mining",
					"Cryptographie/ Sécurité",
					"Programmation Web avancée",
					"Projet de Fin d’études"
			};

			for (String moduleName : modules3) {
				ModuleF module = ModuleF.builder()
						.id(moduleFRepository.count() + 1)
						.name(moduleName)
						.filiere(filiere16)
						.build();
				moduleFRepository.save(module);
			}

			String jsonData = "[\n";
			for (int i = 1; i <= 10; i++) {
				jsonData += "    {\n" +
						"        \"cin\": \"IU74688" + i + "\",\n" +
						"        \"apogee\": 880003" + i + ",\n" +
						"        \"nom\": \"HaJJar" + i + "\",\n" +
						"        \"prenom\": \"Mosine" + i + "\",\n" +
						"        \"cne\": \"L478889" + i + "\",\n" +
						"        \"email\": \"hajar" + i + ".mosine@gmail.com\",\n" +
						"        \"phone\": \"5678900" + i + "\",\n" +
						"        \"dateNaissance\": \"\",\n" +
						"        \"lieuNaissance\": \"fghjkl\",\n" +
						"        \"adresse\": \"ds\",\n" +
						"        \"genre\": \"MALE\",\n" +
						"        \"idFiliere\": 1\n" +
						"    }";
				if (i != 10) {
					jsonData += ",";
				}
				jsonData += "\n";
			}
			jsonData += "]";


			ObjectMapper objectMapper = new ObjectMapper();
			StudentPojo[] students = objectMapper.readValue(jsonData, StudentPojo[].class);

			for (StudentPojo student : students) {
				StudentPojo entity = StudentPojo.builder()
						.cin(student.getCin())
						.apogee(student.getApogee())
						.nom(student.getNom())
						.prenom(student.getPrenom())
						.cne(student.getCne())
						.email(student.getEmail())
						.phone(student.getPhone())
						.dateNaissance(student.getDateNaissance())
						.lieuNaissance(student.getLieuNaissance())
						.adresse(student.getAdresse())
						.genre(student.getGenre())
						.idFiliere(student.getIdFiliere())
						.build();

				service.saveStudent(entity);
			}

		};
	}
}
