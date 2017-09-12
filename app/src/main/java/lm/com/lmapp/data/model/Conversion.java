package lm.com.lmapp.data.model;

/**
 * Created by pnaganjane001 on 9/11/17.
 */

public class Conversion {
    private String to;

    private String rate;

    private String from;

    public String getTo ()
    {
        return to;
    }

    public void setTo (String to)
    {
        this.to = to;
    }

    public String getRate ()
    {
        return rate;
    }

    public void setRate (String rate)
    {
        this.rate = rate;
    }

    public String getFrom ()
    {
        return from;
    }

    public void setFrom (String from)
    {
        this.from = from;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [to = "+to+", rate = "+rate+", from = "+from+"]";
    }
}
