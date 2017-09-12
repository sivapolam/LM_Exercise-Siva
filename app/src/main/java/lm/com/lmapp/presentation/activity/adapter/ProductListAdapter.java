package lm.com.lmapp.presentation.activity.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import lm.com.lmapp.Constants;
import lm.com.lmapp.R;
import lm.com.lmapp.data.model.Products;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> implements Constants {

    private final List<Products> productsList;
    private final Context context;
    private String currencyType;

    public ProductListAdapter(Context context, List<Products> albumDetailsList, String currencyType) {
        this.context = context;
        this.productsList = albumDetailsList;
        this.currencyType = currencyType;
    }

    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductListAdapter.ViewHolder holder, final int position) {

        if (productsList != null) {
            final Products product = productsList.get(position);

            if (product != null) {
                holder.productTitle.setText(product.getName());
                holder.productPrice.setText(getCurrencyPrefix() + " " + product.getPrice());

                Picasso.with(context)
                        .load(product.getUrl()) //this is optional the image to display while the url image is downloading
                        .error(ContextCompat.getDrawable(context, R.mipmap.ic_launcher))
                        .into(holder.productImage);

            }

        }
    }

    private String getCurrencyPrefix() {
        String prefix;
        switch (currencyType) {
            case INR:
                prefix = "\u20B9";
                break;
            case SAR:
                prefix = "SAR";
                break;
            case AED:
                prefix = "AED";
                break;
            default:
                prefix = "\u20B9";
                break;
        }
        return prefix;
    }

    @Override
    public int getItemCount() {
        return productsList != null ? productsList.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productTitle, productPrice;
        private ImageView productImage;

        public ViewHolder(View view) {
            super(view);
            productImage = (ImageView) view.findViewById(R.id.image);
            productTitle = (TextView) view.findViewById(R.id.title);
            productPrice = (TextView) view.findViewById(R.id.price);
        }

    }

}
