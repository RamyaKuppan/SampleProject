package com.allinonesports

import android.Manifest
import android.annotation.TargetApi
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast
import com.allinonesports.database.StoreDatabase
import com.allinonesports.shop.Product
import kotlinx.android.synthetic.main.activity_product.*
import org.jetbrains.anko.doAsync
import java.io.ByteArrayOutputStream


class ProductActivity : AppCompatActivity() {
    private val MY_CAMERA_PERMISSION_CODE = 2
    private val CAMERA_REQUEST = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        image.setOnClickListener({
            if (!TextUtils.isEmpty(product_name.text)) {
                setCameraRequest()
            } else {
                Toast.makeText(this, "Please enter the product name", Toast.LENGTH_SHORT).show()
            }
        })
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun setCameraRequest() {
        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CAMERA),
                    MY_CAMERA_PERMISSION_CODE)

        } else {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, CAMERA_REQUEST)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show()
                val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(cameraIntent, CAMERA_REQUEST)
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {

            val photo: Bitmap = data?.extras?.get("data") as Bitmap

            val productDao = StoreDatabase.getInstance(this).getProducts()

            doAsync {
                val product = Product(product_name.text.toString(), 0.0f, "", getImageBytes(photo))
                productDao.insert(product)
            }
            finish()
            Toast.makeText(this, "Product added to shop", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getImageBytes(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

}
