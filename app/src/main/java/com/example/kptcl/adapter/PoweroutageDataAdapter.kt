package com.example.kptcl.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kptcl.R
import com.example.kptcl.model.Outagemodel
import java.lang.String
import kotlin.Int
import kotlin.IntArray
import kotlin.Long


class PoweroutageDataAdapter(
    outagemodelList: List<Outagemodel>,
    currentDate: Int,
    context: Context
) :
    RecyclerView.Adapter<PoweroutageDataAdapter.PoweroutageViewholder>() {
    lateinit var size1: IntArray
    var outagemodelList: List<Outagemodel>
    private val currentDate: Int
    private var index = 0
    var context: Context
    var dateInterface: DateInterface? = null
    fun setOnClick(dateInterface: DateInterface?) {
        this.dateInterface = dateInterface
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoweroutageViewholder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.poweroutagedateitems, parent, false)
        return PoweroutageViewholder(view)
    }

    override fun onBindViewHolder(holder: PoweroutageViewholder, @SuppressLint("RecyclerView") position: Int) {
        if (currentDate == (outagemodelList[position].date?.toInt() ?: 0)) {
            holder.linearLayout.background =
                context.resources.getDrawable(R.drawable.powerroundedselected)
        } else holder.linearLayout.background =
            context.resources.getDrawable(R.drawable.powerrounedunselected)
        holder.tv_outagedata.setText(String.valueOf(outagemodelList[position].date))
        holder.tv_day.setText(outagemodelList[position].day?.uppercase() ?: "0")
        holder.linearLayout.setOnClickListener {
            index = position
            dateInterface!!.getDay(outagemodelList[holder.adapterPosition].date?.toInt()?: 0)
            holder.linearLayout.background =
                context.resources.getDrawable(R.drawable.powerroundedselected)
            notifyDataSetChanged()
        }
        if (index == position) {
            holder.linearLayout.background =
                context.resources.getDrawable(R.drawable.powerroundedselected)
        } else if (currentDate == outagemodelList[position].date?.toInt()) {
            holder.linearLayout.background =
                context.resources.getDrawable(R.drawable.powerrounedunselected)
        } else {
            holder.linearLayout.background =
                context.resources.getDrawable(R.drawable.powerrounedunselected)
        }
    }

    override fun getItemCount(): Int {
        return outagemodelList.size
    }

    inner class PoweroutageViewholder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        var tv_outagedata: TextView
        var tv_day: TextView
        var linearLayout: LinearLayout
        override fun onClick(v: View) {
            val position = adapterPosition
        }

        init {
            tv_outagedata = itemView.findViewById(R.id.tv_outagedata)
            tv_day = itemView.findViewById(R.id.tv_day)
            linearLayout = itemView.findViewById(R.id.linearLayout)
            //  tv_outagedata.setOnClickListener(this);
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    interface DateInterface {
        fun getDay(Day: Int)
    }

    /*    public PoweroutageDataAdapter(int[] size1, int currentDate,Context context) {
        this.size1 = size1;
        this.context =  context;
        this.currentDate = currentDate;
    }*/
    init {
        this.outagemodelList = outagemodelList
        this.currentDate = currentDate
        this.context = context
    }
}