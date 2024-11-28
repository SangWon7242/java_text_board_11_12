package com.sbs.java.text_board.member;

import com.sbs.java.text_board.container.Container;

public class MemberService {
  private MemberRepository memberRepository;

  public MemberService() {
    memberRepository = Container.memberRepository;
  }

  public void join(String loginId, String loginPW, String name) {
    memberRepository.join(loginId, loginPW, name);
  }
}
