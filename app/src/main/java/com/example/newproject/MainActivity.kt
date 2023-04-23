package com.example.newproject;

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.newproject.CategoriesFragment
import com.example.newproject.FlashcardFragment
import com.example.newproject.R
import com.example.newproject.WordListFragment
import com.example.newproject.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("LanguagePref", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("language", "Spanish")
        loadContentForLanguage(language.toString())
    }
    private fun loadContentForLanguage(language: String) {
        // Load the appropriate content based on the selected language
        wordsToLearn.clear()
        wordsMastered.clear()
        if (language == "Spanish") {
            Dictionary().readData(applicationContext)
        } else if (language == "German") {
            Dictionary().readData(applicationContext)
        }

        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val categoriesFragment: Fragment = CategoriesFragment()
        val flashcardFragment: Fragment = FlashcardFragment()
        val wordsFragment: Fragment = WordListFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.action_categories -> fragment = categoriesFragment
                R.id.action_words -> fragment = wordsFragment
                R.id.action_study -> fragment = flashcardFragment
                R.id.action_settings -> {
                    sharedPreferences.edit().remove("language").apply()
                    val intent = Intent(this, ChooseLanguage::class.java)
                    startActivity(intent)
                    return@setOnItemSelectedListener true
                }
            }
            replaceFragment(fragment)
            true
        }
        bottomNavigationView.selectedItemId = R.id.action_categories
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)

        fragmentTransaction.commit()
    }
}