package net.herit.ami.commons.dto;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeFormatterObject {
    public static DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("uuuuMMdd", Locale.KOREA);
    public static DateTimeFormatter yyyyMMddHHmm = DateTimeFormatter.ofPattern("uuuuMMddHHmm", Locale.KOREA);
    public static DateTimeFormatter yyyyMMddHHmmss = DateTimeFormatter.ofPattern("uuuuMMddHHmmss", Locale.KOREA);
    public static DateTimeFormatter yyyyMMddHHmmssSSS = DateTimeFormatter.ofPattern("uuuuMMddHHmmssSSS", Locale.KOREA);
    public static DateTimeFormatter yyyyMMddHHmmssSSSnnn = DateTimeFormatter.ofPattern("uuuuMMddHHmmssSSSN", Locale.KOREA);

}
