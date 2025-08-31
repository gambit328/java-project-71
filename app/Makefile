.PHONY: build

run-dist:
	@./build/install/app/bin/app

run:
	@./gradlew run

build:
	@./gradlew clean build

build-run: build.run

lint:
	@./gradlew checkstyleMain

test:
	@./gradlew test

report:
	@./gradlew jacocoTestReport

update-deps:
	@./gradlew refreshVersions

install:
	@./gradlew installDist
