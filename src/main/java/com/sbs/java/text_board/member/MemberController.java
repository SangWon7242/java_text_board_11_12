package com.sbs.java.text_board.member;

import com.sbs.java.text_board.Rq;
import com.sbs.java.text_board.container.Container;

public class MemberController {
  private MemberService memberService;

  public MemberController() {
    memberService = Container.memberService;
  }

  public void doJoin(Rq rq) {
    String loginId;
    String loginPW;
    String loginPwConfirm;
    String name;
    Member member;

    System.out.println("== 회원가입 ==");

    // 로그인 아이디 입력
    while (true) {
      System.out.print("로그인 아이디 : ");
      loginId = Container.sc.nextLine();

      member = memberService.findByLoginId(loginId);

      if(member != null) {
        System.out.printf("\"%s\"(은)는 이미 가입 된 로그인 아이디입니다.\n", loginId);
        continue;
      }

      if (loginId.trim().isEmpty()) {
        System.out.println("로그인 아이디를 입력해주세요.");
        continue;
      }

      break;
    }

    // 로그인 비밀번호 입력
    while (true) {
      System.out.print("로그인 비밀번호 : ");
      loginPW = Container.sc.nextLine();

      if (loginPW.trim().isEmpty()) {
        System.out.println("로그인 비밀번호를 입력해주세요.");
        continue;
      }

      while (true) {
        System.out.print("로그인 비밀번호 확인 : ");
        loginPwConfirm = Container.sc.nextLine();

        if (loginPwConfirm.trim().isEmpty()) {
          System.out.println("로그인 비밀번호 확인을 입력해주세요.");
          continue;
        }

        if (!loginPwConfirm.equals(loginPW)) {
          System.out.println("비밀번호가 일치하지 않습니다.");
          continue;
        }

        break;
      }

      break;
    }

    // 이름 입력
    while (true) {
      System.out.print("이름 : ");
      name = Container.sc.nextLine();

      if (name.trim().isEmpty()) {
        System.out.println("이름을 입력해주세요.");
        continue;
      }

      break;
    }

    memberService.join(loginId, loginPW, name);

    System.out.printf("\"%s\"님 회원 가입되었습니다.\n", name);
  }

  public void doLogin(Rq rq) {
    String loginId;
    String loginPW;
    Member member;

    if(rq.isLogined()) {
      System.out.println("이미 로그인 되어 있습니다.");
      return;
    }

    System.out.println("== 로그인 ==");

    // 로그인 아이디 입력
    while (true) {
      System.out.print("로그인 아이디 : ");
      loginId = Container.sc.nextLine();

      member = memberService.findByLoginId(loginId);

      if(member == null) {
        System.out.printf("\"%s\"(은)는 존재하지 않는 아이디입니다.\n", loginId);
        continue;
      }

      if (loginId.trim().isEmpty()) {
        System.out.println("로그인 아이디를 입력해주세요.");
        continue;
      }

      break;
    }

    int tryMaxCount = 3;
    int tryCount = 0;
    boolean loginIsSuccess = false;

    // 로그인 비밀번호 입력
    while (true) {
      if(tryCount == tryMaxCount) {
        System.out.println("비밀번호를 확인 후 다시 입력해 주세요.");
        break;
      }

      System.out.print("로그인 비밀번호 : ");
      loginPW = Container.sc.nextLine();

      if (loginPW.trim().isEmpty()) {
        System.out.println("로그인 비밀번호를 입력해주세요.");
        continue;
      }

      if(!member.getLoginPw().equals(loginPW)) {
        tryCount++;
        System.out.println("비밀번호가 일치하지 않습니다.");
        System.out.printf("일치하지 않은 횟수( %d / %d )\n", tryCount, tryMaxCount);
        continue;
      }

      loginIsSuccess = true;

      break;
    }

    if(loginIsSuccess) {
      System.out.printf("\"%s\"님 로그인 되었습니다.\n", loginId);

      rq.setSessionAttr("loginedMember", member);
    }
    else {
      System.out.println("로그인을 실패하였습니다.");
    }

  }
}
