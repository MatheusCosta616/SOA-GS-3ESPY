# Plataforma de Upskilling - API RESTful

Descrição curta
----------------
API RESTful para gerenciar usuários e trilhas de aprendizagem, desenvolvida como atividade acadêmica. A aplicação fornece endpoints para CRUD de usuários e trilhas, validações básicas e migrações de banco via Liquibase.

Integrantes
-----------
- Caíque Walter Silva - RM550693
- Guilherme Nobre Bernardo - RM98604
- Matheus José de Lima Costa - RM551157

📄 **Arquivo completo de integrantes e resumo:** `INTEGRANTES.txt` (na raiz do projeto)

O que o enunciado pede
----------------------
- Implementar uma API RESTful com endpoints para gerenciamento de usuários e trilhas.
- Aplicar validações de entrada (ex.: campos obrigatórios, email válido, carga horária positiva).
- Persistir dados usando JPA e controlar a evolução do esquema com Liquibase (changesets em `src/main/resources/db/changelog`).
- Fornecer documentação mínima dos endpoints e instruções para executar a aplicação localmente.

Requisitos
----------
- Java 21 ou superior instalado
- Maven (opcional, o projeto inclui o wrapper Maven `mvnw`/`mvnw.cmd`)

Como executar (Windows - PowerShell)
----------------------------------
1. (Opcional) Clone o repositório e acesse a pasta do projeto:

   git clone <REPOSITÓRIO>
   cd upskilling-platform-api

2. Usando o wrapper Maven (recomendado no Windows PowerShell):

   # Limpar e compilar
   .\mvnw.cmd clean package

   # Executar a aplicação
   .\mvnw.cmd spring-boot:run

   A aplicação iniciará na porta 8080 (configuração em `src/main/resources/application.properties`).

3. Alternativa: executar o artefato gerado

   .\mvnw.cmd clean package
   java -jar target\*.jar

Observações técnicas relevantes
-----------------------------
- Banco: H2 em memória configurado em `spring.datasource.url=jdbc:h2:mem:upskillingdb`.
- Console H2: habilitado em `/h2-console` (por padrão, usuário `sa`, senha vazia).
- Migrações Liquibase: localizadas em `src/main/resources/db/changelog/db.changelog-master.yaml` e aplicadas automaticamente ao iniciar a aplicação.
- Porta padrão: `8080`.
- Documentação Swagger: acesse `http://localhost:8080/swagger-ui.html` após iniciar a aplicação para visualizar e testar todos os endpoints de forma interativa.

Principais endpoints (resumo)
----------------------------
- Usuários
  - POST  /api/usuarios    → criar usuário
  - GET   /api/usuarios    → listar usuários
  - GET   /api/usuarios/{id} → obter por id
  - PUT   /api/usuarios/{id} → atualizar
  - DELETE /api/usuarios/{id} → excluir

- Trilhas
  - POST  /api/trilhas
  - GET   /api/trilhas
  - GET   /api/trilhas/{id}
  - PUT   /api/trilhas/{id}
  - DELETE /api/trilhas/{id}

Testes e verificação
--------------------
- Documentação interativa: acesse `http://localhost:8080/swagger-ui.html` para visualizar todos os endpoints, schemas e testar diretamente pela interface do Swagger.
- Use Postman, Insomnia ou cURL para testar os endpoints apontando para `http://localhost:8080`.
- Ao iniciar a aplicação, verifique no console as mensagens do Liquibase confirmando que os changesets foram aplicados.

Observações finais
------------------
Este README foi simplificado conforme solicitado: contém apenas os integrantes, instruções de execução e os requisitos/prioridades explicitados no enunciado da atividade. Não contém contatos, licenças, próximos passos nem frases finais adicionais.
# 🚀 Plataforma de Upskilling/Reskilling - API RESTful

## 📌 Descrição do Projeto

Esta é uma **API RESTful** desenvolvida para uma plataforma de **Upskilling e Reskilling** voltada ao **Futuro do Trabalho 2030+**.

O projeto visa ajudar profissionais a se prepararem para as transformações do mercado de trabalho, oferecendo:
- 🎓 **Trilhas de aprendizagem** focadas em competências do futuro
- 👤 **Cadastro de usuários** (profissionais em transição de carreira)
- 📊 **Competências mapeadas** (IA, Análise de Dados, Soft Skills, etc.)
- ✅ **Sistema de matrículas** em trilhas de capacitação

---

## 🎯 Conexão com o Tema "O Futuro do Trabalho"

Este projeto está alinhado com:

### 🌍 **Objetivos de Desenvolvimento Sustentável (ODS)**
- **ODS 4** - Educação de Qualidade
- **ODS 8** - Trabalho Decente e Crescimento Econômico
- **ODS 9** - Indústria, Inovação e Infraestrutura
- **ODS 10** - Redução das Desigualdades

### 💡 **Desafios do Futuro do Trabalho**
- Automação e risco de substituição de funções
- Necessidade de **requalificação profissional** (reskilling)
- Demanda por **aperfeiçoamento contínuo** (upskilling)
- Competências tecnológicas + competências humanas

---

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|------------|--------|-----------|
| **Java** | 21 | Linguagem de programação |
| **Spring Boot** | 3.2.0 | Framework principal |
| **Spring Data JPA** | - | Persistência de dados |
| **Spring Validation** | - | Validação de DTOs |
| **H2 Database** | - | Banco de dados em memória |
| **Liquibase** | - | **Migração de banco (OBRIGATÓRIO)** |
| **Maven** | - | Gerenciamento de dependências |
| **Swagger/OpenAPI** | 2.3.0 | Documentação interativa da API |

---

## ⚙️ Como Executar o Projeto

### **Pré-requisitos**
- ☕ **Java 21** instalado ([Download aqui](https://www.oracle.com/java/technologies/downloads/#java21))
- 📦 **Maven 3.8+** instalado ([Download aqui](https://maven.apache.org/download.cgi))
- 💻 IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code com extensão Java)

### **Passo 1: Clone o repositório**
```bash
git clone https://github.com/MatheusCosta616/upskilling-platform-api.git
cd upskilling-platform-api
```

### **Passo 2: Instale as dependências**
```bash
mvn clean install
```

### **Passo 3: Execute a aplicação**
```bash
mvn spring-boot:run
```

✅ A aplicação estará disponível em: **http://localhost:8080**

### **Passo 4: Verificar os changesets do Liquibase**
Ao iniciar a aplicação, o Liquibase executará automaticamente os changesets:
- ✅ Changeset 1 - Cria as tabelas
- ✅ Changeset 2 - Insere dados iniciais

Você verá no console:
```
Liquibase: Successfully acquired change log lock
Liquibase: Creating database history table with name: DATABASECHANGELOG
Liquibase: Reading from DATABASECHANGELOG
Liquibase: Table usuarios created
Liquibase: Table trilhas created
Liquibase: ChangeSet db/changelog/db.changelog-master.yaml::1::fiap-team ran successfully
Liquibase: ChangeSet db/changelog/db.changelog-master.yaml::2::fiap-team ran successfully
Liquibase: Successfully released change log lock
```

### **Passo 5: Acesse o console H2 (opcional)**
- URL: **http://localhost:8080/h2-console**
- JDBC URL: `jdbc:h2:mem:upskillingdb`
- Username: `sa`
- Password: *(deixe em branco)*

---

## 📡 Endpoints da API

### **👤 Usuários**

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| POST | `/api/usuarios` | Criar novo usuário | 201 |
| GET | `/api/usuarios` | Listar todos os usuários | 200 |
| GET | `/api/usuarios/{id}` | Buscar usuário por ID | 200/404 |
| PUT | `/api/usuarios/{id}` | Atualizar usuário | 200/404 |
| DELETE | `/api/usuarios/{id}` | Remover usuário | 204/404 |

#### **Exemplo de Requisição - Criar Usuário**
```json
POST /api/usuarios
Content-Type: application/json

{
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "areaAtuacao": "Desenvolvimento",
  "nivelCarreira": "Pleno"
}
```

#### **Exemplo de Resposta - Criar Usuário**
```json
{
  "id": 5,
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "areaAtuacao": "Desenvolvimento",
  "nivelCarreira": "Pleno",
  "dataCadastro": "2025-11-10"
}
```

---

### **📚 Trilhas de Aprendizagem**

| Método | Endpoint | Descrição | Status |
|--------|----------|-----------|--------|
| POST | `/api/trilhas` | Criar nova trilha | 201 |
| GET | `/api/trilhas` | Listar todas as trilhas | 200 |
| GET | `/api/trilhas/{id}` | Buscar trilha por ID | 200/404 |
| PUT | `/api/trilhas/{id}` | Atualizar trilha | 200/404 |
| DELETE | `/api/trilhas/{id}` | Remover trilha | 204/404 |

#### **Exemplo de Requisição - Criar Trilha**
```json
POST /api/trilhas
Content-Type: application/json

{
  "nome": "IA Generativa 2030",
  "descricao": "Domine ChatGPT, Midjourney e ferramentas de IA",
  "nivel": "INTERMEDIARIO",
  "cargaHoraria": 50,
  "focoPrincipal": "IA"
}
```

#### **Exemplo de Resposta - Criar Trilha**
```json
{
  "id": 6,
  "nome": "IA Generativa 2030",
  "descricao": "Domine ChatGPT, Midjourney e ferramentas de IA",
  "nivel": "INTERMEDIARIO",
  "cargaHoraria": 50,
  "focoPrincipal": "IA"
}
```

---

## 🧪 Como Testar

### **Usando Postman ou Insomnia:**

1. Importe a collection com os endpoints acima
2. Configure a base URL: `http://localhost:8080`
3. Execute as requisições na ordem:
    - ✅ Criar usuário
    - ✅ Listar todos
    - ✅ Buscar por ID
    - ✅ Atualizar
    - ✅ Deletar

### **Usando cURL:**

```bash
# Criar usuário
curl -X POST http://localhost:8080/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{"nome":"Maria Santos","email":"maria@email.com","areaAtuacao":"Marketing","nivelCarreira":"Junior"}'

# Listar todos os usuários
curl http://localhost:8080/api/usuarios

# Buscar usuário por ID
curl http://localhost:8080/api/usuarios/1

# Atualizar usuário
curl -X PUT http://localhost:8080/api/usuarios/1 \
  -H "Content-Type: application/json" \
  -d '{"nome":"Maria Santos Silva","email":"maria@email.com","areaAtuacao":"Marketing Digital","nivelCarreira":"Pleno"}'

# Deletar usuário
curl -X DELETE http://localhost:8080/api/usuarios/1

# Listar todas as trilhas
curl http://localhost:8080/api/trilhas

# Buscar trilha por ID
curl http://localhost:8080/api/trilhas/1
```

---

## 🗄️ Banco de Dados

### **Modelo Relacional**

```sql
usuarios (id, nome, email, area_atuacao, nivel_carreira, data_cadastro)
trilhas (id, nome, descricao, nivel, carga_horaria, foco_principal)
competencias (id, nome, categoria, descricao)
trilha_competencia (trilha_id, competencia_id)
matriculas (id, usuario_id, trilha_id, data_inscricao, status)
```

### **Changesets do Liquibase (OBRIGATÓRIO)**

O projeto utiliza **Liquibase** para controle de versão do banco de dados:

| Changeset | Descrição |
|---------|-----------|
| Changeset 1 (id: 1) | Cria todas as tabelas do sistema |
| Changeset 2 (id: 2) | Insere dados iniciais (seeds) |

**Localização:** `src/main/resources/db/changelog/db.changelog-master.yaml`

### **Seeds Incluídos**

O projeto já vem com dados iniciais:
- ✅ 4 usuários de exemplo
- ✅ 8 competências do futuro (IA, Dados, Cloud, Soft Skills, etc.)
- ✅ 5 trilhas de aprendizagem
- ✅ Relações entre trilhas e competências

---

## 🛡️ Validações Implementadas

- ✅ **Nome obrigatório** (@NotBlank)
- ✅ **Email válido** (@Email)
- ✅ **Carga horária positiva** (@Positive)
- ✅ **Nível válido** (INICIANTE, INTERMEDIARIO, AVANCADO)

---

## ⚠️ Tratamento de Exceções

A API possui tratamento centralizado de erros com respostas padronizadas:

| Status | Cenário |
|--------|---------|
| 200 | Sucesso (leitura/atualização) |
| 201 | Recurso criado |
| 204 | Recurso deletado |
| 400 | Erro de validação |
| 404 | Recurso não encontrado |
| 422 | Regra de negócio violada |
| 500 | Erro interno do servidor |

### **Exemplo de Erro 404:**
```json
{
  "status": 404,
  "message": "Usuário não encontrado com id: 999",
  "timestamp": "2025-11-10T23:15:30"
}
```

### **Exemplo de Erro 400 (Validação):**
```json
{
  "nome": "Nome é obrigatório",
  "email": "Email deve ser válido"
}
```

---

## 📦 Estrutura do Projeto

```
src/main/
├── java/br/com/fiap/upskilling/
│   ├── UpskillingPlatformApplication.java
│   ├── controller/
│   │   ├── UsuarioController.java
│   │   └── TrilhaController.java
│   ├── service/
│   │   ├── UsuarioService.java
│   │   └── TrilhaService.java
│   ├── repository/
│   │   ├── UsuarioRepository.java
│   │   └── TrilhaRepository.java
│   ├── model/
│   │   ├── Usuario.java
│   │   └── Trilha.java
│   ├── dto/
│   │   ├── UsuarioRequestDTO.java
│   │   ├── UsuarioResponseDTO.java
│   │   ├── TrilhaRequestDTO.java
│   │   └── TrilhaResponseDTO.java
│   └── exception/
│       ├── ResourceNotFoundException.java
│       ├── UsuarioNaoElegivelParaTrilhaException.java
│       └── GlobalExceptionHandler.java
└── resources/
    ├── application.properties
    └── db/changelog/
        └── db.changelog-master.yaml
```
