package com.phenix.littlechess.service;

import com.phenix.littlechess.sdo.ChessPiece;
import com.phenix.littlechess.sdo.OperatePlaning;

/**
 * 一场对局比赛
 */
public interface MatchService {
    void resetMatchPieces();
    OperatePlaning selectChess(Integer player, Integer offset);
}
