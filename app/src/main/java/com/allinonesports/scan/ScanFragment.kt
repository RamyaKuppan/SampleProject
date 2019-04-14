package com.allinonesports.scan

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.allinonesports.ProductActivity
import com.allinonesports.R
import kotlinx.android.synthetic.main.fragment_scan.view.*

class ScanFragment : Fragment() {

    lateinit var capturedImage: ImageView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_scan, container, false)

        capturedImage = view.imageView
        view.image.setOnClickListener {
            startActivity(Intent(activity, ProductActivity::class.java))
        }
        return view
    }


}