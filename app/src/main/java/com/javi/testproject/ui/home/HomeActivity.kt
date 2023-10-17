package com.javi.testproject.ui.home

import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.javi.testproject.R
import com.javi.testproject.common.Util
import com.javi.testproject.common.Util.startActivity
import com.javi.testproject.common.Util.startActivityWithDelay
import com.javi.testproject.data.remote.dto.BookDto
import com.javi.testproject.databinding.ActivityHomeBinding
import com.javi.testproject.databinding.FragmentLoginNewUserBinding
import com.javi.testproject.ui.book_detail.BookDetailActivity
import com.javi.testproject.ui.home.adapter.FavouriteBooksAdapter
import com.javi.testproject.ui.home.viewmodel.HomeViewModel
import com.javi.testproject.ui.login.viewmodel.LoginViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var bookAdapter: FavouriteBooksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeBooksList.apply {
            this.layoutManager = LinearLayoutManager(context)
            bookAdapter = FavouriteBooksAdapter { book ->
                this@HomeActivity.startActivity(BookDetailActivity::class.java)
            }
            this.adapter = bookAdapter
        }

        bookAdapter.setData(listOf(BookDto(), BookDto(), BookDto(), BookDto()))
    }
}