package com.phenix.littlechess.controller;

import com.phenix.littlechess.sdo.ChessPiece;
import com.phenix.littlechess.sdo.OperatePlaning;
import com.phenix.littlechess.sdo.Result;
import com.phenix.littlechess.service.MatchService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/chess")
public class ChessMainController {
    @Inject
    private MatchService matchService;
    /**
     * 选择棋子
     * @param player 游戏玩家ID
     * @param offset 选择棋子的盘棋位置（4*8） 0-31
     * @return 新操作玩家ID，选择结果（0翻牌／1选定本方正棋子／-1忽略对方正棋子）；0时返回棋子颜色和内容；1时返回待操作位置及操作类型（移动／吃／兑）；-1时无
     * 说明：0时 游戏双方轮换
     */
    @RequestMapping(value = "/select", produces = "application/json")
    public Result<OperatePlaning> selectChess(Integer player, Integer offset) {
        Result<OperatePlaning> result = new Result();
        result.setErrCode(0);
        result.setData(matchService.selectChess(player, offset));

        return result;
    }

    /**
     * 棋子操作（对选定棋子进行移动／兑／吃三种操作）
     * @param playerID 操作玩家ID
     * @param offset1 选定棋子位置
     * @param offset2 待操作棋子位置
     * @return 新操作玩家ID，选定棋子的新位置（兑时可空）；输赢判定结果
     * 说明：操作完成时，游戏双方轮换（ko除外）
     */
    @RequestMapping(value = "/step", produces = "application/json")
    public Result<ChessPiece> stepChess(Integer playerID, Integer offset1, Integer offset2) {
        Result<ChessPiece> result = new Result();
        result.setErrCode(0);

        return result;
    }

}
