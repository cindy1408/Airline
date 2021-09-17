package com.day9exercise.demo.Employee;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class EmployeeServiceTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private EmployeeRepositoryPostgres employeeRepositoryPostgres;


        @Test
        public void itCanAddEmployee()
                throws Exception {

            // Given
            Employee newEmployee = new Employee(
                    "Mariam2",
                    "password",
                    "Mariam",
                    "Musa",
                    "HE76CH54",
                    false);

            ResultActions employeeResultActions = mockMvc.perform(post("/api/employees")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(Objects.requireNonNull(objectToJson(newEmployee))));


            // When

            //Then
            employeeResultActions.andExpect(status().isOk());
        }


        //Checking to see if we can find the test data added to the database

        @Test
        public void getEmployeesByUserName() throws Exception{

            mockMvc.perform(get("/api/employees"))
                    .andExpect(status().isOk());

            List<Employee> all = employeeRepositoryPostgres.findAll();
            assertThat(all.size()).isEqualTo(4);
            assertThat(all.get(0).getUsername()).isEqualTo("Amy16");
            assertThat(all.get(1).getUsername()).isEqualTo("ali1");
            assertThat(all.get(2).getUsername()).isEqualTo("sara12");
        }


        private String objectToJson(Object object) {
            try {
                return new ObjectMapper().writeValueAsString(object);
            } catch (JsonProcessingException e) {
                fail("Failed to convert object to json");
                return null;
            }
        }

    }

