package com.example.kptcl

import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kptcl.adapter.OutagedataAdapter
import com.example.kptcl.adapter.PoweroutageDataAdapter
import com.example.kptcl.api.RegisterApi
import com.example.kptcl.api.RetroClient
import com.example.kptcl.databinding.FragmentFirstBinding
import com.example.kptcl.model.Constant
import com.example.kptcl.model.Constant.CUSTINFOSERVICE
import com.example.kptcl.model.Outagemodel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() , PoweroutageDataAdapter.DateInterface{

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var outagedataAdapter: OutagedataAdapter? = null
    var poweroutageDataAdapter: PoweroutageDataAdapter? = null
    var arrayList: ArrayList<Outagemodel>? = null
    private  var _hasLoadedOnce: Boolean = false
    var progressDialog: ProgressDialog? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrayList = ArrayList()
        progressDialog = ProgressDialog(activity)
        setRecyclerView1()
        setRecyclerView2("2022-11-13")
        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
    }

    fun setRecyclerView1() {
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recyDate.layoutManager = linearLayoutManager
        binding.recyDate.setHasFixedSize(true)
        val c = Calendar.getInstance()
        val monthMaxDays = c.getActualMaximum(Calendar.DAY_OF_MONTH)
        val sdf = SimpleDateFormat("dd")
        val d = Date()
        val dayOfTheWeek = sdf.format(d)
        val outagemodelArrayList = ArrayList<Outagemodel>()
       // val currentDate = dayOfTheWeek.toInt()
        val currentDate = 13
        //  numberDays = new int[monthMaxDays - currentDate];
        for (i in currentDate..monthMaxDays) {
            val outagemodel = Outagemodel()
            outagemodel.date = (i.toString())
            outagemodel.day = (Constant.getDays(i))
            outagemodelArrayList.add(outagemodel)
        }
        poweroutageDataAdapter = PoweroutageDataAdapter(
            outagemodelArrayList, dayOfTheWeek.toInt(),
            requireContext()
        )
        poweroutageDataAdapter!!.setHasStableIds(true)
        binding.recyDate.adapter = poweroutageDataAdapter
        poweroutageDataAdapter!!.setOnClick(this)
    }

    fun setRecyclerView2(date: String?) {
        if (date != null) {
            Log.d("sudh","Date : "+date)
        }
        progressDialog?.let { Constant.showprogressdialog("Loading Data", it, "Wait") }
        val retroClient1 = RetroClient(CUSTINFOSERVICE)
        val api: RegisterApi = retroClient1.apiService

        api.getOutInfo(date)?.enqueue(object : Callback<List<Outagemodel?>?> {
            override fun onResponse(
                call: Call<List<Outagemodel?>?>,
                response: Response<List<Outagemodel?>?>
            ) {
                if (response.isSuccessful) {
                    if (TextUtils.isEmpty(response.body()!![0]?.message)) {
                        Constant.showtoast(context, "Data Found")
                        val linearLayoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        binding.recyScheduledata.layoutManager = linearLayoutManager
                        binding.recyScheduledata.setHasFixedSize(true)
                        outagedataAdapter = OutagedataAdapter(response.body()!! as List<Outagemodel>, activity!!)
                        outagedataAdapter!!.setHasStableIds(true)
                        binding.recyScheduledata.adapter = outagedataAdapter
                        binding.recyScheduledata.visibility = View.VISIBLE
                        binding.animationView.visibility = View.GONE
                    } else {
                        binding.recyScheduledata.visibility = View.GONE
                        binding.animationView.visibility = View.VISIBLE
                    }
                }
                progressDialog!!.dismiss()
            }

            override fun onFailure(call: Call<List<Outagemodel?>?>, t: Throwable) {
                binding.recyScheduledata.visibility = View.GONE
                binding.animationView.visibility = View.VISIBLE
                progressDialog!!.dismiss()
            }
        })
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (this.isVisible) {
            // we check that the fragment is becoming visible
            if (menuVisible && !_hasLoadedOnce) {
                setRecyclerView1()
                setRecyclerView2(Constant.getCurrentDate())
                _hasLoadedOnce = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getDay(Day: Int) {
        val c = Calendar.getInstance()
        val year = c[Calendar.YEAR]
        val month = c[Calendar.MONTH] + 1
        val monthStr = if (month < 10) "0$month" else month.toString()
        Log.d("debug", "$year-$month-$Day")
        setRecyclerView2("$year-$monthStr-$Day")
    }
}