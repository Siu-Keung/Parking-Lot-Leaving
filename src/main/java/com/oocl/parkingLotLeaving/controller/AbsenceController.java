package com.oocl.parkingLotLeaving.controller;

import com.oocl.parkingLotLeaving.entity.Leaving;
import com.oocl.parkingLotLeaving.service.LeavingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void createLeavingRequest(@RequestBody Leaving leavingRequest){
        this.leavingService.createLeavingRequest(leavingRequest);
    }

    @GetMapping
    public Map<String, Object> findAllLeavingRequestList(){
        List<Leaving> resultList =  this.leavingService.findAllLeavingRequest();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("datas", resultList);
        return resultMap;
    }

    @GetMapping("/{id}")
    public Leaving getLeavingRequestById(@PathVariable Long id){
        return this.leavingService.findLeavingRequestById(id);
    }


}
