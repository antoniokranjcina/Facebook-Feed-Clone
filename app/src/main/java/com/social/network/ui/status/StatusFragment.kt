package com.social.network.ui.status

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import com.social.network.R
import com.social.network.databinding.FragmentStatusBinding

class StatusFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentStatusBinding? = null
    private val binding get() = _binding!!

    private val GALLERY_REQUEST_CODE = 123

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStatusBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        setOnClickListeners()

        binding.apply {
            profilePicture.load("https://previews.123rf.com/images/metelsky/metelsky1809/metelsky180900233/109815470-man-avatar-profile-male-face-icon-vector-illustration-.jpg") {
                placeholder(R.drawable.ic_account)
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            binding.imageViewStatusImage.setImageURI(data.data)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_status, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.post -> {
                requireActivity().onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when (v!!) {
            binding.imageViewOpenGallery -> {
                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(Intent.createChooser(intent, "Pick an image"), GALLERY_REQUEST_CODE)
            }
        }
    }

    private fun setOnClickListeners() {
        binding.imageViewOpenGallery.setOnClickListener(this)
    }
}