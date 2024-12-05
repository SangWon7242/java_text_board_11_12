package com.sbs.java.text_board.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Article {
  private int id;
  private String subject;
  private String content;
  private String writerName; // 작성자명
  private int memberId;
  private int boardId;
}