package com.example.carladelima.movie.app.login.view

import android.content.Intent
import android.os.Bundle
import com.example.carladelima.movie.R
import com.example.carladelima.movie.app.movie.view.activity.NavigationActivity
import com.example.carladelima.movie.core.BaseActivity
import kotlinx.android.synthetic.main.actitvity_login.*

class LoginActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actitvity_login)

        buttonLogin.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
        }
    }
}