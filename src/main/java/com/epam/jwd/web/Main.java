package com.epam.jwd.web;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale locale = new Locale("be", "BY");

        System.out.println(Locale.getDefault());
        Locale.setDefault(Locale.CANADA);
        System.out.println(Locale.getDefault());
        final Locale current = new Locale("be", "BY");
        Locale.setDefault(locale);
        System.out.println(locale.getDisplayLanguage());
    }
}
