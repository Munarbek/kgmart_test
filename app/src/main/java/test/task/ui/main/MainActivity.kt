package test.task.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import test.task.data.model.Post
import test.task.data.repository.Repository
import test.task.databinding.ActivityMainBinding
import test.task.ui.create.CreatePostActivity
import test.task.ui.detail.DetailPostActivity

class MainActivity : AppCompatActivity(),ItemPostListener {
    private val viewModel by viewModel<MainViewModel>()
    lateinit var viewBinding: ActivityMainBinding
    private val postAdapter = MainPostAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setupListener()

        supportActionBar?.title = "Posts"
        setupRecyclerView()
        initObserves()
        getData()

    }

    private fun getData() {
        viewModel.getPosts()
        viewBinding.recyclerView.visibility = View.GONE
        viewBinding.progressCircular.visibility = View.VISIBLE

    }

    private fun initObserves() {
        viewModel.post.observe(this) {
            viewBinding.progressCircular.visibility = View.GONE
            viewBinding.recyclerView.visibility = View.VISIBLE

            postAdapter.submitList(it)
        }
        viewModel.errorPost.observe(this) {
            viewBinding.progressCircular.visibility = View.GONE
            viewBinding.recyclerView.visibility = View.VISIBLE
            Toast.makeText(this, "Что-то пошло не так,попробуйте еще раз", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun setupListener() {
        viewBinding.fab.setOnClickListener {
            startActivity(Intent(this, CreatePostActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        viewBinding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = postAdapter
        }
    }

    override fun itemClick(post: Post) {
        val intent =Intent(this, DetailPostActivity::class.java)
        intent.putExtra("key",post)
        startActivity(intent)
    }
}