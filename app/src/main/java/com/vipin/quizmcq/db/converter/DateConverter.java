package com.vipin.quizmcq.db.converter;


import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    @TypeConverter
    public Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public Long toTimeStamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
