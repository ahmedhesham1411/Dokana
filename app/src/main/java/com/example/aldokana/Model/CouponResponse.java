package com.example.aldokana.Model;

public class CouponResponse {
    boolean status;
    CouponDataModel msg;

    public CouponResponse(boolean status, CouponDataModel msg) {
        this.status = status;
        this.msg = msg;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public CouponDataModel getMsg() {
        return msg;
    }

    public void setMsg(CouponDataModel msg) {
        this.msg = msg;
    }
}
