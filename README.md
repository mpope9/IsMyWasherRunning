# IsMyWasherRunning

This is a project I started because I live in the basement of a three story house, and the washing machine is on the third story.  I was tired of walking up the stairs to check if the machine was done, so I decided to utilize my raspberry pi and android phone.

The raspberry pi has a vibration sensor attached to one of it's GPIO ports, and has an Apache server installed.  Everytime the sensor is triggered the apache server's HTML page is changed and the server is reloaded.  The Android app requests the HTML page from the raspberry pi over the local network.  

I also built an easter egg into the app.  If a long press is detected a sound bite of John Cena's intro music will play.

Included in this repo is the python file for the raspberry pi, the Android Studio project, and a copy of the main Android code.  I have the copy because you may not want to install Android Studio just to view how it works.  

Thanks for looking!
