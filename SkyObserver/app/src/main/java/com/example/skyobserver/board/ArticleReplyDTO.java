package com.example.skyobserver.board;

public class ArticleReplyDTO {

    private String articleNo;
    private String replyunum;
    private String replycon;
    private String name;
    private String sumnail;
    private String heart;
    private String replydate;

    public  ArticleReplyDTO(){}

    public ArticleReplyDTO(String articleNo, String replyunum, String replycon, String name, String sumnail, String heart, String replydate) {
        this.articleNo = articleNo;
        this.replyunum = replyunum;
        this.replycon = replycon;
        this.name = name;
        this.sumnail = sumnail;
        this.heart = heart;
        this.replydate = replydate;
    }

    public String getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(String articleNo) {
        this.articleNo = articleNo;
    }

    public String getReplyunum() {
        return replyunum;
    }

    public void setReplyunum(String replyunum) {
        this.replyunum = replyunum;
    }

    public String getReplycon() {
        return replycon;
    }

    public void setReplycon(String replycon) {
        this.replycon = replycon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSumnail() {
        return sumnail;
    }

    public void setSumnail(String sumnail) {
        this.sumnail = sumnail;
    }

    public String getHeart() {
        return heart;
    }

    public void setHeart(String heart) {
        this.heart = heart;
    }

    public String getReplydate() {
        return replydate;
    }

    public void setReplydate(String replydate) {
        this.replydate = replydate;
    }

    @Override
    public String toString() {
        return "ArticleReplyDTO{" +
                "articleNo='" + articleNo + '\'' +
                ", replyunum='" + replyunum + '\'' +
                ", replycon='" + replycon + '\'' +
                ", name='" + name + '\'' +
                ", sumnail='" + sumnail + '\'' +
                ", heart='" + heart + '\'' +
                ", replydate='" + replydate + '\'' +
                '}';
    }
}


