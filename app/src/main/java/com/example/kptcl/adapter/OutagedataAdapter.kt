package com.example.kptcl.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kptcl.R
import com.example.kptcl.model.Constant
import com.example.kptcl.model.Outagemodel


class OutagedataAdapter(arrayList: List<Outagemodel>, context: Context) :
    RecyclerView.Adapter<OutagedataAdapter.outageViewholder>() {
    private val arrayList: List<Outagemodel>
    var context: Context
    private val lastPosition = -1
    var animation: Animation? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): outageViewholder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.outagedataadapter, parent, false)
        view.layoutParams =
            RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )
        animation = AnimationUtils.loadAnimation(context, R.anim.blink) as Animation
        return outageViewholder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: outageViewholder, position: Int) {
        val getSetValues: Outagemodel = arrayList[position]
        holder.tv_OutageFeeder.text = getSetValues.nameofFeeder
        holder.tv_powerhouse.text = getSetValues.nameofPowerHourse
        holder.tv_OutageFrom.text = getSetValues.tentativeDateofOutage.toString() + " " + Constant.getHours(
            getSetValues.tentativeFrom
        )
        holder.tv_OutageTo.text = getSetValues.tentativeDateofOutage.toString() + " " + Constant.getHours(
            getSetValues.tentativeTo
        )
        holder.tv_OutageArea.text = getSetValues.areaAffected
        //  holder.tv_reason.startAnimation(animation);
    }

    private fun setFadeAnimation(view: View, position: Int) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 3000
        anim.repeatMode = Animation.RESTART
        anim.repeatCount = Animation.INFINITE
        view.startAnimation(anim)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class outageViewholder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var tv_OutageFeeder: TextView
        var tv_reason: TextView
        var tv_powerhouse: TextView
        var tv_OutageFrom: TextView
        var tv_OutageTo: TextView
        var tv_OutageArea: TextView
        var tv_town: TextView
        override fun onClick(v: View) {}

        init {
            // itemView.setOnClickListener(this);
            tv_OutageFeeder = itemView.findViewById(R.id.tv_OutageFeeder)
            tv_powerhouse = itemView.findViewById(R.id.tv_powerhouse)
            tv_OutageFrom = itemView.findViewById(R.id.tv_OutageFrom)
            tv_OutageTo = itemView.findViewById(R.id.tv_OutageTo)
            tv_OutageArea = itemView.findViewById(R.id.tv_OutageArea)
            tv_town = itemView.findViewById(R.id.tv_town)
            tv_reason = itemView.findViewById(R.id.tv_reason)
        }
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun onViewAttachedToWindow(holder: outageViewholder) {
        super.onViewAttachedToWindow(holder)
        holder.tv_reason.animation = animation
    }

    override fun onViewDetachedFromWindow(holder: outageViewholder) {
        super.onViewDetachedFromWindow(holder)
        holder.tv_reason.clearAnimation()
    }

    init {
        this.arrayList = arrayList
        this.context = context
    }
}