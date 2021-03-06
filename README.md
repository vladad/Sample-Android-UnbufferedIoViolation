[![Build Status](https://travis-ci.org/vladad/Sample-Android-UnbufferedIoViolation.svg?branch=master)](https://travis-ci.org/vladad/Sample-Android-UnbufferedIoViolation)

# Sample-Android-UnbufferedIoViolation

Sample application which demonstrates StrictMode warning UnbufferedIoViolation during flushing BufferedOutputStream.

Code:

        val builder = StrictMode.ThreadPolicy.Builder()
        builder.permitAll()
        builder.detectUnbufferedIo()
        builder.penaltyDialog().penaltyLog()

        val policy = builder.build()
        StrictMode.setThreadPolicy(policy)
  
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
