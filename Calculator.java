import java.util.Scanner;
import java.text.Normalizer;

public class Calculator {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      double num1;
      while (true) {
        System.out.println("1つ目の数値を入力して下さい。");
        String input1 = scanner.nextLine();
        try {
          input1 = Normalizer.normalize(input1, Normalizer.Form.NFKC);
          num1 = Double.parseDouble(input1);
          break;
        } catch (NumberFormatException e) {
          System.out.println("エラー：不正な値が入力されました");
        }
      }

      double num2;
      while (true) {
        System.out.println("2つ目の数値を入力して下さい。");
        String input2 = scanner.nextLine();
        try {
          input2 = Normalizer.normalize(input2, Normalizer.Form.NFKC);
          num2 = Double.parseDouble(input2);
          break;
        } catch (NumberFormatException e) {
          System.out.println("エラー：不正な値が入力されました");
        }
      }

      String operator;
      while (true) {
        System.out.println("演算記号（+, -, *, /）を入力してください。");
        operator = scanner.nextLine();
        operator = Normalizer.normalize(operator, Normalizer.Form.NFKC);

        if (operator.equals("+") ||
            operator.equals("-") ||
            operator.equals("*") ||
            operator.equals("/")) {
          break;
        } else {
          System.out.println("エラー：不正な値が入力されました");
        }
      }

      double num3 = 0;
      switch (operator) {
        case "+":
          num3 = num1 + num2;
          break;
        case "-":
          num3 = num1 - num2;
          break;
        case "*":
          num3 = num1 * num2;
          break;
        case "/":
          num3 = num1 / num2;
          break;
        default:
          System.out.println("エラー");
      }

      System.out.println("答えは");
      System.out.println(num3);

      System.out.println("エンターで続行、qでやめる");
      String input = scanner.nextLine();

      if (input.equals("q")) {
        break;
      }

    }

    scanner.close();

  }

}
