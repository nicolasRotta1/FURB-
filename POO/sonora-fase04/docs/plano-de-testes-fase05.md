# Plano de testes — Sonora Fase 05

## Objetivo
Validar a refatoração de vetores fixos para `ArrayList`, mantendo o comportamento do sistema e cobrindo a nova associação reflexiva de seguidores.

## Cobertura

### 1) Musica
- validação de título, artista e duração
- formatação da duração em mm:ss
- ids sequenciais e independentes

### 2) Playlist
- criação com nome e dono válidos
- rejeição de nome/dono nulos
- adicionar músicas válidas
- rejeitar música nula
- rejeitar duplicidade
- obter música por posição
- remover por posição
- atualizar dados da música
- calcular duração total
- reproduzir todas as músicas

### 3) Plataforma
- cadastrar músicas e usuários
- buscar música por id e título
- buscar usuário por id
- atualizar dados
- remover música e usuário
- listar playlists cadastradas
- manter compatibilidade com `ArrayList` sem limite de capacidade

### 4) Associações reflexivas / seguidores
- usuário segue outro usuário válido
- não permite seguir a si mesmo
- não permite duplicidade
- remover relacionamento ao deixar de seguir
- contar quantos usuários o usuário segue

## O que foi removido do plano
- testes de capacidade máxima de vetores (`500` ou `100`)
- cenários de "plataforma cheia" ou "playlist cheia"

Esses cenários não fazem mais sentido porque `ArrayList` cresce dinamicamente e não possui limite fixo em memória.
