import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class SearchKeywordTest {
  public static void main(String[] args) {
    List<Article> articles = new ArrayList<>();

    IntStream.rangeClosed(1, 5)
        .forEach(i -> articles.add(new Article(i, "제목" + i, "내용" + i)));

    articles.add(new Article(6, "자바는 무슨 언어인가요?", "자바는 객체지향 프로그래밍 언어입니다."));
    articles.add(new Article(7, "코딩 실력 빨리 늘려면 어떻게 하나요?", "반복학습이 정답이다."));


    String searchKeyword = "제목";

    List<Article> filteredArticles = new ArrayList<>();

    for(Article article : articles) {
      if(article.subject.contains(searchKeyword)) {
        filteredArticles.add(article);
      }
    }

    System.out.println(filteredArticles);
  }
}

class Article {
  int id;
  String subject;
  String content;

  Article(int id, String subject, String content) {
    this.id = id;
    this.subject = subject;
    this.content = content;
  }

  @Override
  public String toString() {
    return "{id: %d, subject: \"%s\", content: \"%s\"}".formatted(id, subject, content);
  }
}
