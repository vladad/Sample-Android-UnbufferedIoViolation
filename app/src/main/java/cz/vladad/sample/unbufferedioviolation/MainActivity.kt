package cz.vladad.sample.unbufferedioviolation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val builder = StrictMode.ThreadPolicy.Builder()
        builder.permitAll()
        builder.detectUnbufferedIo()

        builder.penaltyDialog().penaltyLog()

        val policy = builder.build()
        StrictMode.setThreadPolicy(policy)

        setContentView(R.layout.activity_main)

        val file = File(filesDir, "test.bin")

        if (file.exists()) {
            file.delete()
        }

        val outputStream = BufferedOutputStream(FileOutputStream(file))

        outputStream.use {
            repeat(11) {
                outputStream.write(1);
                outputStream.write(2);
                outputStream.write(3);
                outputStream.write(4);
                outputStream.flush();
            }
        }
    }
}
