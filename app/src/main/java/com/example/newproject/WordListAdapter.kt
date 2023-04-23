package com.example.newproject

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


//val list: List<Word> = spanish_words.get_shopping_list()
class WordListAdapter (private val context: Context, private val wordList: List<Word> ) :
    RecyclerView.Adapter<WordListAdapter.ViewHolder>() {


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val wordTV: TextView
        val englishTV: TextView

        init {
            wordTV = itemView.findViewById<TextView>(R.id.wordlist_wordTv)
            englishTV = itemView.findViewById<TextView>(R.id.wordlist_EnglishTV)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.word_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO: Get the individual article and bind to holder
        val item = wordList.get(position)
        holder.wordTV.text = item.languageWord
        holder.englishTV.text = item.englishTranslation
    }


    override fun getItemCount() = wordList.size
}