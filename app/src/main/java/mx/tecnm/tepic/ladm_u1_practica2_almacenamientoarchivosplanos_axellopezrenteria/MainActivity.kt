package mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.Array.informacion1
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.Array.informacion2
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.Array.informacion3
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.Array.informacion4
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.databinding.ActivityMainBinding
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos_axellopezrenteria.ui.home.HomeFragment
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMain.toolbar)
        binding.appBarMain.fab.setOnClickListener { view ->
            guardarArchivo()
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        try {
            leerArchivo()
        }catch (e:Exception){
            androidx.appcompat.app.AlertDialog.Builder(this).setMessage(e.message).show()
        }

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    private fun guardarArchivo() {
        try {
            val archivo = OutputStreamWriter(openFileOutput("archivo.txt", MODE_PRIVATE))
            var espacio = informacion1.size-1
            var cadena1 = " "
            (0..espacio).forEach{
                if(it == espacio){
                    cadena1 += informacion1[it]+"||"+informacion2[it]+"||"+informacion3[it]+"||"+informacion4[it]
                }
                cadena1 += informacion1[it]+"||"+informacion2[it]+"||"+informacion3[it]+"||"+informacion4[it]+"&&"
                Toast.makeText(this, "${cadena1}", Toast.LENGTH_LONG).show()
            }
            archivo.write(cadena1)
            archivo.flush()
            archivo.close()
            androidx.appcompat.app.AlertDialog.Builder(this).setMessage("SE GUARDO").show()
        }catch (e:Exception){
            androidx.appcompat.app.AlertDialog.Builder(this).setMessage(e.message).show()
        }
    }

    private fun leerArchivo() {
        try {
            val archivo = BufferedReader(InputStreamReader(openFileInput("archivo.txt")))
            var listaContenido = archivo.readLine()//archivo de tipo list
            val dividir = listaContenido.split("&&")
            (0..dividir.size).forEach {
                var cadena2 = dividir[it].split("||")
                informacion1[it]=cadena2[0]
                informacion2[it]=cadena2[1]
                informacion3[it]=cadena2[2]
                informacion4[it]=cadena2[3]
            }

            AlertDialog.Builder(this)
                .setMessage(listaContenido.toString()).show()
        }catch (e: Exception){
            AlertDialog.Builder(this)
                .setMessage(e.message).show()
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}