package net.itdaa.cau.study.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="ROAD_ADDR_INTG")
public class RoadAddrIntg {

    @Id
    @Column(name = "MGR_NO")
    private String mgrNO;

    @Column(name = "SIDO_NAME")
    private String sidoName;

    @Column(name = "SIGUNGU_NAME")
    private String sigunguName;

    @Column(name = "ROAD_NAME")
    private String roadName;

    @Column(name = "BLDG_MAIN_NO")
    private Integer bldgMainNo;

    @Column(name = "BLDG_SUB_NO")
    private Integer bldgSubNo;

    @Column(name = "CONST_BLDG_NAME")
    private String constBldgName;

    @Column(name = "POST_CODE")
    private String postCode;

    @Column(name = "FULL_ROAD_ADDR")
    private String fullRoadAddr;
}
