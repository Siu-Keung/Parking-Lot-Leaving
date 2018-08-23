package com.oocl.parkingLotLeaving.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oocl.parkingLotLeaving.entity.Leaving;
import com.oocl.parkingLotLeaving.exception.IllegalArgumentsException;
import com.oocl.parkingLotLeaving.exception.ResourceNotFoundException;
import com.oocl.parkingLotLeaving.service.LeavingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
        Leaving leaving = new Leaving();
        leaving.setStartDate(format.parse("2018-08-23 16:00"));
        leaving.setEndDate(format.parse("2018-08-25 08:00"));
        leaving.setReason("去相亲");

        mockMvc.perform(post("/absence").content(mapper.writeValueAsString(leaving))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());
        verify(this.leavingService).createLeavingRequest(any(Leaving.class));
    }

    @Test
    public void should_get_403_given_leaving_request_invalid() throws Exception {
        Leaving leaving = new Leaving();
        leaving.setStartDate(format.parse("2018-08-28 16:00"));
        leaving.setEndDate(format.parse("2018-08-25 08:00"));
        leaving.setReason("去相亲");

        doThrow(new IllegalArgumentsException("起始日期不能大于终止日期")).when(this.leavingService).createLeavingRequest(any());
        mockMvc.perform(post("/absence")
                .content(mapper.writeValueAsString(leaving)).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isForbidden())
                .andExpect(content().string("起始日期不能大于终止日期"));
    }

    @Test
    public void should_return_leaving_request_list() throws Exception {
        Map expectedMap = new HashMap();
        List list = new ArrayList();
        expectedMap.put("datas", list);
        when(this.leavingService.findAllLeavingRequest()).thenReturn(list);
        mockMvc.perform(get("/absence")).andExpect(content().string(mapper.writeValueAsString(expectedMap)));
    }

    @Test
    public void should_return_specific_leaving_request_given_valid_id() throws Exception {
        Leaving leaving = new Leaving();
        leaving.setStartDate(format.parse("2018-08-24 16:00"));
        leaving.setEndDate(format.parse("2018-08-25 08:00"));
        leaving.setReason("去相亲");

        when(this.leavingService.findLeavingRequestById(anyLong())).thenReturn(leaving);

        mockMvc.perform(get("/absence/" + anyLong()))
                .andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(leaving)));
    }

    @Test
    public void should_return_404_given_invalid_id() throws Exception {
        doThrow(new ResourceNotFoundException()).when(this.leavingService).findLeavingRequestById(anyLong());

        mockMvc.perform(get("/absence/" + anyLong()))
                .andExpect(status().isNotFound())
                .andExpect(content().string("资源不存在"));
    }

}