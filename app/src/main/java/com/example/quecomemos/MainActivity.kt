package com.example.quecomemos

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = Firebase.auth
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val btnRegister = findViewById<Button>(R.id.btn_registrer)
        val email = findViewById<EditText>(R.id.editText_email).toString()
        val password = findViewById<EditText>(R.id.editText_password).toString()

        btnLogin.setOnClickListener {
            Toast.makeText(this, "email = $email y clave = $password", Toast.LENGTH_SHORT).show()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        Log.d(TAG, "access$user")
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()

                    }
                }
        }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            //usuario activo reload();enviar a homefragment
            Log.d(TAG, "aaaa")
        }
    }

    /**crear usuario
     *
     * auth.createUserWithEmailAndPassword(email, password)
    .addOnCompleteListener(this) { task ->
    if (task.isSuccessful) {
    // Sign in success, update UI with the signed-in user's information
    Log.d(TAG, "createUserWithEmail:success")
    val user = auth.currentUser
    updateUI(user)
    } else {
    // If sign in fails, display a message to the user.
    Log.w(TAG, "createUserWithEmail:failure", task.exception)
    Toast.makeText(baseContext, "Authentication failed.",
    Toast.LENGTH_SHORT).show()
    updateUI(null)
    }
    }
     * **/

    /**Acceso de usuarios existentes
     *
     * auth.signInWithEmailAndPassword(email, password)
    .addOnCompleteListener(this) { task ->
    if (task.isSuccessful) {
    // Sign in success, update UI with the signed-in user's information
    Log.d(TAG, "signInWithEmail:success")
    val user = auth.currentUser
    updateUI(user)
    } else {
    // If sign in fails, display a message to the user.
    Log.w(TAG, "signInWithEmail:failure", task.exception)
    Toast.makeText(baseContext, "Authentication failed.",
    Toast.LENGTH_SHORT).show()
    updateUI(null)
    }
    }
     * **/
}