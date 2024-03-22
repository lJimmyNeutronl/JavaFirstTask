package org.example.task11;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatter {
    public String format(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}