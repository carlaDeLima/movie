package com.example.carladelima.movie.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(var view: View): RecyclerView.ViewHolder(view), BindableView<T>{
    abstract override fun bind(item: T)
}