package adventcode

import java.io.File

/*
    --- Day 2: Password Philosophy ---

    Každý řádek uvádí zásady hesla a poté heslo.
    Zásady hesla označují nejnižší a nejvyšší počet opakování daného písmena, aby bylo heslo platné.
    Například "1-3 a:" znamená, že heslo musí obsahovat "a" alespoň 1x a 3x.
*/
fun passwordPhilosophy(): Int {
    val passwordLines = File("passwords1.txt").readLines()
    var vpohode = 0
    passwordLines.forEach{ p ->

        // vytvorim si pole retezcu, z puvodniho je rozdelim podle mezer
        val passSplit: List<String> = p.split(" ")

        // najdu si min a max, opet rozdelim podle znamenka minus
        val numMinMax = passSplit[0].split("-")

        // odstranim dvojtecku ze znaku pro pravidlo
        val ruleChar = passSplit[1].replace(":", "")

        /*
        Count occurrences of a given character in a string
            fun countOccurrences(s: String, ch: Char): Int {
                return s.filter { it == ch }.count()
            }
        */
        // lze najit hodnotu v poli i pomoci array.contains("value")
        // fce ruleChar.single() prevede jednoznakovy retezec na char
        //
        // najdu pocet vyskytu znaku v retezci v tomto pripade v <it> coz je index z retezce passSplit[2]
        val ruleCharCount = passSplit[2].filter { it == ruleChar.single() }.count()
        if (ruleCharCount in numMinMax[0].toInt()..numMinMax[1].toInt() ) vpohode++
    }
    return vpohode
}

/*
 Pravidlo popisuje dvě pozice v hesle, kde 1 znamená první znak, 2 znamená druhý znak atd.
 (Buďte opatrní; Zásady společnosti Toboggan nemají koncept „indexu nula“!)
 Přesně jedna z těchto pozic musí obsahovat dané písmeno.

    Priklad:
    1-3 a: abcde
    1-3 b: cdefg
    2-9 c: ccccccccc

    1-3 a: abcdeje platný : pozice 1 obsahuje "a" a pozice 3 ne.
    1-3 b: cdefgje neplatná : pozice 1 ani pozice 3 neobsahuje "b".
    2-9 c: cccccccccje neplatný : pozice 2 i pozice 9 obsahují "c".
*/
fun passwordPhilosophy2(): Int {
    val passwordLines = File("passwords1.txt").readLines()
    var vpohode = 0
    passwordLines.forEach { p ->
        val passSplit: List<String> = p.split(" ")
        val numFirstSecond = passSplit[0].split("-")
        val ruleChar = passSplit[1].replace(":", "")
        // podminka
        // if (a==1 && b==0 || a==0 && b==1) "true" else "false"
        // if (znak==znakVyskytFirst && znak!=znakVyskytSecond || znak!=znakVyskytFirst && znak==znakVyskytSecond) "true"
        if (ruleChar.single() == passSplit[2][numFirstSecond[0].toInt()-1] && ruleChar.single() != passSplit[2][numFirstSecond[1].toInt()-1]
            || ruleChar.single() != passSplit[2][numFirstSecond[0].toInt()-1] && ruleChar.single() == passSplit[2][numFirstSecond[1].toInt()-1])
            vpohode++
        //println(passSplit.toString() + "#" + passSplit[2][numFirstSecond[0].toInt()-1] + "#" + passSplit[2][numFirstSecond[1].toInt()-1])
    }
    return vpohode
}