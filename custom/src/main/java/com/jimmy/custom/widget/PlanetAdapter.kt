package com.jimmy.custom.widget


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.jimmy.custom.R
import com.jimmy.custom.bean.Planet


class PlanetAdapter(private val context: Context, private val planetList: MutableList<Planet>) :
    BaseAdapter(), AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    override fun getCount(): Int = planetList.size

    override fun getItem(position: Int): Any = planetList[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val holder: ViewHolder
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_list_view, null)
            holder = ViewHolder(view)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }
        val planet = planetList[position]
        holder.iv_icon.setImageResource(planet.image)
        holder.tv_name.text = planet.name
        holder.tv_desc.text = planet.desc
        return view
    }

    inner class ViewHolder(var view: View) {
        var iv_icon: ImageView = view.findViewById<ImageView>(R.id.iv_icon)
        var tv_name: TextView = view.findViewById<TextView>(R.id.tv_name)
        var tv_desc: TextView = view.findViewById<TextView>(R.id.tv_desc)
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val desc = "您点击了第${position + 1}个行星，它的名字是${planetList[position].name}"
        Toast.makeText(context, desc, Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(
        parent: AdapterView<*>,
        view: View,
        position: Int,
        id: Long
    ): Boolean {
        val desc = "您长按了第${position + 1}个行星，它的名字是${planetList[position].name}"
        Toast.makeText(context, desc, Toast.LENGTH_SHORT).show()
        return true
    }
}
