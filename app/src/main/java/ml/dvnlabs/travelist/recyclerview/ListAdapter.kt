package ml.dvnlabs.travelist.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ml.dvnlabs.travelist.model.TravelModelList

class ListAdapter(context:Context,data : ArrayList<TravelModelList>,rvLayoutRes : Int) : RecyclerView.Adapter<ListHolder>(){
    var context : Context? = context
    var datas: ArrayList<TravelModelList>? = data
    var itemRes : Int = rvLayoutRes

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val view : View = LayoutInflater.from(context).inflate(itemRes,parent,false)
        return ListHolder(context,view)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        var model : TravelModelList = datas!![holder.adapterPosition]
        holder.bindList(model)
    }

    override fun getItemCount(): Int {
        if (datas==null){
            return 0
        }else{
            return datas?.size!!
        }
    }
}