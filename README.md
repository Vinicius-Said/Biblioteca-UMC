Projeto de Gerenciamento de Biblioteca
Descrição
Este é um projeto universitário que implementa a base de um sistema de gerenciamento para uma biblioteca. O foco principal é a construção de uma API RESTful utilizando Java e o ecossistema Spring Boot para gerenciar usuários, e futuramente, livros e empréstimos.

O projeto conta com um fluxo completo de cadastro de usuário, incluindo uma interface de front-end simples para demonstração, validação de dados no back-end, armazenamento seguro de senhas e persistência em banco de dados.

Funcionalidades Implementadas
API para Cadastro de Usuários: Endpoint POST para registrar novos usuários no sistema.

Validação de Dados: Validação robusta no lado do servidor para campos como nome, e-mail (formato e unicidade), senha (tamanho mínimo) e documento (formato de 11 dígitos).

Segurança de Senhas: As senhas são armazenadas no banco de dados de forma segura utilizando hash BCrypt, fornecido pelo Spring Security.

Persistência de Dados: Os dados são salvos em um banco de dados H2 configurado em modo arquivo, garantindo que as informações persistam entre as reinicializações da aplicação.

Front-end para Cadastro: Uma tela de cadastro simples (index.html) que consome a API para registrar usuários, com feedback visual para sucesso e erro.

Tecnologias Utilizadas
Back-end
Java 17

Spring Boot 3

Spring Web: Para a criação dos endpoints REST.

Spring Data JPA: Para a persistência de dados.

Spring Security: Para a codificação (hash) de senhas.

Spring Validation: Para a validação dos dados de entrada.

H2 Database: Banco de dados relacional em modo arquivo.

Lombok: Para reduzir código boilerplate nas entidades.

Maven: Gerenciador de dependências e build do projeto.

Front-end
HTML5

CSS3

JavaScript (Fetch API)

Como Executar o Projeto
Pré-requisitos
Java JDK 17 ou superior.

Maven 3.8 ou superior.

Git para clonar o repositório.

Passos para a Execução
Clone o repositório:

Bash

git clone https://github.com/seu-usuario/seu-repositorio.git
(Lembre-se de substituir pela URL do seu repositório)

Navegue até a pasta do projeto:

Bash

cd seu-repositorio
Execute a aplicação com o Maven Wrapper:

No Windows:

Bash

./mvnw spring-boot:run
No Linux/Mac:

Bash

./mvnw spring-boot:run
Acesse a aplicação:

A tela de cadastro de usuário estará disponível em: http://localhost:8080

O console do banco de dados H2 estará disponível em: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:file:./bibliotecadb

User Name: sa

Password: (deixe em branco)

Endpoints da API
Usuários
Cadastrar um novo usuário
URL: /api/users/register

Método: POST

Corpo da Requisição (JSON):

JSON

{
"name": "Nome Completo do Usuário",
"email": "usuario@email.com",
"password": "senhaCom6+Caracteres",
"document": "11122233344"
}
Respostas:

201 Created: Usuário cadastrado com sucesso.

400 Bad Request: Erro de validação (ex: e-mail inválido, documento fora do padrão). O corpo da resposta conterá os detalhes do erro.

409 Conflict: O e-mail fornecido já está em uso.

Autores
Vinicius Jorge Santos Said

Jose Eduardo Escobar Siqueira
>>>>>>> master
