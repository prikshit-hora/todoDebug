package com.me.mapapp

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_address.*
import java.util.*


class AddressFragment : Fragment() {
    lateinit var latLng: LatLng

    companion object {
        const val LAT_LONG_KEY = "lat_long"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        latLng = arguments?.get(LAT_LONG_KEY) as LatLng
        return inflater.inflate(R.layout.fragment_address, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        reverseGeocode(latLng)
    }

    private fun reverseGeocode(location: LatLng) {
        //TODO: This should be done off the main thread and it exists for the purpose of assignment only
        var addresses: List<Address> = emptyList()

        try {
            val geoCoder = Geocoder(activity, Locale.getDefault())
            addresses = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
            tvAddress.text = addresses.first().getAddressLine(0)
        } catch (exception: Exception) {
            println("Exception reverse geo-coding: $exception")
        }
    }
}
