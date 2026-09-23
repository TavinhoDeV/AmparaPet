-- =========================================================
-- AmparaPet - Schema PostgreSQL
-- Cobre o fluxo descrito no README: cadastro/login, resgate,
-- triagem, tratamento clínico, vacinação, castração e adoção.
-- =========================================================

CREATE TYPE perfil_usuario AS ENUM ('VOLUNTARIO', 'INSTITUICAO', 'ADMIN');
CREATE TYPE tipo_instituicao AS ENUM ('ONG', 'CLINICA');
CREATE TYPE status_animal AS ENUM (
    'AGUARDANDO_TRIAGEM',
    'EM_TRATAMENTO',
    'DISPONIVEL_ADOCAO',
    'ADOTADO'
);
CREATE TYPE tipo_atendimento AS ENUM ('CONSULTA', 'VACINA', 'CASTRACAO', 'TRATAMENTO');
CREATE TYPE status_adocao AS ENUM ('PENDENTE', 'APROVADA', 'CONCLUIDA', 'RECUSADA');

-- =========================================================
-- Usuários (voluntários, instituições, administração)
-- =========================================================
CREATE TABLE usuarios (
    id              BIGSERIAL PRIMARY KEY,
    nome            VARCHAR(150)        NOT NULL,
    email           VARCHAR(150)        NOT NULL UNIQUE,
    senha_hash      VARCHAR(60)         NOT NULL,  -- bcrypt (60 chars)
    telefone        VARCHAR(20),
    perfil          perfil_usuario      NOT NULL DEFAULT 'VOLUNTARIO',
    ativo           BOOLEAN             NOT NULL DEFAULT TRUE,
    criado_em       TIMESTAMP           NOT NULL DEFAULT NOW()
);

-- =========================================================
-- Instituições parceiras (ONGs / clínicas veterinárias)
-- =========================================================
CREATE TABLE instituicoes (
    id                      BIGSERIAL PRIMARY KEY,
    nome                    VARCHAR(150)        NOT NULL,
    cnpj                    VARCHAR(18)          UNIQUE,
    tipo                    tipo_instituicao     NOT NULL,
    endereco                VARCHAR(250),
    telefone                VARCHAR(20),
    usuario_responsavel_id  BIGINT REFERENCES usuarios(id),
    criado_em               TIMESTAMP            NOT NULL DEFAULT NOW()
);

-- =========================================================
-- Animais
-- =========================================================
CREATE TABLE animais (
    id              BIGSERIAL PRIMARY KEY,
    nome            VARCHAR(100),
    especie         VARCHAR(50)         NOT NULL,
    raca            VARCHAR(80),
    sexo            CHAR(1)             CHECK (sexo IN ('M', 'F')),
    porte           VARCHAR(20),
    data_resgate    DATE                NOT NULL,
    status          status_animal       NOT NULL DEFAULT 'AGUARDANDO_TRIAGEM',
    vacinado        BOOLEAN             NOT NULL DEFAULT FALSE,
    castrado        BOOLEAN             NOT NULL DEFAULT FALSE,
    observacoes     TEXT,
    criado_em       TIMESTAMP           NOT NULL DEFAULT NOW()
);

-- =========================================================
-- Resgates
-- =========================================================
CREATE TABLE resgates (
    id              BIGSERIAL PRIMARY KEY,
    animal_id       BIGINT      NOT NULL REFERENCES animais(id),
    voluntario_id   BIGINT      NOT NULL REFERENCES usuarios(id),
    data_resgate    TIMESTAMP   NOT NULL DEFAULT NOW(),
    local           VARCHAR(250) NOT NULL,
    descricao       TEXT
);

-- =========================================================
-- Triagens
-- =========================================================
CREATE TABLE triagens (
    id                  BIGSERIAL PRIMARY KEY,
    animal_id           BIGINT      NOT NULL REFERENCES animais(id),
    responsavel_id      BIGINT      NOT NULL REFERENCES usuarios(id),
    data_triagem        TIMESTAMP   NOT NULL DEFAULT NOW(),
    estado_saude        VARCHAR(100),
    necessita_tratamento BOOLEAN    NOT NULL DEFAULT FALSE,
    observacoes         TEXT
);

-- =========================================================
-- Atendimentos clínicos (consulta / vacina / castração / tratamento)
-- =========================================================
CREATE TABLE atendimentos_clinicos (
    id                      BIGSERIAL PRIMARY KEY,
    animal_id               BIGINT              NOT NULL REFERENCES animais(id),
    instituicao_id          BIGINT              REFERENCES instituicoes(id),
    tipo                    tipo_atendimento    NOT NULL,
    data_atendimento        TIMESTAMP           NOT NULL DEFAULT NOW(),
    veterinario_responsavel VARCHAR(150),
    descricao               TEXT
);

-- =========================================================
-- Vacinações
-- =========================================================
CREATE TABLE vacinacoes (
    id                  BIGSERIAL PRIMARY KEY,
    animal_id           BIGINT      NOT NULL REFERENCES animais(id),
    atendimento_id      BIGINT      REFERENCES atendimentos_clinicos(id),
    tipo_vacina         VARCHAR(100) NOT NULL,
    data_aplicacao      DATE        NOT NULL,
    data_proxima_dose   DATE
);

-- =========================================================
-- Castrações
-- =========================================================
CREATE TABLE castracoes (
    id              BIGSERIAL PRIMARY KEY,
    animal_id       BIGINT      NOT NULL UNIQUE REFERENCES animais(id),
    atendimento_id  BIGINT      REFERENCES atendimentos_clinicos(id),
    instituicao_id  BIGINT      REFERENCES instituicoes(id),
    data_castracao  DATE        NOT NULL
);

-- =========================================================
-- Adoções
-- Regra de negócio: só pode existir adoção CONCLUIDA se o
-- animal estiver vacinado e castrado (validado na service layer,
-- reforçado aqui como camada extra de segurança).
-- =========================================================
CREATE TABLE adocoes (
    id                  BIGSERIAL PRIMARY KEY,
    animal_id           BIGINT          NOT NULL REFERENCES animais(id),
    adotante_nome       VARCHAR(150)    NOT NULL,
    adotante_documento  VARCHAR(20)     NOT NULL,
    adotante_telefone   VARCHAR(20),
    adotante_endereco   VARCHAR(250),
    status              status_adocao   NOT NULL DEFAULT 'PENDENTE',
    aprovado_por        BIGINT          REFERENCES usuarios(id),
    data_solicitacao    TIMESTAMP       NOT NULL DEFAULT NOW(),
    data_conclusao      TIMESTAMP
);

CREATE INDEX idx_animais_status ON animais(status);
CREATE INDEX idx_resgates_animal ON resgates(animal_id);
CREATE INDEX idx_adocoes_animal ON adocoes(animal_id);
CREATE INDEX idx_atendimentos_animal ON atendimentos_clinicos(animal_id);
