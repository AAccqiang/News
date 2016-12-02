package com.example.news.gdentity;

import com.example.news.entity.SubType;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 86409 on 2016/12/2.
 */

@Entity(nameInDb = "news_types")
public class GdNewsType {

    @Id(autoincrement = true)
    private long id;

    private int subid;

    private String subType;

    @Generated(hash = 344565117)
    public GdNewsType(long id, int subid, String subType) {
        this.id = id;
        this.subid = subid;
        this.subType = subType;
    }

    @Generated(hash = 812000767)
    public GdNewsType() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSubid() {
        return subid;
    }

    public void setSubid(int subid) {
        this.subid = subid;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }
 
}
