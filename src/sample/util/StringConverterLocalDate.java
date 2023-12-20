package sample.util;

import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringConverterLocalDate extends StringConverter<LocalDate> {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    @Override
    public String toString(LocalDate localDate) {
        if (localDate != null) {
            return dateFormatter.format(localDate);
        }
        return "";
    }
    
    @Override
    public LocalDate fromString(String s) {
        if (s != null && !s.isEmpty()) {
            return LocalDate.parse(s, dateFormatter);
        }
        return null;
    }
    
    public StringConverterLocalDate localDate() {
        return this;
    }
}
