STORYBOOK_DOCKERFILE	:= infra/docker/storybook/Dockerfile
STORYBOOK_NAME	   	 	:= smartbcity/f2-storybook
STORYBOOK_IMG	    	:= ${STORYBOOK_NAME}:${VERSION}
STORYBOOK_LATEST		:= ${STORYBOOK_NAME}:latest

libs: package-kotlin
docs: package-storybook

package-kotlin:
	@gradle clean build publishToMavenLocal publish

package-storybook:
	@echo "//${CI_SERVER_HOST}/api/v4/projects/127/packages/npm/:_authToken=${CI_JOB_TOKEN}">.npmrc
	@cat .npmrc
	@docker build -f ${STORYBOOK_DOCKERFILE} -t ${STORYBOOK_IMG} .
	@docker push ${STORYBOOK_IMG}
