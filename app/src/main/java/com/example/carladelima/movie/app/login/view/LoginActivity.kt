package com.example.carladelima.movie.app.login.view

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.carladelima.movie.R
import com.example.carladelima.movie.app.login.UserDatabase
import com.example.carladelima.movie.app.login.UserViewModel
import com.example.carladelima.movie.app.movie.view.activity.NavigationActivity
import com.example.carladelima.movie.core.BaseActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.actitvity_login.*

class LoginActivity : BaseActivity() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actitvity_login)

        if (UserDatabase.getUser() != null) {
            val intent = Intent(this@LoginActivity, NavigationActivity::class.java)
            startActivity(intent)
        }

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)

        buttonLogin.setOnClickListener {
            userViewModel.login(user.text.toString(), password.text.toString())
        }

        subscribeUI()
    }

    private fun subscribeUI() {

        with(userViewModel) {

            onUserLogged.observe(this@LoginActivity, Observer {
                val intent = Intent(this@LoginActivity, NavigationActivity::class.java)
                startActivity(intent)
                finish()
            })

            onLoginSucess.observe(this@LoginActivity, Observer {
                val intent = Intent(this@LoginActivity, NavigationActivity::class.java)
                startActivity(intent)
                finish()
            })

            onError.observe(this@LoginActivity, Observer { error ->
                error?.let {
                    Snackbar.make(login_activity, "$error", Snackbar.LENGTH_LONG).show()
                }
            })

        }

    }
}