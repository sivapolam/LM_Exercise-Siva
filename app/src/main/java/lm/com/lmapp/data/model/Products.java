package lm.com.lmapp.data.model;

/**
 * Created by pnaganjane001 on 9/11/17.
 */

public class Products {
    private String price;

    private String name;

    private String url;

    private String currency;

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    public String getCurrency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [price = "+price+", name = "+name+", url = "+url+", currency = "+currency+"]";
    }
}
