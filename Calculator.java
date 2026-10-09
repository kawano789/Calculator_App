import java.util.Scanner;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.Normalizer;

public class Calculator {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("簡易電卓にようこそ!");
    System.out.println("モードを選択して下さい");
    System.out.println("計算モード:c 為替変換モード:e");

    while (true) {
      String mode = scanner.nextLine();

      if (mode.equals("c")) {
        Cal();
        break;
      } else if (mode.equals("e")) {
        Exc();
        break;
      } else {
        System.out.println("エラー:再度入力して下さい");
      }
    }

    scanner.close();

  }

  public static void Cal() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      BigDecimal num1;
      while (true) {
        System.out.println("1つ目の数値を入力して下さい。");
        String input1 = scanner.nextLine();
        try {
          input1 = Normalizer.normalize(input1, Normalizer.Form.NFKC);
          num1 = new BigDecimal(input1);
          break;
        } catch (NumberFormatException e) {
          System.out.println("エラー：不正な値が入力されました");
        }
      }

      BigDecimal num2;
      while (true) {
        System.out.println("2つ目の数値を入力して下さい。");
        String input2 = scanner.nextLine();
        try {
          input2 = Normalizer.normalize(input2, Normalizer.Form.NFKC);
          num2 = new BigDecimal(input2);
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
        operator = operator.replace('ー', '-');

        if (operator.equals("+") ||
            operator.equals("-") ||
            operator.equals("*") ||
            operator.equals("/")) {
          break;
        } else {
          System.out.println("エラー：不正な値が入力されました");
        }
      }

      BigDecimal num3 = BigDecimal.ZERO;

      try {
        switch (operator) {
          case "+":
            num3 = num1.add(num2);
            break;
          case "-":
            num3 = num1.subtract(num2);
            break;
          case "*":
            num3 = num1.multiply(num2);
            break;
          case "/":
            num3 = num1.divide(num2, 3, RoundingMode.HALF_UP);
            break;
          default:
            System.out.println("エラー");
            continue;
        }

        System.out.println("答えは");
        System.out.println(num3);

      } catch (ArithmeticException e) {
        System.out.println("エラー:0で割れません");
        continue;
      }

      System.out.println("エンターで続行、qでやめる");
      String input = scanner.nextLine();

      if (input.equals("q")) {
        break;
      }
    }
    scanner.close();
  }

  public static void Exc() {
    Scanner scanner = new Scanner(System.in);

    while (true) {

      String currency1;
      while (true) {
        System.out.println("(変換元)JPY、USD、EURから選択してください。");
        currency1 = scanner.nextLine();
        currency1 = Normalizer.normalize(currency1, Normalizer.Form.NFKC);
        if (currency1.equals("JPY") ||
            currency1.equals("USD") ||
            currency1.equals("EUR")) {
          break;
        } else {
          System.out.println("エラー：不正な値が入力されました");
        }
      }

      BigDecimal amount;
      while (true) {
        System.out.println("金額を入力してください。");
        String input = scanner.nextLine();
        try {
          input = Normalizer.normalize(input, Normalizer.Form.NFKC);
          amount = new BigDecimal(input);
          break;
        } catch (NumberFormatException e) {
          System.out.println("エラー：不正な値が入力されました");
        }
      }

      String currency2;
      while (true) {
        System.out.println("(変換先)JPY、USD、EURから選択してください。");
        currency2 = scanner.nextLine();
        currency2 = Normalizer.normalize(currency2, Normalizer.Form.NFKC);
        if (currency2.equals("JPY") ||
            currency2.equals("USD") ||
            currency2.equals("EUR")) {
          break;
        } else {
          System.out.println("エラー：不正な値が入力されました");
        }
      }

      BigDecimal jpyRate = new BigDecimal("1");
      BigDecimal usdRate = new BigDecimal("155");
      BigDecimal eurRate = new BigDecimal("175");

      BigDecimal fromRate;
      BigDecimal toRate;

      switch (currency1) {
        case "JPY":
          fromRate = jpyRate;
          break;
        case "USD":
          fromRate = usdRate;
          break;
        default:
          fromRate = eurRate;
          break;
      }

      switch (currency2) {
        case "JPY":
          toRate = jpyRate;
          break;
        case "USD":
          toRate = usdRate;
          break;
        default:
          toRate = eurRate;
          break;
      }

      BigDecimal result = amount.multiply(fromRate);
      result = result.divide(toRate, 2, RoundingMode.HALF_UP);
      System.out.println("結果:" + result + currency2);

      System.out.println("エンターで続行、qでやめる");
      String input = scanner.nextLine();

      if (input.equals("q")) {
        break;
      }
    }

    scanner.close();
  }

}
