package it.unicam.cs.mpgc.rpg130718.utils;

import com.google.gson.*;
import it.unicam.cs.mpgc.rpg130718.model.esploratori.Esploratore;

import java.lang.reflect.Type;

public class EsploratoreAdapter implements JsonSerializer<Esploratore>, JsonDeserializer<Esploratore> {

    @Override
    public JsonElement serialize(Esploratore src, Type typeOfSrc, JsonSerializationContext context) {
        // Traduce normalmente l'oggetto base in JSON
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();

        // Aggiunge l'etichetta con il nome esatto della classe che estende Esploratore
        jsonObject.addProperty("tipoClasse", src.getClass().getSimpleName());

        return jsonObject;
    }

    @Override
    public Esploratore deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Estrae il nome della classe che avevamo salvato nel JSON
        String tipoClasse = jsonObject.get("tipoClasse").getAsString();
        // Costruisce il percorso esatto in cui si trova la classe
        String percorsoCompleto = "it.unicam.cs.mpgc.rpg130718.model.esploratori." + tipoClasse;

        try {
            // Cerca dinamicamente la classe giusta e la passa a Gson
            Class<?> classeCorretta = Class.forName(percorsoCompleto);
            return context.deserialize(jsonObject, classeCorretta);

        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Impossibile caricare il salvataggio: classe " + tipoClasse + " non trovata.");
        }
    }
}