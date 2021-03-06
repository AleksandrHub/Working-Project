package org.vernality.profitclub.view.organization.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_members.view.*
import kotlinx.android.synthetic.main.item_list_members_requests.view.*
import kotlinx.android.synthetic.main.item_list_members_requests.view.iv_membersImage
import kotlinx.android.synthetic.main.item_list_members_requests.view.tv_members_name
import org.vernality.profitclub.R
import org.vernality.profitclub.model.data.Member
import org.vernality.profitclub.utils.ui.setImageToImageView

public class MembersRequestListRVAdapter(
    private val itemListener: OnListItemClickListener,
    private val approveBtnListener: OnListItemClickListener,
    private val rejectBtnListener: OnListItemClickListener
) : RecyclerView.Adapter<MembersRequestListRVAdapter.RecyclerItemViewHolder>() {

    private var data: List<Member> = arrayListOf()

    fun setData(data: List<Member>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerItemViewHolder {
        return RecyclerItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_members_requests, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        fun bind(member: Member) {
            itemView.tv_members_name.setText(member.firstName + " " + member.lastName)
            itemView.setOnClickListener { openMember(member) }
            itemView.tv_approve.setOnClickListener { approveMember(member) }
            itemView.tv_reject.setOnClickListener { rejectMember(member) }

            val imageFile = member.imageFile
            if(imageFile != null){
                itemView.iv_membersImage.setImageToImageView(imageFile.data)
            }
        }

    }


    private fun openMember(member: Member) {
        itemListener.onItemClick(member)
    }

    private fun approveMember(member: Member){
        approveBtnListener.onItemClick(member)
    }

    private fun rejectMember(member: Member){
        rejectBtnListener.onItemClick(member)
    }

    interface OnListItemClickListener {
        fun onItemClick(member: Member)
    }

}
