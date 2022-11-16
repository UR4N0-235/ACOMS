package com.br.acoms.repository.dataSeeder;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.br.acoms.models.Coordinator;
import com.br.acoms.models.Roles;
import com.br.acoms.models.School;
import com.br.acoms.repository.CoordinatorRepository;
import com.br.acoms.repository.SchoolRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RunSeeder {
        @Autowired
        private PasswordEncoder encoder;

        private final SchoolRepository schoolRepository;
        private final CoordinatorRepository coordinatorRepository;

        @EventListener
        public void seed(ContextRefreshedEvent event) {
                schoolDataSeeder();
                coordinatorDataSeeder();

                System.out.println("Database seeder done");
        }

        private void schoolDataSeeder() {
                List<School> schools = new ArrayList<>();
                schools.add(new School("ETEC", "Rua elideo Graces de rezende; numero 130",
                                "admin@teste.com.br", encoder.encode("admin"), "9090", "(17) 9979382",
                                "valdete", Date.valueOf("1950-09-20"), "00001",
                                "(17) 996732", "nothing"));

                schools.add(new School("Angulo", "Rua limos barros de melo; numero 160",
                                "admin@angulo.br", encoder.encode("password"), "9090", "(17) 9979382",
                                "valdete", Date.valueOf("1950-09-20"), "00001",
                                "(17) 996732", "nothing"));

                schools.add(new School("Objetivo", "Rua dacubinha; numero 154",
                                "admin@objetivo.com.br", encoder.encode("password"), "9090", "(17) 9979382",
                                "valdete", Date.valueOf("1950-09-20"), "00001",
                                "(17) 996732", "nothing"));

                // schools.add(new School("Teste", "Rua teste; numero teste",
                //                 "admin@teste.com.br", encoder.encode("admin"), "892", "(17) 9979382",
                //                 "valdete", Date.valueOf("1950-09-20"), "00001",
                //                 "(17) 996732", "nothing"));

                schoolRepository.saveAll(schools);
        }

        private void coordinatorDataSeeder() {
                List<Coordinator> coordinators = new ArrayList<>();

                Optional<School> escola1 = schoolRepository.findById(Long.valueOf(1));
                if (escola1.isPresent()) {
                        School escola = escola1.get();
                        coordinators.add(new Coordinator("Joao otavio", "12311", "jaoOtavio@etec.sp.gov.br",
                                        encoder.encode("password"),
                                        Date.valueOf("1990-09-20"), "(17) 996736281", null, escola, Roles.COORDINATOR,
                                        "1"));
                        coordinators.add(new Coordinator("Joao otavio2", "12312", "jaoOtavio2@etec.sp.gov.br",
                                        encoder.encode("password"),
                                        Date.valueOf("1990-09-20"), "(17) 996736281", null, escola, Roles.COORDINATOR,
                                        "2"));
                        coordinators.add(new Coordinator("Joao otavio3", "12313", "jaoOtavio3@etec.sp.gov.br",
                                        encoder.encode("password"),
                                        Date.valueOf("1990-09-20"), "(17) 996736281", null, escola, Roles.COORDINATOR,
                                        "3"));
                        coordinators.add(new Coordinator("Felipe Bonsai", "12314", "felipe@etec.sp.gov.br",
                                        encoder.encode("password"),
                                        Date.valueOf("1990-09-20"), "(17) 996736281", null, escola, Roles.COORDINATOR,
                                        "4"));
                        coordinators.add(new Coordinator("Bonsai Felipe", "12315", "bonsai@etec.sp.gov.br",
                                        encoder.encode("password"),
                                        Date.valueOf("1990-09-20"), "(17) 996736281", null, escola, Roles.COORDINATOR,
                                        "5"));
                        coordinators.add(new Coordinator("Tia Indiara", "12316", "tia@etec.sp.gov.br",
                                        encoder.encode("password"),
                                        Date.valueOf("1990-09-20"), "(17) 996736281", null, escola, Roles.COORDINATOR,
                                        "6"));
                        coordinators.add(new Coordinator("Professora Indiara", "12317", "indiara@etec.sp.gov.br",
                                        encoder.encode("password"),
                                        Date.valueOf("1990-09-20"), "(17) 996736281", null, escola, Roles.COORDINATOR,
                                        "7"));

                }

                coordinatorRepository.saveAll(coordinators);
        }
}
