FROM java:8
ADD target/settings-service.jar .
EXPOSE 8006
CMD java -jar -Xmx512M settings-service.jar