package com.phenix.littlechess.service.impl;

import com.phenix.littlechess.manager.ChessManager;
import com.phenix.littlechess.sdo.ChessPiece;
import com.phenix.littlechess.sdo.MatchState;
import com.phenix.littlechess.sdo.OperatePlaning;
import com.phenix.littlechess.sdo.PieceTypeEnum;
import com.phenix.littlechess.service.MatchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

@Service
public class MatchServiceImpl implements MatchService {
    private Integer locker = new Integer(1);
    // TODO 单机模式，仅支持一局比赛。扩展时可以将比赛对应双人比赛，并在缓存中操作
    private volatile static ChessPiece[] ChessPieces = null;
    // 记录两个玩家所属的棋子颜色，-1为尚未确定
    private volatile static Integer[] PLAYERS = new Integer[2];
    // 记录下一次操作的玩家
    private volatile static Integer CUR_PLAYER = null;
    // 记录棋盘信息
    private volatile static MatchState MATCH_STATE = new MatchState();

    // 所有棋子数据(颜色+类型)的数值表示
    private final static ArrayList<Integer> RAW_PIECES = new ArrayList<Integer>(){
        {
            add(7); add(1); add(1); add(1); add(1); add(1); add(2); add(2);
            add(3); add(3); add(4); add(4); add(5); add(5); add(6); add(6);
            add(17); add(11); add(11); add(11); add(11); add(11); add(12); add(12);
            add(13); add(13); add(14); add(14); add(15); add(15); add(16); add(16);}
    };

    /**
     * 重置棋盘
     */
    @Override
    public void resetMatchPieces() {
        synchronized (locker) {
            this.initMatchPieces();
            PLAYERS[0] = -1;
            PLAYERS[1] = -1;
        }

    }

    @Override
    public OperatePlaning selectChess(Integer player, Integer offset) {
        if(ChessPieces == null) {
            resetMatchPieces();
        }

        OperatePlaning plan = new OperatePlaning();

        if(CUR_PLAYER  == null) {
            CUR_PLAYER = player;
        } else if(CUR_PLAYER != player) {
            // NOTE 无效操作
            plan.setChessState(-1);
            plan.setNowAllPieces(ChessPieces);
            return plan;
        }

        System.out.println(ChessPieces.length + "-Offset:" + offset);
        ChessPiece curPiece = ChessPieces[offset];
        if(!curPiece.getChessFace()) {
            // NOTE: 当前棋子为反面
            // 翻为正面，标记棋子
            curPiece.setChessFace(true);

            if(MATCH_STATE.getKnownCnt() == 0) { // 如果为开局，则当前翻开的棋子颜色为当前玩家；对家相反
                PLAYERS[player] = curPiece.getColor();
                PLAYERS[ (player + 1) % 2] = (curPiece.getColor() + 1) % 2;
            }
            // 更换当前玩家
            CUR_PLAYER =  (player + 1) % 2;
            plan.setNextPlayer(CUR_PLAYER);
            // 翻牌
            plan.setChessState(0);
            plan.setStepPieces(new ChessPiece[]{curPiece});
        } else if(curPiece.getColor() == PLAYERS[player]) {
            // NOTE: 正面+本玩家棋子, 返回待操作棋子、其位置及操作类型（移动／吃／兑）
            ChessPiece[] stepPieces = ChessManager.getStepPieces(curPiece, ChessPieces);
            // 玩家不变
            plan.setNextPlayer(player);
            // 选定本方棋子
            plan.setChessState(1);
            plan.setStepPieces(stepPieces);
        } else {
            // NOTE: 正面+对家棋子 无效操作
            plan.setChessState(-1);
            plan.setStepPieces(null);
            plan.setNextPlayer(player);
        }

        // 记录棋盘最新信息
        plan.setNowAllPieces(ChessPieces);
        return plan;
    }

    /**
     * 洗牌
     */
    private void initMatchPieces() {
        ChessPieces = new ChessPiece[32];
        // 复制一套棋子
        ArrayList<Integer> rawList = new ArrayList<Integer>(){};
        for (Integer item : RAW_PIECES) {
            rawList.add(item.intValue());
        }
        // shuffle pieces
        for(int i = 0; i < ChessPieces.length; i++) {
            ChessPieces[i] = new ChessPiece();
            ChessPieces[i].setOffset(i);
            ChessPieces[i].setType(PieceTypeEnum.JIANG);
            Random random = new Random(Calendar.getInstance().getTimeInMillis());
            int randomIndex = random.nextInt(rawList.size());
            //System.out.print(rawList.size() + "-" + randomIndex + ":");
            Integer randomPiece = rawList.remove(randomIndex);

            ChessPieces[i].setColor(randomPiece/10);
            ChessPieces[i].setType(randomPiece%10);
            //System.out.println(randomPiece + "::" + ChessPieces[i].toString());
        }
    }
}
