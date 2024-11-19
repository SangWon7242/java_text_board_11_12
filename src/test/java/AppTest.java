import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppTest {
  public static void main(String[] args) {
    // 파라미터 분석
    String queryString = "d=40&a=10&b=20&c=30&f=15";
    String[] queryStringBits = queryString.split("&");
    System.out.println(Arrays.toString(queryStringBits));

    List<String> paramNames = new ArrayList<>();
    List<Integer> paramValues = new ArrayList<>();

    for(String bit : queryStringBits) {
      String[] bitBits = bit.split("=");
      String paramName = bitBits[0];
      String paramValue = bitBits[1];

      paramNames.add(paramName);
      paramValues.add(Integer.parseInt(paramValue));
    }

    for(int i = 0; i < paramNames.size(); i++) {
      String paramName = paramNames.get(i);
      int paramValue = paramValues.get(i);

      System.out.printf("%s : %d\n", paramName, paramValue);
    }
  }
}
