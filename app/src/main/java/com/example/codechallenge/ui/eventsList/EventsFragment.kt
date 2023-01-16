package com.example.codechallenge.ui.eventsList

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.codechallenge.CodeChallengeApplication
import com.example.codechallenge.R
import com.example.codechallenge.databinding.EventsFragmentBinding
import com.example.codechallenge.di.viewModel.ViewModelFactory
import com.example.codechallenge.model.BaseEventModel
import com.example.codechallenge.model.DetailsModel
import com.example.codechallenge.model.pullRequestEvent.PullRequestEventModel
import com.example.codechallenge.model.pushEvent.PushEventModel
import com.example.codechallenge.model.watchEvent.WatchEventModel
import com.example.codechallenge.ui.eventDetails.EventDetailsFragment
import com.example.codechallenge.ui.eventsList.adapters.*
import com.example.codechallenge.utils.LoadingState
import com.example.codechallenge.utils.Mapper
import com.example.codechallenge.utils.delegateAdapter.CompositeDelegateAdapter
import com.example.codechallenge.utils.delegateAdapter.OnItemClickListener
import javax.inject.Inject

class EventsFragment : Fragment(), OnItemClickListener {
    private var _binding: EventsFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel by viewModels<EventsViewModel> { factory }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as CodeChallengeApplication).component.inject(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.loadEvents()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EventsFragmentBinding.inflate(inflater, container, false)

        val recyclerAdapter = CompositeDelegateAdapter(
            PullRequestEventAdapter(this),
            PushEventsAdapter(this),
            CreateEventAdapter(this),
            IssueEventAdapter(this),
            WatchEventAdapter(this)
        )
        binding.eventsRecyclerViewAdapter.adapter = recyclerAdapter

        viewModel.loadingState.observe(viewLifecycleOwner, {
            when (it) {
                is LoadingState.Load -> showLoadBar()
                is LoadingState.Success -> hideLoadBar()
                is LoadingState.Error -> {
                    Toast.makeText(
                        activity,
                        resources.getString(R.string.error_msg),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        viewModel.eventResponse.observe(viewLifecycleOwner, { eventResponse ->
            recyclerAdapter.swapData(eventResponse.filterNotNull())
        })

        return binding.root
    }

    private fun showLoadBar() {
        binding.progressBar.visibility = View.VISIBLE
        binding.eventsRecyclerViewAdapter.visibility = View.GONE
    }

    private fun hideLoadBar() {
        binding.eventsRecyclerViewAdapter.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = EventsFragment()
    }

    private fun openDetailsFragment(detailsModel: DetailsModel) {
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container,
                EventDetailsFragment.newInstance(detailsModel)
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onItemClick(model: BaseEventModel) {
        Mapper.mapToUIModel(model)?.let { openDetailsFragment(it) }
    }
}