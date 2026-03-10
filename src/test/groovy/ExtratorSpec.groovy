
import spock.lang.Specification

class ExtratorSpec extends Specification {

    def "teste çãõ"() {

        given:"Um trecho de um texto "

        String trecho="""

exemplo de palavras
açaí
mapa
saia
maçã


õnion
mão
rua

"""
        when:"A funçao é chamada"

        def saida = Extrator.capturar_çãõ(trecho)

        then:"separe as palavras com ç õ ou õ"

        saida.toList().sort() == ["açaí", "maçã", "mão", "õnion"].sort()
    }


    def "retorno de frases repetidas e quantas vezes se repetem"(){
        given :"frases separadas pro quebra de linha"

        String trecho = """"

trecho
de

teste
para

testar
trecho

com
um

teste
"""





        when: "chamada a funçãod e repetições"

        Map saida = Extrator.repeticoes(trecho)

        then: "conta as palavras e suas repetições "
        saida == [trecho:2,teste:2]

    }


    def "Frase com exatamente 4 palavras"(){

        given: "Frases separadas por quebra de linha"

        String trecho ="""

Exemplo quatro palavras valido

não valido                        

nop

muito menos esse

exemplo valido no meio

esse tambem não é valido

Outro exemplo tambem válido
"""

        when: "Chama da função de coletar chaves de 4 palavras "

        ArrayList<String> Saida = Extrator.Frases_4_palavras(trecho)

        then:"Retorna uma lista com as frases que contem exatas 4 palavras"

        Saida == ["Outro exemplo tambem válido","exemplo valido no meio","Exemplo quatro palavras valido"]




    }


    def "Teste classificação de palavras"(){
        given:"um trecho de palavras seaprados em estrofes"

        String trecho = """


Pai uruguaio
Poeta raiz

Baú 
Pátio pai

"""
        when: "Quando classificar palavras for chamado"

        def Saida = Extrator.classificarPalavras(trecho)

        then: "separa todas as palavras e as classificam"

        Saida == [ "pai": "Ditongo", "uruguaio": "Tritongo", "poeta": "Hiato", "raiz": "Ditongo", "baú": "Hiato",  "pátio": "Ditongo" ]


    }

    def "teste_removerdor_plural"(){
        given:"trecho de texto"

        String trecho="""
exemplo de frases
com palavras
que serão retiradas
"""
        when:
        String saida=Extrator.retirarPlurais(trecho)

        then:
        saida== """
exemplo de
com
que serão"""
    }





}
