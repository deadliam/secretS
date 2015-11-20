package com.example.deadliam.secret;

import android.app.ProgressDialog;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.deadliam.parser.JSONParser;

/**
 * Created by deadliam on 10/27/15.
 */
public class DataBaseCommunicator {

//TODO: make singletone

    private ProgressDialog pDialog;

    private static DataBaseCommunicator mInstance;

    private DataBaseCommunicator(){
        // empty constructor
    }

    public static DataBaseCommunicator getInstance(){
        if(mInstance == null){
            mInstance = new DataBaseCommunicator();
        }
        return mInstance;
    }

    // Create JSON com.example.deadliam.parser
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> productsList;

    // url getting all products
    private static String url_all_products = "http://176.36.176.182/get_all_products.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_PRODUCTS = "products";
    private static final String TAG_PID = "pid";
    private static final String TAG_NAME = "name";

    // array of products
    JSONArray products = null;

    public  void login(LoginStatus callback){
        // create proper asynctask
        callback.onFaild();
    }

    public interface LoginStatus{

        void onSuccess();

        void onFaild();

    }



}
