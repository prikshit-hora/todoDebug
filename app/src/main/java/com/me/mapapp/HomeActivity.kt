package com.me.mapapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.model.LatLng
import com.me.mapapp.AddressFragment.Companion.LAT_LONG_KEY

class HomeActivity : AppCompatActivity(), MapFragmentListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addMapsFragment()
    }

    private fun addMapsFragment() {
        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.container, MapFragment())
        ft.commit()
    }

    override fun onMarkerClicked(latLong: LatLng) {
        openAddressFragment(latLong)
    }

    private fun openAddressFragment(latLong: LatLng) {
        val ft = supportFragmentManager.beginTransaction()
        val addressFragment = AddressFragment()
        val args = Bundle().apply { putParcelable(LAT_LONG_KEY, latLong) }
        addressFragment.arguments = args
        ft.replace(R.id.container, addressFragment)
        ft.commit()
    }
}