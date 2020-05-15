package com.phenix.littlechess.manager;

import com.phenix.littlechess.sdo.ChessPiece;
import com.phenix.littlechess.sdo.EffectedActionEnum;
import com.phenix.littlechess.sdo.PieceTypeEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 象棋算法管理器
 * TODO 算法待优化
 */
public class ChessManager {
    /**
     * 在棋盘布局中，获得当前棋子可选的移动位置和操作
     * @param curPiece 当前所选的棋子
     * @param nowAllPieces 最新的棋盘布局
     * @return
     */
    public static ChessPiece[] getStepPieces(ChessPiece curPiece, ChessPiece[] nowAllPieces) {
        ArrayList results = new ArrayList();
        Map<Integer, ChessPiece> nowAllMap = new HashMap<>();
        for(ChessPiece item: nowAllPieces) {
            nowAllMap.put(item.getOffset(), item);
        }
        Integer curOffset = curPiece.getOffset();
        ChessPiece testPiece = null;

        // NOTE: 获得可移动／吃的位置
        Integer rigthOffset = curOffset + 1;
        if(rigthOffset % 8 != 0) {
            // NOTE：代表向右可以移动
            GetMoveStep(curPiece, nowAllMap, rigthOffset, results);
        }
        Integer leftOffset = curOffset - 1;
        if((leftOffset+1) % 8 != 0) {
            // NOTE：代表向左可以移动
            GetMoveStep(curPiece, nowAllMap, leftOffset, results);
        }
        Integer upOffset = curOffset - 8;
        if(upOffset >= 0) {
            // NOTE：代表向up可以移动
            GetMoveStep(curPiece, nowAllMap, upOffset, results);
        }
        Integer downOffset = curOffset + 8;
        if(downOffset <= 31) {
            // NOTE：代表向down可以移动
            GetMoveStep(curPiece, nowAllMap, downOffset, results);
        }

        // NOTE: 判断并获取炮可吃的位置
        if(curPiece.getType().equals(PieceTypeEnum.PAO)) {
            for(int i = 0 ; i < 4 ; i++) {
                ChessPiece tmp = GetSkipPiece(i+1, curOffset, nowAllMap);
                if(tmp !=null) {
                    results.add(tmp);
                }
            }
        }

        return (ChessPiece[])results.toArray();
    }

    /**
     * 获取指定方向 炮能打到的棋子
     * @param directtion
     * @param curOffset
     * @param nowAllMap
     * @return
     */
    private static ChessPiece GetSkipPiece(Integer directtion, Integer curOffset, Map<Integer, ChessPiece> nowAllMap) {
        ArrayList<ChessPiece> tmpList = new ArrayList<>();
        Integer offset = GetNextOffect(directtion, curOffset);
        while(isOutOfBorder(directtion, offset)) {
            if(nowAllMap.get(offset).getType().getType() > 0) {
                tmpList.add(nowAllMap.get(offset));
            }
            offset = GetNextOffect(directtion, offset);
        }
        if(tmpList.size()>=2) {
            return tmpList.get(1);
        } else {
            return null;
        }
    }

    /**
     * 判断上下左右是否出边界
     * @param direction 1-右 2-左 3-up 4-down
     * @return true-not
     */
    private static boolean isOutOfBorder(int direction, int curOffset) {
        if(1 == direction) {
            return (curOffset % 8 != 0);
        } else if(2==direction) {
            return ((curOffset+1) % 8 != 0);
        } else if(3==direction) {
            return (curOffset >= 0);
        } else if(4==direction) {
            return (curOffset <= 31);
        } else {
            return false;
        }
    }

    private static int GetNextOffect(int direction, int curOffset) {
        if(1 == direction) {
            return curOffset + 1;
        } else if(2==direction) {
            return curOffset - 1;
        } else if(3==direction) {
            return curOffset - 8;
        } else if(4==direction) {
            return curOffset + 8;
        } else {
            return 0;
        }
    }

    private static void GetMoveStep(ChessPiece curPiece, Map<Integer, ChessPiece> nowAllMap, Integer moveOffset, ArrayList results) {
        ChessPiece testPiece = nowAllMap.get(moveOffset);
        if(testPiece.getType().getType() < 0) {
            testPiece.setEffectedAction(EffectedActionEnum.MOVE);
            results.add(testPiece);
        } else {
            // NOTE: 判断是否可以吃/兑
            boolean isStepPiece = ComparePieceType(curPiece, testPiece);
            if(isStepPiece) {
                results.add(testPiece);
            }
        }
    }

    /**
     * 比较相邻两个棋子的子力强弱. 前提是两个棋子color不同
     * @param curPiece A
     * @param testPiece B
     * @return false-忽略 true-可兑／吃
     */
    private static boolean ComparePieceType(ChessPiece curPiece, ChessPiece testPiece) {
        // 1-可吃；0-可兑；-1-忽略
        int check = -1;
        // 如果目标棋子为反面，或与cur同色 则忽略
        if(!curPiece.getChessFace() ||  testPiece.getColor() == curPiece.getColor()) return false;

        if(curPiece.getType().equals(PieceTypeEnum.ZU) && testPiece.getType().equals(PieceTypeEnum.JIANG)) {
            check = 1;
        } else if(curPiece.getType().getType() > testPiece.getType().getType()) {
            check = 1;
        } else if(curPiece.getType().getType() == testPiece.getType().getType()) {
            check = 0;
        }

        if(check > 0) {
            testPiece.setEffectedAction(EffectedActionEnum.SWALLOW);
        }
        if(check == 0 ) {
            testPiece.setEffectedAction(EffectedActionEnum.DRAW);
        }

        return false;
    }
}
