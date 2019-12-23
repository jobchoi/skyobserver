package com.example.skyobserver.notics;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoticeDTO {

    String title,content,writedate;


    public NoticeDTO() {
    }

    public NoticeDTO(String title, String content, String writedate) {
        this.title = title;
        this.content = content;





        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");



        Date date= new Date(Long.parseLong(writedate));
        String timeInFormat = sdf.format(date);



        this.writedate = timeInFormat;
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

    public String getWritedate() {
        return writedate;
    }

    public void setWritedate(String writedate) {
        this.writedate = writedate;
    }

    @Override
    public String toString() {
        return "NoticeDTO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writedate='" + writedate + '\'' +
                '}';
    }
}
