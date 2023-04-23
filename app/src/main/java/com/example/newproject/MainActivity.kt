package com.example.newproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.newproject.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val fragmentManager: FragmentManager = supportFragmentManager

        // define fragments
        val  categoriesFragment: Fragment = CategoriesFragment()
//        val vocabListFragment: Fragment = VocabListFragment()
        val flashcardFragment: Fragment = FlashcardFragment()
        val wordListFragment: Fragment = WordListFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.action_words -> fragment = wordListFragment
                R.id.action_categories -> fragment = categoriesFragment
                R.id.action_study -> fragment = flashcardFragment
//                R.id.action_stats -> fragment = vocabListFragment
            }
            replaceFragment(fragment)
            true
        }
        // Call helper method to swap the FrameLayout with the fragment
//        replaceFragment(ArticleListFragment())

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.action_words

    }

    private fun replaceFragment(articleListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.article_frame_layout, articleListFragment)
        fragmentTransaction.commit()
    }
}