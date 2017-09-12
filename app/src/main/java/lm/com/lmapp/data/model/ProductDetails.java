package lm.com.lmapp.data.model;

import java.util.List;

/**
 * Created by pnaganjane001 on 9/11/17.
 */

public class ProductDetails {
    private String title;

    private List<Conversion> conversion;

    private List<Products> products;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public List<Conversion> getConversion ()
    {
        return conversion;
    }

    public void setConversion (List<Conversion> conversion)
    {
        this.conversion = conversion;
    }

    public List<Products> getProducts ()
    {
        return products;
    }

    public void setProducts (List<Products> products)
    {
        this.products = products;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [title = "+title+", conversion = "+conversion+", products = "+products+"]";
    }
}
