package com.adammcneilly.mobileleaguehacking.activities

import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.os.Bundle



/**
 * Displays a web view.
 *
 * Created by adam.mcneilly on 1/22/17.
 */
class WebviewActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mWebView = WebView(this)
        mWebView.settings.javaScriptEnabled = true
        val url = intent.getStringExtra(URL)
        mWebView.loadUrl(url)
        setContentView(mWebView)
    }

    companion object {
        val URL = "Url"
    }
}