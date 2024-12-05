package com.sbs.java.text_board.base.interceptor;

import com.sbs.java.text_board.Rq;

public class NeedLoginInterceptor implements Interceptor {
  @Override
  public boolean run(Rq rq) {
    if(rq.isLogined()) return true;

    return switch (rq.getUrlPath()) {
      case "/usr/article/write",
           "/usr/article/modify",
           "/usr/article/delete",
           "/usr/member/logout" -> {
        System.out.println("로그인 후 이용해주세요.");
        yield false;
      }

      default -> true;
    };
  }
}
