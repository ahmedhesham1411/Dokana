package com.example.aldokana.Model;

public class CodeResponse {
    boolean status;
    CodeData data;

    public CodeResponse(boolean status, CodeData data) {
        this.status = status;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public CodeData getData() {
        return data;
    }

    public void setData(CodeData data) {
        this.data = data;
    }
}
