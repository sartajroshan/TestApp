package me.sartajroshan.myapp.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import coil.api.load
import dagger.hilt.android.AndroidEntryPoint
import me.sartajroshan.myapp.R
import me.sartajroshan.myapp.data.remote.responses.ResponseData
import me.sartajroshan.myapp.databinding.FragmentNotesBinding
import me.sartajroshan.myapp.util.ItemOffsetDecoration


@AndroidEntryPoint
class NotesFragment : Fragment() {

    private lateinit var binding: FragmentNotesBinding
    private val noteViewModel: NotesViewModel by viewModels()
    private val likesAdapter by lazy {
        LikesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewLikes.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = likesAdapter
            val itemDecoration = ItemOffsetDecoration(context, me.sartajroshan.myapp.R.dimen.corner_8dp)
            addItemDecoration(itemDecoration)
        }

        noteViewModel.data.observe(viewLifecycleOwner) {
            likesAdapter.setData(it.likes.profiles)
            invitesView(it.invites)
        }
    }

    private fun invitesView(invites: ResponseData.Invites) {
        invites.profiles.singleOrNull()?.let {
            binding.txtInvitorName.text =
                it.generalInformation.firstName + ", ${it.generalInformation.age}"
            binding.txtInvitorDesc.text =
                String.format(getString(R.string.tap_to_view), invites.pendingInvitationsCount)
            binding.imgInvite.load(it.photos.first().photo)
            /*{
                transformations(RoundedCornersTransformation(20.0F),
                    )
            }*/
        }
    }
}