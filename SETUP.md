# Configurando o backend e o banco do AmparaPet

O backend foi implementado seguindo a estrutura já planejada no README
(`controller` → `service` → `dao` → `model`), usando JPA/Hibernate (que já
vem embutido no WildFly) em vez de Spring Boot, para não fugir do stack
Jakarta EE que o projeto já usa (Jakarta Faces + PrimeFaces + WildFly).

## 1. Criar o banco PostgreSQL

```bash
createdb ampara
psql -d ampara -f src/main/resources/db/schema.sql
```

O schema cria todas as tabelas do ciclo descrito no README: usuários,
instituições, animais, resgates, triagens, atendimentos clínicos,
vacinações, castrações e adoções.

> Observação: `persistence.xml` está com `hibernate.hbm2ddl.auto=update`,
> então o Hibernate também consegue criar/ajustar as tabelas sozinho a
> partir das entidades. Rodar o `schema.sql` continua sendo recomendado
> como fonte de verdade do modelo (índices, constraints, tipos ENUM).

## 2. Instalar o driver do PostgreSQL como módulo do WildFly

O driver **não** deve ir dentro do `.war` (por isso está com
`scope=provided` no `pom.xml`). Ele precisa ser um módulo do servidor:

```bash
cd $WILDFLY_HOME
bin/jboss-cli.sh --connect --command="module add --name=org.postgresql --resources=/caminho/para/postgresql-42.7.4.jar --dependencies=jakarta.transaction.api"
```

Baixe o jar em: https://jdbc.postgresql.org/download/

## 3. Criar o datasource `java:/AmparaDS`

Com o WildFly rodando, via CLI:

```bash
bin/jboss-cli.sh --connect

/subsystem=datasources/jdbc-driver=postgresql:add(driver-name=postgresql,driver-module-name=org.postgresql,driver-class-name=org.postgresql.Driver)

data-source add \
  --name=AmparaDS \
  --jndi-name=java:/AmparaDS \
  --driver-name=postgresql \
  --connection-url=jdbc:postgresql://localhost:5432/ampara \
  --user-name=SEU_USUARIO \
  --password=SUA_SENHA \
  --enabled=true
```

Isso bate com o `jta-data-source` configurado em
`src/main/resources/META-INF/persistence.xml`.

## 4. Rodar a aplicação

```bash
mvn clean wildfly:dev
```

Login e cadastro agora persistem de verdade no PostgreSQL (senha com
hash bcrypt, e-mail único).

## O que já está implementado

- **Cadastro/Login** (`UsuarioService`, `UsuarioDAO`): completo, ligado aos
  controllers `CadastroController` e `LoginController` que já existiam.
- **Modelo de dados completo**: todas as entidades do ciclo descrito no
  README (`Animal`, `Resgate`, `Triagem`, `AtendimentoClinico`,
  `Vacinacao`, `Castracao`, `Adocao`, `Instituicao`).
- **Regra de negócio da adoção**: `AdocaoService.concluir()` bloqueia a
  conclusão da adoção se o animal não estiver vacinado e castrado
  (`Animal.isElegivelParaAdocao()`), como pede o README.
- **DAO genérico** (`GenericDAO`): as classes de DAO específicas
  (`UsuarioDAO`, `AnimalDAO`, `AdocaoDAO`) só adicionam as consultas
  próprias de cada entidade.

## Próximos passos sugeridos

- Criar `ResgateDAO`/`ResgateService`, `TriagemDAO`/`TriagemService` e
  `InstituicaoDAO`/`InstituicaoService` seguindo exatamente o mesmo padrão
  de `AnimalDAO`/`AnimalService` — a estrutura já está pronta para isso.
- Criar as telas (`.xhtml`) e controllers para cadastro de animal,
  registro de resgate/triagem e fluxo de adoção.
- Implementar controle de perfil (voluntário / instituição / admin) nas
  páginas, usando `Usuario.getPerfil()` guardado na sessão pelo
  `LoginController`.
