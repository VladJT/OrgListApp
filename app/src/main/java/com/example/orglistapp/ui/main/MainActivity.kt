package com.example.orglistapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.orglistapp.R
import com.example.orglistapp.databinding.ActivityMainBinding
import com.example.orglistapp.ui.org_details.OrgDetailsFragment
import com.example.orglistapp.ui.org_list.OrgListFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainer.id, OrgListFragment.newInstance())
                .commitNow()

        }
    }

    private fun showFragment(fragment: Fragment) {
        val f = supportFragmentManager.findFragmentByTag(fragment.tag)
        if (f == null) {
            supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.animator.slide_from_top, R.animator.slide_to_right)
                .replace(binding.fragmentContainer.id, fragment)
                .addToBackStack("")
                .commit()
        }
    }

    fun showOrgDetailsFragment(org_id: String) {
        showFragment(OrgDetailsFragment.newInstance(org_id))
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            showExitDialog()
        } else super.onBackPressed()
    }


    private fun showExitDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Выход")
            .setMessage("Вы уверены, что хотите выйти?")
            .setPositiveButton(
                android.R.string.yes
            ) { _, _ -> finish() }//иначе Activity переходит в "спящий режим" и остается в стеке
            .setNegativeButton(android.R.string.no, null)
            .setIcon(R.drawable.ic_baseline_exit_to_app_24)
            .show()
    }
}