package com.example.orglistapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.orglistapp.databinding.ActivityMainBinding
import com.example.orglistapp.ui.org_list.OrgListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            showFragment(OrgListFragment.newInstance())
        }
    }

    fun showFragment(fragment: Fragment) {
        val f = supportFragmentManager.findFragmentByTag(fragment.tag)
        if (f == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fragmentContainer.id, fragment)
                .addToBackStack("")
                .commit()
        }
    }
}