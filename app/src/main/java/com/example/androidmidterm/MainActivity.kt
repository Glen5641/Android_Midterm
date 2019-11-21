package com.example.androidmidterm

/**
 * Midterm Application Android
 *
 * STUDENT: Clayton Chase Glenn
 * OUID:    113375641
 * CLASS:   Software Engineering 1
 * DATE:    11/21/2019
 */

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Volley uses internet permissions which needs to be enabled in the AndroidManifest.xml
        val queue = Volley.newRequestQueue(this)

        // Url for Volley to get response from. Volley does not work locally with http requests.
        // Only https requests which this issue was resolved with app engine being https.
        val url = "https://software-engineering-1-250517.appspot.com/"

        // Collect the Json object from the endpoint and display the value associated with "random" key
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                // Display the random number in eventTitle text box on screen
                eventTitle.text = "Random Number: ${response.getString("random")}"
            },
            Response.ErrorListener { eventTitle.text = "That didn't work" })

        // Add the request to the queue if the button is clicked
        button.setOnClickListener {
            queue.add(jsonObjectRequest)
        }

        // Add the webview to the queue if the button is clicked
        button2.setOnClickListener{
            webView.loadUrl(url)
        }
    }
}