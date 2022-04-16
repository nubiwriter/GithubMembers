package com.alif.githubmembers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.alif.githubmembers.databinding.ActivityDetailUserBinding
import com.alif.githubmembers.ui.adapter.SectionsPagerAdapter
import com.alif.githubmembers.ui.viewmodel.DetailUserViewModel
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    @StringRes
    private val TAB_TITLES = intArrayOf(
        R.string.tab_followers,
        R.string.tab_following
    )

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME).toString()
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)

        viewModel = ViewModelProvider(this)[DetailUserViewModel::class.java]
        viewModel.setUserDetail(username)
        showLoading(true)

        viewModel.getUserDetail().observe(this) {
            if (it != null) {
                binding.apply {
                    tvDetailRealname.text = it.name
                    tvDetailUserlogin.text = it.login
                    tvDetailCompany.text = it.company
                    tvDetailLocation.text = it.location
                    tvDetailFollowers.text =
                        getString(R.string.sum_followers).format("${it.followers}")
                    tvDetailRepo.text = getString(R.string.sum_repo).format("${it.publicRepos}")
                    tvDetailFollowing.text =
                        getString(R.string.sum_following).format("${it.following}")
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatarUrl)
                        .circleCrop()
                        .into(ivImgDetail)

                    showLoading(false)
                }
            }
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, bundle)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager2)
        val tabs: TabLayout = findViewById(R.id.tabs)

        binding.apply {
            viewPager.adapter = sectionsPagerAdapter
        }

        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_USERNAME = "extra_username"
    }
}