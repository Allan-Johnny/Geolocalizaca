    package com.example.geolocalizao;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;


import org.json.JSONObject;

    public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {


        EditText txtID;
        TextView pais;
        TextView regionName;
        TextView city;
        TextView lat;
        TextView lon;
        TextView CodPais;
        public static String latitude = "0";
        public static String longitude = "0";
        private MapView mapView;
        private GoogleMap gmap;
        private static final String MAP_VIEW_BUNDLE_KEY = "AIzaSyC-DXTLq-5p87v9vJYlIEWPTWe3Yzq0rCE";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            txtID = findViewById(R.id.txtID);
            pais = findViewById(R.id.pais);
            CodPais = findViewById(R.id.CodPais);
            regionName = findViewById(R.id.regionName);
            city = findViewById(R.id.city);
            mapView = findViewById(R.id.mapView);
            lat = findViewById(R.id.lat);
            lon = findViewById(R.id.lon);

//map

                Bundle mapViewBundle = null;
                if (savedInstanceState != null) {
                    mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
                }

                mapView = findViewById(R.id.mapView);
                mapView.onCreate(mapViewBundle);
                mapView.getMapAsync(this);
            }
            @Override
            public void onSaveInstanceState(Bundle outState) {
                super.onSaveInstanceState(outState);

                Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
                if (mapViewBundle == null) {
                    mapViewBundle = new Bundle();
                    outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
                }

                mapView.onSaveInstanceState(mapViewBundle);
            }
            @Override
            protected void onResume() {
                super.onResume();
                mapView.onResume();
            }

            @Override
            protected void onStart()  {
                super.onStart();
                mapView.onStart();
            }

            @Override
            protected void onStop() {
                super.onStop();
                mapView.onStop();
            }
            @Override
            protected void onPause() {
                mapView.onPause();
                super.onPause();
            }
            @Override
            protected void onDestroy() {
                mapView.onDestroy();
                super.onDestroy();
            }
            @Override
            public void onLowMemory() {
                super.onLowMemory();
                mapView.onLowMemory();
            }
            @Override
            public void onMapReady(GoogleMap googleMap)
             {
//                gmap = googleMap;
//                gmap.setMinZoomPreference(12);
//                LatLng ny = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
//                gmap.moveCamera(CameraUpdateFactory.newLatLng(ny));
            }

//chamar URl
        public void btnLocalizarOnClick(View v) {
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "http://ip-api.com/json/" + txtID.getText().toString();

//solicitando resposta da url
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

//exibir os dados

                            JSONObject obj = null;
                            try {
                                obj = new JSONObject(response);
                                pais.setText(obj.get("country").toString());
                                CodPais.setText(obj.get("countryCode").toString());
                                regionName.setText(obj.get("regionName").toString());
                                city.setText(obj.get("city").toString());

                                latitude = obj.get("lat").toString();
                                longitude = obj.get("lon").toString();

                                lat.setText(latitude);
                                lon.setText(longitude);
                            } catch (Exception e) {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });

            // Add the request to the RequestQueue.
            queue.add(stringRequest);
        }

    }