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
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.RectangularBounds
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import java.util.Arrays


class HospitalMap : Fragment(), OnMapReadyCallback {

    private lateinit var mapView : MapView
    private lateinit var mapBackBtn: ImageButton
    private lateinit var placesClient: PlacesClient
    private lateinit var googleMap: GoogleMap

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

        // 위치 권한을 확인
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // 권한이 허용되어 있음, 지도 초기화
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

        return rootView
    }
    private fun initializeMap(rootView: View) {
        mapView = rootView.findViewById(R.id.mapFragment) as MapView
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
                    showPermissionDeniedDialog()
                }
            }
        }
    }
    private fun navigateToOtherFragment() {
        // 이동하고 싶은 Fragment를 생성
        val newsFragment = NewsFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()

        // 다른 Fragment로 교체하고 back stack에 추가
        transaction.replace(R.id.newsFragment, newsFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    //지도 객체를 사용할 수 있을 때 자동으로 호출되는 함수
    override fun onMapReady(gMap: GoogleMap) {
        googleMap = gMap

        //Get the current location
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                val placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS)
                val request = FindCurrentPlaceRequest.newInstance(placeFields)

                //Get the likely places
                val placeResponse = placesClient.findCurrentPlace(request)
                placeResponse.addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        val response = task.result
                        for (placeLikelihood in response?.placeLikelihoods ?: emptyList()){
                            val place = placeLikelihood.place
                            val placeLatLng = place.latLng
                            googleMap.addMarker(
                                MarkerOptions().position(placeLatLng).title(place.name)
                                    .snippet(place.address)
                            )
                        }
                        //Move the camera to the first result
                        response?.placeLikelihoods?.firstOrNull()?.let {
                            val placeLatLng = it.place.latLng
                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(placeLatLng))
                            googleMap.moveCamera(CameraUpdateFactory.zoomTo(15f))
                        }
                    }
                    else {
                        Log.e("HospitalMap", "Error getting places: ${task.exception}")
                    }
                }
            }
//        val marker = LatLng(37.568291,126.997780)
//        googleMap.addMarker(MarkerOptions().position(marker).title("여기"))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
//        googleMap.moveCamera(CameraUpdateFactory.zoomTo(15f))
    }

    private fun showPermissionDeniedDialog() {
        // 사용자에게 알림을 표시하거나 다른 조치를 취할 수 있는 코드를 추가
        // 예: AlertDialog 띄우기 등
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
