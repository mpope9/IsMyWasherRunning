# author: <Matthew Pope>
# email:  <mpope9@mail.csuchico.edu>
# date:   <October 15, 2015>
#
# This is a python script to read GPIO input from a raspberry pi,
# then refresh an Apache HTML server that is running from startup.
# The android app will request the page from the apache server.
# This version only works while on the local network.


import RPi.GPIO as GPIO # To access the GPIO ports on the pi.
import time             # For waiting to refresh the Apache Server.
import subprocess       # For running the bash command to referesh 
						# the Apache Server.

# BCM mode, as opposed to board mode
GPIO.setmode(GPIO.BCM)

# Set pins 23 to inputs, and always down
GPIO.setup(23, GPIO.IN, GPIO.PUD_DOWN) 

# Initializations
inputWasher  = GPIO.input(23)

straang       = "The washer is OFF"
headerStraang = """<span style="font-weight:bold">Swipe down to check the machine</span><br>"""

# Function to update and reload the server
def writeReload(straang):
	lines = [headerStraang, straang]
	with open('/var/www/index.html', 'w') as output:
		output.seek(0)
		output.writelines(lines)
	subprocess.call(["service", "apache2", "reload"])


# Initialize server
writeReload(straang)

# Waits for the sensor to be on/off, the update the server
while True:
	# Wait for the sensor to be activated
	GPIO.wait_for_edge(23, GPIO.BOTH)
	straang = "The washer is ON"
	writeReload(straang)

	# Deactivate every 2 minutes, then check again
	time.sleep(120)
	straang = "The washer is OFF"
	writeReload(straang)
