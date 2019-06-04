package cn.superhh.blogpro.util;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import org.springframework.format.annotation.DateTimeFormat;


import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class DateJsonValueProcessor implements JsonValueProcessor {
    private String format;
    public DateJsonValueProcessor(String format){
        this.format = format;
    }

    public Object processArrayValue(Object value, JsonConfig jsonConfig)
    {
        return null;
    }

    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig)
    {
        if(value == null)
        {
            return "";
        }
        if(value instanceof java.sql.Timestamp)
        {
           String s = new SimpleDateFormat(format).format((java.sql.Timestamp)value);
         // String str=   DateTimeFormatter.ofPattern(format).format((TemporalAccessor) value);
            String str=  s.replace('T', ' ');
            return str;
        }
        if (value instanceof java.util.Date)
        {
            String s = new SimpleDateFormat(format).format((java.util.Date) value);
           // String str=  s.replace('T', ' ');
            return s;
        }

        return value.toString().replace('T',' ');
    }


}
