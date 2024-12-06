package com.sbs.java.text_board.board;

import com.sbs.java.text_board.util.Util;

import java.util.ArrayList;
import java.util.List;

public class BoardRepository {
  private List<Board> boards;

  public BoardRepository() {
    boards = new ArrayList<>();

    makeBoard();
  }

  private void makeBoard() {
    String regDate = Util.getNowDateStr();
    String updateDate = regDate;

    boards.add(new Board(1, regDate, updateDate, "자유", "free"));
    boards.add(new Board(2, regDate, updateDate, "공지", "notice"));
  }

  public Board findByBoardId(int id) {
    return boards.stream()
        .filter(board -> board.getId() == id)
        .findFirst()
        .orElse(null);
  }
}
