STORYBOOK_DOCKERFILE	:= infra/docker/storybook/Dockerfile
STORYBOOK_NAME	   	 	:= smartbcity/storybook-f2
STORYBOOK_IMG	    	:= ${STORYBOOK_NAME}:${VERSION}
STORYBOOK_LATEST		:= ${REST_MOBI_NAME}:latest


clean: clean-kotlin

test: test-kotlin

package: package-kotlin package-storybook

push: push-kotlin push-storybook

clean-kotlin:
	./gradlew clean

package-kotlin:
	./gradlew build

push-kotlin:
	./gradlew publish -P version=${VERSION} --info

package-storybook:
	@docker build -f ${STORYBOOK_DOCKERFILE} -t ${STORYBOOK_IMG} .

push-storybook:
	@docker push ${STORYBOOK_IMG}

push-latest-storybook:
	@docker tag ${STORYBOOK_IMG} ${STORYBOOK_LATEST}
	@docker push ${STORYBOOK_LATEST}