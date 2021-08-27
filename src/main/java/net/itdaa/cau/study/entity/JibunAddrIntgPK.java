package net.itdaa.cau.study.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JibunAddrIntgPK implements Serializable {

    private String mgrNo;
    private Integer seqNo;
}
