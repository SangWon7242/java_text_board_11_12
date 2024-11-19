import java.util.HashMap;
import java.util.Map;

public class AppTest {
  public static void main(String[] args) {
    Rq rq = new Rq("/usr/article/write?id=20&subject=자바수업&content=내용&writerName=김철수&hitCount=30");
    Map<String, String> params = rq.getParams();
    System.out.println(params);
    System.out.println(rq.getParams());
    System.out.println(rq.getParams());

    String urlPath = rq.getUrlPath();
    System.out.println(urlPath);
    System.out.println(rq.getUrlPath());
    System.out.println(rq.getUrlPath());
  }
}

class Rq {
  String url;
  Map<String, String> params;
  String urlPath;

  Rq(String url) {
    this.url = url;
    params = Util.getParamsFromUrl(this.url);
    urlPath = Util.getPathFromUrl(this.url);
  }

  public Map<String, String> getParams() {
    return params;
  }

  public String getUrlPath() {
    return urlPath;
  }
}

class Util {
  static Map<String, String> getParamsFromUrl(String url) {
    System.out.println("getParamsFromUrl 실행!!");
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

  static String getPathFromUrl(String url) {
    System.out.println("getPathFromUrl 실행!!");
    return url.split("\\?", 2)[0];
  }
}