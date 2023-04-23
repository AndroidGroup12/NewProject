package com.example.newproject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WordListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WordListFragment : Fragment() {
    private val studyList = mutableListOf<Word>()
    private val masteredList = mutableListOf<Word>()
    private lateinit var masteredRecyclerView: RecyclerView
    private lateinit var studyRecyclerView: RecyclerView
    private lateinit var wordListAdapter: WordListAdapter
    private lateinit var wordsToLearnTV : TextView
    private lateinit var wordsmasteredTV : TextView
    var set = mutableSetOf<Word>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_word_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: STUDY LIST recyclerview, dapter and layoutManager
        set = spanish_words().get_shopping_list().toMutableSet()
        studyList.addAll(wordsToLearn)
        wordsToLearnTV = view.findViewById<TextView>(R.id.to_learn_count)
        wordsToLearnTV.text = studyList.size.toString()

        studyRecyclerView = view.findViewById(R.id.words_to_learnRV)
        wordListAdapter = WordListAdapter(requireContext(), studyList)
        studyRecyclerView.adapter = wordListAdapter

        // layout
        studyRecyclerView.layoutManager = LinearLayoutManager(requireContext()).also {
            val dividerItemDecoration = DividerItemDecoration(requireContext(), it.orientation)
            studyRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        // TODO: MASTERED LIST recyclerview, dapter and layoutManager
        masteredList.addAll(spanish_words().get_places_list())
        wordsToLearnTV = view.findViewById<TextView>(R.id.mastered_count)
        wordsToLearnTV.text = masteredList.size.toString()

        masteredRecyclerView = view.findViewById(R.id.words_masteredRV)
        wordListAdapter = WordListAdapter(requireContext(), masteredList)
        masteredRecyclerView.adapter = wordListAdapter

        // layout
        masteredRecyclerView.layoutManager = LinearLayoutManager(requireContext()).also {
            val dividerItemDecoration = DividerItemDecoration(requireContext(), it.orientation)
            masteredRecyclerView.addItemDecoration(dividerItemDecoration)
        }

    }

}