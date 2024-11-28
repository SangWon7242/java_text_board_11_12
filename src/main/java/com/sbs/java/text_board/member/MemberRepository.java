package com.sbs.java.text_board.member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
  private List<Member> members;
  private int lastId;

  public MemberRepository() {
    members = new ArrayList<>();
    lastId = 0;
  }

  public void join(String loginId, String loginPW, String name) {
    int id = ++lastId;

    Member member = new Member(id, loginId,  loginPW, name);

    members.add(member);
  }
}
