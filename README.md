# client-simple
Simples serviço standalone de registro de clientes.

## Pré-requisitos
* Sistema operacional Linux
* Java 1.8 ou acima
* MariaDB versão 5.3 ou acima
* Maven versão 3.6 ou acima

## Instalação
Baixe client-simple através do comando `git clone https://github.com/ian-melo/client-simple.git` ou baixe o ZIP disponível e o extraia no diretório de preferência.

## Configuração
Certifique-se que o serviço do banco de dados MariaDB está habilitado e em execução.
Para configurar o banco de dados, acesse o MariaDB. Crie a base `clientcrud` e o usuário `clientcrud` de mesma senha, conceda os respectivos privilégios e salve as configurações:
```
MariaDB> CREATE DATABASE clientcrud;
MariaDB> CREATE USER 'clientcrud'@'localhost' IDENTIFIED BY 'clientcrud';
MariaDB> GRANT ALL PRIVILEGES ON clientcrud.* TO 'clientcrud'@'localhost';
MariaDB> FLUSH PRIVILEGES;
```
O serviço, por padrão, é aberto na porta **8081**. Para alterá-lo, modifique o arquivo `application.properties` no diretório `src/main/resources/` logo abaixo do diretório principal:
```
...
server.port=8081
...
```

## Execução
No diretório encontram-se os *scripts* `client-simple-service.sh` e `client-simple.sh`, sendo o primeiro o serviço responsável por administrar o segundo. Para inicializá-lo, execute-o no diretório:
```
./client-simple-service.sh start
```
Para parar, reiniciar ou verificar o status utilize os argumentos `stop`, `restart` ou `status` respectivamente.

Para verificar se o serviço está de pé, faça uma requisição à raiz do serviço. Por exemplo:
```
curl -X GET http://localhost:8081/
```

## Uso
### Listar clientes
```
GET /clientes/
```
Retorna uma lista contendo os IDs dos clientes registrados. Exemplo:
```
(Request)
GET /clientes/
(Response)
[{"id":1},{"id":2},{"id":3},{"id":4}]
```

### Detalhar cliente
```
GET /clientes/{id}
```
Retorna os detalhes de um cliente. Exemplo:
```
(Request)
GET /clientes/4
(Response)
{"id":4,"nome":"Maria","idade":28}
```

### Inserir cliente
```
POST /clientes/
{"nome":"<nome>","idade":<idade>}
```
Retorna os detalhes de um cliente. Exemplo:
```
(Request)
POST /clientes/
{"nome":"Carlos","idade":31}
(Response)
{"id":5,"nome":"Carlos","idade":31}
```

### Alterar cliente
```
PUT /clientes/{id}
{"nome":"<nome>","idade":<idade>}
```
Altera as informações de um cliente. Exemplo:
```
(Request)
PUT /clientes/4
{"nome":"Maria","idade":29}
(Response)
{"id":4,"nome":"Maria","idade":29}
```

### Excluir cliente
```
DELETE /clientes/{id}
```
Exclui um cliente. Exemplo:
```
(Request)
DELETE /clientes/5
```

## Estatísticas
As estatísticas estão localizadas na tabela `estatistica` da base `clientcrud`.
```
SELECT * FROM estatistica;
```

## Mais informações
A escolha das ferramentas e operação foram baseadas na simplicidade de operação, alinhado da disponiblidade no mercado atual. Além disso, são ferramentas/tecnologias de código aberto ou livre, permitindo a operação do mesmo sem necessitar a habilitação através de licença.
