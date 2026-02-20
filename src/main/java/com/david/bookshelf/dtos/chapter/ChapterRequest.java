package com.david.bookshelf.dtos.chapter;


/**
 * DTO para instanciação de capitulos
 */
public class ChapterRequest {
    String title;
    String summary;

    public ChapterRequest() {
    }

    public ChapterRequest(String title, String summary) {
        this.title = title;
        this.summary = summary;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

}
