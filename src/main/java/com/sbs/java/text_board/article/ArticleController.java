package com.sbs.java.text_board.article;

import com.sbs.java.text_board.Rq;
import com.sbs.java.text_board.board.Board;
import com.sbs.java.text_board.board.BoardService;
import com.sbs.java.text_board.container.Container;
import com.sbs.java.text_board.member.Member;

import java.util.List;

public class ArticleController {

  private ArticleService articleService;
  private BoardService boardService;

  public ArticleController() {
    articleService = Container.articleService;
    boardService = Container.boardService;
  }

  public void doWrite(Rq rq) {
    int boardId = rq.getIntParam("boardId", 0);

    if(boardId == 0) {
      System.out.println("boardId를 입력해주세요.");
      return;
    }

    Board board = boardService.findByBoardId(boardId);

    if(board == null) {
      System.out.println("존재하지 않는 게시판 번호입니다.");
      return;
    }

    System.out.printf("== %s 게시물 작성 ==\n", board.getName());
    System.out.print("제목 : ");
    String subject = Container.sc.nextLine();
    
    if(subject.trim().isEmpty()) {
      System.out.println("제목을 입력해주세요.");
      return;
    }

    System.out.print("내용 : ");
    String content = Container.sc.nextLine();

    if(content.trim().isEmpty()) {
      System.out.println("내용을 입력해주세요.");
      return;
    }

    Member member = rq.getLoginedMember();
    // 1번회원, user1, 1234, 김철수

    int id = articleService.write(subject, content, member.getName(), member.getId(), boardId);

    System.out.printf("%d번 게시물이 등록되었습니다.\n", id);
  }

  public void showList(Rq rq) {
    String searchKeyword = rq.getParam("searchKeyword", "");
    String searchKeywordTypeCode = rq.getParam("searchKeywordTypeCode", "");
    String orderBy = rq.getParam("orderBy", "idDesc");
    int boardId = rq.getIntParam("boardId", 0);
    int page = rq.getIntParam("page", 1);

    int pageItemCount = 10;


    Board board = null;

    if(boardId != 0) {
      board = boardService.findByBoardId(boardId);
    }

    if(board == null && boardId > 0) {
      System.out.println("해당 게시판은 존재하지 않습니다.");
      return;
    }

    List<Article> articles = articleService.getArticles(searchKeyword, searchKeywordTypeCode, orderBy, boardId, page, pageItemCount);

    if (articles.isEmpty()) {
      System.out.println("현재 게시물이 존재하지 않습니다.");
      return;
    }

    String boardName = board == null ? "전체" : board.getName();

    System.out.printf("== %s 게시물 리스트(%d건) ==\n", boardName, articles.size());
    System.out.println("번호 | 작성 날짜 | 제목 | 작성자 | 게시판 | 조회수");

    articles.forEach(
        article -> {
          String articleBoardName = getBoardNameByBoardId(article.getBoardId());

          System.out.printf("%d | %s | %s | %s | %s | %d\n", article.getId(), article.getRegDate(), article.getSubject(), article.getWriterName(), articleBoardName, article.getHitCount());
        }
    );
  }

  private String getBoardNameByBoardId(int boardId) {
    Board board = boardService.findByBoardId(boardId);
    return board.getName();
  }

  public void showDetail(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("올바른 값을 입력해주세요.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    articleService.increaseHitCount(article.getId());

    System.out.println("== 게시물 상세보기 ==");
    System.out.printf("번호 : %d\n", article.getId());
    System.out.printf("작성날짜 : %s\n", article.getRegDate());
    System.out.printf("수정날짜 : %s\n", article.getUpdateDate());
    System.out.printf("조회수 : %s\n", article.getHitCount());
    System.out.printf("작성자 : %s\n", article.getWriterName());
    System.out.printf("제목 : %s\n", article.getSubject());
    System.out.printf("내용 : %s\n", article.getContent());
  }

  public void doModify(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("올바른 값을 입력해주세요.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    Member member = rq.getLoginedMember();

    if(article.getMemberId() != member.getId()) {
      System.out.println("해당 글에 권한이 없습니다.");
      return;
    }

    System.out.print("새 제목 : ");
    String subject = Container.sc.nextLine();

    if(subject.trim().isEmpty()) {
      System.out.println("제목을 입력해주세요.");
      return;
    }

    System.out.print("새 내용 : ");
    String content = Container.sc.nextLine();

    if(content.trim().isEmpty()) {
      System.out.println("내용을 입력해주세요.");
      return;
    }

    articleService.modify(id, subject, content);

    System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
  }

  public void doDelete(Rq rq) {
    int id = rq.getIntParam("id", 0);

    if(id == 0) {
      System.out.println("올바른 값을 입력해주세요.");
      return;
    }

    Article article = articleService.findById(id);

    if (article == null) {
      System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
      return;
    }

    Member member = rq.getLoginedMember();

    if(article.getMemberId() != member.getId()) {
      System.out.println("해당 글에 권한이 없습니다.");
      return;
    }

    articleService.delete(id);

    System.out.printf("%d번 게시물은 삭제하였습니다.\n", id);
  }
}
