package it.unicam.cs.mpgc.rpg130718.utils;

import com.google.gson.*;
import it.unicam.cs.mpgc.rpg130718.model.oggetti.Oggetto;

import java.lang.reflect.Type;

public class OggettoAdapter implements JsonSerializer<Oggetto>, JsonDeserializer<Oggetto> {

    @Override
    public JsonElement serialize(Oggetto src, Type typeOfSrc, JsonSerializationContext context) {
        // Traduce normalmente l'oggetto base in JSON
        JsonObject jsonObject = context.serialize(src).getAsJsonObject();

        // Aggiunge l'etichetta con il nome esatto della classe che implementa Oggetto
        jsonObject.addProperty("tipoOggetto", src.getClass().getSimpleName());

        return jsonObject;
    }

    @Override
    public Oggetto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        // Estrae il nome della classe che avevamo salvato nel JSON
        String tipoOggetto = jsonObject.get("tipoOggetto").getAsString();
        // Costruisce il percorso esatto in cui si trova la classe
        String percorsoCompleto = "it.unicam.cs.mpgc.rpg130718.model.oggetti." + tipoOggetto;

        try {
            // Cerca dinamicamente la classe giusta e la passala a Gson
            Class<?> classeCorretta = Class.forName(percorsoCompleto);
            return context.deserialize(jsonObject, classeCorretta);

        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Impossibile caricare l'inventario: oggetto " + tipoOggetto + " non trovato nel sistema.");
        }
    }
}