package com.phenix.littlechess.sdo;

public class Result<T> extends BaseDO {
    private Integer errCode;
    private String errMsg;
    private boolean success;
    private T data;
    // 下一次操作的玩家ID
    private Integer nextPlayer;

    public Integer getNextPlayer() {
        return nextPlayer;
    }

    public void setNextPlayer(Integer nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static final <T> Result<T> newInstance() {
        return new Result<T>();
    }

}
