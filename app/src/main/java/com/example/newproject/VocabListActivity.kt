package com.example.newproject


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newproject.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.newproject.spanish_words
import org.w3c.dom.Text

var categoryBoolean: Boolean = false

class VocabListActivity : AppCompatActivity() {
    val vocabList = mutableListOf<Word>()
    private lateinit var vocabListRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_vocab_list)


        val category = intent.extras?.getString("category")


        val categoryName: String
        val stickyView = findViewById<TextView>(R.id.vocabListStickyView)
        if (category != null) {
//            Log.d("vocabListActivity", "category is $category")
            if (category == "shopping") {
                vocabList.addAll(spanish_words().get_shopping_list())
                categoryName = "Shopping"
                stickyView.text = categoryName
            }
            else if (category == "tourist") {
                vocabList.addAll(spanish_words().get_tourist_list())
                categoryName = "Tourist"
                stickyView.text = categoryName
            }
            else if (category == "places") {
                vocabList.addAll(spanish_words().get_places_list())
                categoryName = "Places"
                stickyView.text = categoryName
            }
            else if (category == "common") {
                vocabList.addAll(spanish_words().get_common_list())
                categoryName = "100 Most Common Words"
                stickyView.text = categoryName
            }
            else if (category == "food") {
                vocabList.addAll(spanish_words().get_food_list())
                categoryName = "Food/Dining"
                stickyView.text = categoryName
            }
            spanish_words().get_boolean(category)
        }

        // RecyclerView and Adapter
        vocabListRecyclerView = findViewById(R.id.vocabListRV)
        val vocabListAdapter = VocabListAdapter(this, vocabList)
        vocabListRecyclerView.adapter = vocabListAdapter
       //vocabListRecyclerView.layoutManager = LinearLayoutManager(this)

        // layoutout Manager + divider
        vocabListRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            vocabListRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        for (word in wordsToLearn) {
            Log.d("The word", word.languageWord + "|" + word.englishTranslation)
        }

        val goBackBtn = findViewById<Button>(R.id.goBackButton)
        goBackBtn.setOnClickListener() {
            val i = Intent(this@VocabListActivity, MainActivity::class.java)
            startActivity(i)

        }


        val addAllBtn = findViewById<Button>(R.id.addAll_button)
        addAllBtn.setOnClickListener() {
//            if (addAllBtn.text == "Add All") {
//                for (word in vocabList) {
//                    if (!wordsToLearn.contains(word)) {
//                        addToList(word)
//                        addAllBtn.text = "Added All ✓"
//                    }
//                }
//            } else if (addAllBtn.text == "Added All ✓") {
//                for (word in vocabList) {
//                    if (wordsToLearn.contains(word)) {
//                        deleteFromList(word)
//                        addAllBtn.text = "Add All"
//                    }
//                }
//            }
            for (word in vocabList) {
                if (!wordsToLearn.contains(word)) {
                    addToList(word)
                    addAllBtn.text = "Added All ✓"
                } else if (wordsToLearn.contains(word)) {
                    deleteFromList(word)
                    addAllBtn.text = "Add All"
                }
            }
            updateText(addAllBtn)
            // update adapter
            vocabListAdapter.notifyDataSetChanged()
        }


    }

    fun addToList(word: Word) {
        val to_study = spanish_words()
        if (!(wordsToLearn.contains(word))) {
            wordsToLearn.add(word)
        }
    }

    fun deleteFromList(word: Word) {
        val to_study = spanish_words()
        wordsToLearn.remove(word)
    }

    fun updateText(Btn: Button) {
        if (categoryBoolean == false) {
            Btn.text = "Add All"
        } else if (categoryBoolean == true) {
            Btn.text = "Added All ✓"
        }
    }
}