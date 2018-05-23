package com.lighthouse.respuestajson.utilidad;

import java.text.SimpleDateFormat;

/**
 *
 * @author vacardozo
 */
public class Utilidad {
    private static final int NUM_MAX_FORMATEADORES = 32;
    private static final SimpleDateFormat[] FORMATEADORES = new SimpleDateFormat[NUM_MAX_FORMATEADORES];
    private static int FORMATEADORES_LIBRES = 0;
    
    public static synchronized SimpleDateFormat getFormateador()
    {
        if (FORMATEADORES_LIBRES > 0)
        {
            FORMATEADORES_LIBRES--;
            return FORMATEADORES[FORMATEADORES_LIBRES];
        }
        else
        {
            return new SimpleDateFormat();
        }
    }
    
    /**
     * 
     * @param formateador 
     */
    public static synchronized void setFormateador(SimpleDateFormat formateador)
    {
        if (FORMATEADORES_LIBRES < NUM_MAX_FORMATEADORES)
        {
            FORMATEADORES[FORMATEADORES_LIBRES] = formateador;
            FORMATEADORES_LIBRES++;
        }
    }
}
