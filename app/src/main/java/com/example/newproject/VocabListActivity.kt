package com.example.newproject


import android.annotation.SuppressLint
import android.content.Context
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
import org.w3c.dom.Text

var categoryBoolean: Boolean = false

class VocabListActivity : AppCompatActivity() {
    val vocabList = mutableListOf<Word>()
    val indexes = mutableListOf<Int>()
    private lateinit var vocabListRecyclerView: RecyclerView
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_vocab_list)


        val category = intent.extras?.getString("category")


        val categoryName: String
        val stickyView = findViewById<TextView>(R.id.vocabListStickyView)
        if (category != null) {
//            Log.d("vocabListActivity", "category is $category")
            val sharedPreferences = getSharedPreferences("LanguagePref", Context.MODE_PRIVATE)
            val language = sharedPreferences.getString("language", "Spanish")
            if (language == "Spanish") {
                if (category == "shopping") {
                    vocabList.addAll(Dictionary().get_shopping_list())
                    categoryName = "Shopping"
                    stickyView.text = categoryName
                } else if (category == "tourist") {
                    vocabList.addAll(Dictionary().get_tourist_list())
                    categoryName = "Tourist"
                    stickyView.text = categoryName
                } else if (category == "places") {
                    vocabList.addAll(Dictionary().get_places_list())
                    categoryName = "Places"
                    stickyView.text = categoryName
                } else if (category == "common") {
                    vocabList.addAll(Dictionary().get_common_list())
                    categoryName = "100 Most Common Words"
                    stickyView.text = categoryName
                } else if (category == "food") {
                    vocabList.addAll(Dictionary().get_food_list())
                    categoryName = "Food/Dining"
                    stickyView.text = categoryName
                }
            } else if (language == "German") {
                if (category == "shopping") {
                    val d = Dictionary()
                    val germanList = d.germanShopping
                    val germanListEng = d.germanShoppingEng
                    categoryName = "Shopping"
                    stickyView.text = categoryName

                    for (i in germanList.indices) {
                        vocabList.add(Word(germanList[i], germanListEng[i]))
                    }
                } else if (category == "tourist") {
                    val d = Dictionary()
                    val germanList = d.germanTravel
                    val germanListEng = d.germanTravelEng
                    categoryName = "Tourist"
                    stickyView.text = categoryName

                    for (i in germanList.indices) {
                        vocabList.add(Word(germanList[i], germanListEng[i]))
                    }
                } else if (category == "places") {
                    val d = Dictionary()
                    val germanList = d.germanPlaces
                    val germanListEng = d.germanPlacesEng
                    categoryName = "Places"
                    stickyView.text = categoryName

                    for (i in germanList.indices) {
                        vocabList.add(Word(germanList[i], germanListEng[i]))
                    }
                } else if (category == "food") {
                    val d = Dictionary()
                    val germanList = d.germanFood
                    val germanListEng = d.germanFoodEng
                    categoryName = "Food/Dining"
                    stickyView.text = categoryName

                    for (i in germanList.indices) {
                        vocabList.add(Word(germanList[i], germanListEng[i]))
                    }
                } else {
                    val d = Dictionary()
                    val germanList = d.germanCommon
                    val germanListEng = d.germanCommonEng
                    categoryName = "100 Most Common Words"
                    stickyView.text = categoryName

                    for (i in germanList.indices) {
                        vocabList.add(Word(germanList[i], germanListEng[i]))
                    }
                }
            }
            Dictionary().get_boolean(category)
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

        val saveBtn = findViewById<Button>(R.id.goBackButton)
        saveBtn.setOnClickListener() {
            Dictionary().writeData(applicationContext)
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
            indexes.distinct().sorted()
            updateText(addAllBtn)
            // update adapter
            vocabListAdapter.notifyDataSetChanged()
        }


    }

    fun addToList(word: Word) {
        if (!(wordsToLearn.contains(word))) {
            wordsToLearn.add(word)
        }
    }

    fun deleteFromList(word: Word) {
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