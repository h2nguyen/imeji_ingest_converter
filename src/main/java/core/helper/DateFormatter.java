/**
 * License: src/main/resources/license/escidoc.license
 */
package main.java.core.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Utility class to format imeji metadata {@link de.mpg.imeji.logic.vo.predefinedMetadata.Date}
 * 
 * @author saquet (initial creation)
 * @author $Author$ (last modification)
 * @version $Revision$ $LastChangedDate$
 */
public class DateFormatter
{
    /**
     * Return the time of the {@link Date}, if the format is recognized
     * 
     * @param str
     * @return
     */
    public static long getTime(String str)
    {
        Date d = parseDate(str, "yyyy-MM-dd");
        if (d == null)
            d = parseDate(str, "yyyy-MM");
        if (d == null)
            d = parseDate(str, "yyyy");
        if (d != null)
            return d.getTime();
        else
            throw new RuntimeException("Wrong date format");
    }

    /**
     * Parse a {@link Date} as {@link String} according to a {@link Pattern}
     * 
     * @param str
     * @param pattern
     * @return
     */
    public static Date parseDate(String str, String pattern)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            return sdf.parse(str);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    /**
     * Format a {@link String} as a {@link Date}
     * 
     * @param str
     * @return
     */
    public static String format(String str)
    {
        Date d = parseDate(str, "yyyy-MM-dd");
        if (d == null)
            d = parseDate(str, "yyyy-MM");
        if (d == null)
            d = parseDate(str, "yyyy");
        if (d != null)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(d);
        }
        else
            throw new RuntimeException("Wrong date format");
    }
}
