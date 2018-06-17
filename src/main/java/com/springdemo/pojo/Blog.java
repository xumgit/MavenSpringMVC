package com.springdemo.pojo;

public class Blog {
    private Integer id;

    private Integer authorid;

    private String title;

    private String creattime;

    private String mainbody;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Integer authorid) {
        this.authorid = authorid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getCreattime() {
        return creattime;
    }

    public void setCreattime(String creattime) {
        this.creattime = creattime == null ? null : creattime.trim();
    }

    public String getMainbody() {
        return mainbody;
    }

    public void setMainbody(String mainbody) {
        this.mainbody = mainbody == null ? null : mainbody.trim();
    }
}