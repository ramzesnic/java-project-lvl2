clean:
	./gradlew clean

build: clean
	./gradlew build

install: clean
	./gradlew install

run-dist:
	./build/install/app/bin/app

check-updates:
	./gradlew dependencyUpdates

lint:
	./gradlew checkstyleMain
