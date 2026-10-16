# Plano de testes do projeto RangoJá

## ClienteTest

| Caso | Descricao | Entrada | Saida esperada |
| --- | --- | --- | --- |
| idadeNegativaLancaExcecao | Valida idade invalida no construtor. | new Cliente("Ana", "ana@furb.br", -1) | IllegalArgumentException |
| emailSemArrobaLancaExcecao | Valida email invalido. | new Cliente("Ana", "ana.furb.br", 20) | IllegalArgumentException |
| indicacaoCreditaCashbackNoIndicador | Verifica cashback do indicador. | indicador = Maria; indicado = Joao indicado por Maria | saldo da Maria = 10,00 |
| saldoInsuficienteNoUsoCashback | Cliente tenta gastar mais cashback do que possui. | ana.creditarCashback(10.0); ana.usarCashback(15.0) | SaldoInsuficienteException |
| equalsIgnoraMaiusculasEmail | Verifica igualdade ignorando caixa. | "ana@furb.br" e "ANA@FURB.BR" | equals() = true |
| indicadorNullLancaExcecao | Valida indicador nulo. | new Cliente("Beto", "beto@furb.br", 17, null) | IllegalArgumentException |
| deveCadastrarClienteValido | Cliente valido. | new Cliente("Ana Souza", "ana@furb.br", 25) | nome, email, idade e cashback corretos |
| deveLancarExcecaoParaNomeOuEmailVazios | Nome ou email vazios. | "" e "" | IllegalArgumentException |
| deveValidarIdade | Valida maioridade e limite de idade. | cliente.setIdade(18); cliente.setIdade(-1); cliente.setIdade(121) | true ou IllegalArgumentException |
| deveCreditarEUsarCashback | Credita e usa cashback. | 15.0 creditado e 5.0 usado | saldo final = 10,00 |
| deveGerarToStringNoFormatoEsperado | Verifica toString. | cliente = new Cliente("Ana Souza", "ana@furb.br", 20) | "Ana Souza ana@furb.br" |
| deveRetornarFalseParaObjetosDiferentes | Objetos diferentes. | clientes com emails distintos | false |

## ItemCardapioTest

| Caso | Descricao | Entrada | Saida esperada |
| --- | --- | --- | --- |
| deveCalcularPrecoDoPratoComTamanhoMedio | Calcula preco do prato medio. | Prato("Xis Salada", 30.0, 'M') | 39,00 e desconto de 10% = 35,10 |
| deveCalcularPrecoDaBebidaAlcoolica | Calcula preco da bebida alcoolica. | Bebida("Chopp", 12.0, 500, true) | 15,00 |
| deveCalcularPrecoDaSobremesa | Calcula preco da sobremesa. | Sobremesa("Pudim", 8.0, 150) | 12,00 |
| deveLancarExcecaoParaNomeVazio | Nome vazio no cardapio. | new Prato("", 20.0, 'P') | IllegalArgumentException |
| deveLancarExcecaoParaPrecoInvalido | Preco base invalido. | new Bebida("Refri", 0, 350, false) | IllegalArgumentException |
| deveLancarExcecaoParaPercentualInvalido | Percentual fora do intervalo. | prato.calcularPrecoComDesconto(101) | IllegalArgumentException |

## BebidaTest

| Caso | Descricao | Entrada | Saida esperada |
| --- | --- | --- | --- |
| bebidaAlcoolica | Verifica acrescimo de bebida alcoolica. | Bebida("Chopp", 12.0, 500, true) | 15,00 |
| bebidaNaoAlcoolicaSemAcrescimo | Verifica preco sem acrescimo. | Bebida("Refri", 6.0, 350, false) | 6,00 |
| volumeZeroLancaExcecao | Valida volume positivo. | Bebida("Refri", 6.0, 0, false) | IllegalArgumentException |
| toStringBebidaNaoAlcoolica | Verifica toString nao alcoolica. | Bebida("Refri", 6.0, 350, false) | "Refri - R$ 6,00 (350 ml)" |
| toStringBebidaAlcoolica | Verifica toString alcoolica. | Bebida("Chopp", 12.0, 500, true) | "Chopp - R$ 15,00 (500 ml) [alcoólica]" |

## PratoTest

| Caso | Descricao | Entrada | Saida esperada |
| --- | --- | --- | --- |
| tamanhoInvalidoLancaExcecao | Tamanho fora do permitido. | Prato("Xis", 30.0, 'X') | IllegalArgumentException |
| pratoMedioComDesconto | Calcula preco com desconto. | Prato("Xis Salada", 30.0, 'M'); calcularPrecoComDesconto(10) | 35,10 |
| percentualNegativoOuMaiorQue100 | Percentual invalido. | calcularPrecoComDesconto(120) | IllegalArgumentException |
| tamanhoMinusculoLancaExcecao | Tamanho em minusculo. | Prato("Xis", 30.0, 'm') | IllegalArgumentException |
| toStringPrato | Verifica representacao textual. | Prato("Xis Salada", 30.0, 'M') | "Xis Salada - R$ 39,00 (tamanho M)" |

## SobremesaTest

| Caso | Descricao | Entrada | Saida esperada |
| --- | --- | --- | --- |
| sobremesaPeso150 | Calcula valor proporcional ao peso. | Sobremesa("Pudim", 8.0, 150) | 12,00 |
| sobremesaPesoZeroLancaExcecao | Valida peso positivo. | new Sobremesa("Pudim", 8.0, 0) | IllegalArgumentException |
| precoBaseZeroLancaExcecao | Valida preco base positivo. | new Sobremesa("Pudim", 0.0, 150) | IllegalArgumentException |
| toStringSobremesa | Verifica toString da sobremesa. | sobremesa = new Sobremesa("Pudim", 8.0, 150) | "Pudim - R$ 12,00 (150 g)" |

## PedidoTest

| Caso | Descricao | Entrada | Saida esperada |
| --- | --- | --- | --- |
| deveCalcularSubtotalTaxaEValorCobrarDoPedidoBase | Soma subtotal, taxa e total. | pizza? no caso: Xis Salada 30,00 + Refri 6,00 + Pudim 8,00/100g 150g | subtotal 57,00; taxa 7,00; total 64,00 |
| deveDarFreteGratisQuandoSubtotalForMaiorOuIgualA100 | Frete gratis para subtotal >= 100. | dois Xis G de 40,00 | taxa 0,00; total 128,00 |
| deveLancarExcecaoQuandoClienteMenorCompraBebidaAlcoolica | Cliente menor de idade compra chopp. | cliente 17 anos + Bebida alcoolica | IllegalArgumentException |
| deveLancarExcecaoQuandoQuantidadeForZero | Quantidade zero. | adicionarItem(item, 0) | IllegalArgumentException |
| deveLancarExcecaoQuandoFinalizarPedidoSemItens | Pedido vazio finalizado. | finalizar sem itens | IllegalStateException |
| deveConcederCashbackAoFinalizarPedido | Cashback ao finalizar. | subtotal 57,00 | cashback +2,85 |
| deveGerarNumerosConsecutivosParaPedidosCriados | Sequencia de numero de pedido. | dois pedidos criados em sequencia | diferenca = 1 |
| deveLancarExcecaoAoAdicionarItemNulo | Item nulo no pedido. | adicionarItem(null) | IllegalArgumentException |
| deveLancarExcecaoAoAdicionarItemEmPedidoJaFinalizado | Pedido finalizado nao aceita itens. | adicionar item depois de finalizar | IllegalStateException |
| deveLancarExcecaoQuandoQuantidadeForNegativa | Quantidade negativa. | adicionarItem(item, -2) | IllegalArgumentException |
| deveLancarExcecaoQuandoClienteForNulo | Pedido sem cliente. | new Pedido(null) | IllegalArgumentException |

## AssinaturaClubeTest

| Caso | Descricao | Entrada | Saida esperada |
| --- | --- | --- | --- |
| deveCalcularValorDaAssinaturaComDescontoParaDozeMeses | Desconto de 15% para 12 meses. | valorMensal 19,90; meses 12 | 202,98 |
| deveCalcularValorDaAssinaturaSemDescontoParaSeisMeses | Sem desconto para 6 meses. | valorMensal 20,00; meses 6 | 120,00 |
| deveLancarExcecaoQuandoClienteForNulo | Cliente nulo. | new AssinaturaClube(null, 19.90, 12) | IllegalArgumentException |
| deveLancarExcecaoQuandoValorMensalForZero | Valor mensal zero. | new AssinaturaClube(cliente, 0.0, 12) | IllegalArgumentException |
| deveLancarExcecaoQuandoMesesForZero | Meses zero. | new AssinaturaClube(cliente, 19.90, 0) | IllegalArgumentException |
| deveRetornarClienteValorMensalEMesesDaAssinatura | Verifica getters. | assinatura valida | cliente, valor e meses corretos |
| deveLancarExcecaoQuandoValorMensalForNegativo | Valor mensal negativo. | new AssinaturaClube(cliente, -5.0, 6) | IllegalArgumentException |

## CobravelTest

| Caso | Descricao | Entrada | Saida esperada |
| --- | --- | --- | --- |
| deveCalcularValorCobrarPedidoEAssinatura | Soma do pedido e da assinatura. | pedido 64,00 + assinatura 202,98 | total 266,98 |
| deveGerarComprovante | Gera comprovante. | pedido com prato | "Valor a cobrar: R$ 46,00" |
| deveValidarCamposDaAssinatura | Valida campos da assinatura. | null, zero e meses zero | IllegalArgumentException |
| deveGerarRelatorioComLinhas | Gera relatorio com linhas. | central com pedido | relatorio com total final |

## CentralCobrancaTest

| Caso | Descricao | Entrada | Saida esperada |
| --- | --- | --- | --- |
| deveSomarPedidoEAssinaturaNoTotalAReceber | Soma do pedido e da assinatura. | pedido 64,00 + assinatura 202,98 | total 266,98 |
| deveLancarExcecaoQuandoRegistrarCobrancaNula | Cobrança nula. | central.registrar(null) | IllegalArgumentException |
| deveGerarRelatorioComTextoFinalDeTotal | Relatorio com total. | central.gerarRelatorio() | contem "Total a receber" |
| deveContarQuantidadeDeCobrancasRegistradas | Contagem de cobrancas. | 2 registros | 2 |
| deveGerarRelatorioVazioParaCentralSemCobrancas | Relatorio vazio. | central vazia | "Total a receber: R$ 0,00" |
