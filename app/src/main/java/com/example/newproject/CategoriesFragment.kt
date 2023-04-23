package com.example.newproject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch




/**
 * A simple [Fragment] subclass.
 * Use the [CategoriesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoriesFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated
        // define recyclerview and adapter
//        bitFitRecyclerView = view.findViewById(R.id.itemsRv)
//        val bitFitAdapter = BitFitAdapter(requireContext(), itemList)
//        bitFitRecyclerView.adapter = bitFitAdapter

//        // layout
//        bitFitRecyclerView.layoutManager = LinearLayoutManager(requireContext()).also {
//            val dividerItemDecoration = DividerItemDecoration(requireContext(), it.orientation)
//            bitFitRecyclerView.addItemDecoration(dividerItemDecoration)
//        }

        // Calling an intent when button is clicked
        val shoppingBtn = view.findViewById<ImageButton>(R.id.shopping_imageButton)
        shoppingBtn.setOnClickListener() {
            val i = Intent(requireContext(), VocabListActivity::class.java)
            i.putExtra("category", "shopping")
            startActivity(i)
        }

        val touristBtn = view.findViewById<ImageButton>(R.id.tourist_imageButton)
        touristBtn.setOnClickListener() {
            val i = Intent(requireContext(), VocabListActivity::class.java)
            i.putExtra("category", "tourist")
            startActivity(i)

        }

        val foodBtn = view.findViewById<ImageButton>(R.id.food_imageButton)
        foodBtn.setOnClickListener() {
            val i = Intent(requireContext(), VocabListActivity::class.java)
            i.putExtra("category", "food")
            startActivity(i)

        }

        val commonBtn = view.findViewById<ImageButton>(R.id.common_words_imageButton)
        commonBtn.setOnClickListener() {
            val i = Intent(requireContext(), VocabListActivity::class.java)
            i.putExtra("category", "common")
            startActivity(i)

        }

        val placesBtn = view.findViewById<ImageButton>(R.id.places_imageButton)
        placesBtn.setOnClickListener() {
            val i = Intent(requireContext(), VocabListActivity::class.java)
            i.putExtra("category", "places")
            startActivity(i)
        }


//        // Delete all Entries
//        val deleteBtn = view.findViewById<Button>(R.id.deleteBtn)
//        deleteBtn.setOnClickListener(){
//            lifecycleScope.launch(Dispatchers.IO) {
//                (requireActivity().application as BitFitApplication).db.BitFitDao().deleteAll()
//            }
//            itemList.clear()
//            bitFitAdapter.notifyDataSetChanged()
//        }
    }


}