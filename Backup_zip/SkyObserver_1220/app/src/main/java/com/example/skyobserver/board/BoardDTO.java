package com.example.skyobserver.board;

import java.sql.Timestamp;

public class BoardDTO {


    private String articleNo;
    private String subject;
    private String content;
    private String passwd;
    private String ref_Date;
    private String readCount;
    private String ref;
    private String re_Step;
    private String re_Lever;
    private String filename;
    private String filepath;
    private String nickName;
    private String email;

    public BoardDTO(){}

    public BoardDTO(String articleNo, String subject, String content, String passwd, String ref_Date, String readCount, String ref, String re_Step, String re_Lever, String filename, String filepath, String nickName, String email) {
        this.articleNo = articleNo;
        this.subject = subject;
        this.content = content;
        this.passwd = passwd;
        this.ref_Date = ref_Date;
        this.readCount = readCount;
        this.ref = ref;
        this.re_Step = re_Step;
        this.re_Lever = re_Lever;
        this.filename = filename;
        this.filepath = filepath;
        this.nickName = nickName;
        this.email = email;
    }

    public String getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(String articleNo) {
        this.articleNo = articleNo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getRef_Date() {
        return ref_Date;
    }

    public void setRef_Date(String ref_Date) {
        this.ref_Date = ref_Date;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRe_Step() {
        return re_Step;
    }

    public void setRe_Step(String re_Step) {
        this.re_Step = re_Step;
    }

    public String getRe_Lever() {
        return re_Lever;
    }

    public void setRe_Lever(String re_Lever) {
        this.re_Lever = re_Lever;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "BoardDTO{" +
                "articleNo='" + articleNo + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", passwd='" + passwd + '\'' +
                ", ref_Date='" + ref_Date + '\'' +
                ", readCount='" + readCount + '\'' +
                ", ref='" + ref + '\'' +
                ", re_Step='" + re_Step + '\'' +
                ", re_Lever='" + re_Lever + '\'' +
                ", filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
