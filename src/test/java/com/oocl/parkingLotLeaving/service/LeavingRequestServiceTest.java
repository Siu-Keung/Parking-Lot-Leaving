package com.oocl.parkingLotLeaving.service;

import com.oocl.parkingLotLeaving.entity.LeavingRequest;
import com.oocl.parkingLotLeaving.exception.IllegalArgumentsException;
import com.oocl.parkingLotLeaving.repostitory.LeavingRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
public class LeavingRequestServiceTest {
    private LeavingRepository leavingRepository;
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private LeavingService leavingService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void init(){
        this.leavingRepository = mock(LeavingRepository.class);
        this.leavingService = new LeavingService(this.leavingRepository);
    }

    @Test
    public void should_add_leaving_request_successfully_given_valid_application() throws ParseException {
        //given
        LeavingRequest leaving = new LeavingRequest();
        leaving.setStartDate(format.parse("2018-08-23 16:00"));
        leaving.setEndDate(format.parse("2018-08-25 08:00"));
        leaving.setReason("去相亲");
        //when
        this.leavingService.createLeavingRequest(leaving);
        //then
        verify(this.leavingRepository).save(any(LeavingRequest.class));
    }

    @Test
    public void should_leaving_status_correct_given_valid_application() throws ParseException {
        //given
        LeavingRequest leaving = new LeavingRequest();
        leaving.setStartDate(format.parse("2018-08-23 16:00"));
        leaving.setEndDate(format.parse("2018-08-25 08:00"));
        leaving.setReason("去相亲");
        //when
        this.leavingService.createLeavingRequest(leaving);
        //then
        assertThat(leaving.getStatus(),is(PENDING));
        assertThat(leaving.getTerminated(),is(false));
    }

    @Test
    public void should_throw_IllegalArgumentsException_given_illegal_date_region() throws ParseException {
        //given
        LeavingRequest leaving = new LeavingRequest();
        leaving.setStartDate(format.parse("2018-08-26 16:00"));
        leaving.setEndDate(format.parse("2018-08-25 08:00"));
        leaving.setReason("去相亲");

        expectedException.expect(IllegalArgumentsException.class);
        this.leavingService.createLeavingRequest(leaving);
    }


}