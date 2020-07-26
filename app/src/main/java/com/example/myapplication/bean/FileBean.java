package com.example.myapplication.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class FileBean {
    @Id
    @NotNull
    private String fid;   //⽂件ID
    private String pid;   //⽗⽬录ID
    private String fn;    //⽂件名
    private String upt;   //创建或者更新时间
    private String fs;    //⽂件⼤⼩
    @Generated(hash = 1262206485)
    public FileBean(@NotNull String fid, String pid, String fn, String upt,
            String fs) {
        this.fid = fid;
        this.pid = pid;
        this.fn = fn;
        this.upt = upt;
        this.fs = fs;
    }
    @Generated(hash = 1910776192)
    public FileBean() {
    }
    public String getFid() {
        return this.fid;
    }
    public void setFid(String fid) {
        this.fid = fid;
    }
    public String getPid() {
        return this.pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }
    public String getFn() {
        return this.fn;
    }
    public void setFn(String fn) {
        this.fn = fn;
    }
    public String getUpt() {
        return this.upt;
    }
    public void setUpt(String upt) {
        this.upt = upt;
    }
    public String getFs() {
        return this.fs;
    }
    public void setFs(String fs) {
        this.fs = fs;
    }

}
