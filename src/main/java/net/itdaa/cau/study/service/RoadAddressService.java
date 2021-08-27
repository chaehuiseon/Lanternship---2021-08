package net.itdaa.cau.study.service;

import net.itdaa.cau.study.entity.FullRoadJibnAddress;

import java.util.List;

public interface RoadAddressService {

    List<FullRoadJibnAddress> searchRoadAddress(String searchRoadName, Integer bldgMainNo, Integer bldgSubNo) throws Exception;

}
