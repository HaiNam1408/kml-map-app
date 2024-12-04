package com.example.kml_map_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.data.kml.KmlLayer
import org.xmlpull.v1.XmlPullParserException
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.io.IOException

class MapActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        // Setup Toolbar with Back Button
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Enable Back Button

        // Set the action for the Back Button
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Initialize Map Fragment
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        try {
            val kmlLayer = KmlLayer(googleMap, R.raw.map, this)
            kmlLayer.addLayerToMap()

            // kmlLayer.setOnFeatureClickListener { kmlFeature ->
            //     val description = kmlFeature.getProperty("description")
            //     val name = kmlFeature.getProperty("name")

            //     // Display information in a dialog
            //     MaterialAlertDialogBuilder(this)
            //         .setTitle(name ?: "Region Information")
            //         .setMessage(description ?: "No additional information available")
            //         .setPositiveButton("OK", null)
            //         .show()
            // }


            val center = LatLng(1.3389664033162423, 103.79767704879936) // Singapore
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, 12f))
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}
