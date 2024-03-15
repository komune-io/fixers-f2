#### Stage 1: Build the react application
FROM node:21.7-alpine3.19 as build

WORKDIR /app

COPY *.md ./
COPY storybook ./storybook

WORKDIR /app/storybook

ARG CI_NPM_AUTH_TOKEN
RUN echo -e "\
@komune-io:registry=https://npm.pkg.github.com \n\
//npm.pkg.github.com/:_authToken=${CI_NPM_AUTH_TOKEN} \n\
" > .npmrc

RUN yarn install --frozen-lockfile --ignore-scripts
RUN yarn build-storybook

FROM nginx:1.25.4-alpine

COPY --from=build /app/storybook/storybook-static /var/www
COPY infra/docker/storybook/nginx.conf /etc/nginx/nginx.conf

EXPOSE 80

ENTRYPOINT ["nginx","-g","daemon off;"]
