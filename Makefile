all:
	../jdk1.8.0_45/bin/javac -cp "libs/all/*" src/*.java
	cd src/ && jar cvef Tweeter twit.jar *.class
	# rm twit.jar
	mv src/twit.jar build/twit.jar
	
