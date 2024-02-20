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
//import com.company.ait.tobemom.utils.PlacesApiClient
import com.company.ait.tobemom.utils.RetrofitClient2
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
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.material.snackbar.Snackbar

class HospitalMap : Fragment(), OnMapReadyCallback {

    private lateinit var mapView : MapView
    private lateinit var mapBackBtn: ImageButton
    private lateinit var placesClient: PlacesClient
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var hosInfoRv : RecyclerView
    private lateinit var searchHospital : ImageButton
    private lateinit var hospitalInfoAdapter: HospitalInfoAdapter
    private lateinit var hospitalList: MutableList<RetrofitClient2.Hospital>
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_hospital_map,container,false)

        mapView = rootView.findViewById(R.id.mapFragment) as MapView
        hosInfoRv = rootView.findViewById(R.id.hos_info_rv) as RecyclerView

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
            mapView.onCreate(null)
            mapView.getMapAsync(this)
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
                    // 현재 위치로 마커를 추가하고 지도 이동 등의 작업 수행
                    googleMap.addMarker(MarkerOptions().position(currentLatLng).title("My Location"))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLatLng))
                } else {
                    Log.e("HospitalMap", "Error: lastLocation is null")
                }
            }
    }

//    private fun searchNearbyRestaurants() {
//        val radius = 1000 // 1000m 반경 내의 음식점을 검색합니다.
//        val type = "hospital"
//        val apiKey = "AIzaSyA4Dm2lxSKbDk2jfdfE7u4FYhLcULZcX9A"
//
//        // Fused Location Provider를 사용하여 현재 위치 정보 가져오기
//        fusedLocationProviderClient.lastLocation
//            .addOnSuccessListener { location ->
//                if (location != null) {
//                    val currentLatLng = LatLng(location.latitude, location.longitude)
//                    val locationString = "${currentLatLng.latitude},${currentLatLng.longitude}"
//
//                    // Places API로 병원 정보 가져오기
//                    PlacesApiClient.getNearbyHospitals(locationString, radius, type, apiKey) { placesApiResponse ->
//                        Log.e("병원개수",currentLatLng )
//                        if (placesApiResponse != null ) {
//                            // 가져온 병원 정보를 Hospital로 변환하여 리스트에 추가원
//
//                            hospitalList.clear()
//                            // 여기서 hospitalList 초기화 추가
//                            hospitalList = mutableListOf()
//
//
//                            for (hospital in placesApiResponse) {
//                                val hospitalLocation = LatLng(
//                                    hospital.geometry.location.lat,
//                                    hospital.geometry.location.lng
//                                )
//                                val hospitalInfo = RetrofitClient2.Hospital(
//                                    hospital.name,
//                                    hospital.address,
//                                    hospital.vicinity,
//                                    hospital.geometry
//                                )
//                                hospitalList.add(hospitalInfo)
//                            }
//                            // Notify the adapter that the data set has changed
//                            hospitalInfoAdapter.notifyDataSetChanged()
//
//                            // Make the RecyclerView visible
//                            hosInfoRv.visibility = View.VISIBLE
//                        } else {
//                            Log.e("HospitalMap", "Error getting nearby hospitals")
//                        }
//                    }
//                } else {
//                    Log.e("HospitalMap", "Error: lastLocation is null")
//                }
//            }
//    }

    private fun navigateToOtherFragment() {
        // 이동하고 싶은 Fragment를 생성
        val newsFragment = NewsFragment()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()

        // 다른 Fragment로 교체하고 back stack에 추가
        transaction.replace(R.id.newsFragment, newsFragment)
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
