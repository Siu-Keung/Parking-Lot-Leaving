package com.oocl.parkingLotLeaving.repostitory;


import com.oocl.parkingLotLeaving.entity.LeavingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeavingRepository extends JpaRepository<LeavingRequest, Long> {

}
