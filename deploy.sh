#!/bin/bash

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

echo -e "${GREEN}Starting deployment of Thuc Duong Backend...${NC}"

# Check if .env file exists
if [ ! -f .env ]; then
    echo -e "${RED}Error: .env file not found!${NC}"
    echo -e "${YELLOW}Please copy .env.template to .env and fill in your values${NC}"
    exit 1
fi

# Stop existing containers
echo -e "${YELLOW}Stopping existing containers...${NC}"
docker-compose down

# Remove old images (optional, comment out if you want to keep them)
echo -e "${YELLOW}Removing old images...${NC}"
docker image prune -f

# Build and start new containers
echo -e "${YELLOW}Building and starting new containers...${NC}"
docker-compose up --build -d

# Wait for application to start
echo -e "${YELLOW}Waiting for application to start...${NC}"
sleep 30

# Check if application is running
if curl -f http://localhost:8080/actuator/health >/dev/null 2>&1; then
    echo -e "${GREEN}✅ Application is running successfully!${NC}"
    echo -e "${GREEN}Health check: http://localhost:8080/actuator/health${NC}"
else
    echo -e "${RED}❌ Application failed to start. Check logs:${NC}"
    docker-compose logs thucduong-app
fi

echo -e "${GREEN}Deployment completed!${NC}" 