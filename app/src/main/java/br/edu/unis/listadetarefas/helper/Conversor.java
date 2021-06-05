package br.edu.unis.listadetarefas.helper;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class Conversor {

    @TypeConverter
    public long paraLongo(Calendar valor) {
        return valor.getTimeInMillis();
    }

    @TypeConverter
    public Calendar paraCalendar(long valor) {
        Calendar valorCalendar = Calendar.getInstance();
        valorCalendar.setTimeInMillis(valor);
        return valorCalendar;
    }
}
