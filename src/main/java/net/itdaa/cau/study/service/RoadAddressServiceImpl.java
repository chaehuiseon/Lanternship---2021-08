package net.itdaa.cau.study.service;

import lombok.extern.slf4j.Slf4j;
import net.itdaa.cau.study.entity.FullRoadJibnAddress;
import net.itdaa.cau.study.entity.JibunAddrIntg;
import net.itdaa.cau.study.entity.RoadAddrIntg;
import net.itdaa.cau.study.repository.JibunAddrRepository;
import net.itdaa.cau.study.repository.RoadAddrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class RoadAddressServiceImpl implements RoadAddressService {

    @Autowired
    RoadAddrRepository roadAddrRepository;

    @Autowired
    JibunAddrRepository jibunAddrRepository;


    @Override
    public List<FullRoadJibnAddress> searchRoadAddress(String searchRoadName, Integer bldgMainNo, Integer bldgSubNo) throws Exception {

        List<RoadAddrIntg> roadAddressList;

        log.debug("{},{},{}",searchRoadName,bldgMainNo,bldgSubNo);

        if (Objects.isNull(bldgMainNo)) {

            roadAddressList = roadAddrRepository.findByRoadNameStartingWith(searchRoadName);
        }
        else if (!Objects.isNull(bldgMainNo) && Objects.isNull(bldgSubNo)) {

            roadAddressList = roadAddrRepository.findByRoadNameStartingWithAndBldgMainNo(searchRoadName, bldgMainNo);
        }
        else if (!Objects.isNull(bldgMainNo) && !Objects.isNull(bldgSubNo)) {

            roadAddressList = roadAddrRepository.findByRoadNameAndBldgMainNoAndBldgSubNo(searchRoadName, bldgMainNo, bldgSubNo);
        }
        else {
             roadAddressList = new ArrayList<>();
        }

        List<FullRoadJibnAddress> addressList = new ArrayList<>();

        for (RoadAddrIntg roadAddr : roadAddressList) {

            String mgrNo = roadAddr.getMgrNO();

            List<JibunAddrIntg> jibunAddressesList = jibunAddrRepository.findByMgrNoOrderBySeqNo(mgrNo);

            String repJibnAddress = "";
            List<String> relatedJibnAddressList = new ArrayList<>();

            if (jibunAddressesList.size() != 0) {

                repJibnAddress = jibunAddressesList.get(0).getFullJibnAddr();

                for (int i=1 ; i<jibunAddressesList.size(); i++) {
                    relatedJibnAddressList.add(jibunAddressesList.get(i).getFullJibnAddr());
                }
            }

            FullRoadJibnAddress fullRoadJibnAddress = FullRoadJibnAddress.builder()
                                                                         .fullRoadAddress(roadAddr.getFullRoadAddr())
                                                                         .postCode(roadAddr.getPostCode())
                                                                         .repJibunAddress(repJibnAddress)
                                                                         .relatedJibunAddressList(relatedJibnAddressList)
                                                                         .build();

            addressList.add(fullRoadJibnAddress);
        }

        return addressList;
    }

}
