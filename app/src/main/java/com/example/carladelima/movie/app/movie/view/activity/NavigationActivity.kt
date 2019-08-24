package com.example.carladelima.movie.app.movie.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.carladelima.movie.R
import com.example.carladelima.movie.app.login.UserViewModel
import com.example.carladelima.movie.app.login.view.LoginActivity
import com.example.carladelima.movie.app.movie.view.fragment.FavoriteFragment
import com.example.carladelima.movie.app.movie.view.fragment.FilmesFragment
import com.example.carladelima.movie.core.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_navigation.*
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.newTask

class NavigationActivity : BaseActivity() {

    private lateinit var userViewModel: UserViewModel

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_filmes -> {
                this.supportFragmentManager.beginTransaction().replace(R.id.container, FilmesFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favoritos -> {
                this.supportFragmentManager.beginTransaction().replace(R.id.container, FavoriteFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        this.supportFragmentManager.beginTransaction().replace(R.id.container, FilmesFragment()).commit()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        subscribeUI()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logoutmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.sair -> {
                userViewModel.logout()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun subscribeUI(){

        with(userViewModel){

            onLogoutSucess.observe(this@NavigationActivity, Observer {
                val intent = Intent(this@NavigationActivity, LoginActivity::class.java).clearTask().newTask()
                startActivity(intent)
                finish()
            })

            onError.observe(this@NavigationActivity, Observer { error ->
                error?.let {
                    Snackbar.make(ac_navigation, "$error", Snackbar.LENGTH_LONG).show()
                }
            })

        }

    }

}
