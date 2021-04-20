package com.ak.dto;

import lombok.Data;

import java.util.List;

@Data
public class CountryInfo {

    private int countryId;
    private String countryName;
    private List<LeagueInfo> leagueInfoList;
}
