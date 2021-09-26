# Iniciando Aplicação

### Componentes

Esta aplicação foi desenvolvida utilizando os componentes listados abaixo:

* [Java OpenJDK 11 - Amazon Corretto (Distribuição gratuita da OpenJDK)](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/what-is-corretto-11.html)
* [Apache Maven 3.8.2 Documentation](https://maven.apache.org/guides/index.html)
* [Git 2.33.0 Documentation](https://git-scm.com/doc)
* [Spring Boot 2.5.5](https://spring.io/projects/spring-boot)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.5/reference/htmlsingle/#boot-features-developing-web-applications)
* [Swagger - Open API](https://swagger.io/tools/open-source/getting-started/)

### Repositório

O repositório deste projeto esta disponível no gitHub e pode ser clonado para sua 
workspace local utilizando a instrução abaixo:

(Certifique-se que o Git esteja instalado)
* Abra seu terminal (Prompt de Comando, PowerShell ou Git Bash)
```
git clone https://github.com/lcsjr/iti-service-validate-password.git
```

### Iniciar a Aplicação

A inicialização da aplicação pode ser executada através de sua IDE de preferência 
ou através da instrução abaixo:

* Abra seu terminal (Prompt de Comando, PowerShell ou Git Bash)
* Acesar a pasta do projeto clonado, conforme:
````
cd c:\<SUA_WORKSPACE>\iti-service-validate-password
````
(Certifique-se que o Maven esteja instalado e incluído nas variaveis de ambiente)
* Executar a instrução abaixo para o Maven baixar as dependências e criar o build do projeto
````
mvn clean install
````
* Execute a instrução abaixo para iniciar o serviço da aplicação
````
mvn spring-boot:run
````

### Testando

Acesse a url [http://localhost:8081/iti/swagger-ui.html](http://localhost:8081/iti/swagger-ui.html)