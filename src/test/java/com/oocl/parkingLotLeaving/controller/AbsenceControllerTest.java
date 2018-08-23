package com.oocl.parkingLotLeaving.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.parkingLotLeaving.entity.LeavingRequest;
import com.oocl.parkingLotLeaving.service.LeavingService;
import javafx.animation.Animation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Dylan Wei
 * @date 2018-08-23 17:21
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AbsenceController.class)
public class AbsenceControllerTest {
    @MockBean
    private LeavingService leavingService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    @Test
    public void should_get_204_given_valid_Leaving_Request() throws Exception {
        LeavingRequest leaving = new LeavingRequest();
        leaving.setStartDate(format.parse("2018-08-23 16:00"));
        leaving.setEndDate(format.parse("2018-08-25 08:00"));
        leaving.setReason("去相亲");
        mockMvc.perform(post("/absence").content(mapper.writeValueAsString(leaving)))
                .andExpect(status().isNoContent());

    }
}