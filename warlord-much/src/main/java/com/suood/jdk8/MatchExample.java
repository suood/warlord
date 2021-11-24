package com.suood.jdk8;

import java.util.regex.Pattern;

/**
 * @author suguangqiang .
 * @date 2020-08-12 10:47.
 */
public class MatchExample {

  public static void main(String[] args) {
    String content = "hw_202002_math_live_question_log_0";
    String pattern = "^hw[_]\\d+[_]math[_]live[_]question[_]log[_]\\d+$";
    boolean isMatch = Pattern.matches(pattern, content);
    System.out.println(isMatch);
  }
}
