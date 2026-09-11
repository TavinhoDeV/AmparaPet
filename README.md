<div align="center">

# AMPARA

### Sistema de Triagem e Acolhimento de Animais Urbanos

Plataforma desenvolvida para centralizar e acompanhar o processo de resgate, triagem, tratamento e adoção de animais em situação de rua.

</div>

## Sobre o projeto

O Ampara nasceu como um projeto acadêmico voltado à gestão do ciclo de atendimento de animais resgatados. A proposta é reunir em uma única plataforma as informações de resgate, triagem, acompanhamento clínico, tratamento e adoção, facilitando a comunicação entre cidadãos, voluntários, ONGs, clínicas veterinárias parceiras e administração.

Além de organizar os registros, o sistema pretende tornar o processo mais rastreável e seguro, permitindo acompanhar o histórico de cada animal desde a entrada até uma possível adoção.

## Objetivo

Desenvolver uma aplicação web capaz de organizar o fluxo de acolhimento de animais urbanos, mantendo as etapas do atendimento padronizadas e preparando a aplicação para regras como validação de vacinação e castração antes da adoção.

## Estado atual

O projeto está em desenvolvimento. A base da aplicação web e a interface de autenticação já foram iniciadas.

Atualmente o projeto conta com:

- aplicação Java estruturada com Maven;
- execução local no WildFly;
- interface web com Jakarta Faces e PrimeFaces;
- tela de login responsiva;
- identidade visual própria do Ampara;
- validação dos campos de e-mail e senha;
- mensagens de validação personalizadas;
- campo de senha com opção de exibir ou ocultar o conteúdo;
- opção de recuperação de senha preparada na interface;
- acesso institucional preparado na interface;
- opção de cadastro de novos usuários preparada na tela de login;
- PrimeIcons nos componentes da interface;
- estrutura preparada para evolução das camadas de negócio e persistência.

## Tecnologias utilizadas

- Java 21
- Jakarta Faces
- PrimeFaces 15
- PrimeIcons
- Maven
- WildFly 41
- XHTML
- CSS3
- Google Fonts
- Git
- GitHub
- VS Code

### Tipografia

A identidade visual utiliza três famílias principais:

- **Bebas Neue** — títulos e chamadas de maior impacto;
- **Ephesis** — palavras e frases manuscritas da identidade visual;
- **Montserrat** — textos, campos, rótulos e botões.

## Estrutura planejada

```text
src/
└── main/
    ├── java/
    │   └── br/com/ampara/
    │       ├── controller/
    │       ├── model/
    │       ├── service/
    │       └── dao/
    └── webapp/
        ├── resources/
        │   ├── css/
        │   └── images/
        └── login.xhtml
```

A proposta é manter a interface separada das regras do sistema. As páginas XHTML ficam responsáveis pela apresentação, os controllers fazem a comunicação com a interface, os services concentram as regras de negócio e os DAOs serão responsáveis pelo acesso aos dados.

## Executando o projeto

### Pré-requisitos

Para executar o projeto localmente são necessários:

- JDK 21;
- Maven;
- WildFly 41.

Confirme as instalações com:

```bash
java -version
mvn -version
```

### Iniciando a aplicação

Na raiz do projeto, execute:

```bash
mvn clean wildfly:dev
```

Com o servidor iniciado, a aplicação pode ser acessada em:

```text
http://localhost:8080/ampara-1.0-SNAPSHOT/login.xhtml
```

Durante o desenvolvimento, caso seja necessário forçar uma nova publicação sem reiniciar todo o servidor, pode ser utilizado:

```bash
mvn wildfly:redeploy
```

## Fluxos previstos

O Ampara deverá evoluir para contemplar:

- cadastro e gerenciamento de animais;
- registro de resgates;
- triagem;
- histórico de atendimento;
- acompanhamento clínico;
- vacinação;
- castração;
- controle do processo de adoção;
- cadastro de usuários;
- recuperação de senha;
- acesso de instituições e clínicas parceiras;
- controle de perfis e permissões;
- dashboard administrativo;
- persistência em banco de dados relacional.

## Perfis previstos

A aplicação foi pensada para trabalhar com diferentes níveis de acesso, entre eles:

**Voluntário** — participação nos processos de resgate e acompanhamento permitidos ao perfil.

**Clínica / Instituição** — acesso aos fluxos relacionados ao atendimento e acompanhamento dos animais vinculados à instituição.

**Administração** — gerenciamento geral da plataforma, usuários, instituições, animais e processos.

## Regras de negócio

Uma das diretrizes do projeto é não permitir que decisões importantes dependam apenas da interface. Regras relacionadas ao ciclo de atendimento deverão ser validadas pela camada de serviço.

Entre as regras previstas está o impedimento da adoção enquanto etapas obrigatórias, como vacinação e castração, ainda não estiverem devidamente validadas.

## Próximas etapas

As próximas fases previstas são transformar os elementos já apresentados na tela de autenticação em fluxos funcionais e iniciar a implementação das entidades, serviços, DAOs e banco de dados.

A evolução visual das próximas páginas deverá seguir a identidade estabelecida na tela de login para manter consistência em toda a aplicação.

## Contexto acadêmico

O Ampara está sendo desenvolvido como projeto acadêmico na área de Análise e Desenvolvimento de Sistemas, relacionando desenvolvimento de software, banco de dados, engenharia de software e impacto social.

A proposta se conecta ao conceito de Cidades Inteligentes e à abordagem de Saúde Única, considerando a relação entre bem-estar animal, saúde pública e ambiente urbano.

## Desenvolvimento

Projeto em desenvolvimento. A documentação e este README serão atualizados conforme novos módulos e funcionalidades forem implementados.
