package com.example.materialcontainertransformissue

import android.os.Bundle
import androidx.transition.Transition
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialContainerTransform

class DetailFragment : Fragment(), Transition.TransitionListener {
    private lateinit var transitionName: String
    private var rowPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nullableArguments = requireArguments()
        val passedArgs = DetailFragmentArgs.fromBundle(nullableArguments)
        rowPos = passedArgs.pos
        transitionName = passedArgs.transitionName

        val transformation = MaterialContainerTransform().apply {
            fadeMode = MaterialContainerTransform.FADE_MODE_CROSS
            duration = ExampleGodClass.TRANSITION_TIME_MS
            addListener(this@DetailFragment) // comment this out, issue should disappear
        }
        sharedElementEnterTransition = transformation
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false).apply {
            transitionName = this@DetailFragment.transitionName
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.title).text = ExampleGodClass.rows[rowPos].name
        view.findViewById<View>(R.id.icon).setBackgroundColor(
            ExampleGodClass.rows[rowPos].color
        )
    }

    override fun onTransitionEnd(transition: Transition) {
    }

    override fun onTransitionResume(transition: Transition) {
    }

    override fun onTransitionPause(transition: Transition) {
    }

    override fun onTransitionCancel(transition: Transition) {
    }

    override fun onTransitionStart(transition: Transition) {
    }
}