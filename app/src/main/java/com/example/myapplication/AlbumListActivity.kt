package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.AlbumAdapter


class AlbumListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_list)

        recyclerView = findViewById(R.id.albumRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val albums = listOf(
            Album("Álbum 1", "Artista 1", R.drawable.bends),
            Album("Álbum 2", "Artista 2", R.drawable.pablohoney1),
            Album("Álbum 3", "Artista 3", R.drawable.kid),
            Album("Álbum 4", "Artista 4", R.drawable.amne),
            Album("Álbum 5", "Artista 5", R.drawable.hail),
            // Agrega más álbumes aquí según sea necesario
        )

        val adapter = AlbumAdapter(albums) { album ->
            // ... (código para manejar el clic del álbum)
        }

        recyclerView.adapter = adapter
    }
}