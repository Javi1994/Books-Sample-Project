package com.javi.testproject.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javi.testproject.data.remote.dto.BookDto
import com.javi.testproject.databinding.BookItemBinding

class FavouriteBooksAdapter(
    private var books: List<BookDto> = emptyList(),
    private var onClick: (BookDto) -> Unit
) : RecyclerView.Adapter<FavouriteBooksAdapter.BookViewHolder>() {

    private lateinit var binding: BookItemBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookViewHolder {
        binding = BookItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun setData(books: List<BookDto>) {
        this.books = books
        notifyDataSetChanged()
    }

    inner class BookViewHolder(
        private val binding: BookItemBinding,
        private val onClick: (BookDto) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: BookDto) {
            binding.bookTitle.text = book.title
            binding.bookAuthor.text = book.author

            binding.root.setOnClickListener {
                onClick(book)
            }
        }
    }
}