package com.bwie.jd_demo.utils;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "ProductTitle")
public class ProductTitle {
    @Id(autoincrement = true)
    private Long id;
    private String title;

    @Generated(hash = 1771872915)
    public ProductTitle(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    @Generated(hash = 1537374963)
    public ProductTitle() {
    }

    @Override
    public String toString() {
        return "ProductTitle{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
