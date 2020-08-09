package com.example.materialcontainertransformissue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.Hold

class MainFragment : Fragment() {
    private var lastTransitionName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(view.findViewById<RecyclerView>(R.id.recycler_view)) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = TestAdapter()
        }
    }

    override fun onStart() {
        super.onStart()
        if (lastTransitionName != null) {
            postponeEnterTransition()
        }
    }

    private class TestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.row_text)
        val icon: View = view.findViewById<View>(R.id.row_icon)
    }

    private fun onClicked(view: View, row: Int) {
        exitTransition = Hold().apply {
            duration =
                ExampleGodClass.TRANSITION_TIME_MS
            addTarget(requireView())
        }

        val transitionName =
            ExampleGodClass.getTransitionName(
                row
            )
        view.transitionName = transitionName

        lastTransitionName = transitionName
        val extras = FragmentNavigatorExtras(view to transitionName)
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDetailFragment(
                row,
                transitionName
            ),
            extras
        )
    }

    private inner class TestAdapter : RecyclerView.Adapter<TestViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
            return TestViewHolder(
                view
            )
        }

        override fun getItemCount(): Int {
            return ExampleGodClass.rows.size
        }

        override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
            val transitionName =
                ExampleGodClass.getTransitionName(
                    position
                )
            holder.itemView.transitionName = transitionName
            holder.itemView.setOnClickListener {
                onClicked(holder.itemView, position)
            }
            if (transitionName == lastTransitionName) {
                startPostponedEnterTransition()
                lastTransitionName = null
            }

            holder.textView.text = ExampleGodClass.rows[position].name
            holder.icon.setBackgroundColor(ExampleGodClass.rows[position].color)
        }
    }
}