package com.almax.giphy_clean_architecture.presentation.gif

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.almax.giphy_clean_architecture.databinding.ActivityGifBinding
import com.almax.giphy_clean_architecture.presentation.base.UiState
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GifActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGifBinding
    private lateinit var viewModel: GifViewModel

    @Inject
    lateinit var adapter: GifAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGifBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialiseViewModel()
        setupUi()
    }

    private fun initialiseViewModel() {
        viewModel = ViewModelProvider(this)[GifViewModel::class]
    }

    private fun setupUi() {
        binding.apply {
            rvGifs.apply {
                layoutManager = LinearLayoutManager(this@GifActivity)
                setHasFixedSize(true)
                addItemDecoration(
                    DividerItemDecoration(
                        this@GifActivity,
                        (layoutManager as LinearLayoutManager).orientation
                    )
                )
                adapter = this@GifActivity.adapter
            }
        }
        observeDataAndUpdateUi()
    }

    private fun observeDataAndUpdateUi() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is UiState.Success -> {
                            adapter.setData(state.data)
                            updateProgressBarVisibility(false)
                        }

                        is UiState.Error -> {
                            Snackbar.make(
                                binding.root,
                                state.error,
                                Snackbar.LENGTH_SHORT
                            ).show()
                            updateProgressBarVisibility(false)
                        }

                        is UiState.Loading -> {
                            updateProgressBarVisibility(true)
                        }
                    }
                }
            }
        }
    }

    private fun updateProgressBarVisibility(isVisible: Boolean) {
        binding.pbGifs.visibility = if (isVisible) View.VISIBLE else View.GONE
    }
}