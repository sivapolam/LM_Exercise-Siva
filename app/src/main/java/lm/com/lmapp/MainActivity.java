package lm.com.lmapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lm.com.lmapp.business.ApiHandler;
import lm.com.lmapp.data.model.ProductDetails;
import lm.com.lmapp.data.model.Products;
import lm.com.lmapp.presentation.activity.adapter.ProductListAdapter;
import lm.com.lmapp.utils.NetworkUtils;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,Constants {

    private Button buttonINR = null;
    private Button buttonAED = null;
    private Button buttonSAR = null;
    private ProgressBar progressBar;
    private TextView emptyText;
    private RecyclerView recyclerView;
    private ProductDetails productDetails;
    private List<Products> productDetailsList;
    private TextView titleProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        getProductDetails();
    }

    private void getProductDetails() {
        if (NetworkUtils.isNetworkAvailable(MainActivity.this)) {
            setVisibility(GONE, VISIBLE, GONE, null);
            new ApiHandler(apiResponseCallback).getProductDetails();
        } else {
            apiResponseCallback.onFailure(getString(R.string.no_internet_message));
        }
    }

    /**
     * Set Visibility of Views
     * @param recyclerVisibility - RecyclerView visibility status
     * @param progressVisibility - ProgressBar visibility status
     * @param emptyTextVisibility - EmptyTextView visibility status
     * @param emptyTextMessage - EmptyText message
     */
    private void setVisibility(int recyclerVisibility,
                               int progressVisibility,
                               int emptyTextVisibility,
                               String emptyTextMessage) {
        recyclerView.setVisibility(recyclerVisibility);
        titleProduct.setVisibility(recyclerVisibility);
        progressBar.setVisibility(progressVisibility);
        emptyText.setVisibility(emptyTextVisibility);
        emptyText.setText(emptyTextMessage);
    }

    private void initViews() {

        progressBar = (ProgressBar)findViewById(R.id.progress_view);
        emptyText = (TextView)findViewById(R.id.empty_text);
        titleProduct = (TextView)findViewById(R.id.title_product);

        buttonINR = (Button)findViewById(R.id.butINR);
        buttonAED = (Button)findViewById(R.id.butAED);
        buttonSAR = (Button)findViewById(R.id.butSAR);

        buttonINR.setOnClickListener(MainActivity.this);
        buttonAED.setOnClickListener(MainActivity.this);
        buttonSAR.setOnClickListener(MainActivity.this);

        buttonINR.setActivated(true);

        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

//        viewPager = (ViewPager) findViewById(R.id.viewPager);
//        viewPager.setAdapter(new CustomPagerAdapter(this,INR));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.butINR:
                setButtonState(true,false,false);
                displayData(INR);
//                viewPager.setAdapter(new CustomPagerAdapter(this,INR));
                break;
            case R.id.butAED:
                setButtonState(false,true,false);
                displayData(AED);
//                viewPager.setAdapter(new CustomPagerAdapter(this,AED));
                break;
            case R.id.butSAR:
                setButtonState(false,false,true);
                displayData(SAR);
//                viewPager.setAdapter(new CustomPagerAdapter(this,SAR));
                break;
        }

    }

    private void setButtonState(boolean inrSelected, boolean aedSelected, boolean sarSelected){
        buttonINR.setActivated(inrSelected);
        buttonAED.setActivated(aedSelected);
        buttonSAR.setActivated(sarSelected);
    }

    // Api response callback implementation
    private ApiResponseCallback apiResponseCallback = new ApiResponseCallback() {
        @Override
        public void onSuccess(Object responseBody) {
            if(responseBody != null && responseBody instanceof ProductDetails) {

                productDetails = ((ProductDetails) responseBody);
                productDetailsList =
                        (productDetails.getProducts() != null) ? productDetails.getProducts() : new ArrayList<Products>();

                displayData(INR);
                return;
            }

            setVisibility(GONE, GONE, VISIBLE, getString(R.string.no_results_found));
        }

        @Override
        public void onFailure(String errorMessage) {
            setVisibility(GONE, GONE, VISIBLE, errorMessage);
        }
    };

    private void displayData(String currencyType) {
        if (!productDetailsList.isEmpty()) {
            ProductListAdapter commitsAdapter = new ProductListAdapter(MainActivity.this, productDetailsList, currencyType);
            recyclerView.setAdapter(commitsAdapter);

            titleProduct.setText(productDetails.getTitle());
            setVisibility(VISIBLE, GONE, GONE, null);
            return;
        }
    }
}
