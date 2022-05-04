package test.task.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import test.task.data.model.Post
import test.task.databinding.ItemPostBinding


class MainPostAdapter (val listener: ItemPostListener): ListAdapter<Post, MainPostAdapter.ViewHolderPost>(DIFF) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainPostAdapter.ViewHolderPost {
        return ViewHolderPost(
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener
        )

    }
    override fun onBindViewHolder(holder: ViewHolderPost, position: Int) {
        holder.bind(position)
    }


    inner class ViewHolderPost(private val binding: ItemPostBinding,listener: ItemPostListener) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            val currentPost = getItem(position)
            binding.textViewTitle.text = currentPost.title
            binding.textViewDescription.text = currentPost.body
            binding.clickView.setOnClickListener {
                listener.itemClick(getItem(position))
            }

        }
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(
                oldItem: Post,
                newItem: Post
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Post,
                newItem: Post
            ): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }


}
interface ItemPostListener{
    fun itemClick(post: Post)
}