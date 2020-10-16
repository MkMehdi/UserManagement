package ma.sample.usertasks.ui.tasks

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.tasks_fragment.*
import kotlinx.android.synthetic.main.users_fragment.progressView
import ma.sample.usertasks.R
import ma.sample.usertasks.ui.tasks.adapter.TasksAdapter
import ma.sample.usertasks.utils.Progress


@AndroidEntryPoint
class TasksFragment : Fragment() {

    private val viewModel:TasksViewModel by viewModels()
    private lateinit var mAdapter:TasksAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tasks_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString("id")?.let { viewModel.start(it) }
        setupRecyclerView()
        setupObservers()
        progressView.setOnRefreshListener { setupObservers() }
    }


    private fun setupRecyclerView() {
        mAdapter = TasksAdapter()
        taskRecycler.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private fun setupObservers() {
        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Progress.Status.SUCCESS -> {
                    progressView.isRefreshing = false
                    if (!it.data.isNullOrEmpty()) mAdapter.setItems(ArrayList(it.data))
                }
                Progress.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Progress.Status.LOADING -> progressView.isRefreshing = true
            }
        })
    }


}