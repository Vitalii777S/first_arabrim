import java.util.Scanner;

class Main {
    public static void main(String[] args) throws MyCalcException {
        while (true) {
            System.out.println("Введите арифметическое выражение:");
            Scanner string = new Scanner(System.in);
            String str777 = string.nextLine();
            String input = str777.replaceAll(" ", "");
            calc(input);
            System.out.println(calc(input));
        }
    }
    public static String calc(String input) throws MyCalcException {

        String zNak = "+-*/";
        if (input.length() > 9) {
            throw new MyCalcException("Превышено число символов выражения!");
        }

        int m = 0;
        String[] ok = new String[3];
        for (int i = 0; i < input.length(); i++) {
            for (int f = 0; f < zNak.length(); f++) {
                if (input.charAt(0) == zNak.charAt(f)) {
                    throw new MyCalcException("Выражение не может начинаться с оператора!");
                }
                if (input.charAt(i) == zNak.charAt(f)) {
                    m++;
                    String slash = ("\\");
                    String zn = String.valueOf(zNak.charAt(f));
                    String[] x = input.split(slash.concat(String.valueOf(input.charAt(i))));
                    ok[0] = x[0];
                    ok[1] = zn;
                    ok[2] = x[1];
                }
            }
        }
        if (m == 0) {
            throw new MyCalcException("т.к. строка не является математической операцией");
        }
        if (m > 1) {
            throw new MyCalcException("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *");
        }

        Operator operator = new Operator();
        String[] rims = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] arabs = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        int ri = 0, ar = 0;
        for (String arab : arabs) {
            for (String elem : ok) {
                if (elem.equals(arab)) {
                    ar++;
                }
            }
        }
        for (String rim : rims) {
            for (String elem : ok) {
                if (elem.equals(rim)) {
                    ri++;
                }
            }
        }
        if (ri == 1 && ar == 1) {
            throw new MyCalcException("т.к. используются одновременно разные системы счисления");
        }
        if (ri < 2 && ar < 2) {
            throw new MyCalcException("Недопустимое значение операнда!");
        }
        if (ri == 2) {
            for (int i = 0; i < rims.length; i++) {
                for (int j = 0; j < ok.length; j++) {
                    if (rims[i].equals(ok[j])) {
                        if (j == 0) {
                            operator.first = i + 1;
                        }
                        if (j == 2) {
                            operator.second = i + 1;
                        }
                    }
                }
            }
            return calcRim(operator.rasChet(ok));
        }
        if (ar == 2) {
            operator.first = Integer.parseInt(ok[0]);
            operator.second = Integer.parseInt(ok[2]);
            return String.valueOf(operator.rasChet(ok));
        }
        return null;
    }
    static String calcRim(int result) throws MyCalcException {
        String[] rims = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] bigRims = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};

        if (result < 1) {
            throw new MyCalcException("В римской системе нет отрицательных чисел!");
        }
        if (result < 11) {
            for (int i = 1; i < 11; i++) {
                if (result == i) {
                    String res = rims[i];
                    return res;
                }
            }
        }
        if (result > 10 && result < 100) {
            String res1 = "", res2 = "";
            String strRes = String.valueOf(result);
            for (int i = 0; i < bigRims.length; i++) {
                if (i == Integer.parseInt(String.valueOf(strRes.charAt(0)))) {
                    res1 = bigRims[i];
                }
                if (i == Integer.parseInt(String.valueOf(strRes.charAt(1)))) {
                    res2 = rims[i];
                }
            }
            String res = res1 + res2;
            return res;
        }
        if (result == 100) {
            String res = bigRims[11];
            System.out.println(res);
            return res;
        }
        return null;
    }
}