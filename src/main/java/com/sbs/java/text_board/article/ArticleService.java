package com.sbs.java.text_board.article;

import com.sbs.java.text_board.container.Container;

import java.util.List;

public class ArticleService {
  private ArticleRepository articleRepository;

  public ArticleService() {
    articleRepository = Container.articleRepository;
  }

  public int write(String subject, String content, String name, int memberId, int boardId) {
    return articleRepository.write(subject, content, name, memberId, boardId);
  }

  public List<Article> getArticles(String searchKeyword, String searchKeywordTypeCode, String orderBy, int boardId) {
    return articleRepository.getArticles(searchKeyword, searchKeywordTypeCode, orderBy, boardId);
  }

  public Article findById(int id) {
    return articleRepository.findById(id);
  }

  public void modify(int id, String subject, String content) {
    articleRepository.modify(id, subject, content);
  }

  public void delete(int id) {
    articleRepository.delete(id);
  }
}
