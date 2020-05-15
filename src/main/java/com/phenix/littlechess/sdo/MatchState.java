package com.phenix.littlechess.sdo;

public class MatchState {
    public Integer getKnownCnt() {
        return KnownCnt;
    }

    public void setKnownCnt(Integer knownCnt) {
        KnownCnt = knownCnt;
    }

    /**
     * 已翻开的棋子数量
     */
    private Integer KnownCnt = 0;
}
