#!/bin/bash
echo "🚀 Starting Spring Boot..."
cd /home/ubuntu/app
nohup java -jar current.jar > app.log 2>&1 &