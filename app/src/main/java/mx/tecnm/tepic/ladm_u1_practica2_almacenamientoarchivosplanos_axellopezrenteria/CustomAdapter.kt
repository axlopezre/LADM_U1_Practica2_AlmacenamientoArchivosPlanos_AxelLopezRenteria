package mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria

import android.os.Bundle
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.Array.informacion1
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.Array.informacion2
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.Array.informacion3
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.Array.informacion4

class CustomAdapter(): RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    val titles = arrayOf("AXEL",
    "GOD",
    "YEAH",
     "KE")

    val details = arrayOf("Muchos videos",
        "kotlin",
        "gran cantidad",
        "Siiiiiiiii")

    val details2 = arrayOf("Muchos videos",
        "kotlin",
        "gran cantidad",
        "Siiiiiiiii")

    val images = intArrayOf(R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_foreground)


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        var v = LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = informacion1[i]
        viewHolder.itemDetail.text = informacion2[i]
        viewHolder.itemID.text = informacion3[i]
        viewHolder.itemMesa.text = informacion4[i]

    }

    override fun getItemCount(): Int {
        return informacion1.size
    }
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemID: TextView
        var itemTitle: TextView
        var itemDetail: TextView
        var itemMesa: TextView
        init {
            itemID = itemView.findViewById(R.id.idpedidos)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detal)
            itemMesa = itemView.findViewById(R.id.mesa)
        }
    }
}
