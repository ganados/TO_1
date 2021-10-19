package com.company.currencyexchange.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor(staticName = "of")
public class Currency {
    private String name;
    private String code;
    private int converter;
    private double rate;
}
