package com.example.newproject

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import org.w3c.dom.Text

/**
 * A simple [Fragment] subclass.
 * Use the [flashcard.newInstance] factory method to
 * create an instance of this fragment.
 */
class FlashcardFragment() : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flashcard, container, false)
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val d = Dictionary()

        val incorrectBtn: ImageButton = view.findViewById(R.id.incorrect_button)
        incorrectBtn.setImageDrawable(resources.getDrawable(R.drawable.screen_shot_2023_04_14_at_8_59_54_pm))
        val correctBtn: ImageButton = view.findViewById(R.id.correct_button)
        correctBtn.setImageDrawable(resources.getDrawable(R.drawable.screen_shot_2023_04_14_at_8_59_41_pm))
        val saveBtn: Button = view.findViewById(R.id.save_changes_button)

        val flashcardFront: TextView = view.findViewById(R.id.flashcardFront)
        val flashcardBack: TextView = view.findViewById(R.id.flashcardBack)
        flashcardBack.visibility = View.INVISIBLE

        var count: Int = 0
        updateCards(count, flashcardFront, flashcardBack)

        incorrectBtn.setOnClickListener {
            count++
            if (count >= wordsToLearn.size)
                count = 0

            updateCards(count, flashcardFront, flashcardBack)
        }

        correctBtn.setOnClickListener {
            wordsMastered.add(wordsToLearn[count])
            wordsMastered.distinct()
            wordsToLearn.removeAt(count)
            if (count >= wordsToLearn.size)
                count = 0
            updateCards(count, flashcardFront, flashcardBack)
        }

        saveBtn.setOnClickListener {
            Log.i("FILES", "$wordsMastered")
            d.writeData(requireContext())
        }

        flashcardFront.setOnClickListener {
            flipCard(view.context, flashcardBack, it)
        }

        flashcardBack.setOnClickListener {
            flipCard(view.context, flashcardFront, it)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateCards(count: Int, front: TextView, back: TextView) {
        if (wordsToLearn.isEmpty()) {
            front.text = "Add some words!"
            back.text = "Add some words!"
            return
        }
        front.text = wordsToLearn[count].languageWord
        back.text = wordsToLearn[count].englishTranslation
    }

    private fun flipCard(context: Context, visibleView: View, invisibleView: View) {
        try {
            visibleView.visibility = View.VISIBLE
            val scale = context.resources.displayMetrics.density
            val cameraDist = 8000 * scale
            visibleView.cameraDistance = cameraDist
            invisibleView.cameraDistance = cameraDist
            val flipOutAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_out
                ) as AnimatorSet
            flipOutAnimatorSet.setTarget(invisibleView)
            val flipInAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_in
                ) as AnimatorSet
            flipInAnimatorSet.setTarget(visibleView)
            flipOutAnimatorSet.start()
            flipInAnimatorSet.start()
            flipInAnimatorSet.doOnEnd {
                invisibleView.visibility = View.INVISIBLE
            }
        } catch (e: Exception) {
            Log.i("Flashcard Screen", e.toString())
        }
    }
}