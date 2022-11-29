package com.example.orglistapp.ui.org_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.example.orglistapp.databinding.FragmentOrgDetailsBinding
import com.example.orglistapp.model.domain.AppState
import com.example.orglistapp.model.entities.OrganizationInfo
import com.example.orglistapp.utils.BASE_URL
import com.example.orglistapp.utils.ORGANIZATION_ID
import com.example.orglistapp.utils.snackBar
import com.example.orglistapp.viewmodel.OrgInfoViewModel

class OrgDetailsFragment : Fragment() {
    private var _binding: FragmentOrgDetailsBinding? = null
    private val binding get() = _binding!!
    private var orgId = ""


    lateinit var viewModel: OrgInfoViewModel

    companion object {
        fun newInstance(org_id: String) = OrgDetailsFragment().apply {
            arguments = Bundle().apply { putString(ORGANIZATION_ID, org_id) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrgDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orgId = arguments?.getString(ORGANIZATION_ID).toString()

        viewModel = OrgInfoViewModel(orgId)
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }

        binding.btnClose.setOnClickListener { requireActivity().supportFragmentManager.popBackStack() }
    }


    private fun renderData(appState: AppState<OrganizationInfo>) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingFrame.visibility = View.GONE
                appState.data?.let {
                    binding.tvName.text = it.name
                    binding.tvLat.text = it.lat.toString()
                    binding.tvLon.text = it.lon.toString()
                    binding.tvWww.text = it.www
                    binding.tvPhone.text = it.phone
                    binding.tvDescription.text = it.description
                    binding.ivLogo.load(BASE_URL.plus(it.img))
                }
            }
            is AppState.Loading -> {
                binding.loadingFrame.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                binding.loadingFrame.visibility = View.GONE
                snackBar(appState.error.message ?: "")
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}