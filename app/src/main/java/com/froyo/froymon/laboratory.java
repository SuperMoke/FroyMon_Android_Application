package com.froyo.froymon;

public class laboratory {

    private String labname;
    private String labid;
    private String id;



    public laboratory(String id,String labname, String labid) {
        this.id = id;
        this.labname = labname;
        this.labid = labid;
    }

    public String getLabName() {
        return labname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLabName(String name) {
        this.labname = labname;
    }

    public String getLabId() {
        return labid;
    }

    public void setLabId(String labid) {
        this.labid = labid;
    }
}
