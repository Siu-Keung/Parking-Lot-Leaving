package com.oocl.parkingLotLeaving.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.parkingLotLeaving.entity.LeavingRequest;
import com.oocl.parkingLotLeaving.exception.IllegalArgumentsException;
import com.oocl.parkingLotLeaving.service.LeavingService;
import javafx.animation.Animation;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.verify;

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

        mockMvc.perform(post("/absence").content(mapper.writeValueAsString(leaving))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());
        verify(this.leavingService).createLeavingRequest(any(LeavingRequest.class));
    }

    @Test
    public void should_get_403_given_leaving_request_invalid() throws Exception {
        LeavingRequest leaving = new LeavingRequest();
        leaving.setStartDate(format.parse("2018-08-28 16:00"));
        leaving.setEndDate(format.parse("2018-08-25 08:00"));
        leaving.setReason("去相亲");

        doThrow(new IllegalArgumentsException("起始日期不能大于终止日期")).when(this.leavingService).createLeavingRequest(any());
        mockMvc.perform(post("/absence")
                .content(mapper.writeValueAsString(leaving)).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isForbidden())
                .andExpect(content().string("起始日期不能大于终止日期"));
    }

}