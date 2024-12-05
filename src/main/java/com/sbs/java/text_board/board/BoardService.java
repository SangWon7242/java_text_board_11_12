package com.sbs.java.text_board.board;

import com.sbs.java.text_board.container.Container;

public class BoardService {
  private BoardRepository boardRepository;

  public BoardService() {
    boardRepository = Container.boardRepository;
  }

  public Board findByBoardId(int id) {
    return boardRepository.findByBoardId(id);
  }
}
