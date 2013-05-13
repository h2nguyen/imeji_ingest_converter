package core.helper;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Static functions to manipulate {@link String}
 * 
 * @author saquet (initial creation)
 * @author $Author$ (last modification)
 * @version $Revision$ $LastChangedDate$
 */
public class StringHelper
{
    /**
     * Character that separates components of an url
     */
    public static final String urlSeparator = "/";
    /**
     * Character that separates components of a file path. This is "/" on UNIX and "\" on Windows.
     */
    public static final String fileSeparator = System.getProperty("file.separator");
    /**
     * Tha maximum size of a file: Theorically, the max length could be 255. For security, imeji uses qa lower count.
     */
    public static final int FILENAME_MAX_LENGTH = 200;

    /**
     * Encode a {@link String} to MD5
     * 
     * @param pass
     * @return
     * @throws Exception
     */
    public static String convertToMD5(String pass) throws Exception
    {
        MessageDigest dig = MessageDigest.getInstance("MD5");
        dig.update(pass.getBytes("UTF-8"));
        byte messageDigest[] = dig.digest();
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < messageDigest.length; i++)
        {
            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
        }
        return hexString.toString();
    }

    /**
     * Format a uri (URL): add a / if the uri doesn't end with it
     * 
     * @return
     */
    public static String normalizeURI(String uri)
    {
        if (!uri.endsWith(urlSeparator))
        {
            uri += urlSeparator;
        }
        return uri;
    }

    /**
     * Format a system path
     * 
     * @param path
     * @return
     */
    public static String normalizePath(String path)
    {
        if (!path.endsWith(fileSeparator))
        {
            path += fileSeparator;
        }
        return path;
    }

    /**
     * Transform a filename to a correct filename, which can be used by the internal storage to store a file
     * 
     * @param filename
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String normalizeFilename(String filename)
    {
        try
        {
            String filextension = getFileExtension(filename);
            filename = DigestUtils.md5Hex(filename) + "." + filextension;
            return filename;
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error with filename: " + filename, e);
        }
    }

    /**
     * Parse the file extension from its name
     * 
     * @param filename
     * @return
     */
    public static String getFileExtension(String filename)
    {
        int i = filename.lastIndexOf('.');
        if (i > 0)
        {
            return filename.substring(i + 1);
        }
        return null;
    }
}
