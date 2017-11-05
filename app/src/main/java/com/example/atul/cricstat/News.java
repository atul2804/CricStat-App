package com.example.atul.cricstat;

/**
 * Created by Atul on 02-10-2017.
 */

public class News {
    public String title,author,image,date,article;
    public int id;

    News(){

    }

    News(int id,String title,String author,String image,String date,String article){
        this.id = id;
        this.title=title;this.author=author;
        this.image=image;this.date=date;
        this.article=article;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
