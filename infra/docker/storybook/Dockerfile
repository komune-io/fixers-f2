#### Stage 1: Build the react application
FROM node:12.4.0-alpine as build

WORKDIR /app

COPY package.json ./
COPY *.md ./
COPY docs ./docs

RUN ls -al

RUN ls -al docs
RUN yarn install
ENV PATH /app/node_modules/.bin:$PATH
RUN yarn build-storybook -s docs/public

FROM nginx:1.17.0-alpine

COPY --from=build /app/packages/storybook/storybook-static /var/www
COPY infra/docker/storybook/nginx.conf /etc/nginx/nginx.conf

EXPOSE 80

ENTRYPOINT ["nginx","-g","daemon off;"]
