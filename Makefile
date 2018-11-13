SOURCES=src/*

build: ${SOURCES} bin/
	rm -rf bin
	mkdir bin
	javac -d bin/ -sourcepath src/ -classpath bin/ ${SOURCES}

