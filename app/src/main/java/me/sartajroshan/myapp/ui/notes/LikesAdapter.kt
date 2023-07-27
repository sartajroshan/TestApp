package me.sartajroshan.myapp.ui.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.BlurTransformation
import coil.transform.RoundedCornersTransformation
import me.sartajroshan.myapp.data.remote.responses.ResponseData
import me.sartajroshan.myapp.databinding.RecyclerviewItemLikeBinding

class LikesAdapter : RecyclerView.Adapter<LikesAdapter.ProfileViewHolder>() {
    private val profiles: ArrayList<ResponseData.Likes.ProfileLikes> = arrayListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
       val binding = RecyclerviewItemLikeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun getItemCount() = profiles.size

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val profile = profiles[position]
       holder.binding.apply {
           imgAvatar.load(profile.avatar) {
               transformations(RoundedCornersTransformation(15.0F),
                   BlurTransformation(imgAvatar.context, radius = 20.0f, sampling = 2f)
               )

           }
           txtName.text = profile.firstName
       }
    }

    fun setData(profiles:List<ResponseData.Likes.ProfileLikes>) {
        this.profiles.clear()
        this.profiles.addAll(profiles)
        notifyDataSetChanged()
    }

    class ProfileViewHolder(val binding: RecyclerviewItemLikeBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}