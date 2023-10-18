package com.javi.booksampleproject.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javi.booksampleproject.databinding.BookItemBinding
import com.javi.model.Book

class FavouriteBooksAdapter(
    private var books: List<Book> = emptyList(),
    private var onClick: (Book) -> Unit
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

    fun setData(books: List<Book>) {
        this.books = books
        notifyDataSetChanged()
    }

    inner class BookViewHolder(
        private val binding: BookItemBinding,
        private val onClick: (Book) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.bookTitle.text = book.title
            binding.bookAuthor.text = book.author

            binding.root.setOnClickListener {
                onClick(book)
            }
        }
    }
}