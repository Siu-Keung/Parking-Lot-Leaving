package com.oocl.parkingLotLeaving.controller;

import com.oocl.parkingLotLeaving.entity.LeavingRequest;
import com.oocl.parkingLotLeaving.service.LeavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Dylan Wei
 * @date 2018-08-23 17:15
 */
@RestController
@RequestMapping("/absence")
public class AbsenceController {
    @Autowired
    private LeavingService leavingService;

    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createLeavingRequest(LeavingRequest leavingRequest){
        this.leavingService.createLeavingRequest(leavingRequest);
    }

}
