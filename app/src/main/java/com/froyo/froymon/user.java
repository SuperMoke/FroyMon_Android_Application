package com.froyo.froymon;

public class user {

    private String studentname;
    private String studentid;
    private String ccaemail;
    private String computernumber;
    private String computerstatus;
    private String labid;

    public  user(){

    }
    public user(String studentname, String studentid, String ccaemail, String computernumber, String computerstatus,String labid) {
        this.studentname = studentname;
        this.studentid = studentid;
        this.ccaemail = ccaemail;
        this.computernumber = computernumber;
        this.computerstatus = computerstatus;
        this.labid = labid;
    }

    public String getLabid() {
        return labid;
    }

    public void setLabid(String labid) {
        this.labid = labid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getCcaemail() {
        return ccaemail;
    }

    public void setCcaemail(String ccaemail) {
        this.ccaemail = ccaemail;
    }

    public String getComputernumber() {
        return computernumber;
    }

    public void setComputernumber(String computernumber) {
        this.computernumber = computernumber;
    }

    public String getComputerstatus() {
        return computerstatus;
    }

    public void setComputerstatus(String computerstatus) {
        this.computerstatus = computerstatus;
    }
}
