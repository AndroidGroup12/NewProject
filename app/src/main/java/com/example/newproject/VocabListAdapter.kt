package com.example.newproject

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VocabListAdapter (private val context: Context, private val items: List<Word>) :
    RecyclerView.Adapter<VocabListAdapter.ViewHolder>()  {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val wordTV: TextView
        val englishTV: TextView
        val addBtn: Button


        // TODO: Write a helper method to help set up the onBindViewHolder method

//        fun bind(item: Word) {
//            wordTV.text = item.languageWord
//            englishTV.text = item.englishTranslation
//        }

        init {
            wordTV = itemView.findViewById<TextView>(R.id.wordTv)
            englishTV = itemView.findViewById<TextView>(R.id.englishTv)
            addBtn = itemView.findViewById<Button>(R.id.add_button)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.vocab_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO: Get the individual article and bind to holder
        val item = items.get(position)
        holder.wordTV.text = item.languageWord
        holder.englishTV.text = item.englishTranslation

        // button status depending if the word is in the study list
        if (!wordsToLearn.contains(item)) {
            holder.addBtn.text = "+"
        }
        else if (wordsToLearn.contains(item)) {
            holder.addBtn.text = "✓"
        }

        // when button is clicked
        holder.addBtn.setOnClickListener() {
            if (holder.addBtn.text == "+") {
                if (!wordsToLearn.contains(item)) {
                    addToList(item)
                    holder.addBtn.text = "✓"
                }
            }
            else if (holder.addBtn.text == "✓") {
                deleteFromList(item)
                holder.addBtn.text = "+"
            }

        }

        for (word in wordsToLearn) {
            Log.d("The word", word.languageWord + "||" + word.englishTranslation)
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

    override fun getItemCount() = items.size


}