package com.sbs.java.text_board.base.session;

import java.util.LinkedHashMap;
import java.util.Map;

public class Session {
  private Map<String, Object> sessionData;

  public Session() {
    sessionData = new LinkedHashMap<>();
  }

  public Object getAttribute(String attrName) {
    return sessionData.get(attrName);
  }

  public void setAttribute(String attrName, Object value) {
    sessionData.put(attrName, value);
  }

  public boolean hasAttribute(String attrName) {
    return sessionData.containsKey(attrName);
  }

  public void removeAttribute(String attrName) {
    sessionData.remove(attrName);
  }
}
