package com.sbs.java.text_board.base.interceptor;

import com.sbs.java.text_board.Rq;

public class NeedLogoutInterceptor implements Interceptor {
  @Override
  public boolean run(Rq rq) {
    if(rq.isLogout()) return true;

    return switch (rq.getUrlPath()) {
      case "/usr/member/login",
           "/usr/member/join",
           "/usr/member/findLoginId",
           "/usr/member/findLoginPw" -> {
        System.out.println("이미 로그인 상태입니다.");
        yield false;
      }

      default -> true;
    };
  }
}
