package com.phenix.littlechess.sdo;

/**
 * 棋子信息
 */
public class ChessPiece {
    /**
     * 棋子的盘棋位置（4*8）： 0-31
     */
    private Integer offset;
    /**
     * 棋子颜色：true-red, false-black
     */
    private Boolean color;
    /**
     * 附加其上的被动操作
     * TODO：暂时放到此处，待优化
     */
    private EffectedAction effectedAction;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Boolean getColor() {
        return color;
    }

    public void setColor(Boolean color) {
        this.color = color;
    }

    public EffectedAction getEffectedAction() {
        return effectedAction;
    }

    public void setEffectedAction(EffectedAction effectedAction) {
        this.effectedAction = effectedAction;
    }


}
