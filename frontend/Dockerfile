FROM node as build
WORKDIR /app
COPY package*.json /app/
RUN npm i -g @angular/cli
RUN npm install
COPY . /app

RUN ng build --base-href / --delete-output-path=false --outputHashing=all --aot=false --build-optimizer=false --output-path=./dist/out 

FROM nginx:latest

# Copy the build output to replace the default nginx contents.
COPY --from=build /app/dist/out /usr/share/nginx/html
COPY ./nginx.conf /etc/nginx/nginx.conf

EXPOSE 80