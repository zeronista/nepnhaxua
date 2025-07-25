#!/bin/bash

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${GREEN}=== DigitalOcean Server Setup for Thuc Duong Backend ===${NC}"

# Update system
echo -e "${YELLOW}Updating system packages...${NC}"
sudo apt update && sudo apt upgrade -y

# Install essential packages
echo -e "${YELLOW}Installing essential packages...${NC}"
sudo apt install -y curl wget git unzip software-properties-common apt-transport-https ca-certificates gnupg lsb-release

# Install Docker
echo -e "${YELLOW}Installing Docker...${NC}"
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt update
sudo apt install -y docker-ce docker-ce-cli containerd.io

# Install Docker Compose
echo -e "${YELLOW}Installing Docker Compose...${NC}"
sudo curl -L "https://github.com/docker/compose/releases/latest/download/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

# Create deploy user
echo -e "${YELLOW}Creating deploy user...${NC}"
sudo useradd -m -s /bin/bash deploy
sudo usermod -aG docker deploy
sudo usermod -aG sudo deploy

# Install Nginx
echo -e "${YELLOW}Installing Nginx...${NC}"
sudo apt install -y nginx

# Install Certbot for SSL
echo -e "${YELLOW}Installing Certbot for SSL certificates...${NC}"
sudo apt install -y certbot python3-certbot-nginx

# Configure firewall
echo -e "${YELLOW}Configuring UFW firewall...${NC}"
sudo ufw --force reset
sudo ufw default deny incoming
sudo ufw default allow outgoing
sudo ufw allow ssh
sudo ufw allow 'Nginx Full'
sudo ufw --force enable

# Create application directory
echo -e "${YELLOW}Creating application directory...${NC}"
sudo mkdir -p /home/deploy/thucduong
sudo chown deploy:deploy /home/deploy/thucduong

# Setup log rotation
echo -e "${YELLOW}Setting up log rotation...${NC}"
sudo tee /etc/logrotate.d/thucduong > /dev/null <<EOF
/var/log/nginx/thucduong.*.log {
    daily
    missingok
    rotate 14
    compress
    delaycompress
    notifempty
    create 644 www-data www-data
    postrotate
        if [ -f /var/run/nginx.pid ]; then
            kill -USR1 \$(cat /var/run/nginx.pid)
        fi
    endscript
}
EOF

# Enable services
echo -e "${YELLOW}Enabling services...${NC}"
sudo systemctl enable nginx
sudo systemctl enable docker

echo -e "${GREEN}=== Server setup completed! ===${NC}"
echo -e "${BLUE}Next steps:${NC}"
echo -e "1. Switch to deploy user: ${YELLOW}sudo su - deploy${NC}"
echo -e "2. Clone your repository: ${YELLOW}git clone <your-repo-url> /home/deploy/thucduong${NC}"
echo -e "3. Configure your domain in nginx.conf (already set to nepnhaxua.io.vn)"
echo -e "4. Copy .env.template to .env and configure your environment variables"
echo -e "5. Run the deployment script: ${YELLOW}./deploy.sh${NC}"
echo -e "6. Setup SSL certificate: ${YELLOW}sudo certbot --nginx -d nepnhaxua.io.vn -d www.nepnhaxua.io.vn${NC}" 