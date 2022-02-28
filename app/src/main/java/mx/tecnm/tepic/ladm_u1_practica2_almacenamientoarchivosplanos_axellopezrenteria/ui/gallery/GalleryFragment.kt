package mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.ui.gallery

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_gallery.*
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.Array.i
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.Array.informacion1
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.Array.informacion2
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.Array.informacion3
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.Array.informacion4
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.Array.y
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.Array.z
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.CustomAdapter
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.R
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.databinding.FragmentGalleryBinding
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.ui.home.HomeFragment
import kotlin.system.exitProcess

class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.insertar.setOnClickListener {
            try {
                y=0
                val espacio = informacion1.size-1
                (0..espacio).forEach {
                    if (idpedidos.text.toString().equals(informacion3[it])) {
                        AlertDialog.Builder(requireContext())
                            .setTitle("EL ID BUSCADO YA EXISTE")
                            .setPositiveButton("ACEPTAR", { d, i -> d.dismiss() })
                            .setNegativeButton("SALIR", { d, i -> d.cancel() })
                            .show()
                        y = 1
                    }
                }
                (0..espacio).forEach {
                    if (informacion3[it].isEmpty() && y == 0) {
                        informacion1[it] = binding.alimento.text.toString()
                        informacion2[it] = binding.unidad.text.toString()
                        informacion3[it] = binding.idpedidos.text.toString()
                        informacion4[it] = binding.mesa.text.toString()
                        y=1
                    }
                }

            }catch (e:Exception){
                AlertDialog.Builder(requireContext())
                    .setMessage(e.message)
                    .show()
            }
            binding.alimento.setText("")
            binding.unidad.setText("")
            binding.mesa.setText("")
            binding.idpedidos.setText("")
            Toast.makeText(requireContext(), "SE INSERTO CORRECTAMENTE", Toast.LENGTH_LONG).show()
        }
        binding.mostrar.setOnClickListener {
            try{
                y=0
                val espacio =informacion1.size-1
                (0..espacio).forEach {
                    if(idpedidos.text.toString().equals(informacion3[it])){
                        binding.alimento.setText(informacion1[it])
                        binding.unidad.setText(informacion2[it])
                        binding.idpedidos.setText(informacion3[it])
                        binding.mesa.setText(informacion4[it])
                        y=1
                    }
                }
                if(y==0){
                    AlertDialog.Builder(requireContext())
                        .setTitle("EL ID BUSCADO NO EXISTE")
                        .setPositiveButton("ACEPTAR",{d, i->d.dismiss()})
                        .setNegativeButton("SALIR", {d, i->d.cancel()})
                        .show()
                }
            }catch (e:Exception){
                AlertDialog.Builder(requireContext())
                    .setMessage(e.message)
                    .show()
            }
        }
        binding.actualizar.setOnClickListener {
            try {
                y=0
                val espacio = informacion1.size-1
                (0..espacio).forEach {
                    if(idpedidos.text.toString().equals(informacion3[it])) {
                        informacion1[it] = binding.alimento.text.toString()
                        informacion2[it] = binding.unidad.text.toString()
                        informacion3[it] = binding.idpedidos.text.toString()
                        informacion4[it] = binding.mesa.text.toString()
                        y=1
                    }
                }
                if(y==0){
                    AlertDialog.Builder(requireContext())
                        .setTitle("EL ID BUSCADO NO EXISTE")
                        .setPositiveButton("ACEPTAR",{d, i->d.dismiss()})
                        .setNegativeButton("SALIR", {d, i->d.cancel()})
                        .show()
                }
                binding.alimento.setText("")
                binding.unidad.setText("")
                binding.mesa.setText("")
                binding.idpedidos.setText("")
                Toast.makeText(requireContext(), "SE ACTUALIZO CORRECTAMENTE", Toast.LENGTH_LONG).show()
            }catch (e:Exception){
                AlertDialog.Builder(requireContext())
                    .setMessage(e.message)
                    .show()
            }
        }
        binding.eliminar.setOnClickListener {
            try{
                y=0
                z--
                val espacio =informacion1.size-1
                (0..espacio).forEach {
                    if(idpedidos.text.toString().equals(informacion3[it])){
                        //  z--
                        i=it
                        informacion1[it]=""
                        informacion2[it]=""
                        informacion3[it]=""
                        informacion4[it]=""
                        y=1
                    }
                }
                if(y==0){
                    AlertDialog.Builder(requireContext())
                        .setTitle("EL ID BUSCADO NO EXISTE")
                        .setPositiveButton("ACEPTAR",{d, i->d.dismiss()})
                        .setNegativeButton("SALIR", {d, i->d.cancel()})
                        .show()
                }
                binding.alimento.setText("")
                binding.unidad.setText("")
                binding.mesa.setText("")
                binding.idpedidos.setText("")
                Toast.makeText(requireContext(), "SE ELIMINO CORRECTAMENTE", Toast.LENGTH_LONG).show()
            }catch (e:Exception){
                AlertDialog.Builder(requireContext())
                    .setMessage(e.message)
                    .show()
            }

        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}