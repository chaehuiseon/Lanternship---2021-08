package net.itdaa.cau.study.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class FullRoadJibnAddress {

    String fullRoadAddress;

    String postCode;

    String repJibunAddress;

    List<String> relatedJibunAddressList;
}
