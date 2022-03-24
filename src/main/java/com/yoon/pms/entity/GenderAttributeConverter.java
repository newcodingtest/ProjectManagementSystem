package com.yoon.pms.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class GenderAttributeConverter implements AttributeConverter<String, Integer> {
 
    @Override
    public Integer convertToDatabaseColumn(String s) {
        if ("중단".equals(s)) {
            return 110;
        } else if ("진행전".equals(s)) {
            return 111;
        }else if ("진행중".equals(s)) {
            return 112;
        }else if ("완료".equals(s)) {
            return 113;
        }
        return 0;
    }
 
    @Override
    public String convertToEntityAttribute(Integer code) {
        if (110 == code) {
            return "중단";
        } else if (111 == code) {
            return "진행전";
        }else if (112 == code) {
            return "진행중";
        }else if (113 == code) {
            return "완료";
        }
        return "뭐지?";
    }
}