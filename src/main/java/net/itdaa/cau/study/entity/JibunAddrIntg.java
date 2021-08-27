package net.itdaa.cau.study.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="JIBUN_ADDR_INTG")
@IdClass(JibunAddrIntgPK.class)
public class JibunAddrIntg {

    @Id
    @Column(name = "MGR_NO")
    private String mgrNo;

    @Id
    @Column(name = "SEQ_NO")
    private Integer seqNo;

    @Column(name = "REGN_CODE")
    private String regnCode;

    @Column(name = "SIDO_NAME")
    private String sidoName;

    @Column(name = "SIGUNGU_NAME")
    private String sigunguName;

    @Column(name = "UMD_NAME")
    private String umdName;

    @Column(name = "SAN_YN")
    private String sanYn;

    @Column(name = "JIBN_MAIN_NO")
    private Integer jibnMainNo;

    @Column(name = "JIBN_SUB_NO")
    private Integer jibnSubNo;

    @Column(name = "PRES_YN")
    private String presYn;

    @Column(name = "FULL_JIBN_ADDR")
    private String fullJibnAddr;

    public Boolean getPresYn() {
        return this.presYn.equals("0") ? false : true;
    }
}


