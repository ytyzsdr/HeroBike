package com.danchexia.bikehero.main.set.bean;

import com.danchexia.bikehero.api.ApiProperty;

/**
 * Created by farley on 17/5/24.
 * description:
 */

public class SetData {
    @ApiProperty("id")
    private Long id;
    @ApiProperty("title")
    private String title;
    @ApiProperty("content")
    private String content;
   
    public SetData() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

