package com.example.orglistapp.ui.org_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.orglistapp.databinding.FragmentOrgListBinding
import com.example.orglistapp.model.domain.AppState
import com.example.orglistapp.model.entities.Organization
import com.example.orglistapp.ui.main.MainActivity
import com.example.orglistapp.utils.snackBar
import com.example.orglistapp.viewmodel.OrgListViewModel

class OrgListFragment : Fragment() {
    private var _binding: FragmentOrgListBinding? = null
    private val binding get() = _binding!!

    private val orgListAdapter = OrgListAdapter(object : OnItemClickListener<Organization> {
        override fun onClick(data: Organization) {
            (requireActivity() as MainActivity).showOrgDetailsFragment(data.id)
        }
    })

    private val viewModel: OrgListViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(OrgListViewModel::class.java)
    }

    companion object {
        fun newInstance() = OrgListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrgListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecView()
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
    }

    private fun initRecView() {
        binding.recViewOrg.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = orgListAdapter
        }
    }

    private fun renderData(appState: AppState<List<Organization>>) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingFrame.visibility = View.GONE
                appState.data?.let { orgListAdapter.setData(it) }
            }
            is AppState.Loading -> {
                binding.loadingFrame.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingFrame.visibility = View.GONE
                orgListAdapter.setData(listOf())
                snackBar(appState.error.message ?: "")
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        orgListAdapter.removeListener()
        super.onDestroy()
    }

}