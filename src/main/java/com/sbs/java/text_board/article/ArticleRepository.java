package com.sbs.java.text_board.article;

import com.sbs.java.text_board.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ArticleRepository {
  private List<Article> articles;
  private int lastId;

  public ArticleRepository() {
    articles = new ArrayList<>();
    lastId = 0;

    makeTestData();
  }

  void makeTestData() {
    IntStream.rangeClosed(1, 100)
        .forEach(i -> write("제목" + i, "내용" + i, "익명", i));
  }

  public int write(String subject, String content, String name, int memberId) {
    int id = ++lastId;

    Article article = new Article(id, subject, content, name, memberId);

    articles.add(article);

    return id;
  }

  public List<Article> getSortedArticles(String orderBy) {
    List<Article> sortedArticles = articles;

    if(orderBy.equals("idAsc")) {
      return articles;
    }

    if (orderBy.equals("idDesc")) {
      sortedArticles = Util.reverseList(articles);
    }

    return sortedArticles;
  }

  public List<Article> getArticles(String searchKeyword, String orderBy) {
    List<Article> filteredArticls = getSortedArticles(orderBy);

    if (!searchKeyword.trim().isEmpty()) {
      filteredArticls = new ArrayList<>();

      for (Article article : articles) {
        boolean matched = article.getSubject().contains(searchKeyword) || article.getContent().contains(searchKeyword);

        if (matched) filteredArticls.add(article);
      }
    }

    return filteredArticls;
  }

  public void modify(int id, String subject, String content) {
    Article article = findById(id);

    if(article != null) {
      article.setSubject(subject);
      article.setContent(content);
    }
  }

  public void delete(int id) {
    Article article = findById(id);

    if(article != null) {
      articles.remove(article);
    }
  }

  public Article findById(int id) {
    return articles.stream()
        .filter(article -> article.getId() == id)
        .findFirst()
        .orElse(null);
  }
}
