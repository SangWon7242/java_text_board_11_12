package com.sbs.java.text_board.member;

import com.sbs.java.text_board.util.Util;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
  private List<Member> members;
  private int lastId;

  public MemberRepository() {
    members = new ArrayList<>();
    lastId = 0;

    makeTestData();
  }

  void makeTestData() {
    String regDate = Util.getNowDateStr();
    String updateDate = regDate;

    members.add(new Member(1, regDate, updateDate, "user1", "1234", "김철수"));
    members.add(new Member(2, regDate, updateDate, "love", "2345", "김덕희"));
    members.add(new Member(3, regDate, updateDate, "hello", "12555", "최영희"));
  }

  public void join(String loginId, String loginPW, String name) {
    int id = ++lastId;

    String regDate = Util.getNowDateStr();
    String updateDate = regDate;

    Member member = new Member(id, regDate, updateDate, loginId, loginPW, name);

    members.add(member);
  }

  public Member findByLoginId(String loginId) {
    return members.stream()
        .filter(member -> member.getLoginId().equals(loginId))
        .findFirst()
        .orElse(null);
  }

  public Member findById(int id) {
    return members.stream()
        .filter(member -> member.getId() == id)
        .findFirst()
        .orElse(null);
  }
}
