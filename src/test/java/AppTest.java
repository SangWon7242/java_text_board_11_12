import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppTest {
  public static void main(String[] args) {
    // 파라미터 분석
    String queryString = "id=20&subject=자바수업&content=내용&writerName=김철수";
    String[] queryStringBits = queryString.split("&");
    System.out.println(Arrays.toString(queryStringBits));

    List<String> paramNames = new ArrayList<>();
    List<String> paramValues = new ArrayList<>();

    for(String bit : queryStringBits) {
      String[] bitBits = bit.split("=");
      String paramName = bitBits[0];
      String paramValue = bitBits[1];

      paramNames.add(paramName);
      paramValues.add(paramValue);
    }

    System.out.println(paramNames);
    System.out.println(paramValues);

    int findIndex = paramNames.indexOf("content");
    System.out.println(paramValues.get(findIndex));
  }
}
