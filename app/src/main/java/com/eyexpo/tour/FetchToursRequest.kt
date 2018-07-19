package com.eyexpo.tour

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject


class FetchToursRequest(private val authToken: String, listener: Response.Listener<JSONObject>,
                        errorListener: Response.ErrorListener) :
        JsonObjectRequest("https://stage.eyexpo.com/api/v1/users/publictours", null, listener, errorListener) {

    @Throws(AuthFailureError::class)
    override fun getHeaders(): Map<String, String> {
        val params = HashMap<String, String>()
        params["Authorization"] = authToken
        return params
    }
}