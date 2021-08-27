package com.example.codechallenge.ui.eventDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.codechallenge.CodeChallengeApplication
import com.example.codechallenge.databinding.EventDetailsFragmentBinding
import com.example.codechallenge.di.viewModel.ViewModelFactory
import com.example.codechallenge.model.DetailsModel
import javax.inject.Inject

private const val ARG_EVENT_DETAILS = "eventDetails"

class EventDetailsFragment : Fragment() {

    private var _binding: EventDetailsFragmentBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel by viewModels<EventDetailsViewModel> { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as CodeChallengeApplication).component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = EventDetailsFragmentBinding.inflate(inflater, container, false)

        val eventDetails =
            arguments?.getParcelable<DetailsModel>(ARG_EVENT_DETAILS)

        eventDetails?.let { viewModel.loadDetailsInfo(it) }
        /*
                  val recyclerAdapter = CompositeDelegateAdapter(
                PullRequestEventAdapter(this),
                PushEventsAdapter(this)
            )
            binding.eventsRecyclerViewAdapter.adapter = recyclerAdapter
         */
        val recyclerAdapter = EventDetailsAdapter()
        binding.eventDetailsRecyclerViewAdapter.adapter = recyclerAdapter

        viewModel.detailsData.observe(viewLifecycleOwner, {
            recyclerAdapter.setDetailsInfo(it)
        })

        return binding.root

    }

    companion object {
        fun newInstance(eventDetails: DetailsModel) = EventDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ARG_EVENT_DETAILS, eventDetails)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}