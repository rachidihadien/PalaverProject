package com.example.palaver.data;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.palaver.MyApp;
import com.example.palaver.data.model.LoggedInUser;
import com.example.palaver.httpCallHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(final String username, final String password) {
        try {
//            RequestQueue queue = httpCallHandler.getInstance(MyApp.getContext()).getRequestQueue();
            String url = "http://palaver.se.paluno.uni-due.de/api/user/validate";

            final JSONObject jsonBody = new JSONObject("{\"Username\":\""+username+"\",\"Password\":\""+password+"\"}");
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if(response.getInt("MsgType") == 1){
                                    LoggedInUser thisUser = new LoggedInUser(username, password); // Muss jetzt eig zurückgegeben werden, aber async kacke. Wahrscheinlich über Callback handeln
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            Toast toast = Toast.makeText(MyApp.getContext(), response.toString(), Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast toast = Toast.makeText(MyApp.getContext(), "Fehler beim Call: "+error.getMessage(), Toast.LENGTH_LONG);
                            toast.show();
                        }

                    });

// Access the RequestQueue through your singleton class.
            httpCallHandler.getInstance(MyApp.getContext()).addToRequestQueue(jsonObjectRequest);

            // TODO: handle loggedInUser authentication
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
