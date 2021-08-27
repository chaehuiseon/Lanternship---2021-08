package net.itdaa.cau.study.repository;

import net.itdaa.cau.study.entity.RoadAddrIntg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoadAddrRepository extends JpaRepository<RoadAddrIntg, String> {

    List<RoadAddrIntg> findByRoadNameStartingWith(String roadName);

    List<RoadAddrIntg> findByRoadNameStartingWithAndBldgMainNo(String roadName, Integer bldgMainNo);

    List<RoadAddrIntg> findByRoadNameAndBldgMainNoAndBldgSubNo(String roadName, Integer bldgMainNo, Integer bldgSubNo);

}
