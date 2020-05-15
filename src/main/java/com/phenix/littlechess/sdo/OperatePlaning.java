package com.phenix.littlechess.sdo;

/**
 * 记录当选择一个棋子后，后续的可能操作及棋子
 */
public class OperatePlaning {
    // 如有，下一次操作的玩家ID
    private Integer nextPlayer;

    // 本玩家选择棋子对应的操作结果（0翻牌／1选定本方正棋子／-1忽略对方正棋子）
    private Integer chessState;
    // 本玩家下一步可以操作的棋子及动作
    private ChessPiece[] stepPieces;

    // 棋盘最新信息，有哪些棋子，在什么位置，是否反面，颜色等
    private ChessPiece[] nowAllPieces;

    public ChessPiece[] getNowAllPieces() {
        return nowAllPieces;
    }

    public void setNowAllPieces(ChessPiece[] nowAllPieces) {
        this.nowAllPieces = nowAllPieces;
    }


    public Integer getChessState() {
        return chessState;
    }

    public void setChessState(Integer chessState) {
        this.chessState = chessState;
    }

    public ChessPiece[] getStepPieces() {
        return stepPieces;
    }

    public void setStepPieces(ChessPiece[] stepPieces) {
        this.stepPieces = stepPieces;
    }


    public Integer getNextPlayer() {
        return nextPlayer;
    }
    public void setNextPlayer(Integer nextPlayer) {
        this.nextPlayer = nextPlayer;
    }


}
