/*
 *    GeoTools - The Open Source Java GIS Toolkit
 *    http://geotools.org
 *
 *    (C) 2013-2014, Open Source Geospatial Foundation (OSGeo)
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package org.geotools.coverage.io.netcdf;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.geotools.imageio.netcdf.utilities.NetCDFTimeUtilities;
import org.junit.Test;

/**
 * Test UnidataTimeUtilities
 * 
 * @source $URL$
 */
public final class NetCDFTimeUtilitiesTest {
    @Test
    public void testAddTimeUnit() {
        final Calendar cal = new GregorianCalendar();
        cal.setTimeZone(NetCDFTimeUtilities.UTC_TIMEZONE);
        cal.setTimeInMillis(0);

        // year
        Calendar yearCal = (Calendar) cal.clone();
        NetCDFTimeUtilities.addTimeUnit(yearCal, Calendar.YEAR, 1);
        assertEquals(31536000000l, yearCal.getTimeInMillis());

        // month
        Calendar monthCal = (Calendar) cal.clone();
        NetCDFTimeUtilities.addTimeUnit(monthCal, Calendar.MONTH, 2);
        assertEquals(5097600000l, monthCal.getTimeInMillis());

        // date
        Calendar dateCal = (Calendar) cal.clone();
        NetCDFTimeUtilities.addTimeUnit(dateCal, Calendar.DATE, 5);
        assertEquals(432000000l, dateCal.getTimeInMillis());

        // hour
        Calendar hourCal = (Calendar) cal.clone();
        NetCDFTimeUtilities.addTimeUnit(hourCal, Calendar.HOUR, 907);
        assertEquals(3265200000l, hourCal.getTimeInMillis());

        // minute
        Calendar minuteCal = (Calendar) cal.clone();
        NetCDFTimeUtilities.addTimeUnit(minuteCal, Calendar.MINUTE, 112358);
        assertEquals(6741480000l, minuteCal.getTimeInMillis());

        // second
        Calendar secondCal = (Calendar) cal.clone();
        NetCDFTimeUtilities.addTimeUnit(secondCal, Calendar.SECOND, 3333333333l);
        assertEquals(3333333333000l, secondCal.getTimeInMillis());

        // millisecond
        Calendar msCal = (Calendar) cal.clone();
        NetCDFTimeUtilities.addTimeUnit(msCal, Calendar.MILLISECOND, 99999999999999l);
        assertEquals(99999999999999l, msCal.getTimeInMillis());
    }

    @Test
    public void testJulianCalendarUtilities() {
        // Parsing 10/10/2010
        Calendar calendar = NetCDFTimeUtilities.fromJulian(2455480);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        assertEquals(1286668800000l, calendar.getTimeInMillis());
    }

    @Test
    public void testTimeUnits() {
        assertEquals(Calendar.HOUR, NetCDFTimeUtilities.getTimeUnits("days", 1.5d));
        assertEquals(36, NetCDFTimeUtilities.getTimeSubUnitsValue("days", 1.5d));

        assertEquals(Calendar.SECOND, NetCDFTimeUtilities.getTimeUnits("days", 1.42d));
        assertEquals(122688, NetCDFTimeUtilities.getTimeSubUnitsValue("days", 1.42d));

        assertEquals(Calendar.MINUTE, NetCDFTimeUtilities.getTimeUnits("hours", 1.5d));

        assertEquals(Calendar.MILLISECOND, NetCDFTimeUtilities.getTimeUnits("hours", 1.523d));
        assertEquals(131587199, NetCDFTimeUtilities.getTimeSubUnitsValue("hours", 1.523d));

        assertEquals(Calendar.MILLISECOND, NetCDFTimeUtilities.getTimeUnits("seconds", 1.5d));
    }
}
