package com.oocl.parkingLotLeaving.service;

import com.oocl.parkingLotLeaving.entity.Leaving;
import com.oocl.parkingLotLeaving.repostitory.LeavingRepository;
import com.oocl.parkingLotLeaving.service.LeavingService;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.oocl.parkingLotLeaving.entity.LeavingRequestStatus.PENDING;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Dylan Wei
 * @date 2018-08-23 16:00
 */
public class LeavingServiceTest {
    private LeavingRepository leavingRepository;
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private LeavingService leavingService;

    @Before
    public void init(){
        this.leavingRepository = mock(LeavingRepository.class);
        this.leavingService = new LeavingService(this.leavingRepository);
    }

    @Test
    public void should_add_leaving_request_successfully_given_valid_application() throws ParseException {
        //given
        Leaving leaving = new Leaving();
        leaving.setStartDate(format.parse("2018-08-23 16:00"));
        leaving.setEndDate(format.parse("2018-08-25 08:00"));
        leaving.setReason("去相亲");
        //when
        this.leavingService.createLeavingRequest(leaving);
        //then
        verify(this.leavingRepository).save(any(Leaving.class));
    }

    @Test
    public void should_leaving_status_correct_given_valid_application() throws ParseException {
        //given
        Leaving leaving = new Leaving();
        leaving.setStartDate(format.parse("2018-08-23 16:00"));
        leaving.setEndDate(format.parse("2018-08-25 08:00"));
        leaving.setReason("去相亲");
        //when
        this.leavingService.createLeavingRequest(leaving);
        //then
        assertThat(leaving.getStatus(),is(PENDING));
        assertThat(leaving.getTerminated(),is(false));
    }


}