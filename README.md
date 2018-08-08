[![Coverage Status](https://coveralls.io/repos/github/ander-f-silva/beer-api/badge.svg)](https://coveralls.io/github/ander-f-silva/beer-api)
[![Build Status](https://travis-ci.org/ander-f-silva/beer-api.svg?branch=master)](https://travis-ci.org/ander-f-silva/beer-api)
- Solicitação

Construir um microserviço para realizar o crud do estilo de cerveja e um endpoint get no padrão restfull

- Reflexão sobre o problema

Para elaborar um mvp (projeto inicial) pensei em dois frameworks.
Primeiro o Vert.x, pois este framework é gerar um container leve e trabalhar com modelo de programação reativa.
Segundo foi Spring 5 que também traz o modelo reativo e possui muitos projetos que são faceis de integrar com banco de dados NSQL.

Acabei adotando o Spring por que oferecia mais recursos para integrar de forma rápida e também possui um suite mais completar para lidar com Testes.

Para não haver depedência com infra local estou virtualizado o projeto com container docker.

Os banco de dados relacionais não são locais, estou utilizando hospedagem free do mlab e ***redislab?

Pensei em uma solução minimalista para ser rápido para desenvolver e por que tenho maior dominio. 

Outro motivações como uso do Banco pensei um modelo nsql, pois os dados não possuem relacionademento e podem ser guardados em um banco não relacional.

Com Spring Boot é possivel realizar um setup de projeto simples e facill para montar o ambiente.

- Tecnologias utilizadas

Linguagem - Java version  8  

Gerenciador de dependências - Gradle (No projeto pode usar o wrapper para não precisar instalar na máquina) 

Versionador - Git

Repositorio - Gitlab

Banco de Dados - Mongo

Camada de Cache - Redis

Framework Web - Spring Boot (setup), Spring Web Flux (web usando modelo reativo),

- Script de execução local e execução do deploy

Para executar o versão no ambiente local é necessario ter versão a partir do 8.

Antes de realizar o deploy execute o comando 

./gradlew test

-- Ambiente linux 
    --- Execute o arquivo script.sh

-- Ambiente Windows 

Entrar na pastas do projeto e executar os comando abaixo

./gradlew build
docker rmi -f beer-api
docker rm  -f beer
docker build -t beer-api .
docker run --name beer -p 8080:8080 -t beer-api