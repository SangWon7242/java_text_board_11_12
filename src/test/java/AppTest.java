import java.util.HashMap;
import java.util.Map;

public class AppTest {
  public static void main(String[] args) {
    // 파라미터 분석
    String url = "/usr/article/write?id=20&subject=자바수업&content=내용&writerName=김철수&hitCount=30";
    Map<String, String> params = Util.getParamsFromUrl(url);
    System.out.println(params);

    System.out.println(params.get("id")); // 20
    System.out.println(params.get("subject")); // 자바수업
    System.out.println(params.get("content")); // 내용
    System.out.println(params.get("writerName")); // 김철수
    System.out.println(params.get("hitCount")); // 30
  }
}

class Util {
  static Map<String, String> getParamsFromUrl(String url) {
    Map<String, String> params = new HashMap<>();
    String[] urlBits = url.split("\\?", 2);

    if(urlBits.length == 1) {
      return params;
    }

    String queryStr = urlBits[1];

    for(String bit : queryStr.split("&")) {
      String[] bits = bit.split("=", 2);

      if(bits.length == 1) {
        continue;
      }

      params.put(bits[0], bits[1]);
    }

    return params;
  }
}