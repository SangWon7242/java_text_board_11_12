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
        .forEach(i -> write("제목" + i, "내용" + i, "익명", i, (int) (Math.random() * 2) + 1));
  }

  public int write(String subject, String content, String name, int memberId, int boardId) {
    int id = ++lastId;

    String regDate = Util.getNowDateStr();
    String updateDate = regDate;

    Article article = new Article(id, regDate, updateDate, subject, content, name, memberId, boardId, 0);

    articles.add(article);

    return id;
  }

  public List<Article> getSortedArticles(String orderBy) {

    List<Article> sortedArticles = articles;

    if (orderBy.equals("idAsc")) {
      return articles;
    }

    if (orderBy.equals("idDesc")) {
      sortedArticles = Util.reverseList(articles);
    }

    return sortedArticles;
  }

  public List<Article> getArticles(String searchKeyword, String searchKeywordTypeCode, String orderBy, int boardId, int limitFrom, int limitCount) {

    List<Article> filteredArticles = getSortedArticles(orderBy);

    // boardId 로직 시작
    if (boardId > 0) {
      List<Article> boardArticles = filteredArticles.stream()
          .filter(article -> article.getBoardId() == boardId)
          .toList();

      return boardArticles;
    }
    // boardId 로직 끝

    // 정렬 로직 시작
    if (!searchKeyword.trim().isEmpty()) {
      List<Article> sortedArticles = getSortedArticles(orderBy);
      filteredArticles = sortedArticles.stream()
          .filter(article -> switch (searchKeywordTypeCode) {
            // 검색 키워드가 제목에만 있는 경우
            case "subject" -> article.getSubject().contains(searchKeyword);

            // 검색 키워드가 내용에만 있는 경우
            case "content" -> article.getContent().contains(searchKeyword);

            // 검색 키워드가 제목과 내용 둘다 포함 되어 있는 경우
            case "subject,content" ->
                article.getSubject().contains(searchKeyword) && article.getContent().contains(searchKeyword);
            default -> true; // 검색 유형이 없다면 필터링 x
          }).toList();
    }
    // 정렬 로직 끝

    int totalArticlesSize = filteredArticles.size();
    int endIndex = Math.min(limitFrom + limitCount, totalArticlesSize); // 끝 인덱스는 전체 크기를 초과하지 않도록 설정

    if (limitFrom >= totalArticlesSize) {
      return List.of(); // 시작 인덱스가 전체 크기 이상이면 빈 리스트 반환
    }

    return filteredArticles.subList(limitFrom, endIndex); // 지정된 범위의 게시물 반환
  }

  public void modify(int id, String subject, String content) {
    Article article = findById(id);

    if (article != null) {
      article.setSubject(subject);
      article.setContent(content);
    }
  }

  public void delete(int id) {
    Article article = findById(id);

    if (article != null) {
      articles.remove(article);
    }
  }

  public Article findById(int id) {
    return articles.stream()
        .filter(article -> article.getId() == id)
        .findFirst()
        .orElse(null);
  }

  public void increaseHitCount(int id) {
    findById(id).setHitCount(findById(id).getHitCount() + 1);
  }
}
