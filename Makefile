clean:
	./gradlew clean

build: clean
	./gradlew build

install: clean
	./gradlew install

run-dist:
	./build/install/app/bin/app

start:
	./gradlew run

check-updates:
	./gradlew dependencyUpdates

lint:
	./gradlew checkstyleMain
