package com.moa.druid.secondary.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2017/10/18.
 */
@Entity
@Table(name="t_message")
public class MessageInfo {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(strategy = "uuid",name="system-uuid")
    private String id;

    private String title;

    private String info;

    private String msg;

    public MessageInfo() {
    }

    public MessageInfo(String title, String info, String msg) {
        this();
        this.title = title;
        this.info = info;
        this.msg = msg;
    }

    public String getTitle() {
        return title;
    }

    public MessageInfo setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getId() {
        return id;
    }

    public MessageInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public MessageInfo setInfo(String info) {
        this.info = info;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public MessageInfo setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
