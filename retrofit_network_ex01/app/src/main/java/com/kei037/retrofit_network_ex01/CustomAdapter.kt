package com.kei037.retrofit_network_ex01

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kei037.retrofit_network_ex01.databinding.ItemRecyclerBinding

class CustomAdapter: RecyclerView.Adapter<Holder>() {
    var userList: Repository? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // ViewHolder 생성
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding) // Holder 객체에 binding 삽입
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = userList?.get((position))  // 현 위치(인덱스값) 기준으로 화면에 뿌려줌
        holder.setUser(user)
    }

    override fun getItemCount(): Int {
        // userList의 size가 null일 경우 0으로 대체
        return userList?.size?: 0
    }
}

class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
    fun setUser(user: RepositoryItem?) { // RepositoryItem가 리스트
        user?.let {
            binding.textName.text = user.name
            binding.textId.text = user.node_id
            Glide.with(binding.imageAvatar).load(user.owner.avatar_url).into(binding.imageAvatar)
        }
    }

}