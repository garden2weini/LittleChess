package com.phenix.littlechess.service;

import com.phenix.littlechess.sdo.ChessPiece;
import com.phenix.littlechess.sdo.OperatePlaning;

/**
 * 一场对局比赛
 */
public interface MatchService {
    /**
     * 重置棋盘数据
     */
    void resetMatchPieces();
    /**
     * 点击棋盘中某个棋子的处理（翻面／圈定待操作棋子-一元+二元／无效选择）
     * @param player
     * @param offset
     * @return
     */
    OperatePlaning selectChess(Integer player, Integer offset);
}
