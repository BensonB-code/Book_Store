package com.cen.bookstore.domain;

public class Isbn {

    static boolean checkISBNNumber(long number)
    {
        int sum = 0;
        int i, t, intNumber, dNumber;
        String strNumber;

        strNumber = ""+number;

        if (strNumber.length() != 10) {
            return false;
        }

        for (i = 0; i < strNumber.length(); i++) {
            intNumber = Integer.parseInt(strNumber.substring(i, i+1));
            dNumber = i + 1;
            t = dNumber * intNumber;
            sum = sum + t;
        }

        // check whether the sum is divisible by 11 or not

        if ((sum % 11) == 0) {
            return true;
        }

        return false;
    }

}
