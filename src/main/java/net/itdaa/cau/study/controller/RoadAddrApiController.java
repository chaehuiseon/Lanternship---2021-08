package net.itdaa.cau.study.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.itdaa.cau.study.entity.FullRoadJibnAddress;
import net.itdaa.cau.study.service.RoadAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")  // API 를 호출하기 위한 주소 값입니다. 예: http://localhost:8080/api)
public class RoadAddrApiController {

    static final String resMsg       = "resMsg";
    static final String resRoadAddr  = "roadAddr";
    static final String resCnt       = "roadAddrCnt";

    ResponseEntity<?> entity = null;

    @Autowired
    RoadAddressService roadAddressService;

    @ApiOperation(value="조회할 도로명 주소(전체 or 일부)", notes="(도로명 주소의 일부 정보 or 정확한 주소)로 해당하는 도로명주소를 조회합니다.")
    @GetMapping(value="/roadAddr")  // API 를 호출하기 위한 주소 값이며 상위 주소의 하위주소값입니다. 예: http://localhost:8080/api/roadAddr)
    @ApiImplicitParams({
           @ApiImplicitParam(name = "searchRoadAddr", value = "검색할 도로명", required = true, dataType = "String", defaultValue = ""),
           @ApiImplicitParam(name = "searchRoadAddrBldgNumber", value = "검색할 빌딩명", required = false, dataType = "String", defaultValue = "")
    })
    public ResponseEntity<?> getRoadAddr(@RequestParam(value = "searchRoadAddr") String searchRoadAddress
                                        ,@RequestParam(value = "searchRoadAddrBldgNumber", required = false)  String searchBldgNumber) {


        Integer buildingMainNumber = null;      // DB에 조회하기 위한 도로명주소 건물본번
        Integer buildingSubNumber = null;       // DB에 조회하기 위한 도로명주소 건물부번

        HttpStatus resultStatus = HttpStatus.OK;   // 기본적으로 정상적으로 조회가 된다는 가정하에 반환하는 HTTP Status 값은 200 (OK) 입니다.

        List<FullRoadJibnAddress> fullRoadJibnAddressList;  // DB 조회 후 값이 있을 경우 RoadAddress 객체의 값 List 입니다.
        Map<String,Object> returnMap = new HashMap<>();          // 실제 API Return 되는 값이 들어가는 Map 객체 입니다.

        // 실행중 예외발생을 탐지하기 위하여
        try {

            if (searchBldgNumber != null) {

                String[] bldgNumberArray = searchBldgNumber.trim().split("-");

                if (bldgNumberArray.length == 1) {

                    buildingMainNumber = Integer.parseInt(searchBldgNumber.trim());

                }

                else if (bldgNumberArray.length == 2) {

                    buildingMainNumber = Integer.parseInt(bldgNumberArray[0]);
                    buildingSubNumber = Integer.parseInt(bldgNumberArray[1]);

                }
            }

            fullRoadJibnAddressList = roadAddressService.searchRoadAddress(searchRoadAddress,buildingMainNumber,buildingSubNumber);

            int searchResultListSize = fullRoadJibnAddressList.size(); // 최종적으로 DB에서 도로명 주소를 찾은 결과의 갯수

            // 도로명 주소가 검색된 결과가 없다면.
            if (searchResultListSize == 0) {
                resultStatus = HttpStatus.NOT_FOUND; // HTTP Status 코드는 NOT_FOUND 로 합니다. (404)
            }

            returnMap.put(resMsg, "정상처리되었습니다.");    // return 메세지는 "정상" 으로 하고
            returnMap.put(resRoadAddr, fullRoadJibnAddressList);  // return 주소정보는 조회 결과를 넣습니다.
            returnMap.put(resCnt, searchResultListSize); // return 건수정보는 조회 결과의 건수를 넣습니다.

        }
        // 실행중 예외가 발생할 경우
        catch (Exception e) {

            e.printStackTrace();
            log.error(e.getMessage()); // 오류 내용을 로그로 남깁니다.

            resultStatus = HttpStatus.SERVICE_UNAVAILABLE;    // HTTP Status 코드는 SERVICE_UNAVAILABLE 로 합니다. (503)
            returnMap.put(resMsg, "오류가 발생하였습니다.");      // return 메세지는 "오류발생" 으로 하고
            returnMap.put(resRoadAddr, "");                   // return 주소정보는 빈 값을 넣습니다.
            returnMap.put(resCnt, 0);                         // return 건수정보는 0 건으로 넣습니다.
        }
        // 예외여부 상관없이 최종적으로 수행.
        finally {
            entity = new ResponseEntity<>(returnMap, resultStatus);  // 최종적으로 API 결과 ResponseEntity 객체를 생성합니다.

            return entity;  // API 반환.
        }
    }
}
