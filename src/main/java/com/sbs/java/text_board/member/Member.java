package com.sbs.java.text_board.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
  private int id;
  private String loginId;
  private String loginPw;
  private String name;
}