package com.sbs.java.text_board;

import com.sbs.java.text_board.base.session.Session;
import com.sbs.java.text_board.container.Container;
import com.sbs.java.text_board.member.Member;
import com.sbs.java.text_board.util.Util;
import lombok.Getter;

import java.util.Map;

public class Rq {
  public String url;

  @Getter
  public Map<String, String> params;

  @Getter
  public String urlPath;

  public Session session;

  public String loginedMember;

  public Rq() {
    session = Container.session;
    loginedMember = "loginedMember";
  }

  public void setCommand(String url) {
    params = Util.getParamsFromUrl(url);
    urlPath = Util.getPathFromUrl(url);
  }

  public int getIntParam(String paramName, int defaultValue) {
    if(!params.containsKey(paramName)) {
      return defaultValue;
    }

    try {
      return Integer.parseInt(params.get(paramName));
    } catch (NumberFormatException e) {
      return defaultValue;
    }
  }

  public String getParam(String paramName, String defaultValue) {
    if(!params.containsKey(paramName)) {
      return defaultValue;
    }

    return params.get(paramName);
  }

  // isLogined는 세션에 넘겨준 loginedMember가 있는지 확인
  // 세션에서 해당 세션 ID가 있으면 true를 반환, 없으면 false를 반환
  public boolean isLogined() {
    return hasSessionAttr(loginedMember);
  }

  public boolean isLogout() {
    return !isLogined();
  }

  public Object getSessionAttr(String attrName) {
    return session.getAttribute(attrName);
  }

  public void setSessionAttr(String attrName, Object value) {
    session.setAttribute(attrName, value);
  }

  public boolean hasSessionAttr(String attrName) {
    return session.hasAttribute(attrName);
  }

  public void removeSessionAttr(String attrName) {
    session.removeAttribute(attrName);
  }

  public Member getLoginedMember() {
    return (Member) session.getAttribute(loginedMember);
  }

  public void login(Member member) {
    setSessionAttr(loginedMember, member);
  }

  public void logout() {
    removeSessionAttr(loginedMember);
  }
}
