package com.phenix.littlechess.sdo;

/**
 * 对棋子的（被动）操作
 */
public enum EffectedAction {
    MOVE("移动", 1),
    SWALLOW("吃", 2),
    DRAW("兑", 3),
    TURNOVER("翻", 4);

    // 成员变量
    private String name;
    private int index;

    // 构造方法
    private EffectedAction(String name, int index) {
        this.name = name; this.index = index;
    }
    //覆盖方法
    @Override
    public String toString() {
        return this.index+"_"+this.name;
    }

}
