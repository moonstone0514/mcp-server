#!/bin/bash
echo "🔴 Stopping existing Spring Boot..."
pkill -f 'java -jar' || true