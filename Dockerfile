# 1. JDK 이미지 사용 (빌드 산출물 실행용)
FROM eclipse-temurin:17-jdk-alpine

# 2. 작업 디렉토리 설정
WORKDIR /app

# 3. Gradle 빌드 결과 JAR 복사
#    Jenkins 파이프라인에서 ./gradlew build 후 build/libs에 생성됨
COPY build/libs/*.jar app.jar

# 4. 컨테이너 실행 시 JAR 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
