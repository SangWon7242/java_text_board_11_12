package com.sbs.java.text_board.board;

import java.util.ArrayList;
import java.util.List;

public class BoardRepository {
  private List<Board> boards;
  private int lastId;

  public BoardRepository() {
    boards = new ArrayList<>();
    lastId = 0;

    makeTestData();
  }

  private void makeTestData() {
    String regDate = "2024. 21:50. 00:00:00";
    String updateDate = regDate;

    boards.add(new Board(1, regDate, updateDate, "자유", "1"));
    boards.add(new Board(2, regDate, updateDate, "공지", "2"));
  }

  public Board findByBoardId(int id) {
    return boards.stream()
        .filter(board -> board.getId() == id)
        .findFirst()
        .orElse(null);
  }
}
