class Operator {
        int first;
        int second;
        int rasChet(String[] ok) throws MyCalcException {
            int fs = first, ss = second;
            String[] opera = {"+", "-", "*", "/"};

            if (fs > 10 || ss > 10 || fs < 1 || ss < 1) {
                throw new MyCalcException("Недопустимые значения!");
            }

            if (opera[0].equals(ok[1])) {
                int result = fs + ss;
                return result;
            }
            if (opera[1].equals(ok[1])) {
                int result = fs - ss;
                return result;
            }
            if (opera[2].equals(ok[1])) {
                int result = fs * ss;
                return result;
            }
            if (opera[3].equals(ok[1])) {
                int result = fs / ss;
                return result;
            }
            return 0;
        }
    }