package com.oocl.parkingLotLeaving.repostitory;


import com.oocl.parkingLotLeaving.entity.Leaving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeavingRepository extends JpaRepository<Leaving, Long> {

}
