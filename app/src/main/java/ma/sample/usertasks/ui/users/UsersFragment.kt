package ma.sample.usertasks.ui.users

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.users_fragment.*
import ma.sample.usertasks.R
import ma.sample.usertasks.data.entity.User
import ma.sample.usertasks.ui.users.adapter.UsersAdapter
import ma.sample.usertasks.utils.Progress


@AndroidEntryPoint
class UsersFragment : Fragment(),UsersAdapter.Listener {


    private val viewModel:UsersViewModel by viewModels()
    private lateinit var mAdapter:UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.users_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
        progressView.setOnRefreshListener { setupObservers() }
    }


    private fun setupRecyclerView() {
        mAdapter = UsersAdapter(this)
        usersRecycler.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private fun setupObservers() {
        viewModel.users.observe(viewLifecycleOwner, Observer {
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

    override fun onItemClick(user: User) {
        findNavController().navigate(
            R.id.action_usersFragment_to_tasksFragment,
            bundleOf("id" to user.id)
        )
    }

}