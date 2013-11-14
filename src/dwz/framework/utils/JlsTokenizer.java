
package dwz.framework.utils;


public class JlsTokenizer
{

    public JlsTokenizer(String str)
    {
        this(str, "\t\n\r\f");
    }

    public JlsTokenizer(String str, String delim)
    {
        position = 0;
        data = str;
        delimiters = delim;
        dataLength = data.length();
    }

    public boolean hasMoreTokens()
    {
        return position <= dataLength;
    }

    public String nextToken()
    {
        int delPos = position;
        do
        {
            if(delPos >= dataLength)
                break;
            char c = data.charAt(delPos);
            if(delimiters.indexOf(c) != -1)
                break;
            delPos++;
        }
        while(true);
        String ret = data.substring(position, delPos);
        position = delPos + 1;
        return ret;
    }

    public boolean hasMoreElements()
    {
        return hasMoreTokens();
    }

    public Object nextElement()
    {
        return nextToken();
    }

    public int countTokens()
    {
        int savedPosition = position;
        int count;
        for(count = 0; hasMoreTokens(); count++)
            nextToken();

        position = savedPosition;
        return count;
    }

    private String data;
    private int dataLength;
    private String delimiters;
    private int position;
}
