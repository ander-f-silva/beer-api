[![Coverage Status](https://coveralls.io/repos/github/ander-f-silva/beer-api/badge.svg)](https://coveralls.io/github/ander-f-silva/beer-api)
[![Build Status](https://travis-ci.org/ander-f-silva/beer-api.svg?branch=master)](https://travis-ci.org/ander-f-silva/beer-api)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/0a12834e9b4c474fa6e5dffd92c0bb9d)](https://www.codacy.com/app/ander-f-silva/beer-api?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=ander-f-silva/beer-api&amp;utm_campaign=Badge_Grade)

# Projeto API Cervejaria

Construir um microserviço para realizar o crud do estilo de cerveja e um endpoint get no padrão restfull.

## Reflexão sobre o problema

Para elaborar um mvp (projeto inicial) pensei em dois frameworks.
Primeiro o Vert.x, pois este framework é gerar um container leve e trabalhar com modelo de programação reativa.
Segundo foi Spring que possui muitos projetos que são faceis de integrar com banco de dados NSQL e segue padrão de cofigurações muito fácil de usar .

Acabei adotando o Spring por que oferecia mais recursos para integrar de forma rápida e também possui um suite mais completar para lidar com Testes.

Para não haver depedência com infra local estou virtualizado o projeto com container docker.

Os banco de dados relacionais não são locais, estou utilizando hospedagem free do mlab.

Pensei em uma solução minimalista para ser rápido para desenvolver e por que tenho maior dominio. 

Outro motivações como uso do Banco pensei um modelo nsql, pois os dados não possuem relacionademento e podem ser guardados em um banco não relacional.

Com Spring Boot é possivel realizar um setup de projeto simples e facill para montar o ambiente.

## Tecnologias utilizadas

* Linguagem Java - Versão 1.8 (Oracle 1.8.0_121)

```
java version "1.8.0_121"
Java(TM) SE Runtime Environment (build 1.8.0_121-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.121-b13, mixed mode)
```

* Gradle 4 - Ferramenta de Build

```
------------------------------------------------------------
Gradle 4.9
------------------------------------------------------------

Build time:   2018-07-16 08:14:03 UTC
Revision:     efcf8c1cf533b03c70f394f270f46a174c738efc

Kotlin DSL:   0.18.4
Kotlin:       1.2.41
Groovy:       2.4.12
Ant:          Apache Ant(TM) version 1.9.11 compiled on March 23 2018
JVM:          9.0.4 (Oracle Corporation 9.0.4+11)
OS:           Linux 4.15.0-30-generic amd64
```

* Versionador - Git

* Banco de Dados - Mongo (emmbed para ambiente de teste e mLab para hospedagem em prod e local)

* Spring Web (MVC) - Framerwork Web para geração das API's (versão 1.15.15) com Tomcat 8

* Spring Boot - Setup de projeto

O repositório utilizado é o Github, onde utilizei o Git flow com a branch develop e master para gerenciar o fonte.

Para realizar o CI usei o Travis com a plataforma Pass Heroku para relizar o deploy da API.

Na pasta postman tem um projeto que poderá importar para testar  as apis na plataforma Heroku ou local.

Quando realizo um push o pull request, automanticamente o Travis já efetua o build e realizava o deploy no heroku.

Utilizei o framerk Junit (sufixo Test) para os teste unitários e teste integrado (sufixo IT).

# Documentação através do swagger

O projeto possui um documentação de API através do swagger.

Para acessar:

### Endpoints: beer-style-resource : Beer Style Resource

http://localhost:8080/swagger-ui.html#/ ou https://api-beer.herokuapp.com/swagger-ui.html#/beer45style45resource

## Para realizar o build, teste unitários e iniciar o software

Para executar o versão no ambiente local é necessario ter versão a partir do 8.

Não precisa instalar o gradlew, pois na raiz do projeto possui o gradle wrapper que pode ser usado no Windows o Linux.

Antes de realizar o deploy execute o comando abaixo para executar os testes:

```
./gradlew test
```

Para realizar build:

```
./gradlew build
```

Acesse a pasta build/libs e execute o comando:

```
java -jar beer-api-0.0.1.jar
```

Outra solução usando docker para não depender do SO da máquina

Fiz um script para execultar no Linux. Execute os comando abaixo:

```
./gradlew build
 script.sh
```
Para ambiente Windows. Execute os comando abaixo:

./gradlew build
docker rmi -f beer-api
docker rm  -f beer
docker build -t beer-api .
docker run --name beer -p 8080:8080 -t beer-api

## Gestão do Projeto e técnicas para construção da API

Não precisei usar Kaban paraa administrar as atividades, tendo conhecimento do problema.

Mas as etapas foram:

* Passo 0: Criação do projeto no https://start.spring.io/
* Passo 1: Contrução das classes de dominio;
* Passo 2: Construção dos testes unitários;
* Passo 3: Construção da API e mecanismo para armazenar os dados;
* Passo 4: Inclui os serviço na plataforma Heroku.

Como eu já tinha conhecimento do problema eu procurei primeiro fazer os modelos e depois realizar os testes.
Geralmente quando não tinha conhecimento sobre o problema tento fazer usando TDD.

Acesso link do trello https://trello.com/b/FBZLKQK0/ciclic-cervejaria (caso não visualize me informe para dar permissão por email)

Obs: Acesso os markdowns para olhar cobertura de teste, analise estatica do código e CI do projeto.
