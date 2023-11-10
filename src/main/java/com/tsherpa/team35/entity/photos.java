package com.tsherpa.team35.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class photos {
    private int photo_no;
    private int market_id;
    private String photo_file;
    private String realname;
}
