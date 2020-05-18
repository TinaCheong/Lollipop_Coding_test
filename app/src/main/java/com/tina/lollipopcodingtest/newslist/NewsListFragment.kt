package com.tina.lollipopcodingtest.newslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.tina.lollipopcodingtest.LollipopApplication
import com.tina.lollipopcodingtest.databinding.FragmentListBinding

class NewsListFragment : Fragment() {

    private val viewModel by viewModels<NewsListViewModel> {
        ViewModelFactory(LollipopApplication.instance.lollipopRepository)
    }

    lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentListBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.recyclerViewList.adapter = NewsListAdapter(viewModel)

        viewModel.localData.observe(viewLifecycleOwner, Observer {
            (binding.recyclerViewList.adapter as NewsListAdapter).submitList(it)
        })

        return binding.root
    }
}