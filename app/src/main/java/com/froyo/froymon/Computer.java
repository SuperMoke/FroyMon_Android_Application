package com.froyo.froymon;

public class Computer {
    private String computernumber;
    private String computerstatus;

    public Computer(){

    }

    public Computer(String computernumber, String computerstatus){
        this.computernumber = computernumber;
        this.computerstatus = computerstatus;
    }

    public String getComputernumber() {
        return computernumber;
    }

    public void setComputernumber(String computernumber) {
        this.computernumber = computernumber;
    }

    public String getComputerstatus() {return computerstatus;}

    public void setComputerstatus(String computerstatus) {
        this.computerstatus = computerstatus;
    }
}
