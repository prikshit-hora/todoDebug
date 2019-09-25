package com.me.mapapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener,
    GoogleMap.OnMapClickListener {

    private lateinit var mapFragment: SupportMapFragment
    private lateinit var map: GoogleMap
    private var listener: MapFragmentListener? = null
    private var marker: Marker? = null

    companion object {
        const val ZOOM_LEVEL = 15f
        val HERE = LatLng(28.4791359, 77.079358)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        initMap()
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MapFragmentListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement MapFragmentListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun initMap() {
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.isZoomControlsEnabled = true
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(HERE, ZOOM_LEVEL))
        map.setOnMapClickListener(this)
        map.setOnMarkerClickListener(this)
    }

    override fun onMapClick(latLng: LatLng) {
        marker?.remove()

        marker = map.addMarker(
            MarkerOptions()
                .position(latLng)
                .title("Your clicked here!")
        )
        marker?.tag = latLng
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        listener?.onMarkerClicked(marker.tag as LatLng)
        return false
    }

}

interface MapFragmentListener {
    fun onMarkerClicked(latLong: LatLng)
}
