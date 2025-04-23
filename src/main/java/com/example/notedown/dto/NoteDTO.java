package com.example.notedown.dto;

import jakarta.validation.constraints.NotBlank;

public class NoteDTO {

    private Long userId;
    @NotBlank (message = "title cannot be blank")
    private String title;
    private String content;

    public NoteDTO() {}

    public NoteDTO(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

}