package vcmsa.ci.musiccompilerapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class MainActivity2 : AppCompatActivity() {


    private val ratings = mutableListOf<Int>()
    private val artistName = mutableListOf<String>()
    private val songTitle = mutableListOf<String>()
    private val comments = mutableListOf<String>()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val titleEditText = findViewById<EditText>(R.id.songTitleEditText)
        val artistEditText = findViewById<EditText>(R.id.artistNameEditText)
        val ratingEditText = findViewById<EditText>(R.id.ratingEditText)
        val commentEditText = findViewById<EditText>(R.id.commentsEditText)

        val addButton = findViewById<Button>(R.id.addButton)
        val playListButton = findViewById<Button>(R.id.play_list_button)
        val exitButton = findViewById<Button>(R.id.exitButton)

        addButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val artist = artistEditText.text.toString().trim()
            val ratingStr = ratingEditText.text.toString().trim()
            val comment = commentEditText.text.toString().trim()

            if (title.isEmpty() || artist.isEmpty() || ratingStr.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val rating = ratingStr.toIntOrNull()
            if (rating == null || rating !in 1..5) {
                Toast.makeText(this, "Rating must be between 1 and 5", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            songTitle.add(title)
            artistName.add(artist)
            ratings.add(rating)
            comments.add(comment)

            Toast.makeText(this, "Song added!", Toast.LENGTH_SHORT).show()

            titleEditText.text.clear()
            artistEditText.text.clear()
            ratingEditText.text.clear()
            commentEditText.text.clear()
        }

        playListButton.setOnClickListener {
            if (songTitle.isEmpty()) {
                Toast.makeText(this, "Playlist is empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val playlistText = buildString {
                for (i in songTitle.indices) {
                    append(" Song: ${songTitle[i]}\n")
                    append(" Artist: ${artistName[i]}\n")
                    append(" Rating: ${ratings[i]}/5\n")
                    append(" Comments: ${comments[i]}\n")
                    append("———\n")
                }
            }

            AlertDialog.Builder(this)
                .setTitle("Your Playlist")
                .setMessage(playlistText)
                .setPositiveButton("OK", null)
                .show()
        }

        exitButton.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }
}




