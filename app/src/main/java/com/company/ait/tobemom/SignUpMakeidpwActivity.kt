package com.company.ait.tobemom

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.company.ait.tobemom.databinding.ActivitySignupMakeidpwBinding

class SignUpMakeidpwActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupMakeidpwBinding
    private lateinit var spinner_agency : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupMakeidpwBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //통신사 스피너
        agencySpinner()

        //뒤로가기
        goBack()
        //다음 버튼
        goNext()
    }

    private fun agencySpinner() {
        spinner_agency = findViewById<Spinner>(R.id.idpw_agency_sp)
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.스피너목록, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);

        ArrayAdapter.createFromResource(this, R.array.network_agency_array, R.layout.spinner_agency
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner_agency.adapter = adapter
        }
    }

    private fun goBack() {
        binding.idpwBackBtn.setOnClickListener {
            finish()
        }
    }

    private fun goNext() {
        binding.idpwNextBtn.setOnClickListener {
            val intent = Intent(this, SignUpProfileActivity::class.java)
            startActivity(intent)
        }
    }
}