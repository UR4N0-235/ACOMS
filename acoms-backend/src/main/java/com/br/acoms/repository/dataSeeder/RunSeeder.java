package com.br.acoms.repository.dataSeeder;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.br.acoms.models.Chat;
import com.br.acoms.models.Coordinator;
import com.br.acoms.models.Guardian;
import com.br.acoms.models.Message;
import com.br.acoms.models.Roles;
import com.br.acoms.models.School;
import com.br.acoms.models.Student;
import com.br.acoms.repository.ChatRepository;
import com.br.acoms.repository.CoordinatorRepository;
import com.br.acoms.repository.GuardianRepository;
import com.br.acoms.repository.MessageRepository;
import com.br.acoms.repository.SchoolRepository;
import com.br.acoms.repository.StudentRepository;
import com.br.acoms.service.CoordinatorService;
import com.br.acoms.service.GuardianService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RunSeeder {
        @Autowired
        private PasswordEncoder encoder;

        private final SchoolRepository schoolRepository;
        private final CoordinatorRepository coordinatorRepository;
        private final GuardianRepository guardianRepository;
        private final ChatRepository chatRepository;
        private final MessageRepository messageRepository;
        private final StudentRepository studentRepository;

        private final CoordinatorService coordinatorService;
        private final GuardianService guardianService;

        @EventListener
        public void seed(ContextRefreshedEvent event) {
                schoolDataSeeder();
                coordinatorDataSeeder();
                guardianDataSeeder();
                chatDataSeeder();
                studentDataSeeder();
                // testerrr();

                System.out.println("Database seeder done");
        }

        public void testerrr() {
                // School escola = schoolRepository.findById(Long.valueOf(1)).get();
                // Guardian guardian =
                // guardianService.convertPersonToGuardian(escola.getPersons()).get(0);

                // List<Student> students =studentService.getAllByGuardian(guardian.getId());
                // for(Student student : students){
                // System.out.println(student.getName());
                // }
        }

        private void schoolDataSeeder() {
                List<School> schools = new ArrayList<>();
                schools.add(new School("ETEC Professor José Armando Farinazzo",
                                "Rua elideo Graces de rezende; numero 130",
                                "admin@teste.com.br", encoder.encode("admin"), "13", "(17) 99793823",
                                "valdete", Date.valueOf("1950-09-20"), "00001",
                                "(17) 99096732", "nothing", School.Planos.PROFISSIONAL, true));

                schools.add(new School("Angulo", "Rua limos barros de melo; numero 160",
                                "admin@angulo.br", encoder.encode("password"), "17", "(17) 997938233",
                                "valdete", Date.valueOf("1950-09-20"), "00002",
                                "(17) 99673432", "nothing", School.Planos.VIP, false));

                schools.add(new School("Objetivo", "Rua dacubinha; numero 154",
                                "admin@objetivo.com.br", encoder.encode("password"), "22", "(17) 99793824",
                                "valdete", Date.valueOf("1950-09-20"), "00003",
                                "(17) 996732321", "nothing", School.Planos.VIP, true));

                schools.add(new School("SESI",
                                "Rua elideo Graces de rezende; numero 130",
                                "admin@sesi.com.br", encoder.encode("admin"), "14", "(17) 99793825",
                                "valdete", Date.valueOf("1950-09-20"), "00004",
                                "(17) 990967323", "nothing", School.Planos.VIP, true));

                schools.add(new School("Elas", "Rua limos barros de melo; numero 160",
                                "admin@elas.br", encoder.encode("password"), "172", "(17) 997938236",
                                "valdete", Date.valueOf("1950-09-20"), "00005",
                                "(17) 99673432", "nothing", School.Planos.BASICO, false));

                schools.add(new School("Barosi", "Rua dacubinha; numero 154",
                                "admin@barosi.com.br", encoder.encode("password"), "221", "(17) 99793827",
                                "valdete", Date.valueOf("1950-09-20"), "00006",
                                "(17) 996732321", "nothing", School.Planos.VIP, true));

                // schools.add(new School("Teste", "Rua teste; numero teste",
                // "admin@teste.com.br", encoder.encode("admin"), "892", "(17) 9979382",
                // "valdete", Date.valueOf("1950-09-20"), "00001",
                // "(17) 996732", "nothing"));

                schoolRepository.saveAll(schools);
        }

        private void coordinatorDataSeeder() {
                List<Coordinator> coordinators = new ArrayList<>();

                School escola1 = schoolRepository.findById(Long.valueOf(1)).get();
                School escola2 = schoolRepository.findById(Long.valueOf(2)).get();
                School escola3 = schoolRepository.findById(Long.valueOf(3)).get();
                School escola4 = schoolRepository.findById(Long.valueOf(4)).get();
                School escola5 = schoolRepository.findById(Long.valueOf(5)).get();
                School escola6 = schoolRepository.findById(Long.valueOf(6)).get();
                

                coordinators.add(new Coordinator("Joao Otavio", "Gestora de comunicacoes", "12311",
                                "jaoOtavio@etec.sp.gov.br",
                                encoder.encode("password"),
                                Date.valueOf("1990-09-20"), "(17) 996736281", null, "endereco", escola1,
                                Roles.COORDINATOR,
                                "1"));

                coordinators.add(new Coordinator("Gisele", "coordenador admnistratitvo", "12312",
                                "jaoOtavio2@etec.sp.gov.br",
                                encoder.encode("password"),
                                Date.valueOf("1990-09-20"), "(17) 996736281", null, "endereco", escola2,
                                Roles.COORDINATOR,
                                "2"));

                coordinators.add(new Coordinator("Joao otavio3", "coordenador de estagios", "12313",
                                "jaoOtavio3@etec.sp.gov.br",
                                encoder.encode("password"),
                                Date.valueOf("1990-09-20"), "(17) 996736281", null, "endereco", escola3,
                                Roles.COORDINATOR,
                                "3"));

                coordinators.add(new Coordinator("Felipe Bonsai", "coordenador geral", "12314", "felipe@etec.sp.gov.br",
                                encoder.encode("password"),
                                Date.valueOf("1990-09-20"), "(17) 996736281", null, "endereco", escola4,
                                Roles.COORDINATOR,
                                "4"));

                coordinators.add(new Coordinator("Bonsai Felipe", "coordenador adminstrativo", "12315",
                                "bonsai@etec.sp.gov.br",
                                encoder.encode("password"),
                                Date.valueOf("1990-09-20"), "(17) 996736281", null, "endereco", escola5,
                                Roles.COORDINATOR,
                                "5"));

                coordinators.add(new Coordinator("Tia Indiara", "coordenador de estagios", "12316",
                                "tia@etec.sp.gov.br",
                                encoder.encode("password"),
                                Date.valueOf("1990-09-20"), "(17) 996736281", null, "endereco", escola6,
                                Roles.COORDINATOR,
                                "6"));

                coordinators.add(new Coordinator("Indiara Almeida", "Orientadora Educacional", "12317",
                                "indiara@etec.sp.gov.br",
                                encoder.encode("password"),
                                Date.valueOf("1990-09-20"), "(17) 996736281", null, "endereco", escola1,
                                Roles.COORDINATOR,
                                "7"));

                coordinatorRepository.saveAll(coordinators);
        }

        private void guardianDataSeeder() {
                List<Guardian> guardians = new ArrayList<>();

                School escola1 = schoolRepository.findById(Long.valueOf(1)).get();
                School escola2 = schoolRepository.findById(Long.valueOf(2)).get();
                School escola3 = schoolRepository.findById(Long.valueOf(3)).get();
                School escola4 = schoolRepository.findById(Long.valueOf(4)).get();

                
                        guardians.add(
                                        new Guardian("Tamires Lemes de rocha", "890.000.123-12",
                                                        "LemesTamires@gmail.com",
                                                        encoder.encode("senha1"), Date.valueOf("1990-09-20"),
                                                        "(17) 9967-89090", null,
                                                        escola1, Roles.GUARDIAN,
                                                        "numero rua ; cidade estadoAbreviado cep", Long.valueOf(1)));

                        guardians.add(
                                        new Guardian("Alison Rodrigues De Solza", "891.001.132-14", "Alison@gmail.com",
                                                        encoder.encode("senha1"), Date.valueOf("1970-09-20"),
                                                        "(17) 9967-89092", null,
                                                        escola2, Roles.GUARDIAN,
                                                        "numero rua ; cidade estadoAbreviado cep", Long.valueOf(2)));
                        guardians.add(
                                        new Guardian("Jair Antonio De Souza", "525.909.202-20",
                                                        "responsave1321@gmail.com",
                                                        encoder.encode("senha1"), Date.valueOf("1990-09-20"),
                                                        "(17) 9967-89093", null,
                                                        escola3, Roles.GUARDIAN,
                                                        "numero rua ; cidade estadoAbreviado cep", Long.valueOf(3)));
                        guardians.add(
                                        new Guardian("Silvo Cesar Lopes", "123.321.222-13", "Silvio@gmail.com",
                                                        encoder.encode("calvaoDeCria123"), Date.valueOf("1990-09-20"),
                                                        "(17) 9967-89901", null,
                                                        escola4, Roles.GUARDIAN,
                                                        "numero rua ; cidade estadoAbreviado cep", Long.valueOf(4)));

                
                guardianRepository.saveAll(guardians);
        }

        private void chatDataSeeder() {

                Optional<School> escola1 = schoolRepository.findById(Long.valueOf(1));

                if (escola1.isPresent()) {
                        School escola = escola1.get();

                        List<Coordinator> coordinator = coordinatorService
                                        .convertPersonToCoordinator(escola.getPersons());
                        List<Guardian> guardians = guardianService.convertPersonToGuardian(escola.getPersons());

                        if (!coordinator.isEmpty() && !guardians.isEmpty()) {
                                Chat chat = new Chat(coordinator.get(0), guardians.get(0));
                                List<Message> mensagens = new ArrayList<>();

                                mensagens.add(new Message("Olá " + guardians.get(0).getName()
                                                + " seu filho anda faltando muito, aconteceu alguma coisa?",
                                                Date.valueOf(LocalDate.now()),
                                                chat, Message.EnviadoPor.COORDENADOR));

                                mensagens.add(new Message("olá, boa tarde, infelizmente aconteceu sim...",
                                                Date.valueOf(LocalDate.now()),
                                                chat, Message.EnviadoPor.RESPONSAVEL));

                                mensagens.add(new Message("meu pequeno sofreu um acidente, e esta na uti",
                                                Date.valueOf(LocalDate.now()),
                                                chat, Message.EnviadoPor.RESPONSAVEL));

                                mensagens.add(new Message(
                                                "meus sentimentos, espero que melhore logo, seu filho é um otimo aluno!",
                                                Date.valueOf(LocalDate.now()),
                                                chat, Message.EnviadoPor.COORDENADOR));

                                chatRepository.save(chat);
                                messageRepository.saveAll(mensagens);

                        }
                } else {
                        System.out.println("puts caiu nesse if");
                }
        }

        private void studentDataSeeder() {
                School escola1 = schoolRepository.findById(Long.valueOf(1)).get();
                School escola2 = schoolRepository.findById(Long.valueOf(2)).get();
                School escola3 = schoolRepository.findById(Long.valueOf(3)).get();
                School escola4 = schoolRepository.findById(Long.valueOf(4)).get();

                List<Guardian> guardiansList1 = guardianRepository.findBySchool(escola1).get();
                List<Guardian> guardiansList2 = guardianRepository.findBySchool(escola2).get();
                List<Guardian> guardiansList3 = guardianRepository.findBySchool(escola3).get();
                List<Guardian> guardiansList4 = guardianRepository.findBySchool(escola4).get();

                Guardian guardian1 = guardiansList1.get(0);
                Guardian guardian2 = guardiansList2.get(0);
                Guardian guardian3 = guardiansList3.get(0);
                Guardian guardian4 = guardiansList4.get(0);

                List<Student> childrens = new ArrayList<>();

                childrens.add(new Student("Carlos Tomaz", "145.213.231-12", "carlos@protonmail.com",
                                encoder.encode("password"), Date.valueOf("2004-09-02"), "(17) 99673-67329",
                                null, escola1, Roles.STUDENT, "numero rua ; cidade estadoAbreviado cep",
                                guardian1, Long.valueOf(1)));
                childrens.add(new Student("Gabriel Ferrare", "312.231.542-34", "gabriel@protonmail.com",
                                encoder.encode("password"), Date.valueOf("2005-09-02"), "(17) 99673-12345",
                                null, escola1, Roles.STUDENT, "numero rua ; cidade estadoAbreviado cep",
                                guardian1, Long.valueOf(2)));

                childrens.add(new Student("Airton Lima", "321.315.643-32", "airton@protonmail.com",
                                encoder.encode("password"), Date.valueOf("2004-09-02"), "(17) 99673-65429",
                                null, escola2, Roles.STUDENT, "numero rua ; cidade estadoAbreviado cep",
                                guardian2, Long.valueOf(3)));

                childrens.add(new Student("Pereira Caetano", "315.312.665-21", "pereira@protonmail.com",
                                encoder.encode("password"), Date.valueOf("2005-09-02"), "(17) 99673-67354",
                                null, escola3, Roles.STUDENT, "numero rua ; cidade estadoAbreviado cep",
                                guardian3, Long.valueOf(4)));
                childrens.add(new Student("Gabriele Zucarri", "909.921.235-32", "gabrielZucarri@protonmail.com",
                                encoder.encode("password"), Date.valueOf("2004-09-02"), "(17) 99673-67331",
                                null, escola3, Roles.STUDENT, "numero rua ; cidade estadoAbreviado cep",
                                guardian3, Long.valueOf(5)));
                childrens.add(new Student("Gabriel Reis", "313.135.356-45", "GabrrielReis@protonmail.com",
                                encoder.encode("password"), Date.valueOf("2005-09-02"), "(17) 99673-67321",
                                null, escola3, Roles.STUDENT, "numero rua ; cidade estadoAbreviado cep",
                                guardian3, Long.valueOf(6)));

                childrens.add(new Student("Yian de Bessoa", "312.211.213-21", "bessoa@protonmail.com",
                                encoder.encode("password"), Date.valueOf("2004-09-02"), "(17) 99673-84243",
                                null, escola4, Roles.STUDENT, "numero rua ; cidade estadoAbreviado cep",
                                guardian4, Long.valueOf(7)));
                childrens.add(new Student("Paulo Merces", "351.235.123-31", "merces@protonmail.com",
                                encoder.encode("password"), Date.valueOf("2005-09-02"), "(17) 99673-87330",
                                null, escola4, Roles.STUDENT, "numero rua ; cidade estadoAbreviado cep",
                                guardian4, Long.valueOf(8)));

                studentRepository.saveAll(childrens);

        }
}