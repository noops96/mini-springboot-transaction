package com.example.Transaction.Helper;

import java.io.IOException;

import com.example.Transaction.Model.BuyerModel;
import com.example.Transaction.Model.ProductModel;
import com.example.Transaction.Model.SellerModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestService {

    private final static OkHttpClient httpClient = new OkHttpClient();
    private final static String baseURLAccountAccount = "http://localhost:8080/";
    private final static String baseURLAccountProduct = "http://localhost:8181/";
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static Boolean doCheckBuyer(Integer id) throws IOException {
    	
    	Boolean isValid = false;
    	
    	HttpUrl.Builder urlBuilder = HttpUrl.parse(baseURLAccountAccount+"account/is-buyer-exist").newBuilder();
    	urlBuilder.addQueryParameter("id", id.toString());
    	String newUrl = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(newUrl).
                build();
        
        try {
        Response response = httpClient.newCall(request).execute();
        isValid = Boolean.parseBoolean(response.body().string());
        }catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
        
        return isValid;

    }
    
public static BuyerModel doGetBuyer(Integer id) throws IOException {
    	HttpUrl.Builder urlBuilder = HttpUrl.parse(baseURLAccountAccount+"account/get-buyer-by-id").newBuilder();
    	urlBuilder.addQueryParameter("id", id.toString());
    	String newUrl = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(newUrl).
                build();
        String jsonString = "";
        try {
        Response response = httpClient.newCall(request).execute();
        jsonString = response.body().string();
        }catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
        BuyerModel buyer = objectMapper.readValue(jsonString, BuyerModel.class);
        return buyer;
    }

public static SellerModel doGetSeller(Integer id) throws IOException {
	HttpUrl.Builder urlBuilder = HttpUrl.parse(baseURLAccountAccount+"account/get-seller-by-id").newBuilder();
	urlBuilder.addQueryParameter("id", id.toString());
	String newUrl = urlBuilder.build().toString();

    Request request = new Request.Builder()
            .url(newUrl).
            build();
    String jsonString = "";
    try {
    Response response = httpClient.newCall(request).execute();
    jsonString = response.body().string();
    }catch (Exception e) {
		System.out.println(e.getLocalizedMessage());
	}
    SellerModel seller = objectMapper.readValue(jsonString, SellerModel.class);
    return seller;
}

public static ProductModel doGetProduct(Integer id) throws IOException {
	HttpUrl.Builder urlBuilder = HttpUrl.parse(baseURLAccountProduct+"product/get-by-id").newBuilder();
	urlBuilder.addQueryParameter("id", id.toString());
	String newUrl = urlBuilder.build().toString();

    Request request = new Request.Builder()
            .url(newUrl).
            build();
    String jsonString = "";
    try {
    Response response = httpClient.newCall(request).execute();
    jsonString = response.body().string();
    }catch (Exception e) {
		System.out.println(e.getLocalizedMessage());
	}
    ProductModel product = objectMapper.readValue(jsonString, ProductModel.class);
    return product;
}

}
