package com.sbs.java.text_board.container;

import com.sbs.java.text_board.article.ArticleController;
import com.sbs.java.text_board.article.ArticleRepository;
import com.sbs.java.text_board.article.ArticleService;

import java.util.Scanner;

public class Container {
  public static Scanner sc;

  public static ArticleRepository articleRepository;

  public static ArticleService articleService;

  public static ArticleController articleController;

  static {
    sc = new Scanner(System.in);

    articleRepository = new ArticleRepository();

    articleService = new ArticleService();

    articleController = new ArticleController();
  }
}
