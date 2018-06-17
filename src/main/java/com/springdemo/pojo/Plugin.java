package com.springdemo.pojo;

public class Plugin {
    private Integer id;

    private String sender;

    private String received;

    private String link;

    private String status;

    private String hidden;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender == null ? null : sender.trim();
    }

    public String getReceived() {
        return received;
    }

    public void setReceived(String received) {
        this.received = received == null ? null : received.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden == null ? null : hidden.trim();
    }
}