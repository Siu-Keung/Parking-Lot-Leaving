package com.oocl.parkingLotLeaving.service;

import com.oocl.parkingLotLeaving.entity.Leaving;
import com.oocl.parkingLotLeaving.exception.IllegalArgumentsException;
import com.oocl.parkingLotLeaving.exception.ResourceNotFoundException;
import com.oocl.parkingLotLeaving.repostitory.LeavingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.oocl.parkingLotLeaving.entity.LeavingRequestStatus.PENDING;

/**
 * @author Dylan Wei
 * @date 2018-08-23 15:51
 */
@Service
public class LeavingService {
    private LeavingRepository leavingRepository;

    @Autowired
    public LeavingService(LeavingRepository leavingRepository){
        this.leavingRepository = leavingRepository;
    }

    public void createLeavingRequest(Leaving leaving){
        if(leaving.getStartDate().after(leaving.getEndDate()))
            throw new IllegalArgumentsException("起始日期不能大于终止日期");
        leaving.setStatus(PENDING);
        leaving.setTerminated(false);
        this.leavingRepository.save(leaving);
    }

    public List<Leaving> findAllLeavingRequest(){
        return this.leavingRepository.findAll();
    }

    public Leaving findLeavingRequestById(long id){
        Optional<Leaving> optionalLeaving = this.leavingRepository.findById(id);
        if(!optionalLeaving.isPresent())
            throw new ResourceNotFoundException();
        return optionalLeaving.get();
    }
}
