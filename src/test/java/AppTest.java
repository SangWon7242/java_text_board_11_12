import java.util.LinkedHashMap;
import java.util.Map;

public class AppTest {
  public static void main(String[] args) {
    // 파라미터 분석
    String queryString1 = "id=20&subject=자바수업&content=내용&writerName=김철수&hitCount=20";
    Map<String, String> params1 = Util.getParams(queryString1);
    System.out.println(params1);

    String queryString2 = "id=20&subject=자바&content=내용2&writerName=김수정&hitCount=40";
    Map<String, String> params2 = Util.getParams(queryString2);
    System.out.println(params2);

  }
}

class Util {
  static Map<String, String> getParams(String queryStr) {
    Map<String, String> params = new LinkedHashMap<>();
    String[] queryStrBits = queryStr.split("&");

    for(String bit : queryStrBits) {
      String[] bitBits = bit.split("=");

      params.put(bitBits[0], bitBits[1]);
    }

    return params;
  }
}