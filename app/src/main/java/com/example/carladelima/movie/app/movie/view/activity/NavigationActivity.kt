package com.example.carladelima.movie.app.movie.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.carladelima.movie.R
import com.example.carladelima.movie.app.movie.view.fragment.FavoriteFragment
import com.example.carladelima.movie.app.movie.view.fragment.FilmesFragment
import com.example.carladelima.movie.core.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : BaseActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_filmes -> {
                message.setText(R.string.title_filmes)
                this.supportFragmentManager.beginTransaction().replace(R.id.container, FilmesFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favoritos -> {
                message.setText(R.string.title_favoritos)
                this.supportFragmentManager.beginTransaction().replace(R.id.container, FavoriteFragment()).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logoutmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.sair -> {
                finish() //fazer logout
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
