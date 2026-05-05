# Medicar-Backend

Projeto implementado com Spring Boot 3.5.2 e Java 21.  
[Frontend](https://github.com/kurocaelum/medicar-frontend) implementado separadamente como uma aplicação Angular.

## OpenAPI

### Definição da API em JSON

```
GET /api-docs
```

### Swagger UI

```
GET /swagger
```

## Autenticação

### Login

#### Requisição

```
POST /auth/login
{
    "username": "admin",
    "password": "password"
}
```

#### Resposta

```
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
}
```

### Cadastro

#### Requisição

```
{
    "username": "admin",
    "password": "password",
    "email": "admin@mail.com"
}
```

- Campo "email" opcional

#### Resposta

Não há retorno (vazio)

## Usuários

### Listar

Lista todos os usuários cadastrados

#### Requisição

```
GET /users
```

#### Resposta

```
[
    {
        "id": 15,
        "username": "Victor",
        "password": "$2a$10$kF80j6GcQ9ZCZm4KQ5WdeOYL0TvWlUk4URk3vBrUNgY9yXA1nlEJa",
        "email": "victor_teste@gmail.com",
        "consultas": [
            {
                "id": 25,
                "horario": "14:00:00",
                "dataAgendamento": "2026-02-24T14:33:39.022796"
            }
        ],
        "authorities": [
            {
                "authority": "ROLE_USER"
            }
        ],
        "enabled": true,
        "credentialsNonExpired": true,
        "accountNonExpired": true,
        "accountNonLocked": true
    },
    {
        "id": 16,
        "username": "admin",
        "password": "$2a$10$8vx8J3PUjolSUI7QvqhPyuFmwX2U36E.q8MN8wRVLX5UYuw9AjgRG",
        "email": "admin@mail.com",
        "consultas": [],
        "authorities": [
            {
                "authority": "ROLE_USER"
            }
        ],
        "enabled": true,
        "credentialsNonExpired": true,
        "accountNonExpired": true,
        "accountNonLocked": true
    }
]
```

### Visualizar por ID

Retorna os dados do usuário especificado pelo user_id

#### Requisição

```
GET /users/<user_id>
```

#### Resposta

```
{
    "id": 15,
    "username": "Victor",
    "password": "$2a$10$kF80j6GcQ9ZCZm4KQ5WdeOYL0TvWlUk4URk3vBrUNgY9yXA1nlEJa",
    "email": "victor_teste@gmail.com",
    "consultas": [
        {
            "id": 25,
            "horario": "14:00:00",
            "dataAgendamento": "2026-02-24T14:33:39.022796"
        }
    ],
    "authorities": [
        {
            "authority": "ROLE_USER"
        }
    ],
    "enabled": true,
    "credentialsNonExpired": true,
    "accountNonExpired": true,
    "accountNonLocked": true
}
```

### Inserir

Salva novo usuário no banco de dados  

**Projetado para ser usado apenas internamente pelo sistema**. O cadastro de novo usuário deve ser realizado através do endpoint de [Cadastro](#cadastro)

#### Requisição

```
POST /users
{
    "username": "Victor"
    "password": "$2a$10$kF80j6GcQ9ZCZm4KQ5WdeOYL0TvWlUk4URk3vBrUNgY9yXA1nlEJa",
    "email": "victor_teste@gmail.com"
}
```

#### Resposta

```
{
    "id": 19,
    "username": "Victor",
    "password": "$2a$10$kF80j6GcQ9ZCZm4KQ5WdeOYL0TvWlUk4URk3vBrUNgY9yXA1nlEJa",
    "email": "victor_teste@gmail.com",
    "consultas": [],
    "authorities": [
        {
            "authority": "ROLE_USER"
        }
    ],
    "enabled": true,
    "credentialsNonExpired": true,
    "accountNonExpired": true,
    "accountNonLocked": true
}
```

### Remover

Deleta os dados do usuário especificado pelo user_id

#### Requisição

```
DELETE /users/<user_id>
```

#### Resposta

Não há retorno (vazio)

## Especialidades

### Listar

Lista todas as especialidades cadastradas

#### Requisição

```
GET /especialidades
```

#### Resposta

```
[
    {
        "id": 3,
        "nome": "Psiquiatria"
    },
    {
        "id": 4,
        "nome": "Cardiologia"
    },
    {
        "id": 2,
        "nome": "Psicologia"
    }
]
```

#### Filtros

- Nome da especialidade (termo de pesquisa)

```
GET /especialidades/?search=ped
```

### Visualizar por ID

Retorna os dados da especialidade especificada pelo especialidade_id

#### Requisição

```
GET /users/<user_id>
```

#### Resposta

```
{
    "id": 2,
    "nome": "Psicologia"
}
```

### Inserir

Salva nova especialidade no banco de dados  

#### Requisição

```
{
    "nome": "Neurologia"
}
```

#### Resposta

```
{
    "id": 6,
    "nome": "Neurologia"
}
```

#### Regras de negócio

- Não é possível inserir especialidade com mesmo nome de outra especialidade já cadastrada

### Remover

Deleta os dados da especialidade especificada pelo especialidade_id

#### Requisição

```
DELETE /especialidades/<especialidade_id>
```

#### Resposta

Não há retorno (vazio)

### Atualizar

Edita os dados da especialidade especificada pelo especialidade_id

#### Requisição

```
PUT /especialidades/<especialidade_id>
{
    "nome": "Neurologia"
}
```

#### Resposta

```
{
    "id": 5,
    "nome": "Neurologia"
}
```

## Médicos

### Listar

Lista todos os médicos cadastrados

#### Requisição

```
GET /medicos
```

#### Resposta

```
[
    {
        "id": 2,
        "crm": "0002",
        "nome": "Ishigami Senku",
        "email": null,
        "especialidade": {
            "id": 4,
            "nome": "Cardiologia"
        }
    },
    {
        "id": 4,
        "crm": "0004",
        "nome": "Drauzio Varella",
        "email": "drauzio@gmail.com",
        "especialidade": {
            "id": 4,
            "nome": "Cardiologia"
        }
    }
]
```

#### Filtros

- Identificador de uma ou mais especialidades
- Nome do médico (termo de pesquisa)

```
GET /medicos/?search=maria&especialidade=1&especialidade=3
```

### Visualizar por ID

Retorna os dados do médico especificado pelo medico_id

#### Requisição

```
GET /medicos/<medico_id>
```

#### Resposta

```
{
    "id": 4,
    "crm": "0004",
    "nome": "Drauzio Varella",
    "email": "drauzio@gmail.com",
    "especialidade": {
        "id": 4,
        "nome": "Cardiologia"
    }
}
```

### Inserir

Salva novo médico no banco de dados

#### Requisição

```
POST /medicos
{
    "crm": "0005",
    "nome": "Gregory House",
    "email": "house@mail.com",
    "especialidadeId": 4
}
```

- "email" opcional

#### Resposta

```
{
    "id": 5,
    "crm": "0005",
    "nome": "Gregory House",
    "email": "house@mail.com",
    "especialidade": {
        "id": 4,
        "nome": "Cardiologia"
    }
}
```

### Remover

Deleta os dados do médico especificado pelo medico_id

#### Requisição

```
DELETE /medicos/<medico_id>
```

#### Resposta

Não há retorno (vazio)

### Atualizar

Edita os dados do médico especificado pelo medico_id

#### Requisição

```
PUT /medicos/<medico_id>
{
    "nome": "Yukari Takeba",
    "crm": "0001",
    "email": "takeba@sees.com"
}
```

- "email" opcional

#### Resposta

```
{
    "id": 1,
    "crm": "0001",
    "nome": "Yukari Takeba",
    "email": "takeba@sees.com",
    "especialidade": {
        "id": 3,
        "nome": "Psiquiatria"
    }
}
```

## Agendas

### Listar

Lista todas as agendas médicas cadastradas

#### Requisição

```
GET /agendas
```

#### Resposta

```
[
    {
        "id": 4,
        "medico": {
            "id": 1,
            "crm": "0001",
            "nome": "Yukari Takeba",
            "email": "takeba@sees.com",
            "especialidade": {
                "id": 3,
                "nome": "Psiquiatria"
            }
        },
        "dia": "2026-12-02",
        "horarios": [
            "14:00",
            "17:00",
            "16:00",
            "15:00"
        ]
    },
    {
        "id": 7,
        "medico": {
            "id": 4,
            "crm": "0004",
            "nome": "Drauzio Varella",
            "email": "drauzio@gmail.com",
            "especialidade": {
                "id": 4,
                "nome": "Cardiologia"
            }
        },
        "dia": "2027-12-02",
        "horarios": [
            "15:00",
            "16:00",
            "17:00"
        ]
    }
]
```

#### Regras de negócio

- Ordenado pela data (crescente)
- Não retorna agendas de dias passados
- Retorna apenas horários disponíveis (sem agendamento)

#### Filtros

- Identificador de um ou mais médicos
- Identificador de um ou mais CRM
- Intervalo de data

```
# Retorna as agendas dos médicos 1 e 2 no período de 1 a 5 de janeiro
GET /agendas/?medico=1&medico=2&data_inicio=2022-01-01&data_final=2022-01-05

# Retorna as agendas dos médicos de CRM passados no filtro no período de 1 a 5 de janeiro
GET /agendas/?crm=2544&crm=3711&data_inicio=2022-01-01&data_final=2022-01-05
```

### Listagem detalhada

Lista os objetos Agenda conforme definidos na camada de entidades, sem conversão via DTO.  
Exibe também os horários indisponíveis

#### Requisição

```
GET /agendas/details
```

#### Resposta

```
[
    {
        "id": 5,
        "dia": "2025-12-02",
        "medico": {
            "id": 2,
            "crm": "0002",
            "nome": "Ishigami Senku",
            "email": null,
            "especialidade": {
                "id": 4,
                "nome": "Cardiologia"
            }
        },
        "horarios": [
            {
                "id": 17,
                "horario": "14:00:00",
                "dataAgendamento": null
            },
            {
                "id": 18,
                "horario": "15:00:00",
                "dataAgendamento": null
            },
            {
                "id": 19,
                "horario": "16:00:00",
                "dataAgendamento": null
            },
            {
                "id": 20,
                "horario": "17:00:00",
                "dataAgendamento": null
            }
        ]
    },
    {
        "id": 1,
        "dia": "2026-12-01",
        "medico": {
            "id": 1,
            "crm": "0001",
            "nome": "Yukari Takeba",
            "email": "takeba@sees.com",
            "especialidade": {
                "id": 3,
                "nome": "Psiquiatria"
            }
        },
        "horarios": [
            {
                "id": 1,
                "horario": "14:00:00",
                "dataAgendamento": "2025-12-18T20:17:52.144476"
            },
            {
                "id": 2,
                "horario": "15:00:00",
                "dataAgendamento": "2025-12-18T20:17:58.635487"
            },
            {
                "id": 3,
                "horario": "16:00:00",
                "dataAgendamento": "2025-12-18T20:18:05.201173"
            },
            {
                "id": 4,
                "horario": "17:00:00",
                "dataAgendamento": "2026-01-20T16:36:21.710041"
            }
        ]
    }
]
```

### Visualizar por ID

Retorna os dados detalhados da agenda especificada pelo agenda_id

#### Requisição

```
GET /agendas/<agenda_id>
```

#### Resposta

```
{
    "id": 4,
    "dia": "2026-12-02",
    "medico": {
        "id": 1,
        "crm": "0001",
        "nome": "Yukari Takeba",
        "email": "takeba@sees.com",
        "especialidade": {
            "id": 3,
            "nome": "Psiquiatria"
        }
    },
    "horarios": [
        {
            "id": 13,
            "horario": "14:00:00",
            "dataAgendamento": null
        },
        {
            "id": 16,
            "horario": "17:00:00",
            "dataAgendamento": null
        },
        {
            "id": 15,
            "horario": "16:00:00",
            "dataAgendamento": null
        },
        {
            "id": 14,
            "horario": "15:00:00",
            "dataAgendamento": null
        }
    ]
}
```

### Inserir

Salva nova agenda no banco de dados

#### Requisição

```
POST /agendas
{
    "medicoId": 1,
    "dia": "2050-02-26",
    "horarios": [
        "14:00",
        "15:00",
        "16:00",
        "17:00"
    ]
}
```

- "dia" em formato "yyyy-MM-dd"
- "horarios" em formato "HH:mm"

#### Resposta

```
{
    "id": 8,
    "dia": "2050-02-26",
    "medico": {
        "id": 1,
        "crm": "0001",
        "nome": "Yukari Takeba",
        "email": "takeba@sees.com",
        "especialidade": {
            "id": 3,
            "nome": "Psiquiatria"
        }
    },
    "horarios": [
        {
            "id": 29,
            "horario": "14:00:00",
            "dataAgendamento": null
        },
        {
            "id": 30,
            "horario": "15:00:00",
            "dataAgendamento": null
        },
        {
            "id": 31,
            "horario": "16:00:00",
            "dataAgendamento": null
        },
        {
            "id": 32,
            "horario": "17:00:00",
            "dataAgendamento": null
        }
    ]
}
```

#### Regras de negócio

- Não é possível inserir uma agenda com data passada
- Não é possível inserir uma agenda duplicata (i.e. com combinação de médico e data)

#### Remover

Deleta agenda especificada pelo agenda_id

#### Requisição

```
DELETE /agendas/<agenda_id>
```

#### Resposta

Não há retorno (vazio)

### Atualizar data

Edita a data de uma agenda especificada pelo agenda_id

#### Requisição

```
PUT /agendas/<agenda_id>
{
    "dia": "2050-03-26"    
}
```

#### Resposta

```
{
    "id": 4,
    "dia": "2050-03-26",
    "medico": {
        "id": 1,
        "crm": "0001",
        "nome": "Yukari Takeba",
        "email": "takeba@sees.com",
        "especialidade": {
            "id": 3,
            "nome": "Psiquiatria"
        }
    },
    "horarios": [
        {
            "id": 13,
            "horario": "14:00:00",
            "dataAgendamento": null
        },
        {
            "id": 16,
            "horario": "17:00:00",
            "dataAgendamento": null
        },
        {
            "id": 15,
            "horario": "16:00:00",
            "dataAgendamento": null
        },
        {
            "id": 14,
            "horario": "15:00:00",
            "dataAgendamento": null
        }
    ]
}
```

## Consultas

### Listar

Lista todas as consultas cadastradas

#### Requisição

```
GET /consultas
```

#### Resposta

```
[
    {
        "id": 15,
        "dia": "26/03/2050",
        "horario": "16:00",
        "dataAgendamento": null,
        "medico": {
            "id": 1,
            "crm": "0001",
            "nome": "Yukari Takeba",
            "email": "takeba@sees.com",
            "especialidade": {
                "id": 3,
                "nome": "Psiquiatria"
            }
        }
    },
    {
        "id": 16,
        "dia": "26/03/2050",
        "horario": "17:00",
        "dataAgendamento": null,
        "medico": {
            "id": 1,
            "crm": "0001",
            "nome": "Yukari Takeba",
            "email": "takeba@sees.com",
            "especialidade": {
                "id": 3,
                "nome": "Psiquiatria"
            }
        }
    }
]
```

#### Regras de negócio

- Ordenado por agenda.data (crescente) e pelo horário (crescente)
- Não retorna consultas de dias passados
- Não retorna consultas que já passaram do horário (para consultas no dia atual)

#### Filtros

- Identificador de usuário

```
# Retorna as consultas do usuário com user_id 15
GET /consultas?user=15
```

### Listagem detalhada

Lista os objetos Consulta conforme definidos na camada de entidades, sem conversão via DTO.

#### Requisição

```
GET /consultas/details
```

#### Resposta

```
[
     {
        "id": 17,
        "horario": "14:00:00",
        "dataAgendamento": null
    },
    {
        "id": 21,
        "horario": "14:00:00",
        "dataAgendamento": null
    },
    {
        "id": 22,
        "horario": "15:00:00",
        "dataAgendamento": null
    }
]
```

### Visualizar por ID

Retorna a consulta especificada pelo consulta_id

#### Requisição

```
GET /consultas/<consulta_id>
```

#### Resposta

```
{
    "id": 1,
    "dia": "01/12/2026",
    "horario": "14:00",
    "dataAgendamento": "2026-02-27 17:31:27",
    "medico": {
        "id": 1,
        "crm": "0001",
        "nome": "Yukari Takeba",
        "email": "takeba@sees.com",
        "especialidade": {
            "id": 3,
            "nome": "Psiquiatria"
        }
    }
}
```

### Visualização detalhada por ID

Retorna o objeto Consulta especificado pelo consulta_id conforme definido na camada de entidades, sem conversão via DTO.

#### Requisição

```
GET /consultas/details/<consulta_id>
```

#### Resposta

```
{
    "id": 1,
    "horario": "14:00:00",
    "dataAgendamento": "2026-02-27T17:31:27.82419"
}
```

### Remover

Deleta uma consulta especificada pelo consulta_id

#### Requisição

```
DELETE /consultas/delete/<consulta_id>
```

#### Resposta

Não há retorno (vazio)

### Desmarcar consulta

Desmarca uma consulta, tornando seu horário disponível novamente

#### Requisição

```
DELETE /consultas/<consulta_id>
```

#### Resposta

Não há retorno (vazio)

#### Regras de negócio

- Não é possível desmarcar uma consulta sem agendamento marcado
- Não é possível desmarcar uma consulta de uma data passada
- Não é possível desmarcar uma consulta de um horário passado (caso seja no dia atual)

### Marcar consulta

Marca uma consulta, tornando seu horário indisponível

#### Requisição

```
{
    "agenda_id": 1,
    "horario": "14:00",
    "user_id": 1
}
```

#### Resposta

```
{
    "id": 1,
    "dia": "01/12/2026",
    "horario": "14:00",
    "dataAgendamento": "2026-02-27 17:31:27",
    "medico": {
        "id": 1,
        "crm": "0001",
        "nome": "Yukari Takeba",
        "email": "takeba@sees.com",
        "especialidade": {
            "id": 3,
            "nome": "Psiquiatria"
        }
    }
}
```

#### Regras de negócio

- Não é possível marcar consulta para uma agenda inexistente
- Não é possível marcar consulta em uma agenda com data passada
- Não é possível marcar consulta em uma agenda com horário passado
- Não é possível marcar consulta se já tiver sido marcada antes
