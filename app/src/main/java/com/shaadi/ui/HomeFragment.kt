package com.shaadi.ui

import android.content.Context
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.shaadi.R
import com.shaadi.data.entity.UserEntity
import com.shaadi.data.util.Status
import com.shaadi.databinding.FragmentHomeBinding
import com.shaadi.util.showProgressBar
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.coroutines.launch
import okhttp3.internal.userAgent
import javax.inject.Inject

class HomeFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel

    private lateinit var newsAdapter: UserAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        setUpRecycleView()
        setObservers()
    }


    private fun setUpRecycleView() {
        newsAdapter = UserAdapter(object : UserAdapter.OnClickLisner {
            override fun onClickOnDecline(userEntity: UserEntity) {
                lifecycleScope.launch{
                    viewModel.updateData(1, userEntity.id)
                    setObservers()
                }
            }
            override fun onClickOnAccept(userEntity: UserEntity) {
                lifecycleScope.launch{
                    viewModel.updateData(2, userEntity.id)
                    setObservers()
                }
            }

        }, requireActivity())
        binding.recyclerViewNews.setHasFixedSize(true)
        //  layoutManager = LinearLayoutManager(activity)
        //  binding.recyclerViewNotification.layoutManager = layoutManager
        val decorator = DividerItemDecoration(
            ContextThemeWrapper(this.context, R.style.AppTheme),
            LinearLayoutManager.VERTICAL
        )
        binding.recyclerViewNews.addItemDecoration(decorator)
        binding.recyclerViewNews.adapter = newsAdapter
    }

    private fun setObservers() {
        viewModel.loadNews().observe(viewLifecycleOwner, Observer { value ->
            try {
                when {
                    value?.status == Status.LOADING -> binding.progressBar.showProgressBar(
                        true, activity?.window!!
                    )
                    value?.status == Status.ERROR -> {
                        binding.progressBar.showProgressBar(false, activity?.window!!)
                        newsAdapter.submitList(value.data)
                    }
                    value?.status == Status.SUCCESS -> {
                        binding.progressBar.showProgressBar(false, activity?.window!!)
                        newsAdapter.submitList(value.data)

                    }
                }
            } catch (e: Exception) {
                // binding.root.showSnack(getString(R.string.no_internet_message))
            }
        })


    }


}