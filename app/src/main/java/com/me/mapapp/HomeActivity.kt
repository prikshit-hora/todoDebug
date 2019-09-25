package com.me.mapapp

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.model.LatLng
import java.util.*

class HomeActivity : AppCompatActivity(), MapFragmentListener {

    var clickedLatLngs: ArrayList<LatLng> = arrayListOf()
    var addresses: ArrayList<String> = arrayListOf()
    private lateinit var geoCoder: Geocoder

    lateinit var addressFragment: AddressFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        geoCoder = Geocoder(this, Locale.getDefault())
        addMapsFragment()
        addAddressFragment()
    }

    private fun addMapsFragment() {
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.mapContainer, MapFragment())
        ft.commit()
    }

    private fun addAddressFragment() {
        val ft = supportFragmentManager.beginTransaction()
        addressFragment = AddressFragment()
        ft.replace(R.id.addressContainer, addressFragment)
        ft.commit()
    }

    override fun onMarkerClicked(latLong: LatLng) {
        clickedLatLngs.add(latLong)

        reverseGeocode(latLong)?.let { address ->
            addresses.add(address)
            addressFragment.updateAddresses(addresses)
        }
    }

    private fun reverseGeocode(location: LatLng): String? {
        //TODO: This should be done off the main thread and it exists for the purpose of assignment only
        var addresses: List<Address> = arrayListOf()

        return try {
            addresses = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
            addresses.first().getAddressLine(0)
        } catch (exception: Exception) {
            null
        }
    }
}