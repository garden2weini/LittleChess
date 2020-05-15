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
     * 棋子颜色：1-red, 0-black
     */
    private Integer color;

    public Boolean getChessFace() {
        return chessFace;
    }

    public void setChessFace(Boolean chessFace) {
        this.chessFace = chessFace;
    }

    /**
     * 棋子正反，true-正 false-反
     */
    private Boolean chessFace = false;

    public PieceTypeEnum getType() {
        return type;
    }

    public void setType(PieceTypeEnum type) {
        this.type = type;
    }
    public void setType(Integer type) {
        this.type = PieceTypeEnum.getPieceTypeEnumbyType(type);
    }

    /**
     * 棋子类型：将-士-象-马-车-pao-zu
     */
    private PieceTypeEnum type;
    /**
     * 附加其上的被动操作
     * TODO：暂时放到此处，待优化
     */
    private EffectedActionEnum effectedAction;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public EffectedActionEnum getEffectedAction() {
        return effectedAction;
    }

    public void setEffectedAction(EffectedActionEnum effectedAction) {
        this.effectedAction = effectedAction;
    }

    @Override
    public String toString() {
        return this.offset + "_" + this.color + "_" + this.type.name();
    }

}
