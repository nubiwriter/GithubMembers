package com.alif.githubmembers.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.alif.githubmembers.DetailUserActivity
import com.alif.githubmembers.R
import com.alif.githubmembers.data.GithubUser
import com.alif.githubmembers.databinding.TabFragmentBinding
import com.alif.githubmembers.ui.adapter.UserAdapter
import com.alif.githubmembers.ui.viewmodel.FollowersViewModel

class FollowersFragment : Fragment(R.layout.tab_fragment) {

    private var _binding: TabFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        username = arguments?.getString(DetailUserActivity.EXTRA_USERNAME).toString()
        _binding = TabFragmentBinding.bind(view)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[FollowersViewModel::class.java]

        viewModel.setListFollowers(username)
        showLoading(true)

        viewModel.getListFollowers().observe(viewLifecycleOwner) {
            if (it != null) {
                listDataFollowers(it)
                //adapter.setList(it)
                adapter.addData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun listDataFollowers(followers: ArrayList<GithubUser>?) {
        followers?.let{
            showLoading(false)
            adapter = UserAdapter()

            binding.apply {
                rvUser.layoutManager = LinearLayoutManager(activity)
                rvUser.setHasFixedSize(true)
                rvUser.adapter = adapter
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
