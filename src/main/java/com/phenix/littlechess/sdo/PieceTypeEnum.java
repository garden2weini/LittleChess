package com.phenix.littlechess.sdo;

public enum PieceTypeEnum {
    // 将-士-象-马-车-pao-zu
    JIANG(7,"将"),
    SHI(6, "士"),
    XIANG(5, "象"),
    MA(4,"马"),
    JU(3,"车"),
    PAO(2, "炮"),
    ZU(1, "卒"),
    NULL(-1, "NULL");

    // 成员变量
    private String name;
    private int type;

    // 构造方法
    PieceTypeEnum(int type, String name) {
        this.name = name; this.type = type;
    }

    public Integer getType() {return this.type;}

    public static PieceTypeEnum getPieceTypeEnumbyType(int type) {
        for (PieceTypeEnum item : PieceTypeEnum.values()) {
            if (type == item.type) {
                return item;
            }
        }
        return NULL;
    }
}
