package com.apps.loginsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.apps.loginsystem.model.ProfileInformation
import com.apps.todolistapp.databinding.ActivityRegistrationBinding
import com.apps.todolistapp.ui.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    private val profileInformationCollection = Firebase.firestore.collection("profileInformation")
    private lateinit var binding: ActivityRegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.btnRegister.setOnClickListener {
            var emailAddress = binding.editTextEmailAddress.text.toString()
            var password = binding.editTextPassowrd.text.toString()
            val profileInformation = getProfileInformation() // Get Information
            saveProfileInformation(profileInformation)

            if(emailAddress.isNotEmpty() && password.isNotEmpty()){
                auth.createUserWithEmailAndPassword(emailAddress,password).addOnSuccessListener {
                  val currentUser =  auth.currentUser
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this,"Authentication Failed",Toast.LENGTH_LONG).show()
                }
            }

        }
        binding.txLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getProfileInformation(): ProfileInformation {
        var fullName = binding.editTextTextFullName.text.toString()
        var emailAddress = binding.editTextEmailAddress.text.toString()
        var phoneNumber = binding.editTextPhoneNumber.text.toString()
        var password = binding.editTextPassowrd.text.toString()
        return ProfileInformation(fullName, emailAddress, phoneNumber.toInt(), password)
    }

    private fun saveProfileInformation(profileInformation: ProfileInformation) {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                profileInformationCollection.add(profileInformation)
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@RegistrationActivity,
                        "Data Saved Successfully",
                        Toast.LENGTH_LONG
                    ).show()
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@RegistrationActivity, e.message, Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}