import java.util.LinkedHashMap;
import java.util.Map;

public class AppTest {
  public static void main(String[] args) {
    // 파라미터 분석
    String queryString = "id=20&subject=자바수업&content=내용&writerName=김철수&hitCount=20";
    String[] queryStringBits = queryString.split("&");

    // Map<String, String> params = new HashMap<>();
    Map<String, String> params = new LinkedHashMap<>();

    for(String bit : queryStringBits) {
      String[] bitBits = bit.split("=");
      params.put(bitBits[0], bitBits[1]);
    }

    System.out.println(params);

    System.out.println("== 원하는 것을 하나하나 가져와서 출력 ==");
    System.out.printf("id : %d\n", Integer.parseInt(params.get("id")));
    System.out.printf("subject : %s\n", params.get("subject"));
    System.out.printf("content : %s\n", params.get("content"));
    System.out.printf("writerName : %s\n", params.get("writerName"));
    System.out.printf("hitCount : %d\n", Integer.parseInt(params.get("hitCount")));

    System.out.println("== 반복문을 사용하여 순회 출력 v1 ==");
    for(String paramName : params.keySet()) {
      String paramValue = params.get(paramName);

      System.out.printf("%s : %s\n", paramName, paramValue);
    }

    System.out.println("== 반복문을 사용하여 순회 출력 v2 ==");
    params.forEach((key, value) -> System.out.printf("%s : %s\n", key, value));
  }
}
