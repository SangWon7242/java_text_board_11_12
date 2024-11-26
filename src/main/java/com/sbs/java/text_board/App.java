package com.sbs.java.text_board;

import com.sbs.java.text_board.container.Container;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class App {
  List<Article> articles;
  int lastArticleId;

  App() {
    articles = new ArrayList<>();
    lastArticleId = 0;
  }

  void makeTestData() {
    IntStream.rangeClosed(1, 100)
        .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));
  }

  // 우리 로직의 시작점
  void run() {
    makeTestData();

    System.out.println("== 자바 텍스트 게시판 시작 ==");

    while (true) {
      System.out.print("명령) ");
      String cmd = Container.sc.nextLine();

      Rq rq = new Rq(cmd);

      if (rq.getUrlPath().equals("/usr/article/write")) {
        actionUsrArticleWrite();
      } else if (rq.getUrlPath().equals("/usr/article/list")) {
        actionUsrArticleList(rq);
      } else if (rq.getUrlPath().equals("/usr/article/detail")) {
        actionUsrArticleDetail(rq);
      } else if (rq.getUrlPath().equals("/usr/article/modify")) {
        actionUsrArticleModify(rq);
      } else if (rq.getUrlPath().equals("/usr/article/delete")) {
        actionUsrArticleDelete(rq);
      } else if (rq.getUrlPath().equals("exit")) {
        System.out.println("프로그램을 종료합니다.");
        break;
      } else {
        System.out.println("잘못 된 명령어입니다.");
      }
    }

    System.out.println("== 자바 텍스트 게시판 종료 ==");
    Container.sc.close();
  }


  private void actionUsrArticleDelete(Rq rq) {
    if (articles.isEmpty()) {
      System.out.println("현재 게시물이 존재하지 않습니다.");
      return;
    }

    Map<String, String> params = rq.getParams();

    if (!params.containsKey("id")) {
      System.out.println("id 값을 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수형태로 입력해주세요.");
      return;
    }

    Article article = finById(articles, id);

    if (article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    articles.remove(article);

    System.out.printf("%d번 게시물은 삭제하였습니다.\n", id);
  }

  private void actionUsrArticleModify(Rq rq) {
    if (articles.isEmpty()) {
      System.out.println("현재 게시물이 존재하지 않습니다.");
      return;
    }

    Map<String, String> params = rq.getParams();

    if (!params.containsKey("id")) {
      System.out.println("id 값을 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수형태로 입력해주세요.");
      return;
    }

    Article article = finById(articles, id);

    if (article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    System.out.print("새 제목 : ");
    article.subject = Container.sc.nextLine();

    System.out.print("새 내용 : ");
    article.content = Container.sc.nextLine();

    System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
  }

  private void actionUsrArticleWrite() {
    lastArticleId = articles.get(articles.size() - 1).id;

    System.out.println("== 게시물 작성 ==");
    System.out.print("제목 : ");
    String subject = Container.sc.nextLine();

    System.out.print("내용 : ");
    String content = Container.sc.nextLine();

    int id = ++lastArticleId;

    Article article = new Article(id, subject, content);

    articles.add(article);

    System.out.printf("%d번 게시물이 등록되었습니다.\n", article.id);
  }

  private void actionUsrArticleDetail(Rq rq) {
    if (articles.isEmpty()) {
      System.out.println("현재 게시물이 존재하지 않습니다.");
      return;
    }

    Map<String, String> params = rq.getParams();

    if (!params.containsKey("id")) {
      System.out.println("id 값을 입력해주세요.");
      return;
    }

    int id = 0;

    try {
      id = Integer.parseInt(params.get("id"));
    } catch (NumberFormatException e) {
      System.out.println("id를 정수형태로 입력해주세요.");
      return;
    }

    Article article = finById(articles, id);

    if (article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    System.out.println("== 게시물 상세보기 ==");
    System.out.printf("번호 : %d\n", article.id);
    System.out.printf("제목 : %s\n", article.subject);
    System.out.printf("내용 : %s\n", article.content);
  }

  private void actionUsrArticleList(Rq rq) {
    if (articles.isEmpty()) {
      System.out.println("현재 게시물이 존재하지 않습니다.");
      return;
    }

    Map<String, String> params = rq.getParams();

    // 검색 기능 시작
    List<Article> filteredArticls = articles;

    if (params.containsKey("searchKeyword")) {
      String searchKeyword = params.get("searchKeyword");

      filteredArticls = new ArrayList<>();

      for (Article article : articles) {
        boolean matched = article.subject.contains(searchKeyword) || article.content.contains(searchKeyword);

        if (matched) filteredArticls.add(article);
      }
    }
    // 검색 기능 끝

    // 정렬 로직 시작
    boolean orderByIdDesc = true;

    if (params.containsKey("orderBy") && params.get("orderBy").equals("idAsc")) {
      orderByIdDesc = false;
    }

    List<Article> sortedArticles = filteredArticls;

    if (orderByIdDesc) {
      sortedArticles = Util.reverseList(sortedArticles);
    }
    // 정렬 로직 끝

    System.out.println("== 게시물 리스트 ==");
    System.out.println("번호 | 제목");

    sortedArticles.forEach(
        article -> System.out.printf("%d | %s\n", article.id, article.subject)
    );
  }

  private Article finById(List<Article> articles, int id) {
    return articles.stream()
        .filter(article -> article.id == id)
        .findFirst() // 첫 번째 요소 찾기
        .orElse(null);
  }
}

