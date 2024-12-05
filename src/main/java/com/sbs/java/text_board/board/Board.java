package com.sbs.java.text_board.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {
  private int id;
  private String regDate;
  private String updateDate;
  private String name;
  private String code;
}