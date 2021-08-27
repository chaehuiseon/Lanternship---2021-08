package net.itdaa.cau.study.repository;

import net.itdaa.cau.study.entity.JibunAddrIntg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JibunAddrRepository extends JpaRepository<JibunAddrIntg, String> {

    List<JibunAddrIntg> findByMgrNoOrderBySeqNo(String mgrNo);

}
