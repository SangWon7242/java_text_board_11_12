package com.sbs.java.text_board.article;

import com.sbs.java.text_board.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
  private List<Article> articles;
  private int lastId;

  public ArticleRepository() {
    articles = new ArrayList<>();
    lastId = 0;
  }

  public int write(String subject, String content) {
    int id = ++lastId;

    Article article = new Article(id, subject, content);

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
        boolean matched = article.subject.contains(searchKeyword) || article.content.contains(searchKeyword);

        if (matched) filteredArticls.add(article);
      }
    }

    return filteredArticls;
  }

  public void modify(int id, String subject, String content) {
    Article article = findById(id);

    if(article != null) {
      article.subject = subject;
      article.content = content;
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
        .filter(article -> article.id == id)
        .findFirst()
        .orElse(null);
  }
}
