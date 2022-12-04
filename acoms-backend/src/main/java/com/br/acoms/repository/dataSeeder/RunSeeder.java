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
import com.br.acoms.service.ChatService;
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
        private final ChatService chatService;

        @EventListener
        public void seed(ContextRefreshedEvent event) {
                schoolDataSeeder();
                coordinatorDataSeeder();
                guardianDataSeeder();
                chatDataSeeder();
                studentDataSeeder();
                testerrr();

                System.out.println("Database seeder done");
        }

        public void testerrr() {
                School escola = schoolRepository.findById(Long.valueOf(1)).get();
                // for(Guardian guardian : guardianService.getAllBySchool(escola)){
                // System.out.println(guardian.getEmail());
                // }

                // Coordinator coordinator = coordinatorService
                // .convertPersonToCoordinator(escola.getPersons()).get(0);

                // List<Chat> chats = chatService.getAllByCoordinator(coordinator);
                // for(Chat chat : chats){
                // for(Message mensagem : chat.getMessages()){
                // System.out.println(mensagem.getMessage());
                // }
                // }
        }

        private void schoolDataSeeder() {
                List<School> schools = new ArrayList<>();
                schools.add(new School("ETEC", "Rua elideo Graces de rezende; numero 130",
                                "admin@teste.com.br", encoder.encode("admin"), "13", "(17) 99793823",
                                "valdete", Date.valueOf("1950-09-20"), "00001",
                                "(17) 99096732", "nothing"));

                schools.add(new School("Angulo", "Rua limos barros de melo; numero 160",
                                "admin@angulo.br", encoder.encode("password"), "17", "(17) 997938233",
                                "valdete", Date.valueOf("1950-09-20"), "00002",
                                "(17) 99673432", "nothing"));

                schools.add(new School("Objetivo", "Rua dacubinha; numero 154",
                                "admin@objetivo.com.br", encoder.encode("password"), "22", "(17) 99793824",
                                "valdete", Date.valueOf("1950-09-20"), "00003",
                                "(17) 996732321", "nothing"));

                // schools.add(new School("Teste", "Rua teste; numero teste",
                // "admin@teste.com.br", encoder.encode("admin"), "892", "(17) 9979382",
                // "valdete", Date.valueOf("1950-09-20"), "00001",
                // "(17) 996732", "nothing"));

                schoolRepository.saveAll(schools);
        }

        private void coordinatorDataSeeder() {
                List<Coordinator> coordinators = new ArrayList<>();

                Optional<School> escola1 = schoolRepository.findById(Long.valueOf(1));
                if (escola1.isPresent()) {
                        School escola = escola1.get();
                        coordinators.add(new Coordinator("Joao otavio", "12311", "jaoOtavio@etec.sp.gov.br",
                                        encoder.encode("password"),
                                        Date.valueOf("1990-09-20"), "(17) 996736281", null, "endereco", escola,
                                        Roles.COORDINATOR,
                                        "1"));
                        coordinators.add(new Coordinator("Joao otavio2", "12312", "jaoOtavio2@etec.sp.gov.br",
                                        encoder.encode("password"),
                                        Date.valueOf("1990-09-20"), "(17) 996736281", null, "endereco", escola,
                                        Roles.COORDINATOR,
                                        "2"));
                        coordinators.add(new Coordinator("Joao otavio3", "12313", "jaoOtavio3@etec.sp.gov.br",
                                        encoder.encode("password"),
                                        Date.valueOf("1990-09-20"), "(17) 996736281", null, "endereco", escola,
                                        Roles.COORDINATOR,
                                        "3"));
                        coordinators.add(new Coordinator("Felipe Bonsai", "12314", "felipe@etec.sp.gov.br",
                                        encoder.encode("password"),
                                        Date.valueOf("1990-09-20"), "(17) 996736281", null, "endereco", escola,
                                        Roles.COORDINATOR,
                                        "4"));
                        coordinators.add(new Coordinator("Bonsai Felipe", "12315", "bonsai@etec.sp.gov.br",
                                        encoder.encode("password"),
                                        Date.valueOf("1990-09-20"), "(17) 996736281", null, "endereco", escola,
                                        Roles.COORDINATOR,
                                        "5"));
                        coordinators.add(new Coordinator("Tia Indiara", "12316", "tia@etec.sp.gov.br",
                                        encoder.encode("password"),
                                        Date.valueOf("1990-09-20"), "(17) 996736281", null, "endereco", escola,
                                        Roles.COORDINATOR,
                                        "6"));
                        coordinators.add(new Coordinator("Professora Indiara", "12317", "indiara@etec.sp.gov.br",
                                        encoder.encode("password"),
                                        Date.valueOf("1990-09-20"), "(17) 996736281", null, "endereco", escola,
                                        Roles.COORDINATOR,
                                        "7"));
                }

                coordinatorRepository.saveAll(coordinators);
        }

        private void guardianDataSeeder() {
                List<Guardian> guardians = new ArrayList<>();

                Optional<School> escola1 = schoolRepository.findById(Long.valueOf(1));
                if (escola1.isPresent()) {
                        School escola = escola1.get();
                        guardians.add(
                                        new Guardian("fran", "890", "fran@gmail.com",
                                                        encoder.encode("fran123"), Date.valueOf("1990-09-20"),
                                                        "(17) 9967-89090", null,
                                                        escola, Roles.GUARDIAN,
                                                        "numero rua ; cidade estadoAbreviado cep", Long.valueOf(1)));
                        guardians.add(
                                        new Guardian("valter", "891", "valter@gmail.com",
                                                        encoder.encode("valter123"), Date.valueOf("1970-09-20"),
                                                        "(17) 9967-89092", null,
                                                        escola, Roles.GUARDIAN,
                                                        "numero rua ; cidade estadoAbreviado cep", Long.valueOf(2)));
                        guardians.add(
                                        new Guardian("responsavel1", "892", "responsavel1@gmail.com",
                                                        encoder.encode("responsavel1"), Date.valueOf("1990-09-20"),
                                                        "(17) 9967-89093", null,
                                                        escola, Roles.GUARDIAN,
                                                        "numero rua ; cidade estadoAbreviado cep", Long.valueOf(3)));

                }
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
                School escola = schoolRepository.findById(Long.valueOf(1)).get();
                List<Guardian> guardians = guardianRepository.findBySchool(escola).get();

                if (guardians != null) {
                        List<Guardian> guardians1 = new ArrayList<>();
                        guardians1.add(guardians.get(0));
                        List<Student> childrens = new ArrayList<>();

                        childrens.add(new Student("matheus", "10", "matheus.fernandes92.235@protonmail.com",
                                        encoder.encode("password"), Date.valueOf("2004-09-02"), "(17) 99673-67329",
                                        null, escola, Roles.STUDENT, "numero rua ; cidade estadoAbreviado cep",
                                        guardians1, Long.valueOf(1)));
                        childrens.add(new Student("gabriel", "11", "gabriel@protonmail.com",
                                        encoder.encode("password"), Date.valueOf("2005-09-02"), "(17) 99673-67330",
                                        null, escola, Roles.STUDENT, "numero rua ; cidade estadoAbreviado cep",
                                        guardians1, Long.valueOf(2)));

                        studentRepository.saveAll(childrens);
                }

        }
}