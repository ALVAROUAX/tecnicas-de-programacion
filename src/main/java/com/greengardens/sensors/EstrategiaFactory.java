package com.greengardens.sensors;

/**
 * Factory para crear estrategias de medición
 * Patrón Factory Method
 */
public class EstrategiaFactory {
    
    /**
     * Crea la estrategia apropiada según el tipo de sensor
     * @param tipo Tipo de sensor
     * @return Estrategia de medición correspondiente
     */
    public static EstrategiaMedicion crearEstrategia(TipoSensor tipo) {
        return switch (tipo) {
            case HUMEDAD_SUELO -> new EstrategiaHumedad();
            case TEMPERATURA -> new EstrategiaTemperatura();
            case LUZ_SOLAR -> new EstrategiaLuzSolar();
            case PH_SUELO -> new EstrategiaPH();
            case VIENTO -> new EstrategiaViento();
        };
    }
}
