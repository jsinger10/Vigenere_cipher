run: Vigener_cipher.class
	java Vigener_cipher $(ARGS)
Vigener_cipher.class: Vigener_cipher.java
	javac Vigener_cipher.java

help:
	$(info 1 Instructions on How to Run and Comments)
	$(info 2 I created a visual tool to help with the encoding, you will be prompted as to if you want to see it when you run the program)
	$(info 3 make -run ARGS="encode message key" to encode the message using the key)
	$(info 4 make -run ARGS="decode message key" to decode the message using the key)
	$(info 5 if you would like to enable the visual, please put "y" as the fourth arguement in ARGS)
	$(info 6 If make -run is done with no ARGS, my personal two test cases will run)
	$(info)
	$(info Made by Julian Singer)

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

