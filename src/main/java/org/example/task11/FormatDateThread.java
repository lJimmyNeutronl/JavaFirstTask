package org.example.task11;

import java.util.Date;

public class FormatDateThread extends Thread {
    private Formatter formatter;
    private Date date;

    public FormatDateThread(Formatter formatter, Date date) {
        this.formatter = formatter;
        this.date = date;
    }

    @Override
    public void run() {
        String formattedDate = formatter.format(date);
        System.out.println(formattedDate);
    }
}