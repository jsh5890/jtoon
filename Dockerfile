FROM node:16.17.0
#ENV NODE_OPTIONS --openssl-legacy-provider
RUN mkdir /jtoon
COPY . /jtoon
WORKDIR /jtoon/front
#RUN apt install -y yarn
#RUN yarn && yarn build --spa
RUN npm install && npm run build
RUN mkdir -p ../src/main/resources/static
RUN cp -R dist/* ../src/main/resources/static

FROM gradle:jdk11
RUN mkdir /jtoon
WORKDIR /jtoon
COPY --from=0 /jtoon .
RUN gradle bootjar


FROM openjdk:11-jre
COPY --from=1 /jtoon/build/libs/jtoon-*.jar jtoon.jar
VOLUME [ "/root/data" ]
EXPOSE 8080
#CMD [ "java", "-Xshareclasses", "-Xquickstart", "-jar", "jtoon.jar"]
CMD [ "java", "-jar", "jtoon.jar"]
