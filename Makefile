SOURCES=src/*

build: ${SOURCES}
	rm -rf bin
	mkdir bin
	javac -d bin/ -sourcepath src/ -classpath bin/ ${SOURCES}

