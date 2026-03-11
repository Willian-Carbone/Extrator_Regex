class Extrator {

     static HashSet<String> capturar_çãõ (String trecho){

         HashSet<String> Saida=[]

         ArrayList<String> frases= separar_frases(trecho)

         frases.forEach {frase->

             ArrayList<String> palavras = frase.split(/\s+/)

             def palavrasvalidas=palavras.findAll{p -> p.matches(/(?i)[a-zÀ-ü]*[çãõ][a-zÀ-ü]*/)}
             Saida.addAll(palavrasvalidas)



         }


         return Saida



     }


    static ArrayList<String> separar_frases(String trechoMusica){
        ArrayList<String> frases = trechoMusica.split(/\n/)
        return frases
    }



    static Map repeticoes (String trecho){
        ArrayList<String> frases = separar_frases(trecho)


        ArrayList<String> frasesNaoNulas=frases.findAll({f-> !f.isBlank() })

        Map<String,Integer>MapaRepeticoes = [:]


        frasesNaoNulas.forEach {frase->
            Integer rep =frasesNaoNulas.count(frase)
            if (rep  >1 ){
                MapaRepeticoes[frase] = rep
            }


        }

        return MapaRepeticoes
    }

    static ArrayList<String> Frases_4_palavras(String trecho){

        ArrayList <String> Saida = []

        ArrayList<String> frases = separar_frases(trecho)

        frases.forEach {frase->
            String[] palavras = frase.split(" ")
            if (palavras.size() ==4){
                Saida.push(frase)
            }

        }

        return Saida

    }


    static Map<String, String> classificarPalavras(String trecho) {
        Map<String, String> saida = [:]
        def regex_plural = (/(?i)/)

        separar_frases(trecho).each { frase ->
            frase.toLowerCase().split(/\s+/).each { palavra ->
                palavra = palavra.replaceAll(/[.,!?;]/, "")
                if (palavra.isEmpty()) return


                if (palavra =~ /[iu][aeo][iu]/) {
                    saida[palavra] = "Tritongo"
                }

                else if (palavra =~ /[aeo][aeo]|[a-z][íú]/) {
                    saida[palavra] = "Hiato"
                }

                else {
                    saida[palavra] = "Ditongo"
                }
            }
        }
        return saida
    }


    static String retirarPlurais(String trecho) {
        ArrayList<String> frases = separar_frases(trecho)
        ArrayList<String> novasFrases = frases.collect { frase ->

            ArrayList<String> palavras = frase.split(/\s+/)


            def palavrasSingulares = palavras.findAll { p ->
                !p.matches(/\b(?i)(?!deus\b).*(s|es)\b/)
            }


            return palavrasSingulares.join(" ")
        }

        return novasFrases.join("\n")
    }







}







