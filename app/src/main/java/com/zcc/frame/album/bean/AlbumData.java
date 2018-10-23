package com.zcc.frame.album.bean;

import java.util.List;

public class AlbumData {
    private String date;
    private List<MaterialBean> list;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<MaterialBean> getList() {
        return list;
    }

    public void setList(List<MaterialBean> list) {
        this.list = list;
    }
}
