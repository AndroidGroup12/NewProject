package com.example.newproject

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

//Global Vars
var shopping_vocabulary = mutableListOf<Word>()
var places_vocabulary = mutableListOf<Word>()
var food_vocabulary = mutableListOf<Word>()
var tourist_vocabulary = mutableListOf<Word>()
var common_vocabulary = mutableListOf<Word>()
var wordsToLearn = mutableListOf<Word>()
var wordsMastered = mutableListOf<Word>()
var addAllShopping = false
var addAllPlaces = false
var addAllFood = false
var addAllTourist = false
var addAllCommon = false


class Dictionary {
    val spanishDictionary = mutableMapOf("la entrada" to "entrance","la ganga" to "bargain",
        "el letrero" to "sign","la liquidación" to "sale","el mercado" to "market","la salida" to "exit",
        "claro, -a" to "light","de sólo un color" to "solid-colored","oscuro, -a" to "dark","pastel" to "pastel",
        "vivo, -a" to "bright", "¿De qué está hecho, -a?" to "What is it made of", "Está hecho, -a de..." to "It is made of...","algodón" to "cotton",
        "cuero" to "leather","lana" to "wool", "seda" to "silk","tela sintética" to "synthetic fabric",
        "alto, -a" to "high","bajo, -a" to "low","la caja" to "cash register","el cajero, la cajera" to "cashier",
        "el cheque (personal)" to "(personal) check","el cheque de viajero" to "traveler's check", "el cupón de regalo" to "gift certificate","en efectivo" to "cash",
        "gastar" to "to spend","el precio" to "price","tan + adjective" to "so","la tarjeta de crédito" to "credit card",
        "apretado, -a" to "tight","escoger" to "to choose", "estar de moda" to "to be in fashion","el estilo" to "style",
        "flojo, -a" to "loose","la marca" to "brand", "mediano, -a" to "medium","el número" to "shoe size",
        "probarse (o -> ue)" to "to try on","la talla" to "size", "anunciar" to "to announce","encontrar ( o-> ue)" to "to find",
        "en realidad" to "really","me/te importa(n)" to "it matters to they matter/me/to you","inmediatamente" to "immediately","me parace que" to "it seems to me that",
        "¿Qué te parace?" to "What do you think?/ What does it seem to you?","recientemente" to "recently","ir de compras" to "to go shopping", "ir de compras" to "to go shopping","ver unapelícula" to "to see a movie",
        "la lección de piano" to "piano lesson(class)","la biblíoteca" to "library","el café" to "café","el campo" to "countryside",
        "la casa" to "home, house","en casa" to "at home", "el centro commercial" to "mall", "el cine" to "movie theater","el gimnasio" to "gym",
        "la mezquita" to "mosque","la iglesia" to "church", "las montañas" to "mountains","el parque" to "park","la piscina" to "swimming pool",
        "el restaurante" to "restaurant","la playa" to "beach","la sinagoga" to "synagogue","el templo" to "temple, Protestant church","el trabajo" to "word, job",
        "a" to "to (prep.)","a la, al (a + el)" to "to the","¿Adónde?" to "(To) Where?","a casa" to "(to) home","¿Con quien?" to "With whom",
        "con mis/ con amigos" to "with my/ your friends","solo, -a" to "alone","¿Cuando?" to "When?","después" to "afterwards", "después" to "afterwards","después (de)" to "after",
        "los fines de semana" to "on weekends", "los lines, los martes..." to "on Mondays, on Tuesdays...","tiemp libre" to "free time","¿De dónde eres?" to "Where are you from?",
        "de" to "from, of","generalmente" to "generally", "la agencia de viajes" to "travel agency","el / la agente de viajes" to "travel agent",
        "abordar" to "to board","extranjero, -a" to "foreign","hacer una viaje" to "to take a trip", "hacer la maleta" to "to pack the suitcase",
        "el passaporte" to "passport","planear" to "to plan", "la reservación" to "reservation", "la tarjeta de embarque" to "boarding pass","el/la turista" to "tourist",
        "la aduana" to "customs", "el aduanero, la aduanera" to "customs officer", "el aeropuerto" to "airport","el anuncio" to "announcement","el/la auxiliar de vuelp" to "flight attendant",
        "con destino a" to "going to","de ida y vuelta" to "round-trip","directo, -a" to "direct","durar" to "to last","el empleado, la empleada" to "employee",
        "facturar" to "to check (luggage)","hacer escala" to "to stop over","la inspección" to "security checkpoint","la línea aérea" to "airline","la llegada" to "arrival",
        "el pasajero" to "passenger","el passillo" to "aisle","el/la piloto" to "pilot","la puerta de embarque" to "departure gate", "registrar" to "to inspect, to search (luggage)",
        "abierto, -a" to "open","bienvenido, -a" to "welcome", "cerrado, -a" to "closed","listo, -a" to "ready","el cajero automático" to "ATM",
        "la casa de cambio" to "currency exchange","el castillo" to "castle", "la catedral" to "catherdral","histórico, -a" to "historical","el palacio" to "palace",
        "el quiosco" to "newsstand","el ascensor" to "elevator", "conseguir (e -> i)" to "to obtain","la habitación" to "room","la habitación doble" to "double room",
        "la habitación individual" to "single room", "la llave" to "key", "la recepción" to "reception desk","atento, -a" to "attentive","cortés" to "polite", "hacer ruido" to "to make noise",
        "observar" to "to observe","ofender" to "to offend","la propina" to "tip", "puntual" to "punctual","el/la guía" to "guide","la guīa" to "guidebook",
        "hacer una gira" to "to take a tour","el itinerario" to "itinerary","regatear" to "to bargain", "la agencia de viajes" to "travel agency","el / la agente de viajes" to "travel agent",
        "abordar" to "to board","extranjero, -a" to "foreign","hacer una viaje" to "to take a trip", "hacer la maleta" to "to pack the suitcase",
        "el passaporte" to "passport","planear" to "to plan", "la reservación" to "reservation", "la tarjeta de embarque" to "boarding pass","el/la turista" to "tourist",
        "la aduana" to "customs", "el aduanero, la aduanera" to "customs officer", "el aeropuerto" to "airport","el anuncio" to "announcement","el/la auxiliar de vuelp" to "flight attendant",
        "con destino a" to "going to","de ida y vuelta" to "round-trip","directo, -a" to "direct","durar" to "to last","el empleado, la empleada" to "employee",
        "facturar" to "to check (luggage)","hacer escala" to "to stop over","la inspección" to "security checkpoint","la línea aérea" to "airline","la llegada" to "arrival",
        "el pasajero" to "passenger","el passillo" to "aisle","el/la piloto" to "pilot","la puerta de embarque" to "departure gate", "registrar" to "to inspect, to search (luggage)",
        "abierto, -a" to "open","bienvenido, -a" to "welcome", "cerrado, -a" to "closed","listo, -a" to "ready","el cajero automático" to "ATM",
        "la casa de cambio" to "currency exchange","el castillo" to "castle", "la catedral" to "catherdral","histórico, -a" to "historical","el palacio" to "palace",
        "el quiosco" to "newsstand","el ascensor" to "elevator", "conseguir (e -> i)" to "to obtain","la habitación" to "room","la habitación doble" to "double room",
        "la habitación individual" to "single room", "la llave" to "key", "la recepción" to "reception desk","atento, -a" to "attentive","cortés" to "polite", "hacer ruido" to "to make noise",
        "observar" to "to observe","ofender" to "to offend","la propina" to "tip", "puntual" to "punctual","el/la guía" to "guide","la guīa" to "guidebook",
        "hacer una gira" to "to take a tour","el itinerario" to "itinerary","regatear" to "to bargain", "desear" to "to want","pedir" to "to order",
        "yo pido" to "I order (I'm ordering)","el postre" to "dessert","el azúcar" to "sugar", "la cuchara" to "spoon",
        "el cuchillo" to "knife","la pimienta" to "pepper", "el plato" to "plate", "la sal" to "salt","la servilleta" to "napkin",
        "la taza" to "cup","el tenedor" to "fork", "el vaso" to "glass","el camarero(a); el mesero(a)" to "waiter/waitress (server)","la cuenta" to "bill",
        "el menú" to "menu","delicioso" to "delicious","me falta" to "I need" ,"quisiera" to "I would like","traer" to "to bring",
        "le traigo" to "I will bring you","¿Me trae?" to "will you bring me?","yo traigo" to "I bring","ahora" to "now","¿Algo más?" to "anything else?",
        "de nada" to "you're welcome","otro" to "other, another","venir" to "to come","faltar" to "to be lacking/ missing", "servir" to "to serve",
        "de plato principal" to "as a main dish", "de postre" to "for dessert", "¡qué + adjective!" to "how . . .!", "voy" to "I go", "sabes" to "you know", "estaba" to "I was", "puede" to "can",
        "eres" to "are", "ese" to "that", "usted" to "you", "en" to "in", "en" to "in", "en" to "in", "en" to "in", "en" to "in",
        "en" to "in", "en" to "in", "en" to "in", "en" to "in", "en" to "in", "entonces" to "then", "hola" to "hello", "solo" to "only",
        "verdad" to "true", "casa" to "house", "tan" to "so", "quién" to "who", "sus" to "their", "tiempo" to "weather", "dos" to "two",
        "esa" to "this", "nunca" to "never", "dónde" to "where", "de" to "of","que" to "that", "no" to "No","a" to "a","la" to "the", "el" to "the",
        "y" to "and","es" to "it is", "en" to "in", "lo" to "it","un" to "a", "por" to "by","que" to "that", "me" to "I","una" to "a","los" to "the",
        "se" to "I know","te" to "tea", "con" to "with","para" to "for","está" to "this","mi" to "me","pero" to "but",
        "sí" to "Yes","bien" to "right","eso" to "it's","su" to "its","las" to "the","yo" to "me","del" to "of","como" to "What","aquí" to "here",
        "tu" to "your","to the" to "más","plus" to "le","you" to "this","todo" to "all", "ya" to "Already", "estoy" to "am","ahora" to "now",
        "muy" to "very","ha" to "he has", "esta" to "is", "así" to "So", "vamos" to "we go", "algo" to "something", "hay" to "there are",
        "bueno" to "okay", "tengo" to "I've got", "cuando" to "when", "estás" to "are", "sé" to "HE", "tú" to "your", "nos" to "us", "nada" to "any", "cómo" to "What",
        "este" to "East", "o" to "either", "he" to "He", "ser" to "be", "tiene" to "have", "puedo" to "Can I", "ella" to "she", "quiero" to "I want", "hacer" to "do",
        "fue" to "it was", "gracias" to "Thank you", "vez" to "time", "era" to "it was", "soy" to "am", "sólo" to "single", "todos" to "everybody", "porque" to "because",
        "son" to "are","tienes" to "you have", "creo" to "I think", "va" to "goes", "dónde" to "where", "mucho" to "a lot", "siento" to "I feel", "mejor" to "better", "hace" to "does")

    val shopping_vocab = mutableMapOf<String, String>("la entrada" to "entrance","la ganga" to "bargain",
        "el letrero" to "sign","la liquidación" to "sale","el mercado" to "market","la salida" to "exit",
        "claro, -a" to "light","de sólo un color" to "solid-colored","oscuro, -a" to "dark","pastel" to "pastel",
        "vivo, -a" to "bright", "¿De qué está hecho, -a?" to "What is it made of", "Está hecho, -a de..." to "It is made of...","algodón" to "cotton",
        "cuero" to "leather","lana" to "wool", "seda" to "silk","tela sintética" to "synthetic fabric",
        "alto, -a" to "high","bajo, -a" to "low","la caja" to "cash register","el cajero, la cajera" to "cashier",
        "el cheque (personal)" to "(personal) check","el cheque de viajero" to "traveler's check", "el cupón de regalo" to "gift certificate","en efectivo" to "cash",
        "gastar" to "to spend","el precio" to "price","tan + adjective" to "so","la tarjeta de crédito" to "credit card",
        "apretado, -a" to "tight","escoger" to "to choose", "estar de moda" to "to be in fashion","el estilo" to "style",
        "flojo, -a" to "loose","la marca" to "brand", "mediano, -a" to "medium","el número" to "shoe size",
        "probarse (o -> ue)" to "to try on","la talla" to "size", "anunciar" to "to announce","encontrar ( o-> ue)" to "to find",
        "en realidad" to "really","me/te importa(n)" to "it matters to they matter/me/to you","inmediatamente" to "immediately","me parace que" to "it seems to me that",
        "¿Qué te parace?" to "What do you think?/ What does it seem to you?","recientemente" to "recently","ir de compras" to "to go shopping")

    fun get_shopping_list(): List<Word> {
        for ((key,value) in shopping_vocab) {
            val new_word = Word(key, value)
            if (!(shopping_vocabulary.contains(new_word))) {
                shopping_vocabulary.add(new_word)
            }
        }
        return shopping_vocabulary
    }

    val places_vocab = mutableMapOf<String, String>("ir de compras" to "to go shopping","ver unapelícula" to "to see a movie",
        "la lección de piano" to "piano lesson(class)","la biblíoteca" to "library","el café" to "café","el campo" to "countryside",
        "la casa" to "home, house","en casa" to "at home", "el centro commercial" to "mall", "el cine" to "movie theater","el gimnasio" to "gym",
        "la mezquita" to "mosque","la iglesia" to "church", "las montañas" to "mountains","el parque" to "park","la piscina" to "swimming pool",
        "el restaurante" to "restaurant","la playa" to "beach","la sinagoga" to "synagogue","el templo" to "temple, Protestant church","el trabajo" to "word, job",
        "a" to "to (prep.)","a la, al (a + el)" to "to the","¿Adónde?" to "(To) Where?","a casa" to "(to) home","¿Con quien?" to "With whom",
        "con mis/ con amigos" to "with my/ your friends","solo, -a" to "alone","¿Cuando?" to "When?","después" to "afterwards", "después" to "afterwards","después (de)" to "after",
        "los fines de semana" to "on weekends", "los lines, los martes..." to "on Mondays, on Tuesdays...","tiemp libre" to "free time","¿De dónde eres?" to "Where are you from?",
        "de" to "from, of","generalmente" to "generally")

    fun get_places_list(): List<Word> {
        for ((key,value) in places_vocab) {
            val new_word = Word(key, value)
            if (!(places_vocabulary.contains(new_word))) {
                places_vocabulary.add(new_word)
            }
        }
        return places_vocabulary
    }


    val tourist_vocab = mutableMapOf<String, String>("la agencia de viajes" to "travel agency","el / la agente de viajes" to "travel agent",
        "abordar" to "to board","extranjero, -a" to "foreign","hacer una viaje" to "to take a trip", "hacer la maleta" to "to pack the suitcase",
        "el passaporte" to "passport","planear" to "to plan", "la reservación" to "reservation", "la tarjeta de embarque" to "boarding pass","el/la turista" to "tourist",
        "la aduana" to "customs", "el aduanero, la aduanera" to "customs officer", "el aeropuerto" to "airport","el anuncio" to "announcement","el/la auxiliar de vuelp" to "flight attendant",
        "con destino a" to "going to","de ida y vuelta" to "round-trip","directo, -a" to "direct","durar" to "to last","el empleado, la empleada" to "employee",
        "facturar" to "to check (luggage)","hacer escala" to "to stop over","la inspección" to "security checkpoint","la línea aérea" to "airline","la llegada" to "arrival",
        "el pasajero" to "passenger","el passillo" to "aisle","el/la piloto" to "pilot","la puerta de embarque" to "departure gate", "registrar" to "to inspect, to search (luggage)",
        "abierto, -a" to "open","bienvenido, -a" to "welcome", "cerrado, -a" to "closed","listo, -a" to "ready","el cajero automático" to "ATM",
        "la casa de cambio" to "currency exchange","el castillo" to "castle", "la catedral" to "catherdral","histórico, -a" to "historical","el palacio" to "palace",
        "el quiosco" to "newsstand","el ascensor" to "elevator", "conseguir (e -> i)" to "to obtain","la habitación" to "room","la habitación doble" to "double room",
        "la habitación individual" to "single room", "la llave" to "key", "la recepción" to "reception desk","atento, -a" to "attentive","cortés" to "polite", "hacer ruido" to "to make noise",
        "observar" to "to observe","ofender" to "to offend","la propina" to "tip", "puntual" to "punctual","el/la guía" to "guide","la guīa" to "guidebook",
        "hacer una gira" to "to take a tour","el itinerario" to "itinerary","regatear" to "to bargain")

    fun get_tourist_list(): List<Word> {
        for ((key, value) in tourist_vocab) {
            val new_word = Word(key, value)
            if (!(tourist_vocabulary.contains(new_word))) {
                tourist_vocabulary.add(new_word)
            }
        }
        return tourist_vocabulary
    }

    val food_vocab = mutableMapOf<String, String>("desear" to "to want","pedir" to "to order",
        "yo pido" to "I order (I'm ordering)","el postre" to "dessert","el azúcar" to "sugar", "la cuchara" to "spoon",
        "el cuchillo" to "knife","la pimienta" to "pepper", "el plato" to "plate", "la sal" to "salt","la servilleta" to "napkin",
        "la taza" to "cup","el tenedor" to "fork", "el vaso" to "glass","el camarero(a); el mesero(a)" to "waiter/waitress (server)","la cuenta" to "bill",
        "el menú" to "menu","delicioso" to "delicious","me falta" to "I need" ,"quisiera" to "I would like","traer" to "to bring",
        "le traigo" to "I will bring you","¿Me trae?" to "will you bring me?","yo traigo" to "I bring","ahora" to "now","¿Algo más?" to "anything else?",
        "de nada" to "you're welcome","otro" to "other, another","venir" to "to come","faltar" to "to be lacking/ missing", "servir" to "to serve",
        "de plato principal" to "as a main dish", "de postre" to "for dessert", "¡qué + adjective!" to "how . . .!" )

    fun get_food_list(): List<Word> {
        for ((key, value) in food_vocab) {
            val new_word = Word(key, value)
            if (!(food_vocabulary.contains(new_word))) {
                food_vocabulary.add(new_word)
            }
        }
        return food_vocabulary
    }

    val common_vocab = mutableMapOf<String, String>( "voy" to "I go", "sabes" to "you know", "estaba" to "I was", "puede" to "can",
        "eres" to "are", "ese" to "that", "usted" to "you", "en" to "in", "en" to "in", "en" to "in", "en" to "in", "en" to "in",
        "en" to "in", "en" to "in", "en" to "in", "en" to "in", "en" to "in", "entonces" to "then", "hola" to "hello", "solo" to "only",
        "verdad" to "true", "casa" to "house", "tan" to "so", "quién" to "who", "sus" to "their", "tiempo" to "weather", "dos" to "two",
        "esa" to "this", "nunca" to "never", "dónde" to "where", "de" to "of","que" to "that", "no" to "No","a" to "a","la" to "the", "el" to "the",
        "y" to "and","es" to "it is", "en" to "in", "lo" to "it","un" to "a", "por" to "by","que" to "that", "me" to "I","una" to "a","los" to "the",
        "se" to "I know","te" to "tea", "con" to "with","para" to "for","está" to "this","mi" to "me","pero" to "but",
        "sí" to "Yes","bien" to "right","eso" to "it's","su" to "its","las" to "the","yo" to "me","del" to "of","como" to "What","aquí" to "here",
        "tu" to "your","to the" to "más","plus" to "le","you" to "this","todo" to "all", "ya" to "Already", "estoy" to "am","ahora" to "now",
        "muy" to "very","ha" to "he has", "esta" to "is", "así" to "So", "vamos" to "we go", "algo" to "something", "hay" to "there are",
        "bueno" to "okay", "tengo" to "I've got", "cuando" to "when", "estás" to "are", "sé" to "HE", "tú" to "your", "nos" to "us", "nada" to "any", "cómo" to "What",
        "este" to "East", "o" to "either", "he" to "He", "ser" to "be", "tiene" to "have", "puedo" to "Can I", "ella" to "she", "quiero" to "I want", "hacer" to "do",
        "fue" to "it was", "gracias" to "Thank you", "vez" to "time", "era" to "it was", "soy" to "am", "sólo" to "single", "todos" to "everybody", "porque" to "because",
        "son" to "are","tienes" to "you have", "creo" to "I think", "va" to "goes", "dónde" to "where", "mucho" to "a lot", "siento" to "I feel", "mejor" to "better", "hace" to "does")

    fun get_common_list(): List<Word> {
        for ((key, value) in common_vocab) {
            val new_word = Word(key, value)
            if (!(common_vocabulary.contains(new_word))) {
                common_vocabulary.add(new_word)
            }
        }
        return common_vocabulary
    }

    fun get_boolean(categoryName: String) {
        when (categoryName ) {
            "shopping" -> categoryBoolean === addAllShopping
            "places" -> categoryBoolean === addAllPlaces
            "tourist" -> categoryBoolean === addAllTourist
            "food" -> categoryBoolean === addAllFood
            "common" -> categoryBoolean === addAllCommon
        }
    }

    val germanDictionary = mutableMapOf("voy" to "I go", "sabes" to "you know", "estaba" to "I was", "puede" to "can",
        "eres" to "are", "ese" to "that", "usted" to "you")

    private fun combineGermanLists(): MutableMap<String, String> {
        val map = mutableMapOf<String, String>()
        for (i in germanCommon.indices) {
            map[germanCommon[i]] = germanCommonEng[i]
        }
        for (i in germanTravel.indices) {
            map[germanTravel[i]] = germanTravelEng[i]
        }
        for (i in germanFood.indices) {
            map[germanFood[i]] = germanFoodEng[i]
        }
        for (i in germanPlaces.indices) {
            map[germanPlaces[i]] = germanPlacesEng[i]
        }
        for (i in germanShopping.indices) {
            map[germanShopping[i]] = germanShoppingEng[i]
        }

        return map
    }

    val germanCommon = mutableListOf("der / die / das", "und", "sein", "in", "haben", "ich", "du", "sie", "er", "sie", "zu", "werden", "für", "gehen", "brauchen", "sehen", "schreiben", "laufen", "mein")
    val germanCommonEng = mutableListOf("the", "und", "to be", "in", "to have", "I", "you", "they", "he", "she", "to / at", "to become", "for", "to go", "to eat", "to need", "to see", "to write", "to run", "mine")

    val germanTravel = mutableListOf("der Reisepass", "das Visum", "das Gepäck", "das Reiseziel", "der Tourist", "die Reiseroute", "der Flug", "das Hotel", "der Rucksack", "die Karte", "der Reiseführer", "die Kultur", "die Stadt", "der Transport", "das Souvenir", "ausländisch", "das Flugzeug", "zu reisen", "das Reisebüro", "der Reisevermittler")
    val germanTravelEng = mutableListOf("Passport", "Visa", "Luggage", "Destination", "Tourist", "Itinerary", "Flight", "Hotel", "Backpack", "Map", "Guidebook", "Culture", "City", "Transportation", "Souvenir", "foreign", "Airplane",  "to travel", "Travel Agency", "Travel Agent")

    val germanFood = mutableListOf("das Eis", "das Erdbeereis", "das Vanilleeis", "das Schokoeis", "das Wasser", "der Kaffee", "der Kakao", "das Soda", "die Cola", "die Milch", "der Tee", "der Saft", "der Salat", "der Käse", "der Käsebrot", "die Kuchen", "die Wurst", "die Pizza", "die Kartoffel", "di Pommes Frites")
    val germanFoodEng = mutableListOf("Ice Cream", "Strawberry Ice Cream", "Vanilla Ice Cream", "Chocolate Ice Cream", "Water", "Coffee", "Cocoa", "Soda", "Cola", "Milk", "Tea", "Juice", "Salad", "Cheese", "Cheese Sandwich", "Cake", "Sausage", "Pizza", "Potato", "French Fries")

    val germanPlaces = mutableListOf("der Park", "die Bibliothek", "die Schule", "das Museum", "das Rathaus", "das Krankenhaus", "die Polizeistation", "die Feuerwache", "das Einkaufszentrum", "das Kino", "das Restaurant", "das Fitnessstudio", "das Schwimmbad", "das Gemeindezentrum", "die Kirche", "die Post", "die Bank", "die Tankstelle", "der Supermarkt", "der Bahnhof")
    val germanPlacesEng = mutableListOf("Park", "Library", "School", "Museum", "City Hall", "Hospital", "Police Station", "Fire Station", "Shopping Mall", "Movie Theater", "Restaurant", "Gym", "Swimming Pool", "Community Center", "Church", "Post Office", "Bank", "Gas Station", "Supermarket", "Train Station")

    val germanShopping = mutableListOf("Kleidung", "Schuhe", "Lebensmittel", "Accessoires", "Lebensmittel", "Elektronik", "Möbel", "Schönheitsprodukte", "Spielzeug", "Bücher", "Musik", "Schmuck", "Handtaschen", "Sportartikel", "Tierbedarf", "Werkzeuge", "Einkaufszentrum", "Geschäft", "Geld", "kaufen")
    val germanShoppingEng = mutableListOf("Clothing", "Shoes", "Food", "Accessories", "Groceries", "Electronics", "Furniture", "Beauty products", "Toys", "Books", "Music", "Jewelry", "Handbags", "Sporting goods", "Pet supplies", "Tools", "Mall", "Store", "Money", "to buy")

    /*fun writeData(context: Context) {
        val sharedPreferences = context.getSharedPreferences("LanguagePref", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("language", "Spanish")

        if (language == "Spanish") {
            val filename1: String = "$language.txt"
            val filename2: String = "${language}Eng.txt"

            val file1 = File(context.filesDir, filename1)
            val file2 = File(context.filesDir, filename2)
        }



//        try {
//            if (!file1.exists()) {
//                file1.createNewFile()
//                Log.i("FILES", "Opened $filename1")
//            } else {
//                val fileReader = FileReader(file)
//                val bufferedReader = BufferedReader(fileReader)
//                var line: String?
//                while (bufferedReader.readLine().also { line = it } != null) {
//                    indexes.add(line!!.toInt())
//                }
//                // Close the file
//                fileReader.close()
//                bufferedReader.close()
//                Log.i("FILES", "Read $filename: $indexes")
//            }
//
//        } catch (e: IOException) {
//            File(filename).createNewFile()
//            Log.i("FILES", "Failed to open $filename")
//        }
//
//        if (!file2.exists()) {
//            file2.createNewFile()
//        }
    }*/

    fun writeData(context: Context) {
        val sharedPreferences = context.getSharedPreferences("LanguagePref", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("language", "Spanish")

        // if language is Spanish, filenameLearning is SpanishLearning.txt
        val filenameLearning: String = "${language}Learning.txt"
        val filenameMastered: String = "${language}Mastered.txt"

        val fileLearning = File(context.filesDir, filenameLearning)
        try {
            if (!fileLearning.exists()) {
                fileLearning.createNewFile()
            }
            val fileWriter = FileWriter(fileLearning)
            for (word in wordsToLearn) {
                Log.i("FILES", "Writing ${word.languageWord} to ${language}Learning.txt")
                fileWriter.write("${word.languageWord}\n")
            }
            fileWriter.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val fileMastered = File(context.filesDir, filenameMastered)
        try {
            if (!fileMastered.exists()) {
                fileMastered.createNewFile()
            }
            val fileWriter = FileWriter(fileMastered)
            for (word in wordsMastered) {
                Log.i("FILES", "Writing ${word.languageWord} to to ${language}Mastered.txt")
                fileWriter.write("${word.languageWord}\n")
            }
            fileWriter.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    //updates wordsToLearn and wordsMastered
    fun readData(context: Context) {
        val sharedPreferences = context.getSharedPreferences("LanguagePref", Context.MODE_PRIVATE)
        val language = sharedPreferences.getString("language", "Spanish")

        // if language is Spanish, filenameLearning is SpanishLearning.txt
        val filenameLearning: String = "${language}Learning.txt"
        val filenameMastered: String = "${language}Mastered.txt"

        try {
            val file = File(context.filesDir, filenameLearning)
            Log.i("FILES", "Opened $filenameLearning")
            if (!file.exists()) {
                file.createNewFile()
                Log.i("FILES", "Opened $filenameLearning")
            } else {
                val fileReader = FileReader(file)
                val bufferedReader = BufferedReader(fileReader)
                var line: String?
                while (bufferedReader.readLine().also { line = it } != null) {
                    if (language == "Spanish") {
                        Log.i("FILES", "$line")
                        val word = Word(line!!, spanishDictionary[line!!]!!)
                        wordsToLearn.add(word)
                    } else if (language == "German") {
                        val word = Word(line!!, germanDictionary[line!!]!!)
                        wordsToLearn.add(word)
                    }
                }
                // Close the file
                fileReader.close()
                bufferedReader.close()
                Log.i("FILES", "Read $filenameLearning: $wordsToLearn")
            }

        } catch (e: IOException) {
            File(context.filesDir, filenameLearning).createNewFile()
            Log.i("FILES", "Failed to open $filenameLearning")
        }

        try {
            val file = File(context.filesDir, filenameMastered)
            Log.i("FILES", "Opened $filenameMastered")
            if (!file.exists()) {
                file.createNewFile()
                Log.i("FILES", "Opened $filenameMastered")
            } else {
                val fileReader = FileReader(file)
                val bufferedReader = BufferedReader(fileReader)
                var line: String?
                while (bufferedReader.readLine().also { line = it } != null) {
                    if (language == "Spanish") {
                        val word = Word(line!!, spanishDictionary[line!!]!!)
                        wordsMastered.add(word)
                    } else if (language == "German") {
                        val word = Word(line!!, germanDictionary[line!!]!!)
                        wordsMastered.add(word)
                    }
                }
                // Close the file
                fileReader.close()
                bufferedReader.close()
                Log.i("FILES", "Read $filenameMastered: $wordsMastered")
            }

        } catch (e: IOException) {
            File(context.filesDir, filenameMastered).createNewFile()
            Log.i("FILES", "Failed to open $filenameMastered")
        }
    }
}