package utn.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import utn.models.algoritmos.IAlgoritmoConsenso;
import utn.models.algoritmos.impl.AlgoritmoMayoriaAbsoluta;
import utn.models.algoritmos.impl.AlgoritmoMayoriaSimple;
import utn.models.algoritmos.impl.AlgoritmoMultiplesMenciones;

@Converter(autoApply = true)
public class AlgoritmoConsensoConverter implements AttributeConverter<IAlgoritmoConsenso, String> {

    @Override
    public String convertToDatabaseColumn(IAlgoritmoConsenso attribute) {
        if (attribute == null) return null;
        return attribute.getClass().getSimpleName(); // Ej: "MayoríaSimple"
    }

    @Override
    public IAlgoritmoConsenso convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return switch (dbData) {
            case "AlgoritmoMayoriaSimple" -> new AlgoritmoMayoriaSimple();
            case "AlgoritmoMayoríaAbsoluta" -> new AlgoritmoMayoriaAbsoluta();
            case "AlgoritmoMultiplesMenciones" -> new AlgoritmoMultiplesMenciones();
            default -> throw new IllegalArgumentException("Algoritmo desconocido: " + dbData);
        };
    }
}