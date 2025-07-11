# projetoBAN2_v2
projeto desenvolvido para a segunda fase do trabalho de banco de dados 2

Projeto Livraria com Spring Boot e MongoDB
Este projeto é uma aplicação web simples para gerenciar uma livraria, desenvolvida com Spring Boot para o backend e MongoDB como banco de dados, com um frontend básico em HTML, CSS (Tailwind CSS) e JavaScript puro.
Funcionalidades
CRUD Completo (Criar, Ler, Atualizar, Deletar):
Livros
Autores
Editoras
Associação de Entidades: Livros são associados a Autores e Editoras através de seus IDs.
Prevenção de Duplicatas: Garante que não haja autores com nomes duplicados e livros com títulos duplicados.
Consultas e Relatórios Personalizados:
Top 3 livros mais caros.
Livros publicados nos últimos 20 anos.
Número de livros por editora (apenas editoras com mais de 1 livro).
Preço médio dos livros por nacionalidade de autor.
Relatórios detalhados de livros com informações de autor e/ou editora.
Busca de livros por autor e editora específicos.
Frontend Simples: Interface web básica para interagir com a API.
Página principal (index.html) para adicionar novos cadastros e listar todos os itens.
Página de atualizações (updates.html) para buscar e editar itens existentes.
Página de consultas (consultas.html) para visualizar os resultados dos relatórios.
Tecnologias Utilizadas
Backend:
Java 17+
Spring Boot 3.x
Spring Data MongoDB
Maven (ou Gradle, dependendo da sua configuração)
Banco de Dados:
MongoDB
Frontend:
HTML5
CSS3 (Tailwind CSS via CDN)
JavaScript (ES6+)
Como Configurar e Rodar
Pré-requisitos
Java Development Kit (JDK) 17 ou superior.
MongoDB instalado e rodando (versão 5.0+ recomendada).
Maven ou Gradle (se você não usar o wrapper do Spring Boot).
Um editor de código (VS Code, IntelliJ IDEA, Eclipse).
1. Configuração do Backend (Spring Boot)
a. Clonar o Repositório (Conceitual)
Se este fosse um projeto Git, você o clonaria. Como estamos trabalhando com arquivos, certifique-se de ter todos os arquivos .java nas suas respectivas pastas.
b. Configurar o MongoDB
Inicie o servidor MongoDB. No terminal, você pode usar:
mongod


Verifique se o MongoDB está rodando na porta padrão (27017).
Configuração da Aplicação: Abra src/main/resources/application.properties (ou application.yml) e configure a conexão com o MongoDB:
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=livraria_db # Nome do seu banco de dados
server.port=8080 # Porta da sua aplicação Spring Boot


c. Configurar Dependências (pom.xml para Maven)
Abra seu arquivo pom.xml e certifique-se de que a dependência do Spring Data MongoDB está presente:
<dependencies>
    <!-- ... outras dependências ... -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
    </dependency>
    <!-- ... -->
</dependencies>


Se você estiver usando Gradle, a linha seria implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'.
d. Adicionar Configuração CORS
Para permitir que o frontend (especialmente se acessado via file:// ou em um domínio diferente) se comunique com o backend, adicione a anotação @CrossOrigin(origins = "*") nas suas classes de recurso (controllers):
LivrosResources.java
AutorResources.java
EditoraResources.java
Exemplo (para cada classe de recurso):
package com.thais.livraria.resources;

import org.springframework.web.bind.annotation.CrossOrigin; // Importe esta anotação
// ... outros imports ...

@RestController
@RequestMapping(value = "/livro") // ou /autor, /editora
@CrossOrigin(origins = "*") // Permite requisições de qualquer origem (para desenvolvimento)
public class LivrosResources {
    // ...
}


e. Rodar a Aplicação Backend
Limpe e Reconstrua o Projeto:
Pare sua aplicação Spring Boot se estiver rodando.
No seu editor de código (ex: VS Code): Abra a Paleta de Comandos (Ctrl+Shift+P ou Cmd+Shift+P), digite "Java: Clean Java Language Server Workspace" e selecione essa opção. Confirme a reinicialização.
Se usar Maven no terminal: mvn clean install
Feche e reabra seu editor de código.
Inicie a Aplicação:
Pelo seu editor de código: Abra a classe LivrariaApplication.java e clique no botão "Run" (geralmente um triângulo verde) acima do método main.
Pelo terminal (se usar Maven): Na pasta raiz do projeto (livraria/), execute mvn spring-boot:run.
A aplicação deve iniciar na porta 8080 e popular o banco de dados com dados de exemplo (se Instantiation.java estiver configurado para isso).
2. Configuração do Frontend (HTML/JavaScript)
Para evitar problemas de CORS e servir o frontend pela mesma origem do backend:
Crie a pasta static:
Dentro do seu projeto Spring Boot, crie o diretório: src/main/resources/static
Mova seus arquivos HTML para lá:
Coloque index.html, updates.html e consultas.html dentro de src/main/resources/static.
Certifique-se de que API_BASE_URL está correto:
Nos seus arquivos HTML (index.html, updates.html, consultas.html), a constante API_BASE_URL no JavaScript deve apontar para a porta da sua aplicação Spring Boot:
const API_BASE_URL = 'http://localhost:8080';


Como Acessar o Frontend
Após iniciar sua aplicação Spring Boot e mover os arquivos HTML para src/main/resources/static:
Página Principal: Abra seu navegador e acesse http://localhost:8080/index.html
Página de Atualizações: Abra seu navegador e acesse http://localhost:8080/updates.html
Página de Consultas: Abra seu navegador e acesse http://localhost:8080/consultas.html
API Endpoints
A sua API REST expõe os seguintes endpoints (base: http://localhost:8080):
Livros (/livro)
GET /livro: Lista todos os livros.
GET /livro/{id}: Busca um livro pelo ID.
POST /livro: Adiciona um novo livro. (Body JSON: {"titulo": "...", "idAutor": "...", "idEditora": "...", ...})
PUT /livro/{id}: Atualiza um livro existente. (Body JSON: {"id": "...", "titulo": "...", ...})
DELETE /livro/{id}: Deleta um livro pelo ID.
Consultas de Livros (/livro/consultas)
GET /livro/consultas/top-3-caros: Top 3 livros mais caros.
GET /livro/consultas/ultimos-20-anos: Livros publicados nos últimos 20 anos.
GET /livro/consultas/contagem-por-editora: Número de livros por editora (apenas > 1 livro).
GET /livro/consultas/preco-medio-por-nacionalidade: Preço médio dos livros por nacionalidade de autor.
Relatórios de Livros (/livro/relatorios)
GET /livro/relatorios/livros-com-autores: Livros com detalhes do autor.
GET /livro/relatorios/livros-com-editoras: Livros com detalhes da editora.
GET /livro/relatorios/livros-por-autor-editora?nomeAutor={nome}&nomeEditora={nome}: Livros de um autor e editora específicos.
Autores (/autor)
GET /autor: Lista todos os autores.
GET /autor/{id}: Busca um autor pelo ID.
POST /autor: Adiciona um novo autor. (Body JSON: {"nome": "...", "nacionalidade": "..."})
PUT /autor/{id}: Atualiza um autor existente. (Body JSON: {"id": "...", "nome": "...", ...})
DELETE /autor/{id}: Deleta um autor pelo ID.
Editoras (/editora)
GET /editora: Lista todas as editoras.
GET /editora/{id}: Busca uma editora pelo ID.
POST /editora: Adiciona uma nova editora. (Body JSON: {"nome": "..."})
PUT /editora/{id}: Atualiza uma editora existente. (Body JSON: {"id": "...", "nome": "..."})
DELETE /editora/{id}: Deleta uma editora pelo ID.

