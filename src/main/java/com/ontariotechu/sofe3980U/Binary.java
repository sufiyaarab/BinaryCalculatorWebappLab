package com.ontariotechu.sofe3980U;

public class Binary {
    private String number = "0";  // string containing the binary value '0' or '1'

    public Binary(String number) {
        if (number == null || number.isEmpty()) {
            this.number = "0";
            return;
        }

        // Validate the binary string (only '0' or '1' allowed)
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch != '0' && ch != '1') {
                this.number = "0";
                return;
            }
        }

        int beg;
        for (beg = 0; beg < number.length(); beg++) {
            if (number.charAt(beg) != '0') {
                break;
            }
        }

        this.number = (beg == number.length()) ? "0" : number.substring(beg);

        if (this.number.isEmpty()) {
            this.number = "0";
        }
    }

    public String getValue() {
        return this.number;
    }

    public static Binary add(Binary num1, Binary num2) {
        int ind1 = num1.number.length() - 1;
        int ind2 = num2.number.length() - 1;
        int carry = 0;
        String num3 = "";

        while (ind1 >= 0 || ind2 >= 0 || carry != 0) {
            int sum = carry;
            if (ind1 >= 0) {
                sum += (num1.number.charAt(ind1) == '1') ? 1 : 0;
                ind1--;
            }
            if (ind2 >= 0) {
                sum += (num2.number.charAt(ind2) == '1') ? 1 : 0;
                ind2--;
            }
            carry = sum / 2;
            sum = sum % 2;
            num3 = ((sum == 0) ? "0" : "1") + num3;
        }

        return new Binary(num3);
    }

    public static Binary binaryOR(Binary num1, Binary num2) {
        StringBuilder result = new StringBuilder();
        int ind1 = num1.number.length() - 1;
        int ind2 = num2.number.length() - 1;

        while (ind1 >= 0 || ind2 >= 0) {
            char bit1 = ind1 >= 0 ? num1.number.charAt(ind1) : '0';
            char bit2 = ind2 >= 0 ? num2.number.charAt(ind2) : '0';
            result.append(bit1 == '1' || bit2 == '1' ? '1' : '0');
            ind1--;
            ind2--;
        }

        return new Binary(result.reverse().toString());
    }

    public static Binary binaryAND(Binary num1, Binary num2) {
    StringBuilder result = new StringBuilder();
    int ind1 = num1.number.length() - 1;
    int ind2 = num2.number.length() - 1;
    int maxLength = Math.max(num1.number.length(), num2.number.length());

    // Perform AND operation
    while (ind1 >= 0 || ind2 >= 0) {
        char bit1 = ind1 >= 0 ? num1.number.charAt(ind1) : '0';
        char bit2 = ind2 >= 0 ? num2.number.charAt(ind2) : '0';
        result.append(bit1 == '1' && bit2 == '1' ? '1' : '0');
        ind1--;
        ind2--;
    }

    // Reverse result to get correct order
    result.reverse();

    // Pad with leading zeros to match the length of the longest operand or expected length
    int expectedLength = 4; // Adjust this value to the expected number of digits (e.g., 4)
    while (result.length() < expectedLength) {
        result.insert(0, '0');
    }

    return new Binary(result.toString());
}



    public static Binary binaryMULTI(Binary num1, Binary num2) {
        Binary result = new Binary("0");
        Binary tempNum1 = new Binary(num1.number);

        for (int i = num2.number.length() - 1; i >= 0; i--) {
            if (num2.number.charAt(i) == '1') {
                result = add(result, tempNum1);
            }
            tempNum1 = shiftLeft(tempNum1);
        }

        return result;
    }

    private static Binary shiftLeft(Binary num) {
    if (num.getValue().equals("0")) {
        return new Binary("0");
    }
    return new Binary(num.number + "0");
}

}
