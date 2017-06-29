@echo off
start "LAZY-LOADING-MS" java -jar %cd%/lazyloading-ms/target/deploy-swarm.jar --profile=default
start "LAZY-LOADING-WEB" java -jar %cd%/lazyloading-web/target/deploy-swarm.jar