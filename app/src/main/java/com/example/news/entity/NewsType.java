package com.example.news.entity;

import java.util.List;

/**
 * Created by 86409 on 2016/11/28.
 */

public class NewsType {
    //分类编号
    private int gid;
    private String group;
    List<SubType> subgrp;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<SubType> getSubgrp() {
        return subgrp;
    }

    public void setSubgrp(List<SubType> subgrp) {
        this.subgrp = subgrp;
    }


}
