package com.company.ait.tobemom

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.material.snackbar.Snackbar

class HospitalMap : Fragment(), OnMapReadyCallback {

    private lateinit var mapView : MapView
    private lateinit var mapBackBtn: ImageButton
    private lateinit var placesClient: PlacesClient
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_hospital_map,container,false)

        mapView = rootView.findViewById(R.id.mapFragment) as MapView

        // Initialize Places SDK
        Places.initialize(requireContext(), "AIzaSyCVZftl7Ka0UEsOnSJRnchSSb1Mu_Y7Vrc")
        placesClient = Places.createClient(requireContext())

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())

        // 위치 권한을 확인
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            initializeMap(rootView)
        } else {
            // 권한이 허용되어 있지 않음, 권한 요청
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
        mapBackBtn = rootView.findViewById(R.id.map_back_btn)
        mapBackBtn.setOnClickListener {
            navigateToOtherFragment()
        }
        //searchNearbyRestaurants()

        return rootView
    }
    private fun initializeMap(rootView: View) {
        mapView.onCreate(null)
        mapView.getMapAsync(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 권한이 허용되었을 때
                    initializeMap(requireView())
                } else {
                    // 권한이 거부되었을 때 사용자에게 알림을 표시
                    Snackbar.make(
                        requireView(),
                        "Location permission denied. Some features may not work.",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    //지도 객체를 사용할 수 있을 때 자동으로 호출되는 함수
    override fun onMapReady(gMap: GoogleMap) {
        googleMap = gMap

        //Enable location tracking on the map
        googleMap.isMyLocationEnabled = true

        // Get the current location
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    // 현재 위치로 마커를 추가하고 지도 이동 및 확대
                    googleMap.addMarker(MarkerOptions().position(currentLatLng).title("My Location"))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 18f)) // 15f는 확대 수준입니다.
                } else {
                    Log.e("HospitalMap", "Error: lastLocation is null")
                }
            }
    }

    private fun navigateToOtherFragment() {
        // 이동하고 싶은 Fragment를 생성
        val newsFragment = NewsFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()

        // 다른 Fragment로 교체하고 back stack에 추가
        transaction.replace(R.id.hospitalMap_fragment, newsFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }
}
