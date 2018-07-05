FROM alpine:3.7
RUN apk --update add openjdk8-jre
ADD /build/libs/Parcelsize-Pipeline-all-0.1.jar Parcelsize-all-0.1.jar
CMD java -jar Parcelsize-all-0.1.jar