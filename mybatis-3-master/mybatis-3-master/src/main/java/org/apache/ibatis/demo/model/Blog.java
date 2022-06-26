package org.apache.ibatis.demo.model;


import java.io.Serializable;

public class Blog implements Serializable {
  private int id;
  private String title;
  private String author;

  public Blog() {
  }

  public Blog(int id, String title, String author) {
    this.id = id;
    this.title = title;
    this.author = author;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
}
