package com.testplatform.platformbackend.entity;

public class FuncTcDetial {
    int id;
    int xid;
    String pre;
    String steps;
    String expected;
    String actual;

    public int getXid() {
        return xid;
    }

    public void setXid(int xid) {
        this.xid = xid;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPre() {
        return pre;
    }

    public void setPre(String pre) {
        this.pre = pre;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }


}
