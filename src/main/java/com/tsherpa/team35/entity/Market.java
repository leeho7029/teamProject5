package com.tsherpa.team35.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Market {
    private int market_no;
    private String title;
    private int price;
    private String description;
    private int user_id;
    private String actice;
    private String condition;
    private String regdate;
    private int category_id;
    private String addr1;
    private String addr2;
    private String postcode;
}
