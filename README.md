# Sistema One - Sinapsis

Projeto criado para a fase de teste para a vaga de Desenvolvedor Fullstack da Sinapsis Inovação em Energia.
O sistema desenvolvido é uma aplicação web para realização de cadastro e listagem de Subestações e Redes MT. A aplicação foi criada em Vue.js/Typescript para o front-end e Java (Spring Boot) para o back-end.

## Funcionalidades
O sistema conta com a navegação por 5 telas
- Login (sem autenticação de usuário e senha nesta fase da entrega)
- Lista das subestações cadastradas com seus respectivos códigos e nomes e as funcionalidades de 'Excluir', 'Aterar', 'Exibir no Mapa' e 'Incluir'
- Tela de inclusão, onde podem ser adicionadas novas Subestações (código, nome, latitude e longitude), Redes MT (código e nome) e sua respectiva lista, caso haja.
- Tela de alteração, onde é possível, a partir de seleção de uma Subestação, alterar seus campos de nome, latitude e longitude, além das respectivas Redes MT, caso haja.
- Tela de visualização, onde é exibido no mapa o local da Subestação com base na sua latitude e longitude.

## Requisitos
### Back-end
- Java 17 ou superior
- Maven

## Front-end
- Node v18.16.0
- npm

## Instalação
### Clone do repositório
```
git clone https://github.com/ritalisboar/sistema-sinapsis.git
cd sistema-sinapsis
```

### Back-end
```
cd sistema
./mvnw install
./mvnw spring-boot:run
```

### Front-end
```
cd ../sistemaFront
npm install
npm run dev
```

### Banco de dados
- Foi criado um banco de dados MySQL local, acessado com base nas configurações do arquivo `application.properties`
```
#  MYSQL
spring.datasource.url=jdbc:mysql://localhost:3306/sistemaDatabase
spring.datasource.username=root
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
```

### Observações
A base url integrada no front-end foi a `http://localhost:8080`, de movo que o back-end precisa ser executado nesta porta.
