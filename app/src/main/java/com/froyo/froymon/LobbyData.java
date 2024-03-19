package com.froyo.froymon;

public class LobbyData {

    private String name;

    private String id;
    private String email;
    private String subcode;
    private String classcode;
    private String classpress;
    private String labname;
    private String labid;

    private String randomCode;

    // Required default constructor for Firebase
    public LobbyData() {
    }

    public LobbyData(String name, String id, String email, String subcode, String classcode, String classpress, String labname, String labid, String randomCode) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.subcode = subcode;
        this.classcode = classcode;
        this.classpress = classpress;
        this.labname = labname;
        this.labid = labid;
        this.randomCode = randomCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubcode() {
        return subcode;
    }

    public void setSubcode(String subcode) {
        this.subcode = subcode;
    }

    public String getClasscode() {
        return classcode;
    }

    public void setClasscode(String classcode) {
        this.classcode = classcode;
    }

    public String getClasspress() {
        return classpress;
    }

    public void setClasspress(String classpress) {
        this.classpress = classpress;
    }

    public String getLabname() {
        return labname;
    }

    public void setLabname(String labname) {
        this.labname = labname;
    }

    public String getLabid() {
        return labid;
    }

    public void setLabid(String labid) {
        this.labid = labid;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }
}
