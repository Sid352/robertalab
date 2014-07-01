package de.fhg.iais.roberta.ast.syntax.sensoren;

import java.util.Locale;

import de.fhg.iais.roberta.dbc.DbcException;

/**
 * All sensor ports that a brick can have.
 * 
 * @author kcvejoski
 */
public enum SensorPort {
    _1( "1" ), _2( "2" ), _3( "3" ), _4( "4" );

    private final String[] values;

    private SensorPort(String... values) {
        this.values = values;
    }

    /**
     * get sensor port from {@link SensorPort} from string parameter. It is possible for one sensor port to have multiple string mappings.
     * Throws exception if the operator does not exists.
     * 
     * @param name of the sensor port
     * @return sensor port from the enum {@link SensorPort}
     */
    public static SensorPort get(String s) {
        if ( s == null || s.isEmpty() ) {
            throw new DbcException("Invalid sensor port: " + s);
        }
        String sUpper = s.trim().toUpperCase(Locale.GERMAN);
        for ( SensorPort sp : SensorPort.values() ) {
            if ( sp.toString().equals(sUpper) ) {
                return sp;
            }
            for ( String value : sp.values ) {
                if ( sUpper.equals(value) ) {
                    return sp;
                }
            }
        }
        throw new DbcException("Invalid sensor port: " + s);
    }
}
