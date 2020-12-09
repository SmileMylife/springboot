package com.example.springboot;

public enum Season {
    SPRING(1202, "工单综合查询"), SUMMER(1203, "工单池查询"), AUTUMN(1204, "待办区查询"), WINTER(1205, "工单查询");

    private int codeTypeCd;
    private String codeTypeNm;
    Season(int codeTypeCd, String codeTypeNm) {
        this.codeTypeCd = codeTypeCd;
        this.codeTypeNm = codeTypeNm;
    }


    @Override
    public String toString() {
        return this.codeTypeNm;
    }
}
