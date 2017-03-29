package com.xzteam.pizzeria;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("myserver")
public class ServerProperties {
    private Long defaultId;
    private String imgpath;

    public Long getDefaultId() {
        return defaultId;
    }

    public void setDefaultId(Long defaultId) {
        this.defaultId = defaultId;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }
}
