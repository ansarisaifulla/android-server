package com.saifulla.server_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<DataAdapter> ListOfdataAdapter;
    RecyclerView recyclerView;
//    String HTTP_JSON_URL = "http://xxxxxxxxx/index.php";
    String HTTP_JSON_URL = "https://xxxxxxx.000webhostapp.com/index.php";


    String Video_User_JSON = "v_user";
    String Video_Desc_JSON = "v_desc";
    String Image_URL_JSON = "v_image";
    String Video_ID_JSON = "v_id";
    String Video_Name_JSON = "v_name";
    String Video_URL_JSON = "v_video_url";


    JsonArrayRequest RequestOfJSonArray ;
    RequestQueue requestQueue ;

    View view ;

    int RecyclerViewItemPosition ;
    RecyclerView.LayoutManager layoutManagerOfrecyclerView;

    RecyclerView.Adapter recyclerViewadapter;

    ArrayList<String> VideoTitleNameArrayListForClick;
    ArrayList<String> videoUserArrayListForClick;

    ArrayList<String> VideoIDArrayListForClick;
    ArrayList<String> videoDescArrayListForClick;


    ArrayList<String> videoURLArrayListForClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoTitleNameArrayListForClick = new ArrayList<>();
        videoUserArrayListForClick = new ArrayList<>();

        VideoIDArrayListForClick = new ArrayList<>();
        videoDescArrayListForClick = new ArrayList<>();

        videoURLArrayListForClick = new ArrayList<>();

        ListOfdataAdapter = new ArrayList<>();

        recyclerView = findViewById(R.id.rcv);
        recyclerView.setHasFixedSize(true);
        layoutManagerOfrecyclerView = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManagerOfrecyclerView);
        JSON_HTTP_CALL();

        // Implementing Click Listener on RecyclerView.
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            GestureDetector gestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

                @Override public boolean onSingleTapUp(MotionEvent motionEvent) {

                    return true;
                }

            });
            @Override
            public boolean onInterceptTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

                view = Recyclerview.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if(view != null && gestureDetector.onTouchEvent(motionEvent)) {

                    //Getting RecyclerView Clicked Item value.
                    RecyclerViewItemPosition = Recyclerview.getChildAdapterPosition(view);

                    // Showing RecyclerView Clicked Item value using Toast.  videoURLArrayListForClick
                    Toast.makeText(MainActivity.this, VideoTitleNameArrayListForClick.get(RecyclerViewItemPosition), Toast.LENGTH_LONG).show();

                    String currentVideoURL = videoURLArrayListForClick.get(RecyclerViewItemPosition);
                    String currentVideoTitle = VideoTitleNameArrayListForClick.get(RecyclerViewItemPosition);
                    String currentVideoDescription = videoDescArrayListForClick.get(RecyclerViewItemPosition);


                    Intent i = new Intent(MainActivity.this, ActivityForVideo.class);

                    i.putExtra("url",currentVideoURL);
                    i.putExtra("title",currentVideoTitle);
                    i.putExtra("desc",currentVideoDescription);

                    startActivity(i);

                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView Recyclerview, MotionEvent motionEvent) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    public void JSON_HTTP_CALL(){

        RequestOfJSonArray = new JsonArrayRequest(HTTP_JSON_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        ParseJSonResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(RequestOfJSonArray);
    }

    public void ParseJSonResponse(JSONArray array){

        for(int i = 0; i<array.length(); i++) {

            DataAdapter GetDataAdapter2 = new DataAdapter();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

                GetDataAdapter2.setVideoUser(json.getString(Video_User_JSON));
                GetDataAdapter2.setVideoDesc(json.getString(Video_Desc_JSON));
                GetDataAdapter2.setVideoID(json.getString(Video_ID_JSON));

                GetDataAdapter2.setVideoTitle(json.getString(Video_Name_JSON));
                GetDataAdapter2.setVideoURL(json.getString(Video_URL_JSON));

                //String Video_User_JSON = "v_user";
                //    String Video_Desc_JSON = "v_desc";
                //    String Image_URL_JSON = "v_image";
                //
                //    String Video_ID_JSON = "v_id";
                //    String Video_Name_JSON = "v_name";
                //    String Video_URL_JSON = "v_video_url";

                // Adding image title name in array to display on RecyclerView click event.
                VideoTitleNameArrayListForClick.add(json.getString(Video_Name_JSON));
                videoUserArrayListForClick.add(json.getString(Video_User_JSON));

                videoDescArrayListForClick.add(json.getString(Video_Desc_JSON));
                VideoIDArrayListForClick.add(json.getString(Video_ID_JSON));

                videoURLArrayListForClick.add(json.getString(Video_URL_JSON));

                GetDataAdapter2.setImageUrl(json.getString(Image_URL_JSON));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            ListOfdataAdapter.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerViewAdapter(ListOfdataAdapter, this);

        recyclerView.setAdapter(recyclerViewadapter);
    }


}
