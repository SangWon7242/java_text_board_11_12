public class Test {
  public static void main(String[] args) {
    Article article = new Article();
    article.id = 1;
    article.subject = "제목1";
    article.content = "내용1";

    System.out.println(article);
  }
}

// 모든 class는 Object 클래스는 상속받는다.
class Article {
  int id;
  String subject;
  String content;

  @Override
  public String toString() {
    return "{id: %d, subject: \"%s\", content: \"%s\"}".formatted(id, subject, content);
  }
}
